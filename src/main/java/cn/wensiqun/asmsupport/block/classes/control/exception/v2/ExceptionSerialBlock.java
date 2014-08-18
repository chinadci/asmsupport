package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.common.AbstractBlock;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AnyException;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.BreakStack;
import cn.wensiqun.asmsupport.operators.Throw;
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.asmdirect.Marker;
import cn.wensiqun.asmsupport.operators.asmdirect.Store;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.utils.common.TryCatchInfo;

public class ExceptionSerialBlock extends AbstractBlock
{

    private ProgramBlock targetParent;
    
    private Try tryBlock;
    
    private List<Catch> catchs;
    
    private ImplicitCatch implicitCatch;
    
    private Finally  finallyBlock;
    
    private Label serialStart;

    private Label serialEnd;
    
    private List<Label> anyCatchRange;
    
    private List<TryCatchInfo> tryCatchInfoes;
    
    public ExceptionSerialBlock(ProgramBlock parent, Try tryBlock)
    {
        this.tryBlock = tryBlock;
        this.targetParent = parent;
        serialStart = new Label("serial start");
        serialEnd = new Label("serial end");
        
        parent.addExe(this);
        
        tryBlock.setParent(parent);
        tryBlock.setSerial(this);
        queue.add(tryBlock);
    }

    @Override
    public void prepare()
    {
        if(CollectionUtils.isEmpty(catchs) && finallyBlock == null)
        {
            throw new ASMSupportException("Syntax error, insert \"Finally\" block or \"Catch\" block to complete Try Block.");
        }
        else if(CollectionUtils.isNotEmpty(catchs) && finallyBlock == null)
        {
            tryBlock.prepare();

            if(!tryBlock.isFinish())
            {
                OperatorFactory.newOperator(GOTO.class, 
                        new Class[]{ProgramBlock.class, Label.class}, 
                        tryBlock, serialEnd);
            }
            
            //new GOTO(tryBlock, serialEnd);
            
            for(Catch c : catchs)
            {
                c.prepare();
                if(!c.isFinish())
                {
                    OperatorFactory.newOperator(GOTO.class, 
                            new Class[]{ProgramBlock.class, Label.class}, 
                            c, serialEnd);
                    //new GOTO(c, serialEnd);
                }
            }
        }
        else if(CollectionUtils.isEmpty(catchs) && finallyBlock != null)
        {
        	addAnyExceptionCatchRange(tryBlock.getStart());
            
            tryBlock.prepare();
            
            if(!tryBlock.isFinish())
            {
                OperatorFactory.newOperator(GOTO.class, 
        				new Class[]{ProgramBlock.class, Label.class}, 
        				tryBlock, finallyBlock.getStart());
                //new GOTO(tryBlock, finallyBlock.getStart());
            }
            
            //insert finally for BreakStack operator in try block.
            insertFinallyBeforeBreakStack(tryBlock);
            
            addAnyExceptionCatchRange(tryBlock.getEnd());
            
            implicitCatch.prepare();
            
            finallyBlock.prepare();
        }
        else
        {
        	addAnyExceptionCatchRange(tryBlock.getStart());
            
            tryBlock.prepare();
            
            if(!tryBlock.isFinish())
            {
                OperatorFactory.newOperator(GOTO.class, 
                    new Class[]{ProgramBlock.class, Label.class}, 
                    tryBlock, finallyBlock.getStart());
                //new GOTO(tryBlock, finallyBlock.getStart());
            }
            
            //insert finally for BreakStack operator in try block.
            insertFinallyBeforeBreakStack(tryBlock);
            
            for(Catch c : catchs)
            {
                c.prepare();
                
                //insert finally for BreakStack operator in try block.
                insertFinallyBeforeBreakStack(c);
                
                if(!c.isFinish())
                {
                    Label finallyStart = new Label("catch's implicit finally start");
                    Label finallyEnd = new Label("catch's implicit finally end");
                    
                    addAnyExceptionCatchRange(finallyStart);
                    {
                        //inject implicit finally block code at end of catch
                        OperatorFactory.newOperator(Marker.class, 
                                new Class[]{ProgramBlock.class, Label.class}, 
                                c, finallyStart);
                        //new Marker(c, finallyStart);
                        finallyBlock.generateInsnTo(c);
                        
                        OperatorFactory.newOperator(GOTO.class, 
                                new Class[]{ProgramBlock.class, Label.class}, 
                                c, serialEnd);
                        
                        //new GOTO(c, serialEnd);
                        
                        OperatorFactory.newOperator(Marker.class, 
                                new Class[]{ProgramBlock.class, Label.class}, 
                                c, finallyEnd);
                        //new Marker(c, finallyEnd);
                    }
                    addAnyExceptionCatchRange(finallyEnd);
                }
            	
            }
            
            addAnyExceptionCatchRange(catchs.get(catchs.size() - 1).getEnd());
            
            implicitCatch.prepare();
            
            finallyBlock.prepare();
        }

        /*
         *  implicitCatch and finallyBlock is not null 
         *  if finallyBlock is null
         */
        if(finallyBlock != null)
        {
            //build Exception Table(just for any type)
            for(int i=0; i<anyCatchRange.size();)
            {
            	Label start = anyCatchRange.get(i++);
            	Label end = anyCatchRange.get(i++);
            	this.addTreCatchInfo(start, end, implicitCatch.getStart(), AnyException.ANY);
            }
        }
        
        //for exception table
        if(CollectionUtils.isNotEmpty(tryCatchInfoes))
        {
        	for(TryCatchInfo info : tryCatchInfoes)
        	{
        		targetParent.getMethod().getMethodBody().addTryCatchInfo(info);
        	}
        }
    }
    
    
    
    @Override
    public void execute()
    {
        targetParent.getInsnHelper().mark(serialStart);
        
        tryBlock.execute();
        
        if(CollectionUtils.isNotEmpty(catchs))
        {
            for(Catch c : catchs)
            {
                c.execute();
            }
        }
        
        if(finallyBlock != null)
        {
            implicitCatch.execute();
            finallyBlock.execute();
        }
        
        targetParent.getInsnHelper().mark(serialEnd);
    }
    
    void appendEpisode(Catch catchBlock)
    {
        if(catchs == null)
        {
            catchs = new ArrayList<Catch>();
            queue.addAfter(tryBlock, catchBlock);
        }
        else
        {
            Catch previous = catchs.get(catchs.size() - 1);
            AClass exceptionType = catchBlock.getExceptionType();
            
            if(exceptionType != null &&
               exceptionType.isChildOrEqual(previous.getExceptionType()))
            {
                throw new ASMSupportException("Unreachable catch block for " + exceptionType + 
                    ". It is already handled by the catch block for " + exceptionType);
            }
            
            queue.addAfter(previous, catchBlock);
        }
        
        catchBlock.setParent(targetParent);
        catchBlock.setSerial(this);
        catchs.add(catchBlock);
        
        //add Exception Table:
        addTreCatchInfo(tryBlock.getStart(), tryBlock.getEnd(), 
            catchBlock.getStart(), catchBlock.getExceptionType());
    }
    
    void appendEpisode(Finally  finallyBlock)
    {
        if(this.finallyBlock != null)
        {
            throw new ASMSupportException("Finally block already exists.");
        }
        finallyBlock.setParent(targetParent);
        finallyBlock.setSerial(this);
        this.finallyBlock = finallyBlock;
        
        //add implicit catch block;
        implicitCatch = new ImplicitCatch();
        implicitCatch.setParent(targetParent);
        
        queue.setLast(implicitCatch);
        queue.setLast(finallyBlock);
    }


    /**
     * 
     * 
     * @param block
     */
    private void insertFinallyBeforeBreakStack(AbstractBlock block)
    {
        List<BreakStack> breaks = fetchAllBreakStack(block, null);
        
        for(BreakStack brk : breaks)
        {

    		//check the throw exception type,
    		//if exist suitable catch block, do not insert 
    		//finally block code
        	if(brk instanceof Throw)
        	{
        		continue;
        	}
        	/*if(brk instanceof Throw && CollectionUtils.isNotEmpty(catchs))
        	{
        		boolean existCatch = false;
        		Throw throwOper = (Throw) brk;
        		for(Catch c : catchs)
        		{
        			if(throwOper.getThrowExceptionType().isChildOrEqual(c.getExceptionType()))
        			{
        				existCatch = true;
        				break;
        			}
        		}
        		
        		if(existCatch)
        		{
        			continue;
        		}
        	}*/
        	
            ProgramBlock breakBlock = brk.getBlock();
            
            Label startLbl = new Label("implicit finally before break stack start");
            Label endLbl = new Label("implicit finally before break stack end");
            addAnyExceptionCatchRange(startLbl);
            addAnyExceptionCatchRange(endLbl);
            
            //first remove node start with b from list
            breakBlock.getQueue().removeFrom(brk);
            
            breakBlock.setFinish(false);
            
            //dosen't detected previous whether serial block.
        	OperatorFactory.newOperator(Marker.class, false,
    				new Class[]{ProgramBlock.class, Label.class}, 
    				breakBlock, startLbl);
        	
            //new Marker(breakBlock, startLbl);
            
            //append finally block code to list
            finallyBlock.generateInsnTo(breakBlock);
            
            {
	            //*hard to understand*
	            //append the b to end of the list
	            breakBlock.addExe(brk);
	            

	            //if already returned in inserted finally code
	            //remove only the break stack operator
	            if(breakBlock.isFinish())
	            {
	            	//remove only brk node
	            	breakBlock.removeExe(brk);
	            }
	            else
	            {
	                breakBlock.setFinish(true);
	            }
            }
            
            OperatorFactory.newOperator(Marker.class, 
    				new Class[]{ProgramBlock.class, Label.class}, 
    				breakBlock, endLbl);
            
            //new Marker(breakBlock, endLbl);
        }
    }
    
    
    private List<BreakStack> fetchAllBreakStack(AbstractBlock block, List<BreakStack> container)
    {
        if(container == null)
        {
            container = new ArrayList<BreakStack>();
        }
        for(ByteCodeExecutor executor : block.getQueue())
        {
            if(executor instanceof BreakStack)
            {
                container.add((BreakStack) executor);
            }
            else if(executor instanceof AbstractBlock &&
            		!(executor instanceof ImplicitCatch))
            {
                fetchAllBreakStack((AbstractBlock) executor, container);
            }
        }
        return container;
    }
    
    
    private void addTreCatchInfo(Label start, Label end, Label handler, AClass type)
    {
    	if(tryCatchInfoes == null)
    	{
    		tryCatchInfoes = new ArrayList<TryCatchInfo>();
    	}
    	tryCatchInfoes.add(new TryCatchInfo(start, end, handler, type.getType()));
    }
    
    public void addAnyExceptionCatchRange(Label label)
    {
        if(anyCatchRange == null)
            anyCatchRange = new ArrayList<Label>();
        anyCatchRange.add(label);
    }
    
    
    public Finally getFinally() {
		return finallyBlock;
	}
	
	/**
	 * 
	 *
	 */
    private class ImplicitCatch extends ProgramBlock{

        @Override
        public void generateInsn()
        {
            LocalVariable exception = getLocalAnonymousVariableModel(AnyException.ANY);

        	OperatorFactory.newOperator(Store.class, 
    				new Class[]{ProgramBlock.class, LocalVariable.class}, 
    				this, exception);
        	
            //new Store(this, exception);
            
            ExceptionSerialBlock.this.finallyBlock.generateInsnTo(this);
            
            if(!this.isFinish())
            {
                throwException(exception);
            }
        }

        @Override
        public void executing()
        {
            insnHelper.getMv().getStack().push(AnyException.ANY.getType());
            for(ByteCodeExecutor exe : getQueue())
            {
                exe.execute();
            }
        }

		@Override
		public void setFinish(boolean finish) {
			super.setFinish(finish);
		}
        
        
        
    }
    
    
}

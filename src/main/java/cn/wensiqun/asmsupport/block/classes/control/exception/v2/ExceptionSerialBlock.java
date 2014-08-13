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
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.asmdirect.Marker;
import cn.wensiqun.asmsupport.operators.asmdirect.Store;

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
            new GOTO(tryBlock, serialEnd);
            
            for(Catch c : catchs)
            {
                c.prepare();
                new GOTO(c, serialEnd);
            }
        }
        else if(CollectionUtils.isEmpty(catchs) && finallyBlock != null)
        {
        	addAnyExceptionCatchRange(tryBlock.getStart());
            
            tryBlock.prepare();
            
            new GOTO(tryBlock, finallyBlock.getStart());

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
            new GOTO(tryBlock, finallyBlock.getStart());
            
            //insert finally for BreakStack operator in try block.
            insertFinallyBeforeBreakStack(tryBlock);
            
            for(Catch c : catchs)
            {
                c.prepare();
                
                //insert finally for BreakStack operator in try block.
                insertFinallyBeforeBreakStack(c);
                
                Label finallyStart = new Label("catch's implicit finally start");
            	Label finallyEnd = new Label("catch's implicit finally end");
            	
            	addAnyExceptionCatchRange(finallyStart);
            	{
        	    //inject implicit finally block code at end of catch
            	new Marker(c, finallyStart);
            	finallyBlock.generateInsnTo(c);
                new GOTO(c, serialEnd);
            	new Marker(c, finallyEnd);
            	}
            	addAnyExceptionCatchRange(finallyEnd);
            	
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


    private void insertFinallyBeforeBreakStack(AbstractBlock block)
    {
        List<BreakStack> breaks = fetchAllBreakStack(block, null);
        
        for(BreakStack b : breaks)
        {
            ProgramBlock breakBlock = b.getBlock();
            
            Label startLbl = new Label("implicit finally before break stack start");
            Label endLbl = new Label("implicit finally before break stack end");
            addAnyExceptionCatchRange(startLbl);
            addAnyExceptionCatchRange(endLbl);
            
            //first remove node start with b from list
            breakBlock.getQueue().removeFrom(b);
            
            new Marker(breakBlock, startLbl);
            
            //append finally block code to list
            finallyBlock.generateInsnTo(breakBlock);
            
            //append the b to end of the list
            breakBlock.addExe(b);
            
            new Marker(breakBlock, endLbl);
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
            else if(executor instanceof AbstractBlock)
            {
                fetchAllBreakStack((AbstractBlock) executor, container);
            }
        }
        return container;
    }
    
    
    private void addTreCatchInfo(Label start, Label end, Label handler, AClass type)
    {
        targetParent.getMethod().getMethodBody()
              .addTryCatchInfo(start, end, handler, type);
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
            new Store(this, exception);
            
            ExceptionSerialBlock.this.finallyBlock.generateInsnTo(this);
            
            throwException(exception);
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
        
    }
    
    
}

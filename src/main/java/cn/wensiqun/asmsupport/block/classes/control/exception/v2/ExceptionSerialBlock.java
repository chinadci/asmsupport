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
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
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

    public ExceptionSerialBlock(ProgramBlock parent, Try tryBlock)
    {
        this.tryBlock = tryBlock;
        this.targetParent = parent;
        serialStart = new Label();
        serialEnd = new Label();
        
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
            tryBlock.prepare();
            new GOTO(tryBlock, finallyBlock.getStart());
            
            implicitCatch.prepare();
            
            finallyBlock.prepare();
        }
        else
        {
            tryBlock.prepare();
            new GOTO(tryBlock, finallyBlock.getStart());
            
            for(Catch c : catchs)
            {
                c.prepare();
                new GOTO(c, serialEnd);
            }
            
            implicitCatch.prepare();
            
            finallyBlock.prepare();
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
        
        //add implicit catch block;
        implicitCatch = new ImplicitCatch();
        this.finallyBlock = finallyBlock;
        
        queue.setLast(implicitCatch);
        queue.setLast(finallyBlock);
    }
    
    public Finally getFinally() {
		return finallyBlock;
	}

	private void addTreCatchInfo(Label start, Label end, Label handler, AClass type)
    {
        targetParent.getMethod().getMethodBody()
              .addTryCatchInfo(start, end, handler, type);
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

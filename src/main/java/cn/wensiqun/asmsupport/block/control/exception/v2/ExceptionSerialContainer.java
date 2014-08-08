package cn.wensiqun.asmsupport.block.control.exception.v2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AnyException;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.BreakStack;
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.asmdirect.Store;
import cn.wensiqun.asmsupport.utils.collections.CommonLinkedList;

public class ExceptionSerialContainer extends ByteCodeExecutor
{

    private ProgramBlock parent;
    
    private Try tryBlock;
    
    private List<Catch> catchs;
    
    private ImplicitCatch implicitCatch;
    
    private Finally  finallyBlock;
    
    private Label serialStart;

    private Label serialEnd;

    private CommonLinkedList<ByteCodeExecutor> executeQueue;
    
    public ExceptionSerialContainer(ProgramBlock parent, Try tryBlock)
    {
        this.tryBlock = tryBlock;
        this.parent = parent;
        serialStart = new Label();
        serialEnd = new Label();
        executeQueue = new CommonLinkedList<ByteCodeExecutor>();
        
        tryBlock.setParent(parent);
        executeQueue.add(tryBlock);
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
                addTreCatchInfo(tryBlock.getStart(), tryBlock.getEnd(), c.getStart(), c.getExceptionType());
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
        
        
        //process exception table
    }
    
    private List<BreakStack> checkoutBreakStack()
    {
        return null;
    }

    @Override
    public void execute()
    {
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
    }
    
    void appendEpisode(Catch catchBlock)
    {
        if(catchs == null)
        {
            catchs = new ArrayList<Catch>();
            executeQueue.addAfter(tryBlock, catchBlock);
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
            
            executeQueue.addAfter(previous, catchBlock);
        }
        catchs.add(catchBlock);
    }
    
    void appendEpisode(Finally  finallyBlock)
    {
        if(this.finallyBlock != null)
        {
            throw new ASMSupportException("Finally block already exists.");
        }
        //add implicit catch block;
        implicitCatch = new ImplicitCatch();
        this.finallyBlock = finallyBlock;
        
        executeQueue.setLast(implicitCatch);
        executeQueue.setLast(finallyBlock);
    }
    
    private void addTreCatchInfo(Label start, Label end, Label handler, AClass type)
    {
        parent.getMethod().getMethodBody()
              .addTryCatchInfo(start, end, handler, type);
    }
    
    private class ImplicitCatch extends ProgramBlock{

        @Override
        public void generateInsn()
        {
            LocalVariable exception = getLocalAnonymousVariableModel(AnyException.ANY);
            new Store(this, exception);
            
            ExceptionSerialContainer.this.finallyBlock.generateInsnTo(this);
            
            throwException(exception);
        }

        @Override
        public void executing()
        {
            insnHelper.getMv().getStack().push(AnyException.ANY.getType());
            for(ByteCodeExecutor exe : getExecuteQueue())
            {
                exe.execute();
            }
        }
        
    }
}

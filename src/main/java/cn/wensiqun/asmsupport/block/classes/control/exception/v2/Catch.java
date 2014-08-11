package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.asmdirect.Store;

public abstract class Catch extends ExceptionEpisodeBlock implements LocalVariableBody
{

    private AClass exceptionType;
    
    private Label  injectFinallyCodeStart;
    
    public Catch(AClass exceptionType)
    {
        if(exceptionType == null)
        {
            throw new ASMSupportException("missing catch exception type.");
        }
        this.exceptionType = exceptionType;
        injectFinallyCodeStart = new Label();
    }

    @Override
    public void generateInsn()
    {
        LocalVariable exception = getLocalAnonymousVariableModel(exceptionType);
        new Store(getExecutor(), exception);
        body(exception);
    }

    @Override
    public void executing()
    {
        //the exception variable already exists at the top of the statck.
        insnHelper.getMv().getStack().push(exceptionType.getType());
        
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
    }
    
    public Catch catchException(Catch catchBlock)
    {
        ExceptionSerialBlock serial = getSerial();
        
        if(serial.getFinally() != null)
        {
            throw new ASMSupportException("Exists finally block. please create catch before finally block");
        }
        getSerial().appendEpisode(catchBlock);
        return catchBlock;
    }
    
    public Finally finallyThan(Finally block)
    {
        ExceptionSerialBlock serial = getSerial();
        if(serial.getFinally() != null)
        {
            throw new ASMSupportException("Already exists finally block.");
        }
        getSerial().appendEpisode(block);
        
        return block;
    }
    
    AClass getExceptionType()
    {
        return exceptionType;
    }
    
    void injectFinally(Finally finallyBlock)
    {
        finallyBlock.generateInsnTo(this);
    }

    Label getInjectFinallyCodeStart()
    {
        return injectFinallyCodeStart;
    }
    
    
}

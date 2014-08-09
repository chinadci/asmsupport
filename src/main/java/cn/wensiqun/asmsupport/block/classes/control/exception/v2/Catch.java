package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;

public abstract class Catch extends ProgramBlock implements LocalVariableBody
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
        body(null);
    }

    @Override
    public void executing()
    {
        for(ByteCodeExecutor exe : getExecuteQueue()){
            exe.execute();
        }
    }
    
    AClass getExceptionType()
    {
        return exceptionType;
    }
    
    void injectFinallyCode(Finally finallyBlock)
    {
        
    }

    Label getInjectFinallyCodeStart()
    {
        return injectFinallyCodeStart;
    }
    
    
}

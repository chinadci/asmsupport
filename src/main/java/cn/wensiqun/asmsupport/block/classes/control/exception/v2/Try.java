package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Try extends ProgramBlock implements Body
{

    @Override
    public void generateInsn()
    {
        body();
    }

    @Override
    public void executing()
    {
        for(ByteCodeExecutor exe : getExecuteQueue()){
            exe.execute();
        }
    }
    
    void injectFinallyCode(Finally finallyBlock)
    {
        
    }

    
}

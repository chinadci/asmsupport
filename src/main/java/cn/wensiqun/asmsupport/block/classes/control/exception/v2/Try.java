package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Try extends ExceptionEpisodeBlock implements Body
{

    @Override
    public void generateInsn()
    {
        body();
    }

    @Override
    public void executing()
    {
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
    }
    
    void injectFinallyCode(Finally finallyBlock)
    {
        
    }

    
}

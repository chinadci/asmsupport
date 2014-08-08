package cn.wensiqun.asmsupport.block.control.exception.v2;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.ProgramBlock;
import cn.wensiqun.asmsupport.block.body.Body;

public abstract class Finally extends ProgramBlock implements Body
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

    
}

package cn.wensiqun.asmsupport.block.classes.control.exception;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;
import cn.wensiqun.asmsupportgeneric.GenericFinally;

public abstract class FinallyInternal extends EpisodeBlock<ExceptionSerialBlock> implements GenericFinally
{

    @Override
    public void generate()
    {
        body();
    }

    @Override
    protected void doExecute()
    {
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
    }

}

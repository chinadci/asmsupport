package cn.wensiqun.asmsupport.block.classes.control.exception;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Finally extends EpisodeBlock<ExceptionSerialBlock> implements Body
{

    @Override
    public void generate()
    {
        body();
    }

    @Override
    public void doExecute()
    {
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
    }

}

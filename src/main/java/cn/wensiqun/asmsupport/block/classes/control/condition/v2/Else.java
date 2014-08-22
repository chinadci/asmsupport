package cn.wensiqun.asmsupport.block.classes.control.condition.v2;

import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Else extends EpisodeBlock<ConditionSerialBlock> implements Body
{

    @Override
    public void generate()
    {
        body();
    }

    @Override
    public void doExecute()
    {

    }

}

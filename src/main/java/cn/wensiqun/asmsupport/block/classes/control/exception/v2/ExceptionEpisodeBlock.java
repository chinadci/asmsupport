package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;

public abstract class ExceptionEpisodeBlock extends EpisodeBlock
{

    protected ExceptionSerialContainer serial;

    
    void setSerial(ExceptionSerialContainer serial)
    {
        this.serial = serial;
    }

}

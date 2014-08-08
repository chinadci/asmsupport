package cn.wensiqun.asmsupport.block.control.exception.v2;

import cn.wensiqun.asmsupport.block.control.EpisodeBlock;

public abstract class ExceptionEpisodeBlock extends EpisodeBlock
{

    protected ExceptionSerialContainer serial;

    
    void setSerial(ExceptionSerialContainer serial)
    {
        this.serial = serial;
    }

}

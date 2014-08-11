package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;
import cn.wensiqun.asmsupport.exception.ASMSupportException;

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
    
}

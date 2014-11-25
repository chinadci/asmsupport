package cn.wensiqun.asmsupport.block.classes.control.exception;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupportgeneric.GenericTry;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class TryInternal extends EpisodeBlock<ExceptionSerialBlock> implements GenericTry<CatchInternal, FinallyInternal>
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

    @Override
    public CatchInternal _catch(CatchInternal catchBlock)
    {
        ExceptionSerialBlock serial = getSerial();
        
        if(serial.getFinally() != null)
        {
            throw new ASMSupportException("Exists finally block. please create catch before finally block");
        }
        getSerial().appendEpisode(catchBlock);
        return catchBlock;
    }
    
    @Override
    public FinallyInternal _finally(FinallyInternal block)
    {
        ExceptionSerialBlock serial = getSerial();
        if(serial.getFinally() != null)
        {
            throw new ASMSupportException("Already exists finally block.");
        }
        getSerial().appendEpisode(block);
        
        return block;
    }
    
}

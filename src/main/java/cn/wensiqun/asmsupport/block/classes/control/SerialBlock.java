package cn.wensiqun.asmsupport.block.classes.control;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.AbstractBlockInternal;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;

public abstract class SerialBlock extends AbstractBlockInternal
{
    protected ProgramBlockInternal targetParent;
    
    protected SerialBlock(ProgramBlockInternal targetParent)
    {
        this.targetParent = targetParent;
        targetParent.getQueue().add(this);
    }

    /*@Override
    public final void execute()
    {
        targetParent.getInsnHelper().mark(getSerialStart());
        doExecute();
        targetParent.getInsnHelper().mark(getSerialEnd());
        targetParent.getInsnHelper().nop();
    }
    
    protected abstract void doExecute();*/
    
    
    @SuppressWarnings("unchecked")
    protected void initEpisode(@SuppressWarnings("rawtypes") EpisodeBlock block)
    {
        block.setParent(targetParent);
        block.setSerial(this);
    }

    
    public abstract Label getSerialStart();

    
    public abstract Label getSerialEnd();
}


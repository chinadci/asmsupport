package cn.wensiqun.asmsupport.block.classes.control;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.AbstractBlock;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

public abstract class SerialBlock extends AbstractBlock
{
    protected ProgramBlock targetParent;
    
    protected SerialBlock(ProgramBlock targetParent)
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


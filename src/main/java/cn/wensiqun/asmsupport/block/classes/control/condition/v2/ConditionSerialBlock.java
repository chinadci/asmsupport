package cn.wensiqun.asmsupport.block.classes.control.condition.v2;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.SerialBlock;
import cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf;
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class ConditionSerialBlock extends SerialBlock
{

    private IF ifBlock;
    
    private List<ElseIf> elseIfs;
    
    private Else elseBlock;
    
    protected ConditionSerialBlock(ProgramBlock targetParent, IF ifBlock)
    {
        super(targetParent);
        
        this.ifBlock = ifBlock;
        initEpisode(ifBlock);
        getQueue().add(ifBlock);
    }

    @Override
    public void prepare()
    {
        //prepare IF block
        //OperatorFactory.newOperator(GOTO.class, parameterTypes, arguments);
        
        
        if(CollectionUtils.isNotEmpty(elseIfs) || elseBlock != null)
        { 
            //add goto instruction in end of the block.
            OperatorFactory.newOperator(GOTO.class, 
                    new Class[]{ProgramBlock.class, Label.class}, 
                    ifBlock, getSerialEnd());
            
            
        }
    }

    @Override
    public void execute()
    {
        //
        ifBlock.execute();
        
        if(CollectionUtils.isNotEmpty(elseIfs))
        {
            for(ElseIf elseIf : elseIfs)
            {
                elseIf.execute();
            }
        }
        
        if(elseBlock != null)
        {
            elseBlock.execute();
        }
    }

    @Override
    public Label getSerialStart()
    {
        return ifBlock.getStart();
    }

    @Override
    public Label getSerialEnd()
    {
        if(elseBlock != null)
        {
            return elseBlock.getEnd();
        }
        else if(CollectionUtils.isNotEmpty(elseIfs))
        {
            return elseIfs.get(elseIfs.size() - 1).getEnd();
        }
        else
        {
            return ifBlock.getEnd();
        }
    }

}

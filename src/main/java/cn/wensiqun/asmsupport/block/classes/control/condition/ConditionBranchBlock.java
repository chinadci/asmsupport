package cn.wensiqun.asmsupport.block.classes.control.condition;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class ConditionBranchBlock extends EpisodeBlock {

    private Label end;

    public ConditionBranchBlock() {
        super();
        end = new Label();
    }
    
    abstract Label getLastLabel();
    
    Label getEndLabel(){
        return end;
    }
}

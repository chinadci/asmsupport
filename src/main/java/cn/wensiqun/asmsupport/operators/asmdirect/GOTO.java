/**
 * 
 */
package cn.wensiqun.asmsupport.operators.asmdirect;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;


/**
 * @author 温斯群(Joe Wen)
 *
 */
public class GOTO extends ASMDirect {
    
    private Label to;

    protected GOTO(ProgramBlock block, Label label) {
        super(block);
        this.to = label;
    }

    @Override
    protected void verifyArgument() {
        
    }

    @Override
    protected void checkCrement() {
        
    }

    @Override
    protected void checkAsArgument() {
        
    }
    
    @Override
    protected void executing() {
        block.getInsnHelper().goTo(to);
    }


}

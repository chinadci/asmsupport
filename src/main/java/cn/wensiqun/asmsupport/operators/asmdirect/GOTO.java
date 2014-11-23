/**
 * 
 */
package cn.wensiqun.asmsupport.operators.asmdirect;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;


/**
 * @author 温斯群(Joe Wen)
 *
 */
public class GOTO extends ASMDirect {
    
    private Label to;

    protected GOTO(ProgramBlockInternal block, Label label) {
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
    protected void doExecute() {
        block.getInsnHelper().goTo(to);
    }


}

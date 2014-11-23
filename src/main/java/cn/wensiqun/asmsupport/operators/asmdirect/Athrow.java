package cn.wensiqun.asmsupport.operators.asmdirect;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class Athrow extends ASMDirect {
    
    
	protected Athrow(ProgramBlockInternal block) {
		super(block);
	}

	protected Athrow(ProgramBlockInternal block, Label lbl) {
        super(block);
    }

    @Override
    protected void doExecute() {
    	block.getInsnHelper().throwException();
    }
    
}
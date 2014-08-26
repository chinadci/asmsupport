package cn.wensiqun.asmsupport.operators.asmdirect;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class Athrow extends ASMDirect {
    
    
	protected Athrow(ProgramBlock block) {
		super(block);
	}

	protected Athrow(ProgramBlock block, Label lbl) {
        super(block);
    }

    @Override
    protected void doExecute() {
    	block.getInsnHelper().throwException();
    }
    
}
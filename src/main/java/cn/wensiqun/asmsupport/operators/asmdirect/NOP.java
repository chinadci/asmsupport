package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.UnreachableCodeCheckSkipable;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class NOP extends ASMDirect implements UnreachableCodeCheckSkipable {
    
	protected NOP(ProgramBlock block) {
        super(block);
    }

    @Override
    protected void doExecute() {
        block.getInsnHelper().nop();
    }
    
}
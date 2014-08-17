package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitInsn extends AbstractOperator {

	private int opcode;
	
	protected VisitInsn(ProgramBlock block, int opcode) {
		super(block);
		this.opcode = opcode;
	}

	@Override
	protected void executing() {
        block.getInsnHelper().getMv().visitInsn(opcode);
	}

}

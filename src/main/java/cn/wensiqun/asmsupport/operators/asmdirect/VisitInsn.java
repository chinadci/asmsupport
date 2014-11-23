package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitInsn extends AbstractOperator {

	private int opcode;
	
	protected VisitInsn(ProgramBlockInternal block, int opcode) {
		super(block);
		this.opcode = opcode;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitInsn(opcode);
	}

}

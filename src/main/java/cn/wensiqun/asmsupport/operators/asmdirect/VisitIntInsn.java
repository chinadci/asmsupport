package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitIntInsn extends AbstractOperator {

	private int opcode;
	private int operand;
	
	protected VisitIntInsn(ProgramBlockInternal block, int opcode, int operand) {
		super(block);
		this.opcode = opcode;
		this.operand = operand;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitIntInsn(opcode, operand);
	}

}

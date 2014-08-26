package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitTypeInsn extends AbstractOperator {

	private int opcode;
	private String type;
	
	protected VisitTypeInsn(ProgramBlock block, int opcode, String type) {
		super(block);
		this.opcode = opcode;
		this.type = type;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitTypeInsn(opcode, type);
	}

}

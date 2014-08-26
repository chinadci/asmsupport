package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitVarInsn extends AbstractOperator {

	private int opcode;
	private int var;
	
	protected VisitVarInsn(ProgramBlock block, int opcode, int var) {
		super(block);
		this.opcode = opcode;
		this.var = var;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitVarInsn(opcode, var);
	}

}

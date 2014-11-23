package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitVarInsn extends AbstractOperator {

	private int opcode;
	private int var;
	
	protected VisitVarInsn(ProgramBlockInternal block, int opcode, int var) {
		super(block);
		this.opcode = opcode;
		this.var = var;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitVarInsn(opcode, var);
	}

}

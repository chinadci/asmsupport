package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitVarInsn;

public class VisitVarInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private int var;
	
	public VisitVarInsnAdapter(int opcode, int var) {
		this.opcode = opcode;
		this.var = var;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		new VisitVarInsn(block, opcode, var);
	}

}

package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitVarInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitVarInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private int var;
	
	public VisitVarInsnAdapter(int opcode, int var) {
		this.opcode = opcode;
		this.var = var;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitVarInsn.class, 
				new Class[]{ProgramBlock.class, int.class, int.class}, 
				block, opcode, var);
		//new VisitVarInsn(block, opcode, var);
	}

}

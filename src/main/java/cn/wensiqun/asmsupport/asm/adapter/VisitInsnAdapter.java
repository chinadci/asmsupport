package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	
	public VisitInsnAdapter(int opcode) {
		this.opcode = opcode;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitInsn.class, 
				new Class[]{ProgramBlock.class, int.class}, 
				block, opcode);
		//new VisitInsn(block, opcode);
	}

}

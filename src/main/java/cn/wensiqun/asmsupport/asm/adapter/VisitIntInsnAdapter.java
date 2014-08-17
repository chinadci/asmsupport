package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitIntInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitIntInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private int operand;
	
	public VisitIntInsnAdapter(int opcode, int operand) {
		this.opcode = opcode;
		this.operand = operand;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitIntInsn.class, 
				new Class[]{ProgramBlock.class, int.class, int.class}, 
				block, opcode, operand);
		//new VisitIntInsn(block, opcode, operand);
	}

}

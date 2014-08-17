package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitTypeInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitTypeInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private String type;
	
	public VisitTypeInsnAdapter(int opcode, String type) {
		this.opcode = opcode;
		this.type = type;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitTypeInsn.class, 
				new Class[]{ProgramBlock.class, int.class, String.class}, 
				block, opcode, type);
		//new VisitTypeInsn(block, opcode, type);
	}

}

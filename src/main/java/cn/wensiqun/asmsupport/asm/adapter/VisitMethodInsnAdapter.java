package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitMethodInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitMethodInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private String owner;
	private String name;
	private String desc;

	public VisitMethodInsnAdapter(int opcode, String owner, String name,
			String desc) {
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlockInternal block) {
		OperatorFactory.newOperator(VisitMethodInsn.class, 
				new Class[]{ProgramBlockInternal.class, int.class, String.class, String.class, String.class}, 
				block, opcode, owner, name, desc);
		//new VisitMethodInsn(block, opcode, owner, name, desc);
	}

}

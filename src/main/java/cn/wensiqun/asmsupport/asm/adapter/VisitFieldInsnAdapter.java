package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitFieldInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitFieldInsnAdapter implements VisitXInsnAdapter {

	private int opcode;
	private String owner;
	private String name;
	private String desc;
	
	public VisitFieldInsnAdapter(int opcode, String owner, String name,
			String desc) {
		super();
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}



	@Override
	public void newVisitXInsnOperator(ProgramBlockInternal block) {
		OperatorFactory.newOperator(VisitFieldInsn.class, 
				new Class[]{ProgramBlockInternal.class, int.class, String.class,
							String.class, String.class}, 
				block, opcode, owner, name, desc);
	}

}

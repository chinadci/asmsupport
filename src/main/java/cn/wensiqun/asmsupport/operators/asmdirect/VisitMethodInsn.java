package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitMethodInsn extends AbstractOperator {

	private int opcode;
	private String owner;
	private String name;
	private String desc;

	protected VisitMethodInsn(ProgramBlock block, int opcode, String owner, String name,
			String desc) {
		super(block);
		this.opcode = opcode;
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitMethodInsn(opcode, owner, name, desc);
	}

}

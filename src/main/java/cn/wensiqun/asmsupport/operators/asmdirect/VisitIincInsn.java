package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitIincInsn extends AbstractOperator {

	private int var;
	private int increment;
	
	protected VisitIincInsn(ProgramBlock block, int var, int increment) {
		super(block);
		this.var = var;
		this.increment = increment;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitIincInsn(var, increment);
	}

}

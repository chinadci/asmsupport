package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitLdcInsn extends AbstractOperator {

	private Object cts;
	
	protected VisitLdcInsn(ProgramBlock block, Object cts) {
		super(block);
		this.cts = cts;
	}

	@Override
	protected void executing() {
        block.getInsnHelper().getMv().visitLdcInsn(cts);
	}

}

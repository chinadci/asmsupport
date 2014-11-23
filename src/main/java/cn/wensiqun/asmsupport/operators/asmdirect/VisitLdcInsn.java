package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class VisitLdcInsn extends AbstractOperator {

	private Object cts;
	
	protected VisitLdcInsn(ProgramBlockInternal block, Object cts) {
		super(block);
		this.cts = cts;
	}

	@Override
	protected void doExecute() {
        block.getInsnHelper().getMv().visitLdcInsn(cts);
	}

}

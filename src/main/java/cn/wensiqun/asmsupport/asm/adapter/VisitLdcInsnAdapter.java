package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitLdcInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitLdcInsnAdapter implements VisitXInsnAdapter {

    private Object cts;
	
	public VisitLdcInsnAdapter(Object cts) {
		this.cts = cts;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitLdcInsn.class, 
				new Class[]{ProgramBlock.class, Object.class}, 
				block, cts);
		//new VisitLdcInsn(block, cts);
	}

}

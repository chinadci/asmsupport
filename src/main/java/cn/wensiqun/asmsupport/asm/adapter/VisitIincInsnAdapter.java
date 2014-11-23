package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitIincInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitIincInsnAdapter implements VisitXInsnAdapter {

	private int var;
	private int increment;
	
	public VisitIincInsnAdapter(int var, int increment) {
		this.var = var;
		this.increment = increment;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlockInternal block) {
		OperatorFactory.newOperator(VisitIincInsn.class, 
				new Class[]{ProgramBlockInternal.class, int.class, int.class}, 
				block, var, increment);
		//new VisitIincInsn(block, var, increment);
	}

}

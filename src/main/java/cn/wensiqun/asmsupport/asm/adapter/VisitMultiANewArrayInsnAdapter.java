package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.asmdirect.VisitMultiANewArrayInsn;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;

public class VisitMultiANewArrayInsnAdapter implements VisitXInsnAdapter {

	private int dims;
	private String desc;
	
	public VisitMultiANewArrayInsnAdapter(String desc, int dims) {
		this.dims = dims;
		this.desc = desc;
	}

	@Override
	public void newVisitXInsnOperator(ProgramBlockInternal block) {
		OperatorFactory.newOperator(VisitMultiANewArrayInsn.class, 
				new Class[]{ProgramBlockInternal.class, String.class, int.class}, 
				block, desc, dims);
		//new VisitMultiANewArrayInsn(block, desc, dims);
	}

}

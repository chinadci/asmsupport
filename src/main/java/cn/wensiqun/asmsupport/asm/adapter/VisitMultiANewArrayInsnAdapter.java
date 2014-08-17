package cn.wensiqun.asmsupport.asm.adapter;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
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
	public void newVisitXInsnOperator(ProgramBlock block) {
		OperatorFactory.newOperator(VisitMultiANewArrayInsn.class, 
				new Class[]{ProgramBlock.class, String.class, int.class}, 
				block, desc, dims);
		//new VisitMultiANewArrayInsn(block, desc, dims);
	}

}

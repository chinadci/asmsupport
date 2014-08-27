package cn.wensiqun.asmsupport.operators.numerical.crement.v2;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.Operators;

public class PostposeDecrment extends AbstractCrement {

	protected PostposeDecrment(ProgramBlock block, Crementable factor) {
		super(block, factor, Operators.DECREMENT, true);
	}

}

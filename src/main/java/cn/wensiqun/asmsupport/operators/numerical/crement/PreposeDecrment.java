package cn.wensiqun.asmsupport.operators.numerical.crement;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.Operators;

public class PreposeDecrment extends AbstractCrement {

	protected PreposeDecrment(ProgramBlockInternal block, Crementable factor) {
		super(block, factor, Operators.DECREMENT, false);
	}

}

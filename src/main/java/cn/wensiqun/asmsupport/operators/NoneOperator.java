package cn.wensiqun.asmsupport.operators;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

public class NoneOperator extends AbstractOperator implements UnreachableCodeCheckSkipable {

	protected NoneOperator(ProgramBlock block) {
		super(block);
	}

	@Override
	protected void executing() {

	}

}

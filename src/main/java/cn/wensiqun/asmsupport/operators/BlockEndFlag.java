package cn.wensiqun.asmsupport.operators;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

public class BlockEndFlag extends AbstractOperator implements UnreachableCodeCheckSkipable {

	public BlockEndFlag(ProgramBlock block) {
		super(block);
	}

	@Override
	protected void executing() {

	}

}

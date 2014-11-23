package cn.wensiqun.asmsupport.operators;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;

public class BlockEndFlag extends AbstractOperator implements UnreachableCodeCheckSkipable {

	protected BlockEndFlag(ProgramBlockInternal block) {
		super(block);
	}

	@Override
	protected void doExecute() {

	}

}

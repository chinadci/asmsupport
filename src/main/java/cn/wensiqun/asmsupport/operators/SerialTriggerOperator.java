package cn.wensiqun.asmsupport.operators;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

public class SerialTriggerOperator extends AbstractOperator implements UnreachableCodeCheckSkipable {

	protected SerialTriggerOperator(ProgramBlock block) {
		super(block);
	}

	@Override
	protected void executing() {

	}

}

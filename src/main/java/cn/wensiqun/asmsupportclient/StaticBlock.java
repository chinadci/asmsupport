package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.clinit.ClinitBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class StaticBlock extends ProgramBlock<ClinitBodyInternal> implements Body {

	public StaticBlock() {
		target = new ClinitBodyInternal() {
			@Override
			public void body() {
				StaticBlock.this.body();
			}
		};
	}
	
}

package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.Parameterized;
import cn.wensiqun.asmsupport.core.block.control.loop.WhileInternal;
import cn.wensiqun.asmsupport.standard.loop.IWhile;

public abstract class While extends ProgramBlock<WhileInternal> implements IWhile {
    
	public While(Parameterized condition) {
		target = new WhileInternal(condition) {
			@Override
			public void body() {
				While.this.body();
			}
		};
	}
	
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.WhileInternal;
import cn.wensiqun.asmsupportgeneric.loop.IWhile;

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

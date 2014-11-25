package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.WhileInternal;
import cn.wensiqun.asmsupportgeneric.GenericWhile;

public abstract class While extends ProgramBlock<WhileInternal> implements GenericWhile {
    
	public While(Parameterized condition) {
		target = new WhileInternal(condition) {
			@Override
			public void body() {
				While.this.body();
			}
		};
	}
	
}

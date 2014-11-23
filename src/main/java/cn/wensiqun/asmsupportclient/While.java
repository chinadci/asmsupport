package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.WhileInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class While extends ProgramBlock<WhileInternal> implements Body {
    
	public While(Parameterized condition) {
		target = new WhileInternal(condition) {
			@Override
			public void body() {
				While.this.body();
			}
		};
	}
	
}

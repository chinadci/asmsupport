package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class WhileClient extends ProgramBlockClient<While> implements Body {
    
	public WhileClient(Parameterized condition) {
		target = new While(condition) {
			@Override
			public void body() {
				WhileClient.this.body();
			}
		};
	}
	
}

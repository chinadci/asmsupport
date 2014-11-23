package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.DoWhile;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class DoWhileClient extends ProgramBlockClient<DoWhile> implements Body {
    
	public DoWhileClient(Parameterized condition) {
		target = new DoWhile(condition) {
			@Override
			public void body() {
				DoWhileClient.this.body();
			}
		};
	}
	
}

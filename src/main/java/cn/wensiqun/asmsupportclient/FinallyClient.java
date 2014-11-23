package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.Finally;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;
import cn.wensiqun.asmsupport.clazz.AClass;

public abstract class FinallyClient extends ProgramBlockClient<Finally> implements Body {

	public FinallyClient(AClass aclass) {
		target = new Finally() {

			@Override
			public void body() {
				FinallyClient.this.body();
			}
		};
	}
	
}

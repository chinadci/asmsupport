package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.FinallyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;
import cn.wensiqun.asmsupport.clazz.AClass;

public abstract class Finally extends ProgramBlock<FinallyInternal> implements Body {

	public Finally(AClass aclass) {
		target = new FinallyInternal() {

			@Override
			public void body() {
				Finally.this.body();
			}
		};
	}
	
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.FinallyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupportgeneric.GenericFinally;

public abstract class Finally extends ProgramBlock<FinallyInternal> implements GenericFinally {

	public Finally(AClass aclass) {
		target = new FinallyInternal() {

			@Override
			public void body() {
				Finally.this.body();
			}
		};
	}
	
}

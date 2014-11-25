package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.condition.ElseInternal;
import cn.wensiqun.asmsupportgeneric.GenericElse;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class Else extends ProgramBlock<ElseInternal> implements GenericElse  {

	public Else() {
		target = new ElseInternal() {
			@Override
			public void body() {
				Else.this.body();
			}
		};
	}
	
}

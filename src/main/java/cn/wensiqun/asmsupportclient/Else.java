package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.condition.ElseInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Else extends ProgramBlock<ElseInternal> implements Body  {

	public Else() {
		target = new ElseInternal() {
			@Override
			public void body() {
				Else.this.body();
			}
		};
	}
	
}

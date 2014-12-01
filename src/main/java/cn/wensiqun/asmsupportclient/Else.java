package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.condition.ElseInternal;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;
import cn.wensiqun.asmsupportgeneric.branch.IElse;

public abstract class Else extends ProgramBlock<ElseInternal> implements IElse  {

	public Else() {
		target = new ElseInternal() {
			@Override
			public void body() {
				Else.this.body();
			}
		};
	}
	
}

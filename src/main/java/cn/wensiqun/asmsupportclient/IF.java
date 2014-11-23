package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.IFInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class IF extends ProgramBlock<IFInternal> implements Body {

	public IF(Parameterized condition) {
		target = new IFInternal(condition) {
			@Override
			public void body() {
				IF.this.body();
			}
		};
	}

	public ElseIF _elseif(ElseIF elseif) {
		target._elseif(elseif.target);
		return elseif;
	}
	
	public Else _else(Else els) {
		target._else(els.target);
		return els;
	}
}

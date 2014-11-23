package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.ElseIFInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class ElseIF extends ProgramBlock<ElseIFInternal> implements Body {
	
	public ElseIF(Parameterized condition) {
		target = new ElseIFInternal(condition) {
			@Override
			public void body() {
				ElseIF.this.body();
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

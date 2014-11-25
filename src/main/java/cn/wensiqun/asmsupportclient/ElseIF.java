package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.ElseIFInternal;
import cn.wensiqun.asmsupportgeneric.IElseIF;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class ElseIF extends ProgramBlock<ElseIFInternal> implements IElseIF<ElseIF, Else> {
	
	public ElseIF(Parameterized condition) {
		target = new ElseIFInternal(condition) {
			@Override
			public void body() {
				ElseIF.this.body();
			}
		};
	}

	@Override
	public ElseIF _elseif(ElseIF elseif) {
		target._elseif(elseif.target);
		return elseif;
	}
	
	@Override
	public Else _else(Else els) {
		target._else(els.target);
		return els;
	}
}

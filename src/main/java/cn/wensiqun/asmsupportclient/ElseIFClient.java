package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.ElseIF;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class ElseIFClient extends ProgramBlockClient<ElseIF> implements Body {
	
	public ElseIFClient(Parameterized condition) {
		target = new ElseIF(condition) {
			@Override
			public void body() {
				ElseIFClient.this.body();
			}
		};
	}

	public ElseIFClient _elseif(ElseIFClient elseif) {
		target._elseif(elseif.target);
		return elseif;
	}
	
	public ElseClient _else(ElseClient els) {
		target._else(els.target);
		return els;
	}
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.IF;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class IFClient extends ProgramBlockClient<IF> implements Body {

	public IFClient(Parameterized condition) {
		target = new IF(condition) {
			@Override
			public void body() {
				IFClient.this.body();
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

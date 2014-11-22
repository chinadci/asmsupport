package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.condition.Else;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class ElseClient extends ProgramBlockClient<Else> implements Body  {

	public ElseClient() {
		target = new Else() {
			@Override
			public void body() {
				ElseClient.this.body();
			}
		};
	}
	
}

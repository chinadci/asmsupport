package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.loop.ForEach;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class ForEachClient extends ProgramBlockClient<ForEach> implements LocalVariableBody {

	public ForEachClient(ExplicitVariable iteratorVar) {
		target = new ForEach(iteratorVar) {

			@Override
			public void body(LocalVariable e) {
				ForEachClient.this.body(e);
			}
			
		};
	}
	
}

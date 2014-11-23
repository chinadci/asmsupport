package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.loop.ForEachInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class ForEach extends ProgramBlock<ForEachInternal> implements LocalVariableBody {

	public ForEach(ExplicitVariable iteratorVar) {
		target = new ForEachInternal(iteratorVar) {

			@Override
			public void body(LocalVariable e) {
				ForEach.this.body(e);
			}
			
		};
	}
	
}

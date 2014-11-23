package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class StaticMethodBody extends ProgramBlock<StaticMethodBodyInternal> implements
		LocalVariablesBody {

	public StaticMethodBody() {
	     target = new StaticMethodBodyInternal() {

			@Override
			public void body(LocalVariable... args) {
				StaticMethodBody.this.body(args);
			}
	    	 
	     };
	}

    

}

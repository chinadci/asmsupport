package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.IStaticMethodBody;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public abstract class StaticMethodBody extends ProgramBlock<StaticMethodBodyInternal> implements IStaticMethodBody {

	public StaticMethodBody() {
	     target = new StaticMethodBodyInternal() {

			@Override
			public void body(LocalVariable... args) {
				StaticMethodBody.this.body(args);
			}
	    	 
	     };
	}

    

}

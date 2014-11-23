package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class MethodBody extends ProgramBlock<CommonMethodBodyInternal> implements
		LocalVariablesBody {

	public MethodBody() {
	     target = new CommonMethodBodyInternal() {

			@Override
			public void body(LocalVariable... args) {
				MethodBody.this.body(args);
			}
	    	 
	     };
	}

    

}

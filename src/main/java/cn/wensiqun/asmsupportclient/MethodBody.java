package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.method.IMethodBody;

public abstract class MethodBody extends ProgramBlock<CommonMethodBodyInternal> implements IMethodBody {

	public MethodBody() {
	     target = new CommonMethodBodyInternal() {

			@Override
			public void body(LocalVariable... args) {
				MethodBody.this.body(args);
			}
	    	 
	     };
	}

}

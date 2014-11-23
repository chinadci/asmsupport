package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.init.EnumInitBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class EnumConstructorBody extends ProgramBlock<EnumInitBodyInternal> implements LocalVariablesBody {

	public EnumConstructorBody() {
		target = new EnumInitBodyInternal(){

			@Override
			public void body(LocalVariable... args) {
				EnumConstructorBody.this.body(args);
			}
			
		};
	}
}

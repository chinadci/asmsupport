package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.init.EnumInitBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

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

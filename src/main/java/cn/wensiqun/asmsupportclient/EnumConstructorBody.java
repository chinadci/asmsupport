package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.init.EnumInitBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;
import cn.wensiqun.asmsupportgeneric.method.IEnumContructorBody;

public abstract class EnumConstructorBody extends ProgramBlock<EnumInitBodyInternal> implements IEnumContructorBody {

	public EnumConstructorBody() {
		target = new EnumInitBodyInternal(){

			@Override
			public void body(LocalVariable... args) {
				EnumConstructorBody.this.body(args);
			}
			
		};
	}
}

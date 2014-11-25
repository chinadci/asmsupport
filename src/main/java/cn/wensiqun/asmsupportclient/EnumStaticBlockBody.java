package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.method.clinit.EnumClinitBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public abstract class EnumStaticBlockBody extends ProgramBlock<EnumClinitBodyInternal> implements LocalVariablesBody {

	public EnumStaticBlockBody() {
		target = new EnumClinitBodyInternal() {

			@Override
			public void body(LocalVariable... argus) {
				EnumStaticBlockBody.this.body(argus);
			}

			@Override
			public void constructEnumField() {
				EnumStaticBlockBody.this.constructEnumField();
			}
		};
	}
	
	public void newEnum(String name, Parameterized... argus) {
		target.newEnum(name, argus);
	}
	
	/**
	 * call newEnum method at this method.
	 * get some information about current enum type constructor
	 * 
	 * 在此方法中调用newEnum方法， 获取构造枚举列表中每个枚举类型需要的参数信息，枚举类型名等信息。
	 * 
	 */
	public abstract void constructEnumField();
	
	
}

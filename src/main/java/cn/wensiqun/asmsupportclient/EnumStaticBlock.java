package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.method.clinit.EnumClinitBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class EnumStaticBlock extends ProgramBlock<EnumClinitBodyInternal> implements LocalVariablesBody {

	public EnumStaticBlock() {
		target = new EnumClinitBodyInternal() {

			@Override
			public void body(LocalVariable... argus) {
				EnumStaticBlock.this.body(argus);
			}

			@Override
			public void constructEnumField() {
				EnumStaticBlock.this.constructEnumField();
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

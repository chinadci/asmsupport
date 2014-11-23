package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.common.ModifiedMethodBodyInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class ModifiedMethodBody extends ProgramBlock<ModifiedMethodBodyInternal> implements
		LocalVariablesBody {

	public ModifiedMethodBody() {
	     target = new ModifiedMethodBodyInternal() {

			@Override
			public void body(LocalVariable... args) {
				ModifiedMethodBody.this.body(args);
			}
	    	 
	     };
	}

	public AClass getOriginalMethodReturnClass(){
		return target.getOriginalMethodReturnClass();
	}

}

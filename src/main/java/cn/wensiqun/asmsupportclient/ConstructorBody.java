package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.method.init.InitBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupportgeneric.IContructorBody;

public abstract class ConstructorBody extends ProgramBlock<InitBodyInternal> implements IContructorBody {

	public ConstructorBody() {
		target = new InitBodyInternal(){

			@Override
			public void body(LocalVariable... args) {
				ConstructorBody.this.body(args);
			}
			
		};
	}

	@Override
	public MethodInvoker invokeSuperConstructor(Parameterized... arguments) {
    	return target.invokeSuperConstructor(arguments);
	}
}

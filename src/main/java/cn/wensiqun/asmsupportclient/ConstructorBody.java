package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.method.init.InitBodyInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public abstract class ConstructorBody extends ProgramBlock<InitBodyInternal> implements LocalVariablesBody {

	public ConstructorBody() {
		target = new InitBodyInternal(){

			@Override
			public void body(LocalVariable... args) {
				ConstructorBody.this.body(args);
			}
			
		};
	}

	public MethodInvoker invokeSuperConstructor(Parameterized... arguments) {
    	return target.invokeSuperConstructor(arguments);
	}
}

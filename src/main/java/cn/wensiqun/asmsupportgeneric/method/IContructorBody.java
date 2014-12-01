package cn.wensiqun.asmsupportgeneric.method;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public interface IContructorBody extends LocalVariablesBody {
	
	MethodInvoker invokeSuperConstructor(Parameterized... arguments);

}

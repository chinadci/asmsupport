package cn.wensiqun.asmsupportclient.creator;

import cn.wensiqun.asmsupport.clazz.AClass;

public interface IMethodCreatorClient {
    
	IMethodCreatorClient _private();
	
	IMethodCreatorClient _public();
	
	IMethodCreatorClient _protected();
	
	IMethodCreatorClient _default();
	
	IMethodCreatorClient _synchronized();

	IMethodCreatorClient _static();
	
	IMethodCreatorClient _abstract();
	
	IMethodCreatorClient _final();
	
	IMethodCreatorClient setReturnType(AClass ret);
	
	IMethodCreatorClient setReturnType(Class<?> ret);

	IMethodCreatorClient setName(String name);
	
	IMethodCreatorClient setArgumentTypes(AClass[] argus);

	IMethodCreatorClient setArgumentTypes(Class<?>[] argus);
	
	IMethodCreatorClient setArgumentNames(String[] argNames);
	
	IMethodCreatorClient setThrowTypes(Class<?>[] exceptionTypes);
	
}

package cn.wensiqun.asmsupportgeneric;

import cn.wensiqun.asmsupport.clazz.AClass;

public interface IMethodCreator {
    
	IMethodCreator asPrivate();
	
	IMethodCreator asPublic();
	
	IMethodCreator asProtected();
	
	IMethodCreator asDefault();
	
	IMethodCreator setReturnType(AClass ret);
	
	IMethodCreator setReturnType(Class<?> ret);

	IMethodCreator setName(String name);
	
	IMethodCreator setArgumentTypes(AClass[] argus);

	IMethodCreator setArgumentTypes(Class<?>[] argus);
	
	IMethodCreator setArgumentNames(String[] argNames);
	
	IMethodCreator setThrowTypes(Class<?>[] exceptionTypes);
	
}

package cn.wensiqun.asmsupportgeneric;

import cn.wensiqun.asmsupport.clazz.AClass;

public interface IFieldCreator {

	IMethodCreator asPrivate();
	
	IMethodCreator asPublic();
	
	IMethodCreator asProtected();
	
	IMethodCreator asDefault();
	
	IMethodCreator setType(AClass ret);
	
	IMethodCreator setType(Class<?> ret);

	IMethodCreator setName(String name);
	
}

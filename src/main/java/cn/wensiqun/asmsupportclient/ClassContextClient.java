package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.clazz.NewMemberClass;
import cn.wensiqun.asmsupport.creator.IClassContext;
import cn.wensiqun.asmsupportasm.ClassVisitor;

public class ClassContextClient<B extends IClassContext> implements IClassContext {

	B target;
	
	@Override
	public ClassVisitor getClassVisitor() {
		return target.getClassVisitor();
	}

	@Override
	public NewMemberClass getCurrentClass() {
		return target.getCurrentClass();
	}

	@Override
	public void setClassOutPutPath(String classOutPutPath) {
		target.setClassOutPutPath(classOutPutPath);
	}

	@Override
	public Class<?> startup() {
		return target.startup();
	}

	
}

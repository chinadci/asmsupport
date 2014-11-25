package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.creator.InterfaceCreatorInternal;

public class InterfaceCreator extends ClassContextClient<InterfaceCreatorInternal> {

	public InterfaceCreator(int version, String name, Class<?>[] interfaces) {
		target = new InterfaceCreatorInternal(version, name, interfaces);
	}
	
	public void createMethod(String name, AClass[] argClasses, AClass returnClass, AClass[] exceptions) {
        target.createMethod(name, argClasses, returnClass, exceptions);
	}
	
    public void createGlobalVariable(String name, AClass fieldClass){
    	target.createGlobalVariable(name, fieldClass);
    }
	
    public InterfaceCreatorInternal createStaticBlock(StaticBlockBody body) {
    	return target.createStaticBlock(body.target);
    }
	
}

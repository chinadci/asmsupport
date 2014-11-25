package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.creator.EnumCreatorInternal;

public class EnumCreator extends ClassContextClient<EnumCreatorInternal> {
	
	public EnumCreator(int version, String name, Class<?>[] interfaces) {
        target = new EnumCreatorInternal(version, name, interfaces);
    }

    public void createGlobalVariable(String name, int modifiers,
            AClass fieldClass) {
        target.createGlobalVariable(name, modifiers, fieldClass);
    }
    
    public void createEnumConstant(String name){
    	target.createEnumConstant(name);
    }
    
    public final void createMethod(String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            int access, MethodBody body) {
    	target.createMethod(name, argClasses, argNames, returnClass, exceptions, access, body.target);
    }
    
    public void createStaticMethod(String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            int access, StaticMethodBody body) {
    	target.createStaticMethod(name, argClasses, argNames, returnClass, exceptions, access, body.target);
    }

    public void createConstructor(AClass[] argClasses,
            String[] argNames, EnumConstructorBody body) {
    	target.createConstructor(argClasses, argNames, body.target);
    }

    public void createStaticBlock(EnumStaticBlockBody body) {
    	target.createStaticBlock(body.target);
    }
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.creator.ClassModifierInternal;

public class ClassModifier extends ClassContextClient<ClassModifierInternal> {

	public ClassModifier(Class<?> clazz) {
		target = new ClassModifierInternal(clazz);
	}
	
	public final void modifyMethod(String name, Class<?>[] argClasses, ModifiedMethodBody body){
		target.modifyMethod(name, argClasses, body.target);
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
    
    public void createStaticBlock(StaticBlockBody body){
    	target.createStaticBlock(body.target);
    }

    public void createGlobalVariable(String name, int modifiers, AClass fieldClass) {
        target.createGlobalVariable(name, modifiers, fieldClass);
    }
}

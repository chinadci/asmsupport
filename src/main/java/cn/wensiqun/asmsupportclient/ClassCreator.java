package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.creator.ClassCreatorInternal;

public class ClassCreator extends ClassContextClient<ClassCreatorInternal> {

    public ClassCreator(int version, int access, String name,
            Class<?> superCls, Class<?>[] interfaces) {
    	target = new ClassCreatorInternal(version, access, name, superCls, interfaces);
    }
    
    /**
     * 
     * @param name
     * @param modifiers
     * @param fieldClass
     * @param value
     * @return
     */
    public void createGlobalVariable(String name, int modifiers,
            AClass fieldClass) {
        target.createGlobalVariable(name, modifiers, fieldClass);
    }
    
    /**
     * 
     * @param name
     * @param arguments
     * @param argNames
     * @param returnClass
     * @param exceptions
     * @param access
     * @param body
     * @return
     */
    public final void createMethod(String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            int access, MethodBody body) {
    	target.createMethod(name, argClasses, argNames, returnClass, exceptions, access, body.target);
    }
    
    /**
     * 
     * @param name
     * @param argClasses
     * @param argNames
     * @param returnClass
     * @param exceptions
     * @param access
     * @param mb
     * @return
     */
    public void createStaticMethod(String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            int access, StaticMethodBody body) {
    	target.createStaticMethod(name, argClasses, argNames, returnClass, exceptions, access, body.target);
    }

    /**
     * create constructor;
     * 
     * @param arguments
     * @param argNames
     * @param mb
     * @param access
     */
    public void createConstructor(AClass[] arguments,
            String[] argNames, ConstructorBody body, int access) {
    	target.createConstructor(arguments, argNames, body.target, access);
    }

    /**
     * 
     * @param mb
     */
    public void createStaticBlock(StaticBlock block) {
    	target.createStaticBlock(block.target);
    }
}

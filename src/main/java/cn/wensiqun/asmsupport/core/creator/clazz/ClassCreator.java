package cn.wensiqun.asmsupport.core.creator.clazz;


import cn.wensiqun.asmsupport.core.block.method.clinit.StaticBlockBodyInternal;
import cn.wensiqun.asmsupport.core.block.method.common.MethodBodyInternal;
import cn.wensiqun.asmsupport.core.block.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.core.block.method.init.ConstructorBodyInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.creator.FieldCreator;
import cn.wensiqun.asmsupport.core.creator.IFieldCreator;
import cn.wensiqun.asmsupport.core.creator.IMethodCreator;
import cn.wensiqun.asmsupport.core.creator.MethodCreator;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.utils.ASConstant;
import cn.wensiqun.asmsupport.org.objectweb.asm.Opcodes;


/**
 * 
 * 
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class ClassCreator extends AbstractClassCreatorContext {

    public ClassCreator(int version, int access, String name,
            Class<?> superCls, Class<?>[] interfaces) {
        super(version, access, name, superCls, interfaces);
    }

    /**
     * 
     * Create a field for a class.
     * 
     * @param name            the field name
     * @param modifiers       the field modifiers
     * @param fieldClass      the field type
     * @return
     */
    public IFieldCreator createField(String name, int modifiers,
            AClass fieldClass) {
        IFieldCreator fc = new FieldCreator(name, modifiers,
                fieldClass);
        fieldCreators.add(fc);
        return fc;
    }

    /**
     * 
     * create constructor.
     * 
     * @param access
     * @param argTypes
     * @param argNames
     * @param body
     * @return
     */
	public IMethodCreator createConstructor(int access, AClass[] argTypes, String[] argNames, AClass[] exceptions, ConstructorBodyInternal body) {
		IMethodCreator creator = MethodCreator.methodCreatorForAdd(ASConstant.INIT, argTypes, argNames,
                null, exceptions, access, body);
        methodCreaters.add(creator);
        haveInitMethod = true;
        return creator;
	}

	/**
	 * Crate method for dummy call, remove static check that different to {@link #createMethod} method
	 * 
	 * @param access
	 * @param name
	 * @param argTypes
	 * @param argNames
	 * @param returnClass
	 * @param exceptions
	 * @param body
	 * @return
	 */
	public IMethodCreator createMethodForDummy(int access, String name, AClass[] argTypes, String[] argNames,
            AClass returnClass, AClass[] exceptions, MethodBodyInternal body) {
        IMethodCreator creator = MethodCreator.methodCreatorForAdd(name, argTypes, argNames,
                returnClass, exceptions, access, body);
        methodCreaters.add(creator);
        return creator;
	}

	/**
     * Create a method 
     * 
     * @param access          the method modifiers
     * @param name            the method name
     * @param argClasses      the method argument type list
     * @param argNames        the method arguments name list
     * @param returnClass     the method return class
     * @param exceptions      throw exceptions for this method
     * @param body            method body that is method logic implementation
     * @return
     */
	public IMethodCreator createMethod(int access, String name, AClass[] argTypes, String[] argNames,
			AClass returnClass, AClass[] exceptions, MethodBodyInternal body) {
		if((access & Opcodes.ACC_STATIC) != 0){
    		access -= Opcodes.ACC_STATIC;
    	}
		IMethodCreator creator = MethodCreator.methodCreatorForAdd(name, argTypes, argNames,
                returnClass, exceptions, access, body);
		methodCreaters.add(creator);
		return creator;
	}

	/**
     * Create a static method 
     * 
     * @param access          the method modifiers
     * @param name            the method name
     * @param argClasses      the method argument type list
     * @param argNames        the method arguments name list
     * @param returnClass     the method return class
     * @param exceptions      throw exceptions for this method
     * @param body            method body that is method logic implementation
     * @return
     */
	public IMethodCreator createStaticMethod(int access, String name, AClass[] argClasses, String[] argNames,
			AClass returnClass, AClass[] exceptions, StaticMethodBodyInternal body) {
		if((access & Opcodes.ACC_STATIC) == 0){
    		access += Opcodes.ACC_STATIC;
    	}
		IMethodCreator creator = MethodCreator.methodCreatorForAdd(name, argClasses, argNames,
                returnClass, exceptions, access, body);
        methodCreaters.add(creator);
        return creator;
	}

	/**
	 * Create static block.
	 * 
	 * @param block
	 * @return
	 */
	public IMethodCreator createStaticBlock(StaticBlockBodyInternal block) {
    	checkStaticBlock();
    	existedStaticBlock = true;
    	IMethodCreator creator = MethodCreator.methodCreatorForAdd(ASConstant.CLINIT, null, null, null, null,
                Opcodes.ACC_STATIC, block);
    	methodCreaters.add(0, creator);
    	return creator;
	}

    @Override
    protected void createDefaultConstructor() {
        createConstructor(Opcodes.ACC_PUBLIC, null, null, null, new ConstructorBodyInternal() {
            @Override
            public void body(LocalVariable... argus) {
                invokeSuperConstructor();
                _return();
            }
            
        });
    }
}

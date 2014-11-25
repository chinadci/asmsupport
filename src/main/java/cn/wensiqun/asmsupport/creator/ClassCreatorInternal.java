package cn.wensiqun.asmsupport.creator;


import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.method.clinit.ClinitBodyInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBodyInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.block.classes.method.init.InitBodyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.ASConstant;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class ClassCreatorInternal extends AbstractClassCreatorContext {

    public ClassCreatorInternal(int version, int access, String name,
            Class<?> superCls, Class<?>[] interfaces) {
        super(version, access, name, superCls, interfaces);
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
        GlobalVariableCreator fc = new GlobalVariableCreator(name, modifiers,
                fieldClass);
        fieldCreators.add(fc);
    }
    
    /**
     * 
     * @param name
     * @param arguments
     * @param argNames
     * @param returnClass
     * @param exceptions
     * @param access
     * @param mb
     * @return
     */
    public final void createMethod(String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            int access, CommonMethodBodyInternal mb) {
    	if((access & Opcodes.ACC_STATIC) != 0){
    		access -= Opcodes.ACC_STATIC;
    	}
        methodCreaters.add(MethodCreator.methodCreatorForAdd(name, argClasses, argNames,
                returnClass, exceptions, access, mb));
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
            int access, StaticMethodBodyInternal mb) {
    	if((access & Opcodes.ACC_STATIC) == 0){
    		access += Opcodes.ACC_STATIC;
    	}
        methodCreaters.add(MethodCreator.methodCreatorForAdd(name, argClasses, argNames,
                returnClass, exceptions, access, mb));
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
            String[] argNames, InitBodyInternal initBody, int access) {
        methodCreaters.add(MethodCreator.methodCreatorForAdd(ASConstant.INIT, arguments, argNames,
                null, null, access, initBody));
        haveInitMethod = true;
    }

    /**
     * 
     * @param mb
     */
    public void createStaticBlock(ClinitBodyInternal clinitb) {
    	checkStaticBlock();
    	existedStaticBlock = true;
        methodCreaters.add(0, MethodCreator.methodCreatorForAdd(ASConstant.CLINIT, null, null, null, null,
                Opcodes.ACC_STATIC, clinitb));
    }
    
    @Override
    protected void createDefaultConstructor() {
        createConstructor(null, null, new InitBodyInternal() {
            @Override
            public void body(LocalVariable... argus) {
                invokeSuperConstructor();
                _return();
            }
            
        }, Opcodes.ACC_PUBLIC);
    }
}
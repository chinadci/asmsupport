package jw.asmsupport.creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jw.asmsupport.asm.adapter.VisitXInsnAdapter;
import jw.asmsupport.block.method.cinit.CInitBody;
import jw.asmsupport.block.method.common.CommonMethodBody;
import jw.asmsupport.block.method.common.MethodBodyForModify;
import jw.asmsupport.block.method.common.StaticMethodBody;
import jw.asmsupport.clazz.AClass;
import jw.asmsupport.clazz.AClassFactory;
import jw.asmsupport.clazz.ProductClass;
import jw.asmsupport.exception.ASMSupportException;
import jw.asmsupport.exception.NoSuchMethod;
import jw.asmsupport.loader.ClassModifierClassLoader;
import jw.asmsupport.utils.ASConstant;
import jw.asmsupport.utils.ClassFileUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class ClassModifier extends AbstractClassContext {
	
    private static Log LOG = LogFactory.getLog(ClassModifier.class);
	
    protected List<IMethodCreator> methodModifiers;
    
    private List<MethodBodyForModify> modifyConstructorBodies;
    
	private ProductClass productClass;
	
	public ClassModifier(Class<?> clazz) {
		super();
		if(!clazz.isArray()){
			this.productClass = (ProductClass) AClassFactory.getProductClass(clazz);
		}else{
			throw new ASMSupportException("cannot modify array type : " + clazz);
		}
		methodCreaters = new ArrayList<IMethodCreator>();
		methodModifiers = new ArrayList<IMethodCreator>();
		fieldCreators = new ArrayList<IGlobalVariableCreator>();
	}

	public void modify(Map<String, List<VisitXInsnAdapter>> superConstructorMap){
		
        if(LOG.isDebugEnabled()){
        	LOG.debug("Start modify class : " + productClass.getReallyClass());
        }
		
        // create field
        for (IMemberCreator ifc : fieldCreators) {
            ifc.create(this, productClass);
        }

        // create method
        for (IMethodCreator imc : methodCreaters) {
            imc.create(this, productClass);
        }
        
        // modify method
        for (IMethodCreator imc : methodModifiers) {
            imc.create(this, productClass);
        }

		if(modifyConstructorBodies != null){
			for(MethodBodyForModify mbfm : modifyConstructorBodies){
				 Type[] argumentTypes = mbfm.getMethod().getMethodEntity().getArgTypes();
				 String desc = Type.getMethodDescriptor(Type.VOID_TYPE, argumentTypes);
				 mbfm.setSuperConstructorOperators(superConstructorMap.get(desc));
			}
		}
        
        for (IMemberCreator ifc : fieldCreators) {
            ifc.prepare();
        }

        for (IMethodCreator imc : methodCreaters) {
            imc.prepare();
        }
        
        for (IMethodCreator imc : methodModifiers) {
            imc.prepare();
        }

        for (IMemberCreator ifc : fieldCreators) {
            ifc.execute();
        }

        for (IMethodCreator imc : methodCreaters) {
            imc.execute();
        }
        
        for (IMethodCreator imc : methodModifiers) {
            imc.execute();
        }

        if(LOG.isDebugEnabled()){
        	LOG.debug("End modify class : " + productClass.getReallyClass());
        }
	}

	@Override
	public Class<?> startup() {
		ClassModifierClassLoader loader = new ClassModifierClassLoader(this);
		
		try {
			loader.loadClass(productClass.getName());
			
			/*ClassReader nameRefactorReader = new ClassReader(loader.getModifiedClassBytes());
			ClassWriter ernameRefactorWrit = new ClassWriter(0);
			ClassNameRefactorAdapter nameRefactorAdapter = new ClassNameRefactorAdapter(ernameRefactorWrit);
			nameRefactorReader.accept(nameRefactorAdapter, 0);
			
			String proxyClassName = nameRefactorAdapter.getJVMProxyClassName().replace("/", ".");
            byte[] modifiedBytes = ernameRefactorWrit.toByteArray();*/
	        
			String proxyClassName = productClass.getName();
			byte[] modifiedBytes = loader.getModifiedClassBytes();
            if(StringUtils.isNotBlank(getClassOutPutPath())){
	    	    ClassFileUtils.toLocal(modifiedBytes, getClassOutPutPath(), proxyClassName);
	        }
			return loadClass(proxyClassName, modifiedBytes);
		} catch (ClassNotFoundException e) {
			throw new ASMSupportException("Class Not Found Exception");
		} finally{
	        //cleanCach();
		}
	}
	
	public final void modifyMethod(String name, Class<?>[] argClasses, MethodBodyForModify mb){
		Class<?> clazz = productClass.getReallyClass();
		if(argClasses == null){
			argClasses = new Class<?>[0];
		}
		AClass[] argCls = new AClass[argClasses.length];
		String[] defaultArgNames = new String[argClasses.length];
		for(int i=0; i<argCls.length; i++){
			argCls[i] = getProductClass(argClasses[i]);
			defaultArgNames[i] = "arg" + i;
		}
		try {
			
			MethodCreator methodCreator;
			if(name.equals(ASConstant.CLINIT)){
				methodCreator = MethodCreator.methodCreatorForModify(name, argCls, defaultArgNames, AClass.VOID_ACLASS, null, Opcodes.ACC_STATIC, mb);
			}else if(name.equals(ASConstant.INIT)){
				if(modifyConstructorBodies == null){
					modifyConstructorBodies = new ArrayList<MethodBodyForModify>();
				}
				modifyConstructorBodies.add(mb);
				
				Constructor<?> constructor = clazz.getDeclaredConstructor(argClasses);
				/*Type[] argumentTypes = new Type[argClasses.length];
				for(int i=0; i<argClasses.length; i++){
					argumentTypes[i] = Type.getType(argClasses[i]);
				}*/
				methodCreator = MethodCreator.methodCreatorForModify(ASConstant.INIT, argCls, defaultArgNames, AClass.VOID_ACLASS, constructor.getExceptionTypes(),
						constructor.getModifiers(), mb);
			}else{
				Method method = clazz.getDeclaredMethod(name, argClasses);
				methodCreator = MethodCreator.methodCreatorForModify(name, argCls, defaultArgNames, getProductClass(method.getReturnType()), method.getExceptionTypes(),
						method.getModifiers(), mb);
			}
			
			methodModifiers.add(methodCreator);
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethod(productClass, name, argCls);
		}
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
            String[] argNames, AClass returnClass, Class<?>[] exceptions,
            int access, CommonMethodBody mb) {
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
            String[] argNames, AClass returnClass, Class<?>[] exceptions,
            int access, StaticMethodBody mb) {
    	if((access & Opcodes.ACC_STATIC) == 0){
    		access += Opcodes.ACC_STATIC;
    	}
        methodCreaters.add(MethodCreator.methodCreatorForAdd(name, argClasses, argNames,
                returnClass, exceptions, access, mb));
    }
    
    protected void checkStaticBlock(){
    	if(productClass.existStaticInitBlock()){
        	existedStaticBlock = true;
    	}
    	super.checkStaticBlock();
    }
    
    public void createStaticBlock(CInitBody mb){
    	checkStaticBlock();
    	existedStaticBlock = true;
        methodCreaters.add(0, MethodCreator.methodCreatorForAdd(ASConstant.CLINIT, null, null, null, null,
                Opcodes.ACC_STATIC, mb));
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
    
	@Override
	public ProductClass getCurrentClass() {
		return productClass;
	}
	
	public void setClassWriter(ClassWriter cw){
		if(this.cw == null){
			this.cw = cw;
		}
	}

	public List<IMethodCreator> getMethodModifiers() {
		return methodModifiers;
	}

}

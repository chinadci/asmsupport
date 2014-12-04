package cn.wensiqun.asmsupport.creator.clazz;

import java.util.List;

import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.IFieldCreator;
import cn.wensiqun.asmsupport.creator.IMethodCreator;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.loader.ASMClassLoader;
import cn.wensiqun.asmsupportasm.ClassVisitor;
import cn.wensiqun.asmsupportasm.ClassWriter;
import cn.wensiqun.asmsupportgeneric.creator.IClassContext;


public abstract class AbstractClassContext extends AClassFactory implements IClassContext{

    protected List<IMethodCreator> methodCreaters;

    protected List<IFieldCreator> fieldCreators;

    protected String classOutPutPath;
	
    protected boolean existedStaticBlock;
	
    protected ClassWriter cw;
    
    /**
     * 
     */
    private   ClassLoader parentClassLoader;
    
	protected void checkStaticBlock(){
    	if(existedStaticBlock){
    		throw new UnsupportedOperationException("the static block has alreay exist this method!");
    	}
    }
    
    protected Class<?> loadClass(String name, byte[] b) {
        Class<?> clazz = null;
        try {
        	ASMClassLoader classLoader;
        	if(parentClassLoader != null){
        		classLoader = ASMClassLoader.getInstance(parentClassLoader);
        	}else{
        		classLoader = ASMClassLoader.getInstance();
        	}
        	classLoader.defineClass(name, b);
        	clazz = classLoader.findClass(name);
        } catch (Throwable e) {
            throw new ASMSupportException("Error on define class " + name, e);
        }
        return clazz;
    }

    @Override
    public final ClassVisitor getClassVisitor() {
        return cw;
    }

	public String getClassOutPutPath() {
		return classOutPutPath;
	}
	
    public final void setClassOutPutPath(String classOutPutPath) {
        this.classOutPutPath = classOutPutPath;
    }

	public ClassLoader getParentClassLoader() {
		return parentClassLoader;
	}

	public void setParentClassLoader(ClassLoader parentClassLoader) {
		this.parentClassLoader = parentClassLoader;
	}

}
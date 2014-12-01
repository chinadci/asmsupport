/**1
 * 
 */
package cn.wensiqun.asmsupportgeneric.creator;


import cn.wensiqun.asmsupport.clazz.NewMemberClass;
import cn.wensiqun.asmsupportasm.ClassVisitor;


/**
 * 方法创建或者修改的上下文环境
 * @author 温斯群(Joe Wen)
 *
 */
public interface IClassContext {
	
    /**
     * 获取Class的ClassVisitor
     * @return
     */
    ClassVisitor getClassVisitor();
    
    
    NewMemberClass getCurrentClass();
    
	public void setClassOutPutPath(String classOutPutPath);
    
    
    /**
     * 启动Creator开始生成class，注意，调用完这个参数以后将不不会触发生成的class的static程序块
     * 
     * @return
     */
    Class<?> startup();
	
}

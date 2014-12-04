package cn.wensiqun.asmsupport.creator;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupportgeneric.creator.IClassContext;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public interface IMethodCreator extends Executable {
    
    /**
     * 创建逻辑实体
     * @param cv
     */
    public void create(IClassContext cv);
	
    public String getName();

    public AClass[] getArguments();
    
    public String getMethodString();
}

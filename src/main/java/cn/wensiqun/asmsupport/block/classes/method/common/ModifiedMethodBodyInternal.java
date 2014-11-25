/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.common;

import java.util.List;

import cn.wensiqun.asmsupport.asm.adapter.VisitXInsnAdapter;
import cn.wensiqun.asmsupport.block.interfaces.operator.KeywordVariableable;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.method.meta.AMethodMeta;
import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupportgeneric.IModifiedMethodBody;

/**
 * @author
 * 
 */
public abstract class ModifiedMethodBodyInternal extends CommonMethodBodyInternal implements IModifiedMethodBody {
    
	private List<VisitXInsnAdapter> superConstructorOperators;
	
    public AClass getOriginalMethodReturnClass(){
    	return getMethod().getMethodMeta().getReturnClass();
    }
    
	public void setSuperConstructorOperators(
			List<VisitXInsnAdapter> superConstructorOperators) {
		this.superConstructorOperators = superConstructorOperators;
	}

	@Override
    public void generateBody() {
		AMethodMeta me = getMethod().getMethodMeta();
		if(me.getName().equals(ASConstant.INIT)){
			//如果是构造方法，将被修改的构造方法中调用父类构造方法的那段字节码转移到新的构造方法中。
			if(superConstructorOperators != null){
			    for(VisitXInsnAdapter visitXInsnAdapter : superConstructorOperators){
			    	visitXInsnAdapter.newVisitXInsnOperator(getExecutor());
			    }
			}
		}
		super.generateBody();
    }
}

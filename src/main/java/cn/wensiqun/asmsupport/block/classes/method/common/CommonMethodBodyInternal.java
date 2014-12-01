/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.AbstractMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.operator.KeywordVariableable;
import cn.wensiqun.asmsupportgeneric.method.IMethodBody;

/**
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class CommonMethodBodyInternal extends AbstractMethodBody implements IMethodBody {
    
	@Override
    public void generateBody() {
		body(argments);
    }

}

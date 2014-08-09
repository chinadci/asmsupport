/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.ArgumentsBody;
import cn.wensiqun.asmsupport.block.interfaces.operator.KeywordVariableable;

/**
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class CommonMethodBody extends GenericMethodBody implements KeywordVariableable, ArgumentsBody {
    
	@Override
    public void generateBody() {
		body(argments);
    }

}

/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.operator.KeywordVariableable;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

/**
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class CommonMethodBodyInternal extends GenericMethodBody implements KeywordVariableable, LocalVariablesBody {
    
	@Override
    public void generateBody() {
		body(argments);
    }

}

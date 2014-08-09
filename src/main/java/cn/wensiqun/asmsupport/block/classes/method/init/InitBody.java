/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.init;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.ArgumentsBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupport.operators.method.SuperConstructorInvoker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.utils.reflet.ModifierUtils;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class InitBody extends GenericMethodBody implements ArgumentsBody {

	public MethodInvoker invokeSuperConstructor(Parameterized... arguments) {
    	AClass owner = getMethodOwner();
    	if(ModifierUtils.isEnum(getMethodOwner().getModifiers())){
    		throw new ASMSupportException("Cannot invoke super constructor from enum type " + owner);
    	}
        invokeVerify(owner);

        return OperatorFactory.newOperator(SuperConstructorInvoker.class, 
        		new Class<?>[]{ProgramBlock.class, AClass.class, Parameterized[].class}, 
        		getExecuteBlock(), owner, arguments);
	}
    
    @Override
    public final void generateBody() {
        body(argments);
    }
    
}

/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.init;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.block.classes.method.AbstractMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupport.operators.method.SuperConstructorInvoker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.utils.reflet.ModifierUtils;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class InitBodyInternal extends AbstractMethodBody implements LocalVariablesBody {

	public MethodInvoker invokeSuperConstructor(Parameterized... arguments) {
    	AClass owner = getMethodOwner();
    	if(ModifierUtils.isEnum(getMethodOwner().getModifiers())){
    		throw new ASMSupportException("Cannot invoke super constructor from enum type " + owner);
    	}
        invokeVerify(owner);

        return OperatorFactory.newOperator(SuperConstructorInvoker.class, 
        		new Class<?>[]{ProgramBlockInternal.class, AClass.class, Parameterized[].class}, 
        		getExecutor(), owner, arguments);
	}
    
    @Override
    public final void generateBody() {
        body(argments);
    }
    
}

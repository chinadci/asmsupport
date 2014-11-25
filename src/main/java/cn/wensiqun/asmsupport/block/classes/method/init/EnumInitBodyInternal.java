/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.init;

import org.apache.commons.lang3.ArrayUtils;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.block.classes.method.AbstractMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.method.SuperConstructorInvoker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupportgeneric.IEnumContructorBody;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class EnumInitBodyInternal extends AbstractMethodBody implements IEnumContructorBody {
    
    @Override
    public final void generateBody() {
        OperatorFactory.newOperator(SuperConstructorInvoker.class, 
        		new Class<?>[]{ProgramBlockInternal.class, AClass.class, Parameterized[].class}, 
        		getExecutor(), getMethodOwner(), new Parameterized[]{argments[0], argments[1]});
        body((LocalVariable[]) ArrayUtils.subarray(argments, 2, argments.length));
    }
    

}

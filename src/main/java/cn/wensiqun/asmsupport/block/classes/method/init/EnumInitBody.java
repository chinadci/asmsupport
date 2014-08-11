/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.method.init;

import org.apache.commons.lang3.ArrayUtils;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.ArgumentsBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.method.SuperConstructorInvoker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class EnumInitBody extends GenericMethodBody implements ArgumentsBody{
    
    @Override
    public final void generateBody() {
        OperatorFactory.newOperator(SuperConstructorInvoker.class, 
        		new Class<?>[]{ProgramBlock.class, AClass.class, Parameterized[].class}, 
        		getExecutor(), getMethodOwner(), new Parameterized[]{argments[0], argments[1]});
        body((LocalVariable[]) ArrayUtils.subarray(argments, 2, argments.length));
    }
    

}

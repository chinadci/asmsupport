/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.operators.AbstractOperator;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.AbstractArithmetic;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractNumerical extends AbstractOperator implements
        Parameterized {
    
    private static Log log = LogFactory.getLog(AbstractArithmetic.class);

    /**执行的结果类型 */
    protected AClass resultClass;
    
    protected String operator;
    
    protected AbstractNumerical(ProgramBlock block) {
        super(block);
    }

    
    /**
     * 运算因子入栈
     */
    protected abstract void factorToStack();

    /**
     * 
     * @param factor
     */
    protected void pushFactorToStack(Parameterized factor){
        
        AClass factorCls = factor.getParamterizedType();
        
        //factor to stack
        log.debug("push the first arithmetic factor to stack");
        factor.loadToStack(block);
        
        //unbox if needs
        if(!factorCls.isPrimitive()){
            log.debug("unbox " + factorCls);
            insnHelper.unbox(factorCls.getType());
        }
        
        //cast if needs
        if(!factorCls.equals(resultClass) &&
            resultClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder() ){
            log.debug("cast factor from " + factorCls + " to " + resultClass);
            insnHelper.cast(factorCls.getType(), resultClass.getType());    
        }
    }
    
    @Override
    public final AClass getParamterizedType() {
        return resultClass;
    }
    
    
}

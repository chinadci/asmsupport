/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical.bitwise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.operators.Operators;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class BinaryBitwise extends AbstractBitwise {

    private static Log log = LogFactory.getLog(BinaryBitwise.class);
    
    protected Parameterized factor1;
    protected Parameterized factor2;
    
    protected BinaryBitwise(ProgramBlock block, Parameterized factor1, Parameterized factor2) {
        super(block);
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    @Override
    protected void verifyArgument() {
        AClass ftrCls1 = factor1.getParamterizedType();
        AClass ftrCls2 = factor2.getParamterizedType();
        
        checkFactor(ftrCls1);
        checkFactor(ftrCls2);
    }

    @Override
    protected void checkAsArgument() {
        factor1.asArgument();
        factor2.asArgument();
    }

    @Override
    protected void initAdditionalProperties() {
        AClass ftrCls1 = factor1.getParamterizedType();
        AClass ftrCls2 = factor2.getParamterizedType();
        
        if(ftrCls2.getCastOrder() < ftrCls1.getCastOrder()){
            targetClass = ftrCls1;
        }else{
            targetClass = ftrCls2;
        }
        
        if(factor1 instanceof Value)
            ((Value)factor1).convert(targetClass);
        
        if(factor2 instanceof Value)
            ((Value)factor2).convert(targetClass);
    }

    @Override
    protected final void factorToStack() {
        log.debug("push the first arithmetic factor to stack");
        factor1.loadToStack(block);
        if(log.isDebugEnabled()){
            if(!factor1.getParamterizedType().equals(targetClass)){
                log.debug("cast arithmetic factor from " + factor1.getParamterizedType() + " to " + targetClass);
            }
        }
        insnHelper.unbox(factor1.getParamterizedType().getType());
        insnHelper.cast(factor1.getParamterizedType().getType(), targetClass.getType());    
        
        log.debug("push the second arithmetic factor to stack");
        factor2.loadToStack(block);
        
        if(log.isDebugEnabled()){
            if(!factor2.getParamterizedType().equals(targetClass)){
                log.debug("cast arithmetic factor from " + factor2.getParamterizedType() + " to " + targetClass);
            }
        }
        
        insnHelper.unbox(factor2.getParamterizedType().getType());
        
        if(operator.equals(Operators.LEFT_SHIFT) ||
           operator.equals(Operators.RIGHT_SHIFT) ||
           operator.equals(Operators.UNSIGNED_RIGHT_SHIFT) ){
            insnHelper.cast(factor2.getParamterizedType().getType(), AClass.INT_ACLASS.getType());
        }else{
            insnHelper.cast(factor2.getParamterizedType().getType(), targetClass.getType());
        }
    }
    
    @Override
    public final void doExecute() {
        log.debug("prepare operator " + operator);
        factorToStack();
        log.debug("execute operator " + operator);
        innerRunExe();
    }

    protected abstract void innerRunExe();

}

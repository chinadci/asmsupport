package cn.wensiqun.asmsupport.operators.relational;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.operators.numerical.crement.AbstractCrement;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class NumericalAndReferenceRelational extends AbstractRelational {
    
    private static Log log = LogFactory.getLog(NumericalRelational.class);
    
    protected NumericalAndReferenceRelational(ProgramBlock block, Parameterized factor1, Parameterized factor2) {
        super(block, factor1, factor2);
    }
    
    @Override
    protected void verifyArgument() {
        AClass ftrCls1 = AClassUtils.getPrimitiveAClass(factor1.getParamterizedType());
        AClass ftrCls2 = AClassUtils.getPrimitiveAClass(factor2.getParamterizedType());
        
        if(ftrCls1.equals(AClass.BOOLEAN_ACLASS)&&
           ftrCls2.equals(AClass.BOOLEAN_ACLASS)){
        
        } else if(ftrCls1.isPrimitive() && ftrCls2.isPrimitive()){
            checkFactorForNumerical(ftrCls1);
            checkFactorForNumerical(ftrCls2);
        }
    }

    @Override
    protected void checkOutCrement() {
        if(factor1 instanceof AbstractCrement){
            allCrement.add((AbstractCrement) factor1);
        }
        if(factor2 instanceof AbstractCrement){
            allCrement.add((AbstractCrement) factor2);
        }
    }

    @Override
    protected void checkAsArgument() {
        factor1.asArgument();
        factor2.asArgument();
    }

    @Override
    protected void afterInitProperties() {
        AClass ftrCls1 = AClassUtils.getPrimitiveAClass(factor1.getParamterizedType());
        AClass ftrCls2 = AClassUtils.getPrimitiveAClass(factor2.getParamterizedType());
        
        if(ftrCls1.getCastOrder() > ftrCls2.getCastOrder()){
            targetClass = ftrCls1;
        }else{
            targetClass = ftrCls2;
        }
    }

    @Override
    protected void factorsToStack() {
        AClass ftrCls1 = factor1.getParamterizedType();
        AClass ftrCls2 = factor2.getParamterizedType();
        
        if(ftrCls1.isPrimitive() || ftrCls2.isPrimitive()){
            
            log.debug("push the first factor to stack");
            factor1.loadToStack(block);
            
            if(!ftrCls1.isPrimitive()){
                log.debug("unbox " + ftrCls1);
                insnHelper.unbox(ftrCls1.getType());
            }
            
            boolean isNumerical = (targetClass.getCastOrder() >= AClass.BYTE_ACLASS.getCastOrder() &&
                       targetClass.getCastOrder() <= AClass.DOUBLE_ACLASS.getCastOrder());
            
            if(isNumerical){
                if(!ftrCls1.equals(targetClass) &&
                   targetClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder()){
                    log.debug("cast from " + ftrCls1 + " to " + targetClass);
                    insnHelper.cast(ftrCls1.getType(), targetClass.getType());
                }
            }

            log.debug("push the second factor to stack");
            factor2.loadToStack(block);
            
            if(!ftrCls2.isPrimitive()){
                log.debug("unbox " + ftrCls1);
                insnHelper.unbox(ftrCls2.getType());
            }
            
            if(isNumerical){
                if(!ftrCls2.equals(targetClass) &&
                   targetClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder()){
                    log.debug("cast from " + ftrCls2 + " to " + targetClass);
                    insnHelper.cast(ftrCls2.getType(), targetClass.getType());
                }
            }
        }else{
            log.debug("push the first factor to stack");
            factor1.loadToStack(block);
            
            log.debug("push the second factor to stack");
            factor2.loadToStack(block);
        }
    }
    
}

package cn.wensiqun.asmsupport.operators.relational;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class NumericalRelational extends AbstractRelational {

    private static Log log = LogFactory.getLog(NumericalRelational.class);
    
    protected NumericalRelational(ProgramBlock block, Parameterized factor1, Parameterized factor2) {
        super(block, factor1, factor2);
    }
    
    @Override
    protected void verifyArgument() {
        AClass ftrCls1 = AClassUtils.getPrimitiveAClass(factor1.getParamterizedType());
        AClass ftrCls2 = AClassUtils.getPrimitiveAClass(factor2.getParamterizedType());
        checkFactorForNumerical(ftrCls1);
        checkFactorForNumerical(ftrCls2);
    }

    @Override
    protected void checkAsArgument() {
        factor1.asArgument();
        factor2.asArgument();
    }
    

    @Override
    protected void ifCmp(Type type, int mode, Label label)
    {
        super.ifCmp(type, mode, label);
    }
    
    private boolean isZeorValue(Parameterized val)
    {
        if(val != null && val instanceof Value){
            Value v = (Value) val;
            switch (v.getType().getSort())
            {
                case Type.BOOLEAN :
                	return !((Boolean)v.getValue()).booleanValue();
                case Type.CHAR :
                    return ((Character)v.getValue()).charValue() == 0;
                case Type.BYTE :
                    return ((Byte)v.getValue()).byteValue() == 0;
                case Type.SHORT :
                    return ((Short)v.getValue()).shortValue() == 0;
                case Type.INT :
                	return ((Integer)v.getValue()).intValue() == 0;
                case Type.FLOAT :
                	return ((Float)v.getValue()).floatValue() == 0;
                case Type.LONG :
                	return ((Long)v.getValue()).longValue() == 0;
                case Type.DOUBLE :
                	return ((Double)v.getValue()).doubleValue() == 0;
            }
        }
        return false;
    }

    @Override
    protected void factorsToStack() {
        pushFactorToStack(factor1);
        pushFactorToStack(factor2);
        /*AClass ftrCls1 = factor1.getParamterizedType();
        AClass ftrCls2 = factor2.getParamterizedType();

        log.debug("push the first factor to stack");
        factor1.loadToStack(block);
        
        if(!ftrCls1.isPrimitive()){
            log.debug("unbox " + ftrCls1);
            insnHelper.unbox(ftrCls1.getType());
        }
        
        if(!ftrCls1.equals(targetClass) &&
            targetClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder()){
            log.debug("cast from " + ftrCls1 + " to " + targetClass);
            insnHelper.cast(ftrCls1.getType(), targetClass.getType());
        }
        
        log.debug("push the second factor to stack");
        factor2.loadToStack(block);
        
        if(!ftrCls2.isPrimitive()){
            log.debug("unbox " + ftrCls2);
            insnHelper.unbox(ftrCls2.getType());
        }
        
        if(!ftrCls2.equals(targetClass) &&
            targetClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder()){
            log.debug("cast from " + ftrCls2 + " to " + targetClass);
            insnHelper.cast(ftrCls2.getType(), targetClass.getType());
        }*/
        
    }
    
    private void pushFactorToStack(Parameterized factor){
        
        AClass factorCls = factor.getParamterizedType();
        
        //factor to stack
        log.debug("push the first arithmetic factor to stack");
        factor.loadToStack(block);
        
        AClass factorPrimitiveAClass = factorCls;
        //unbox if needs
        if(!factorCls.isPrimitive()){
            log.debug("unbox " + factorCls);
            insnHelper.unbox(factorCls.getType());
            factorPrimitiveAClass = AClassUtils.getPrimitiveAClass(factorCls);
        }
        
        //cast if needs
        if(factorPrimitiveAClass.getCastOrder() < targetClass.getCastOrder() &&
           targetClass.getCastOrder() > AClass.INT_ACLASS.getCastOrder())
        {
            log.debug("cast factor from " + factorCls + " to " + targetClass);
            insnHelper.cast(factorPrimitiveAClass.getType(), targetClass.getType());    
        }
    }
    
}

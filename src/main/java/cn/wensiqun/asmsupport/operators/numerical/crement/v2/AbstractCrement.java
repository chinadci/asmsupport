/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical.crement.v2;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.operators.assign.Assigner;
import cn.wensiqun.asmsupport.operators.numerical.AbstractNumerical;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractCrement extends AbstractNumerical {

    protected Crementable factor;
    
    protected Assigner assigner;
    
    protected AbstractCrement(ProgramBlock block, Crementable factor) {
        super(block);
        this.factor = factor;
    }
    
    protected Value getValue(){
        AClass type = factor.getParamterizedType();
        if(type.equals(AClass.DOUBLE_ACLASS)){
            return Value.value(1d);
        }else if(type.equals(AClass.FLOAT_ACLASS)){
            return Value.value(1f);
        }else if(type.equals(AClass.LONG_ACLASS)){
            return Value.value(1l);
        }else{
            return Value.value(1);
        }
    }
    
    protected abstract void before();
    
    protected abstract void after();

    @Override
    public void loadToStack(ProgramBlock block) {
        before();
        factor.loadToStack(block);
        after();
    }

    @Override
    public void asArgument() {
        block.removeExe(this);
    }

    @Override
    protected void factorToStack() {
        factor.loadToStack(block);
        insnHelper.unbox(factor.getParamterizedType().getType());
    }

    @Override
    protected void initAdditionalProperties() {
        resultClass = factor.getParamterizedType();
    }

    @Override
    protected void verifyArgument() {
        AClass fatCls = factor.getParamterizedType();
        if(!AClassUtils.arithmetical(fatCls)){
            throw new ArithmeticException("cannot execute arithmetic operator whit " + fatCls);
        }
    }


    @Override
    protected void checkAsArgument() {
        factor.asArgument();
    }

}

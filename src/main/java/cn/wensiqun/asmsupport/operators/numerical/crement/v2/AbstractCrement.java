/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical.crement.v2;

import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.operators.Operators;
import cn.wensiqun.asmsupport.operators.numerical.AbstractNumerical;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractCrement extends AbstractNumerical {

    private Crementable factor;
    
    /**
     * indicate the operators position, 
     * 
     * true  : like i++
     * false : like ++i;
     */
    private boolean post;
    
    protected AbstractCrement(ProgramBlock block, Crementable factor, String operator, boolean post) {
        super(block);
        this.factor = factor;
        this.operator = operator;
        this.post = post;
    }

    @Override
    public void loadToStack(ProgramBlock block) {
        execute();
    }

    @Override
    public void asArgument() {
        block.removeExe(this);
    }
    
    @Override
    protected void factorToStack() {
    	
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

    @Override
    protected void doExecute()
    {
    	Type type = resultClass.getType();
    	boolean asArgument = !block.getQueue().contains(this); 
    	
    	if(factor instanceof LocalVariable && 
    	           Type.INT_TYPE.equals(type))
        {
    		if(asArgument && post)
    			factor.loadToStack(block);
    		
            insnHelper.iinc(((LocalVariable)factor).getScopeLogicVar().getInitStartPos(), 
            		Operators.INCREMENT.equals(operator) ? 1 : -1);
            
            if(asArgument && !post)
            	factor.loadToStack(block);
        }
        else
        {
            AClass primitiveClass = AClassUtils.getPrimitiveAClass(resultClass);
            
            //factor load to stack
            factor.loadToStack(block);
            
            if(asArgument && post)
            	insnHelper.dup(type);
            
            //cast and unbox
            autoCast(resultClass, primitiveClass);
            
            //load 1 to stack 
            getIncreaseValue().loadToStack(block);

            //generate xadd/xsub for decrement
            if(Operators.INCREMENT.equals(operator))
            	insnHelper.add(type);
            else
                insnHelper.sub(type);
            
            //cast and box
            autoCast(primitiveClass, resultClass);
            
            if(asArgument && !post)
            	insnHelper.dup(type);
         
             //将栈内的值存储到全局变量中
            //判读如果是静态变量
            insnHelper.commonPutField((ExplicitVariable) factor);
        }
    }
    
    private Value getIncreaseValue(){
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

}

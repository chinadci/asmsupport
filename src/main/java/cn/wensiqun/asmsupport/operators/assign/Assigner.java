/**
 * 
 */
package cn.wensiqun.asmsupport.operators.assign;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.IVariable;
import cn.wensiqun.asmsupport.operators.AbstractOperator;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 */
public abstract class Assigner extends AbstractOperator implements Parameterized {

    protected Parameterized value;
    
    private IVariable var;
    
    /**该操作是否被其他操作引用 */
    protected boolean byOtherUsed;
    
    protected Assigner(ProgramBlock block, IVariable var, Parameterized value) {
        super(block);
        this.value = value;
        this.var = var;
    }

	@Override
	public String toString() {
		return var + " = " + value;
	}

	@Override
	protected void verifyArgument() {
		if(!AClassUtils.checkAssignable(value.getParamterizedType(), var.getParamterizedType())) {
			throw new IllegalArgumentException("Type mismatch: cannot convert from " + value.getParamterizedType() + " to " + var.getParamterizedType() + "");
		}
	}

	@Override
	protected void checkAsArgument() {
        value.asArgument();
	}

	/**
     * auto cast
     */
    protected void autoCast(){
        autoCast(value.getParamterizedType(), var.getParamterizedType(), false);
    }
    
    
    @Override
	public void loadToStack(ProgramBlock block) {
        this.execute();
		var.loadToStack(block);
	}

	@Override
	public AClass getParamterizedType() {
		return var.getParamterizedType();
	}

	@Override
	public void asArgument() {
        byOtherUsed = true;
        block.removeExe(this);
	}

	protected static class AssignerException extends RuntimeException{

        private static final long serialVersionUID = 5667984928208743770L;
        
        protected AssignerException(String msg){
            super(msg);
        } 
    }

}

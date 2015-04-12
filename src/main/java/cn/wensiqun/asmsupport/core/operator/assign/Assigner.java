/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package cn.wensiqun.asmsupport.core.operator.assign;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.definition.variable.IVariable;
import cn.wensiqun.asmsupport.core.operator.AbstractOperator;
import cn.wensiqun.asmsupport.core.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 */
public abstract class Assigner extends AbstractOperator implements InternalParameterized {

    protected InternalParameterized value;
    
    private IVariable var;
    
    /**该操作是否被其他操作引用 */
    protected boolean byOtherUsed;
    
    protected Assigner(ProgramBlockInternal block, IVariable var, InternalParameterized value) {
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
	public void loadToStack(ProgramBlockInternal block) {
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

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
package cn.wensiqun.asmsupport.standard.action;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.core.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.operator.assign.Assigner;

/**
 * 变量操作
 *
 */
public interface VariableAction {

    /**
     * Create a local variable with anonymous, this method equivalent to following code :
     * <p>
     * var("", {@link AClassFactory#getType(type)}, true, para)
     * </p>
     * @param type
     * @param para
     * @return
     */
    public LocalVariable var(Class<?> type, InternalParameterized para);

    /**
     * Create a local variable with anonymous, this method equivalent to following code :
     * <p>
     * var("", type, true, para)
     * </p>
     * @param type
     * @param para
     * @return
     */
    public LocalVariable var(AClass type, InternalParameterized para);

    /**
     * Create a local variable, this method equivalent to following code :
     * <p>
     * var(name, {@link AClassFactory#getType(type)}, false, para)
     * </p>
     * @param name
     * @param type
     * @param para
     * @return
     */
    public LocalVariable var(String name, Class<?> type, InternalParameterized para);
    
    /**
     * Create a local variable, this method equivalent to following code :
     * <p>
     * var(name, type, false, para)
     * </p>
     * @param name
     * @param type
     * @param para
     * @return
     */
    public LocalVariable var(String name, AClass type, InternalParameterized para);
    

    /**
     * Get field from current object. the method is same to call following :
     * 
     * <pre>
     * this_().field(name);
     * </pre>
     * 
     * @param name
     * @return
     */
    public GlobalVariable field(String name);
    
	/**
	 * assign a value to a variable. for exampel:
	 * java code:<br/>
	 * <pre>
	 * i = 10;
	 * </pre>
	 * asmsupport code:<br/>
	 * <pre>
	 * assign(i, getValue(10));
	 * </pre>
	 * @param variable
	 * @param val
	 * @return
	 */
	public Assigner assign(ExplicitVariable variable, InternalParameterized val);
}

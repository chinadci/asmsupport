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

import cn.wensiqun.asmsupport.core.operator.logical.LogicalAnd;
import cn.wensiqun.asmsupport.core.operator.logical.LogicalOr;
import cn.wensiqun.asmsupport.core.operator.logical.LogicalXor;
import cn.wensiqun.asmsupport.core.operator.logical.Not;
import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.standard.Parameterized;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalOr;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalXor;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitOr;


/**
 * 逻辑操作
 * 
 * @author wensiqun(at)163.com
 *
 */
public interface LogicalAction<_P extends Parameterized> {
    /**
     * 生成逻辑与操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">factor1 & factor2;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link LogicalAnd}
     */
    public ILogicalAnd<_P> logicalAnd(_P factor1, _P factor2);
    
    /**
     * 生成逻辑或操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">factor1 | factor2;</b>
     * </p>
     * 
     *  
     * @param factor1
     * @param factor2
     * @return {@link LogicalOr}
     */
    public ILogicalOr<_P> logicalOr(_P factor1, _P factor2);

    /**
     * 
     * 生成逻辑异或操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">factor1 ^ factor2;</b>
     * </p>
     *  
     * @param factor1
     * @param factor2
     * @return {@link LogicalXor}
     */
    public ILogicalXor<_P> logicalXor(_P factor1, _P factor2);
    
    /**
     * 
     * 生成条件与操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">factor1 && factor2;</b>
     * </p>
     * 
     * 
     * @param factor1
     * @param factor2
     * @return {@link ShortCircuitAnd}
     */
    public IShortCircuitAnd<_P> and(_P factor1, _P factor2, _P... otherFactors);
    
    /**
     * 
     * 生成条件或操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">factor1 || factor2;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link ShortCircuitOr}
     */
    public IShortCircuitOr<_P> or(_P factor1, _P factor2, _P... otherFactors);
    
    /**
     * 生成条件非操作指令,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">!factor;</b>
     * </p>
     * 
     * 
     * @param factor
     * @return {@link Not}
     */
    public Not no(_P factor);
}

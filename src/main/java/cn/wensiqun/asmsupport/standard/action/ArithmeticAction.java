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

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Addition;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Division;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Modulus;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Multiplication;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Subtraction;
import cn.wensiqun.asmsupport.standard.Parameterized;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IAdd;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IDiv;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IMod;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IMul;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.ISub;


/**
 * 生成算数运算
 *
 * @author wensiqun(at)163.com
 */
public interface ArithmeticAction<_P extends Parameterized> {
    
    /**
     * 生成加法操作指令例如：factor1 + factor2,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int count = <b style="color:#FF3300">factor1 + factor2;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link Addition}
     */
    public IAdd<_P> add(_P factor1, _P factor2);

    /**
     * 生成减法操作指令例如：factor1 - factor2,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int count = <b style="color:#FF3300">factor1 - factor12;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link Subtraction}
     */
    public ISub<_P> sub(_P factor1, _P factor2);
    
    /**
     * 生成乘法操作指令例如：factor1 * factor2,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int count = <b style="color:#FF3300">factor1 * factor12;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link Multiplication}
     */
    public IMul<_P> mul(_P factor1, _P factor2);
    
    /**
     * generate division instruction : factor1 / factor2
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int count = <b style="color:#FF3300">factor1 / factor12;</b>
     * </p>
     * 
     * @param factor1
     * @param factor2
     * @return {@link Division}
     */
    public IDiv<_P> div(_P factor1, _P factor2);
    
    /**
     * 
     * generate mod instruction : factor1 % factor2,对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int count = <b style="color:#FF3300">factor1 % factor12;</b>
     * </p>
     * 
     * 
     * @param factor1
     * @param factor2
     * @return {@link Modulus}
     */
    public IMod<_P> mod(_P factor1, _P factor2);
    
}

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
import cn.wensiqun.asmsupport.core.operator.Return;
import cn.wensiqun.asmsupport.core.operator.checkcast.CheckCast;
import cn.wensiqun.asmsupport.core.operator.numerical.posinegative.Negative;
import cn.wensiqun.asmsupport.core.operator.numerical.ternary.TernaryOperator;

/**
 * 
 * The all action.
 * 
 * @author wensiqun(at)163.com
 *
 */
public interface ActionSet<_IF, _While, _DoWhile, _ForEach, _Try, _Synchronized> extends ValueAction, AClassDefAction,
        KeywordAction, VariableAction, MethodInvokeAction, ArrayAction, ArithmeticAction, BitwiseAction, CrementAction,
        RelationalAction, LogicalAction, CreateBlockAction<_IF, _While, _DoWhile, _ForEach, _Try, _Synchronized> {

    /**
     * check cast object type, such as following code:
     * <p style="border:1px solid;width:300px;padding:10px;">
     * String cc1 = <b style="color:#FF3300">(String)cc;</b>
     * </p>
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:300px;padding:10px;">
     * checkcast(cc_para, AClassFactory.defType(String.class));
     * </p>
     * 
     * 
     * @param obj
     *            original object
     * @param to
     *            the type need to check cast.
     * @return {@link CheckCast}
     */
    public CheckCast checkcast(InternalParameterized obj, AClass to);

    /**
     * check cast object type, such as following code:
     * <p style="border:1px solid;width:300px;padding:10px;">
     * String cc1 = <b style="color:#FF3300">(String)cc;</b>
     * </p>
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:300px;padding:10px;">
     * checkcast(cc_para, AClassFactory.defType(String.class));
     * </p>
     * 
     * 
     * @param obj
     *            original object
     * @param to
     *            the type need to check cast.
     * @return
     */
    public CheckCast checkcast(InternalParameterized cc, Class<?> to);

    /**
     * the negative operator. following is example.
     * <p style="border:1px solid;width:300px;padding:10px;">
     * int negValue = <b style="color:#FF3300">-factor;</b>
     * </p>
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:300px;padding:10px;">
     * neg(factor);
     * </p>
     * 
     * 
     * @param factor
     *            must a number.
     * @return {@link Negative}
     */
    public Negative neg(InternalParameterized factor);

    /**
     * The ternary operator in java.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * String result = <b style="color:#FF3300">booleanValue ? "YES" : "NO"
     * ;</b>
     * </p>
     * 
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * ternary(booleanValueParameterized, Value.value("YES"),
     * Value.value("NO"));
     * </p>
     * 
     * 
     * @param exp1
     *            the expression result must be a boolean.
     * @param exp2
     * @param exp3
     * @return {@link TernaryOperator}
     */
    public TernaryOperator ternary(InternalParameterized exp1, InternalParameterized exp2, InternalParameterized exp3);

    /**
     * 
     * <p>
     * The string append operator. such as "A" + "B" + "C". if we want append
     * some string, can't use
     * {@link ArithmeticAction#add(InternalParameterized, InternalParameterized)} method, that
     * is different to write java code directly.
     * </p>
     * 
     * Following is the java sample.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * String sayHello = <b style="color:#FF3300">"hello " + "world";</b>
     * </p>
     * 
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * stradd(Value.value("hello "), Value.value("world"));
     * </p>
     * 
     * @param par1
     * @param pars
     * @return
     */
    public InternalParameterized stradd(InternalParameterized par1, InternalParameterized... pars);

    /**
     * Generate the instanceof instruction.
     * 
     * <p style="border:1px solid;width:500px;padding:10px;">
     * boolean isString = <b style="color:#FF3300">object instanceof String;</b>
     * </p>
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * instanceOf(object, AClassFactory.defType(String.class));
     * </p>
     * 
     * 
     * @param obj
     * @param type
     * @return {@link InternalParameterized}a boolean type Parameterized
     */
    public InternalParameterized instanceof_(InternalParameterized obj, AClass type);

    /**
     * Generate the instanceof instruction, the method is same as
     * {@link #instanceof_(InternalParameterized, AClass)}
     * 
     * @see #instanceof_(InternalParameterized, AClass)
     * @param obj
     * @param type
     * @return
     */
    public InternalParameterized instanceof_(InternalParameterized obj, Class<?> type);

    /**
     * Corresponding to break statement in loop.
     * 
     */
    public void break_();

    /**
     * Corresponding to continue statement in loop.
     * 
     */
    public void continue_();

    /**
     * Throw an exception.
     * 
     * <p style="border:1px solid;width:500px;padding:10px;">
     * <b style="color:#FF3300">throw new RuntimeException()</b>
     * </p>
     * 
     * Following code is the asmsupport code.
     * <p style="border:1px solid;width:500px;padding:10px;">
     * throwException(invokeConstructor(RuntimeException_ACLASS));
     * </p>
     * 
     * 
     * @param exception
     */
    public void throw_(InternalParameterized exception);

    /**
     * Corresponding to return statement with no return value.
     * 
     * @return {{@link Return}
     */
    public Return return_();

    /**
     * Corresponding to return statement with return value.
     * 
     * @param parame
     *            return value.
     * @return {{@link Return}
     * 
     */
    public Return return_(InternalParameterized parame);
}

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

import cn.wensiqun.asmsupport.core.clazz.ArrayClass;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLength;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLoader;
import cn.wensiqun.asmsupport.core.operator.array.ArrayStorer;
import cn.wensiqun.asmsupport.core.operator.array.ArrayValue;
import cn.wensiqun.asmsupport.standard.Parameterized;


/**
 * 数组操作
 *
 * @author wensiqun(at)163.com
 */
public interface ArrayAction<_P extends Parameterized> {
    
	/**
     * <p>
     * 根据传入的数组类型以及每一维度的长度创建一个空数组，对应下面的红色java代码
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:550px;padding:10px;">
     * <b style="color:#FF3300">new String[2][3][4]</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:550px;padding:10px;">
     * newArray(AClassFactory.getArrayClass(String[][][].class), Value.value(2), Value.value(3), Value.value(4));<br>
     * </p>
	 * 空间
	 * @param aClass 数组类型
	 * @param allocateDims 每一维度的长度
	 * @return {@link ArrayValue}
	 * @see ActionSet#arrayvar(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #newarray(ArrayClass, Object)
	 * @see #newarray(ArrayClass, Parameterized[])
	 * @see #newarray(ArrayClass, Parameterized[][])
	 * @see #newarray(ArrayClass, Parameterized[][][])
	 * @see #newarray(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue makeArray(final ArrayClass aClass, final _P... allocateDims);
	
	/**
	 * 
	 * @param arraytype
	 * @param dimensions
	 * @return
	 */
	public ArrayValue makeArray(Class<?> arraytype, final _P... dimensions);

	/**
	 * 
     * <p>
     * 根据传入数组类型以及每一个数组元素的值来创建一个数组，这个实际上是模拟编写java代码的时候我们可以在创建数组
     * 的同时为每一个元素赋值，对应下面的红色java代码
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:550px;padding:10px;">
     * <b style="color:#FF3300">new String[][]{{"00", "01"}, {"10", "11"}}</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <pre style="border:1px solid;width:550px;padding:10px;">
     * Parameterized[][] values = new Parameterized[][]{
     *     {Value.value("00"), Value.value("01")},
     *     {Value.value("10"), Value.value("11")}
     * };
     * newArrayWithValue(AClassFactory.getArrayClass(String[][].class), values);
     * </pre>
	 * 
	 * @param aClass 数组类型
	 * @param arrayObject 创建数组的初始值，这个值必须是一个Parameterized数组
	 * @return {@link ArrayValue}
	 * @see ActionSet#arrayvar(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #makeArray(ArrayClass, Parameterized...)
	 * @see #newarray(ArrayClass, Parameterized[])
	 * @see #newarray(ArrayClass, Parameterized[][])
	 * @see #newarray(ArrayClass, Parameterized[][][])
	 * @see #newarray(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue newarray(final ArrayClass aClass, final Object arrayObject);
	
	
	/**
	 * 
	 * @param type
	 * @param arrayObject
	 * @return
	 */
	public ArrayValue newarray(Class<?> type, Object arrayObject);
    
    /**
     * 
     * <p>
     * 根据传入的下标从数组中获取值,这里的数组是直接new出来的.对应下面的红色java代码
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * String value = <b style="color:#FF3300">{{"[0][0]","[0][1]"},{"[1][0]","[1][1]"}}[0][1]</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayLoad(values, Value.value(0), Value.value(1));<br>
     * </p>
     * 
     * 
     * 
     * @param arrayReference 数组值
     * @param pardim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param parDims 除第一维以外的所有维度下标
     * @return {@link ArrayLoader}
     */
    public ArrayLoader arrayLoad(_P arrayReference, _P pardim, _P... parDims);
    

    /**
     * 将值存储在数组的指定下标位置上,这里的数组是直接通过new操作获得的.对应下面的红色java代码<br>
     * 
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * <b style="color:#FF3300">{{"[0][0]","[0][1]"},{"[1][0]","[1][1]"}}[0][1]=100</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayStore(arrayValues, Value.value(100), Value.value(0), Value.value(1))
     * </p>
     * 
     * @param arrayReference 数组值
     * @param value 需要存入的值
     * @param dim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param dims 除第一维以外的所有维度下标
     * @return {@link ArrayStorer}
     */
    public ArrayStorer arrayStore(_P arrayReference, _P value, _P dim, _P... dims);
    
    
    /**
     * 获取数组长度，多维数组的时候可以传入下标获取指定某一子元素数组的下标，这里传入的数组是直接通过new操作获得的，对应于下面红色部分代码.
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">{{"[0][0]","[0][1]"},{"[1][0]","[1][1]"}}[0].length</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:300px;padding:10px;">
     * _arrayLength(values, Value.value(0))
     * </p>
     * 
     * 
     * @param arrayReference 数组值
     * @param dims 下标列表
     * @return {@link ArrayLength}
     */
    public ArrayLength arrayLength(_P arrayReference, _P... dims);
    

}

package cn.wensiqun.asmsupport.core.block.interfaces;

import cn.wensiqun.asmsupport.core.Parameterized;
import cn.wensiqun.asmsupport.core.clazz.ArrayClass;
import cn.wensiqun.asmsupport.core.definition.variable.IVariable;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLength;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLoader;
import cn.wensiqun.asmsupport.core.operator.array.ArrayStorer;
import cn.wensiqun.asmsupport.core.operator.array.ArrayValue;
import cn.wensiqun.asmsupport.core.operator.assign.Assigner;
import cn.wensiqun.asmsupport.core.operator.method.MethodInvoker;


/**
 * 数组操作
 *
 * @author wensiqun(at)gmail
 */
public interface ArrayOperator {
    

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
	 * 
	 * @param aClass 数组类型
	 * @param allocateDims 每一维度的长度
	 * @return {@link ArrayValue}
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Object)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue _newArray(final ArrayClass aClass, final Parameterized... allocateDims);
	

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
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArray(ArrayClass, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue _newArrayWithValue(final ArrayClass aClass, final Object arrayObject);
	
	
	/**
	 * <p>
	 * 这个方法其实底层就是调用{@link #_newArrayWithValue(ArrayClass, Object)}实现的，只是这里显式的说明了我创建的是一个一维数组，主要是为了便于开发，
	 * 使用方法对应于下面红色代码部分。
	 * </p>
	 * 
	 * <p style="border:1px solid;width:550px;padding:10px;">
     * <b style="color:#FF3300">new String[]{"00", "01"}</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <pre style="border:1px solid;width:550px;padding:10px;">
     * Parameterized[] values = new Parameterized[]{
     *     Value.value("00"), Value.value("01")
     * };
     * newArrayWithValue(AClassFactory.getArrayClass(String[].class), values);
     * </pre>
	 * 
	 * @param aClass 数组类型
	 * @param values 创建一维数组的初始值
	 * @return {@link ArrayValue}
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArray(ArrayClass, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Object)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[] values);

	/**
	 * <p>
	 * 这个方法其实底层就是调用{@link #_newArrayWithValue(ArrayClass, Object)}实现的，只是这里显式的说明了我创建的是一个二维数组，主要是为了便于开发，
	 * </p>
     *
	 * @param aClass 数组类型
	 * @param values 创建二维数组的初始值
	 * @return {@link ArrayValue}
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArray(ArrayClass, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Object)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][] values);

	/**
	 * <p>
	 * 这个方法其实底层就是调用{@link #_newArrayWithValue(ArrayClass, Object)}实现的，只是这里显式的说明了我创建的是一个三维数组，主要是为了便于开发，
	 * </p>
     *
	 * @param aClass 数组类型
	 * @param values 创建三维数组的初始值
	 * @return {@link ArrayValue}
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArray(ArrayClass, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Object)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][][])
	 */
	public ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][][] values);

	/**
	 * 
	 * <p>
	 * 这个方法其实底层就是调用{@link #_newArrayWithValue(ArrayClass, Object)}实现的，只是这里显式的说明了我创建的是一个四维数组，主要是为了便于开发，
	 * </p>
     *
	 * @param aClass 数组类型
	 * @param values 创建四维数组的初始值
	 * @return {@link ArrayValue}
	 * @see IBlock#_createArrayVariable(String, ArrayClass, Parameterized, Parameterized...)
	 * @see #_newArray(ArrayClass, Parameterized...)
	 * @see #_newArrayWithValue(ArrayClass, Object)
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][])
	 * @see #_newArrayWithValue(ArrayClass, Parameterized[][][])
	 */
	public ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][][][] values);
	
    
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
     * @see #_arrayLoad(IVariable, Parameterized, Parameterized...)
     * @see #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)
     * @see #_arrayLoad(Assigner, Parameterized, Parameterized...)
     */
    public ArrayLoader _arrayLoad(ArrayValue arrayReference, Parameterized pardim, Parameterized... parDims);
    
    
	
	/**
     * 根据传入的下标从数组中获取值,这里的数组是存储在一个变量中，对应下面的红色java代码
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * String value = <b style="color:#FF3300">values[0][1];</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:300px;padding:10px;">
     * _arrayLoad(values, Value.value(0), Value.value(1))
     * </p>
     * 
     * @param arrayReference 数组变量
     * @param pardim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param parDims 除第一维以外的所有维度下标
     * @return {@link ArrayLoader}
     * @see #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayValue, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)
     * @see #_arrayLoad(Assigner, Parameterized, Parameterized...)
     * 
     * 
     */
    public ArrayLoader _arrayLoad(IVariable arrayReference, Parameterized pardim, Parameterized... parDims);
    
    /**
     * <p>
     * 根据传入的下标从数组中获取值,这里的数组是直接通过方法调用获得的.对应下面的红色java代码
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * String value = <b style="color:#FF3300">getValues()[0][1];</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:300px;padding:10px;">
     * _arrayLoad(getValuesMethodInvoker, Value.value(0), Value.value(1))
     * </p>
     * 
     * 
     * @param arrayReference 方法调用操作
     * @param pardim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param parDims 除第一维以外的所有维度下标
     * @return {@link ArrayLoader}
     * @see #_arrayLoad(IVariable, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayValue, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)
     * @see #_arrayLoad(Assigner, Parameterized, Parameterized...)
     * 
     */
    public ArrayLoader _arrayLoad(MethodInvoker arrayReference, Parameterized pardim, Parameterized... parDims);
    
    /**
     * 
     * <p>
     * 根据传入的下标从数组中获取值,这里的数组是通过赋值操作获得的.对应下面的红色java代码
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * String[][] values = null;<br>
     * String value = <b style="color:#FF3300">(values=getValues())[0]</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayLoad(assignerOperator, Value.value(0), Value.value(1));<br>
     * </p>
     * 
     * @param arrayReference 赋值操作
     * @param pardim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param parDims 除第一维以外的所有维度下标
     * @return {@link ArrayLoader}
     * @see #_arrayLoad(IVariable, Parameterized, Parameterized...)
     * @see #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayValue, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)
     * 
     */
    public ArrayLoader _arrayLoad(Assigner arrayReference, Parameterized pardim, Parameterized... parDims);
    
    
    /**
     * <p>
     * 根据传入的下标从数组中获取值,这里的数组是从另一个数组中获得的，对于同一个数组其执行效果和其他的重载方法
     * {@link #_arrayLoad(IVariable, Parameterized, Parameterized...)},
     * {@link #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)},
     * {@link #_arrayLoad(ArrayValue, Parameterized, Parameterized...)},
     * {@link #_arrayLoad(Assigner, Parameterized, Parameterized...)}一样。
     * 仅仅只是语义上有所不同，当前方法表示<span style="color=#00FF00">“获取数组的值(这个值同样也是个数组)的值，”</span>，而其他重载方法可能表示的
     * 是<span style="color=#00FF00">“获取多维数组的某一个元素的值”</span>.
     * </p>
     * 
     * <p>
     * 下面是将这个方法与其他从在方法在使用上做的比较(红色部分)。
     * </p>
     * 
     * <p style="border:1px solid;width:700px;padding:10px;">
     * String value = <b style="color:#FF3300">{{"[0][0]","[0][1]"},{"[1][0]","[1][1]"}}[0][1]</b>;//{@link #_arrayLoad(ArrayValue, Parameterized, Parameterized...)}<br>
     * String value = <b style="color:#FF3300">values[0][1];</b>//{@link #_arrayLoad(IVariable, Parameterized, Parameterized...)}<br>
     * String value = <b style="color:#FF3300">getValues()[1][2];</b>//{@link #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)}<br>
     * String[][] values = null;<br>
     * String value = <b style="color:#FF3300">(values=getValues())[0]</b>;//{@link #_arrayLoad(Assigner, Parameterized, Parameterized...)}<br>
     * </p>
     * 
     * 上面红色部分对应其他重载方式的实现
     * <p style="border:1px solid;width:700px;padding:10px;">
     * _arrayLoad(values, Value.value(0), Value.value(1));<br>
     * _arrayLoad(values_variable, Value.value(0), Value.value(1));<br>
     * _arrayLoad(getValuesMethodInvoker, Value.value(0), Value.value(1));<br>
     * _arrayLoad(assignerOperator, Value.value(0), Value.value(1));<br>
     * </p>
     * 
     * 
     * 上面红色部分使用当前方法实现
     * 
     * <p style="border:1px solid;width:700px;padding:10px;">
     * _arrayLoad(arrayLoad(values, Value.value(0), Value.value(1));<br>
     * _arrayLoad(arrayLoad(values_variable, Value.value(0)), Value.value(1));<br>
     * _arrayLoad(arrayLoad(getValuesMethodInvoker, Value.value(0)), Value.value(1));<br>
     * _arrayLoad(arrayLoad(assignerOperator, Value.value(0)), Value.value(1));<br>
     * </p>
     * 
     * @param arrayReference ArrayLoader
     * @param pardim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param parDims 除第一维以外的所有维度下标
     * @return {@link ArrayLoader}
     * @see #_arrayLoad(IVariable, Parameterized, Parameterized...)
     * @see #_arrayLoad(MethodInvoker, Parameterized, Parameterized...)
     * @see #_arrayLoad(ArrayValue, Parameterized, Parameterized...)
     * @see #_arrayLoad(Assigner, Parameterized, Parameterized...)
     * 
     */
    public ArrayLoader _arrayLoad(ArrayLoader arrayReference, Parameterized pardim, Parameterized... parDims);
    
    

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
     * @see #_arrayStore(IVariable, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(MethodInvoker, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(Assigner, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(ArrayLoader, Parameterized, Parameterized, Parameterized...)
     */
    public ArrayStorer _arrayStore(ArrayValue arrayReference, Parameterized value, Parameterized dim, Parameterized... dims);
    
    
    /**
     * 将值存储在数组的指定下标位置上,这里的数组是存储在一个变量中.对应下面的红色java代码<br>
     * 
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * <b style="color:#FF3300">values[0][1] =  100;</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayStore(values, Value.value(100), Value.value(0), Value.value(1))
     * </p>
     * 
     * @param arrayReference 数组变量
     * @param value 需要存入的值
     * @param dim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param dims 除第一维以外的所有维度下标
     * @return {@link ArrayStorer}
     * @see #_arrayStore(ArrayValue, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(MethodInvoker, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(Assigner, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(ArrayLoader, Parameterized, Parameterized, Parameterized...)
     */
    public ArrayStorer _arrayStore(IVariable arrayReference, Parameterized value, Parameterized dim, Parameterized... dims);
    

    /**
     * 将值存储在数组的指定下标位置上,这里的数组是通过方法调用获得的.对应下面的红色java代码<br>
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * <b style="color:#FF3300">getValues()[0][1] = 100;</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayStore(methodInvoker, Value.value(100), Value.value(0), Value.value(1))
     * </p>
     * 
     * @param arrayReference 方法调用
     * @param value 需要存入的值
     * @param dim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param dims 除第一维以外的所有维度下标
     * @return {@link ArrayStorer}
     * @see #_arrayStore(ArrayValue, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(IVariable, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(Assigner, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(ArrayLoader, Parameterized, Parameterized, Parameterized...)
     */
    public ArrayStorer _arrayStore(MethodInvoker arrayReference, Parameterized value, Parameterized dim, Parameterized... dims);
    
    /**
     * 
     * 将值存储在数组的指定下标位置上,这里的数组是通过赋值操作获得的.对应下面的红色java代码<br>
     * 
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * int[] values = null;<br>
     * <b style="color:#FF3300">(values=getValues())[0][1] = 100</b>;
     * </p>

     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayStore(assigOperator, Value.value(100), Value.value(0), Value.value(100))
     * </p>
     * 
     * @param arrayReference 赋值操作
     * @param value 需要存入的值
     * @param dim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param dims 除第一维以外的所有维度下标
     * @return {@link ArrayStorer}
     * @see #_arrayStore(ArrayValue, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(IVariable, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(MethodInvoker, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(ArrayLoader, Parameterized, Parameterized, Parameterized...)
     */
    public ArrayStorer _arrayStore(Assigner arrayReference, Parameterized value, Parameterized dim, Parameterized... dims);
    
    
    /**
     * 将值存储在数组的指定下标位置上,这里的数组是从另一个数组中获得的.这个方法的第一个参数是{@link ArrayLoader}类型，其作用和
     * {@link #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)}方法的第一参数是一样的，这里就不举例说明，
     * 这个方法和其他重载的arrayStore方法不同之处仅仅只是语义上不同。当前方法表示<span style="color=#00FF00">
     * “将值保存到数组的值(这个值同样也是个数组)的某个下标上，”</span>，而其他重载方法可能表示的
     * 是<span style="color=#00FF00">“将值保存到多维数组的某一个元素的值”</span>.
     * 
     * @param arrayReference ArrayLoader
     * @param value 需要存入的值
     * @param dim 第一维的下标，由于获取数组元素操作至少需要一个下标，所以第一维下标和其他维的下标分为两个参数表示
     * @param dims 除第一维以外的所有维度下标
     * @return {@link ArrayStorer}
     * @see #_arrayStore(ArrayValue, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(IVariable, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(MethodInvoker, Parameterized, Parameterized, Parameterized...)
     * @see #_arrayStore(Assigner, Parameterized, Parameterized, Parameterized...)
     */
    public ArrayStorer _arrayStore(ArrayLoader arrayReference, Parameterized value, Parameterized dim, Parameterized... dims);

    
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
     * @see #_arrayLength(IVariable, Parameterized...)
     * @see #_arrayLength(MethodInvoker, Parameterized...)
     * @see #_arrayLength(Assigner, Parameterized...)
     * @see #_arrayLength(ArrayLoader, Parameterized...)
     */
    public ArrayLength _arrayLength(ArrayValue arrayReference, Parameterized... dims);
    
    /**
     * 
     * 获取数组长度，多维数组的时候可以传入下标获取指定某一子元素数组的下标，这里传入的数组是存储在某一个变量中的，对应于下面红色部分代码.
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">values[0].length</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:300px;padding:10px;">
     * _arrayLength(values, Value.value(0))
     * </p>
     * 
     * @param arrayReference 数组变量
     * @param dims 下标列表
     * @return {@link ArrayLength}
     * @see #_arrayLength(ArrayValue, Parameterized...)
     * @see #_arrayLength(MethodInvoker, Parameterized...)
     * @see #_arrayLength(Assigner, Parameterized...)
     * @see #_arrayLength(ArrayLoader, Parameterized...)
     */
    public ArrayLength _arrayLength(IVariable arrayReference, Parameterized... dims);
    
    /**
     * 
     * 获取数组长度，多维数组的时候可以传入下标获取指定某一子元素数组的下标，这里传入的数组是通过方法调用获得的，对应于下面红色部分代码.
     * 
     * <p style="border:1px solid;width:300px;padding:10px;">
     * <b style="color:#FF3300">getValues()[0].length</b>
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:300px;padding:10px;">
     * _arrayLength(methodInvoker, Value.value(0))
     * </p>
     * 
     * @param arrayReference 方法调用
     * @param dims 下标列表
     * @return {@link ArrayLength}
     * @see #_arrayLength(ArrayValue, Parameterized...)
     * @see #_arrayLength(IVariable, Parameterized...)
     * @see #_arrayLength(Assigner, Parameterized...)
     * @see #_arrayLength(ArrayLoader, Parameterized...)
     */
    public ArrayLength _arrayLength(MethodInvoker arrayReference, Parameterized... dims);
    

    /**
     * 
     * 获取数组长度，多维数组的时候可以传入下标获取指定某一子元素数组的下标，这里传入的数组是通过赋值操作获得的，对应于下面红色部分代码.
     * 
     * <p style="border:1px solid;width:400px;padding:10px;">
     * String[][] values = null;<br>
     * <b style="color:#FF3300">(values=getValues())[0].length</b>;
     * </p>
     * 
     * 上面红色部分对应的asmsupport代码
     * <p style="border:1px solid;width:400px;padding:10px;">
     * _arrayLength(assignerOperator, Value.value(0));<br>
     * </p>
     * 
     * @param arrayReference 赋值操作
     * @param dims 下标列表
     * @return {@link ArrayLength}
     * @see #_arrayLength(ArrayValue, Parameterized...)
     * @see #_arrayLength(IVariable, Parameterized...)
     * @see #_arrayLength(MethodInvoker, Parameterized...)
     * @see #_arrayLength(ArrayLoader, Parameterized...)
     */
    public ArrayLength _arrayLength(Assigner arrayReference, Parameterized... dims);
    
    /**
     * 
     * 获取数组长度，多维数组的时候可以传入下标获取指定某一子元素数组的下标,这里的数组是从另一个数组中获得的.这个方法的第一个参数是{@link ArrayLoader}类型，
     * 其作用和{@link #_arrayLoad(ArrayLoader, Parameterized, Parameterized...)}方法的第一参数是一样的，这里就不举例说明，
     * 这个方法和其他重载的arrayLength方法不同之处仅仅只是语义上不同。当前方法表示<span style="color=#00FF00">“获取数组的值(这个值同样也是
     * 个数组)的某个下标对应的数组元素的长度，”</span>，而其他重载方法可能表示的是<span style="color=#00FF00">“获取多维数组的某一个数组元素的长度”</span>.
     * 
     * @param arrayReference ArrayLoader
     * @param dims 下标列表
     * @return {@link ArrayLength}
     * @see #_arrayLength(ArrayValue, Parameterized...)
     * @see #_arrayLength(IVariable, Parameterized...)
     * @see #_arrayLength(MethodInvoker, Parameterized...)
     * @see #_arrayLength(Assigner, Parameterized...)
     */
    public ArrayLength _arrayLength(ArrayLoader arrayReference, Parameterized... dims);
    

}

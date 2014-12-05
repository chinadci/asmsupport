package cn.wensiqun.asmsupport.core.block.interfaces;

import cn.wensiqun.asmsupport.core.Parameterized;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.ArrayClass;
import cn.wensiqun.asmsupport.core.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.operator.assign.Assigner;

/**
 * 变量操作
 *
 */
public interface VariableOperator {

	/**
	 * create a local variable
	 * 
	 * @param name variable name.
	 * @param aClass variable type.
	 * @param anonymous true will not put the variable to "local variable table". in other world, the name will be invalid.
	 * @param para this variable initial value, set to null if you want the initial is null.
	 * @return the LocalVariable
	 */
	public LocalVariable _createVariable(final String name, final AClass aClass, boolean anonymous, final Parameterized para);
	
	/**
	 * 
	 * <p>创建数组变量，可分配数组空间大小</p>
	 * 
	 * <pre>
	 * _createArrayVariableWithAllocateDimension("array", AClassFactory.getArrayClass(String[][].class), false, null) --> String[][] array = null;
	 * _createArrayVariableWithAllocateDimension("array", AClassFactory.getArrayClass(String[][].class), false, Value.value(3)) --> String[][] array = new String[3][];
	 * _createArrayVariableWithAllocateDimension("array", AClassFactory.getArrayClass(String[][].class), false, Value.value(3), Value.value(2)) --> String[][] array = new String[3][2];
	 * <pre>
	 * 
	 * @param name   变量名
	 * @param aClass 数组类型
	 * @param anonymous 是否匿名
	 * @param allocateDim 预分配的数组空间
	 * @return
	 */
	public LocalVariable _createArrayVariableWithAllocateDimension(final String name, final ArrayClass aClass, boolean anonymous, Parameterized... allocateDim);

	/**
	 * 
	 * @param name
	 * @param aClass
	 * @param value
	 * @return
	 */
	public LocalVariable _createArrayVariable(final String name, final ArrayClass aClass, boolean anonymous, final Parameterized value);
	
	
	/**
	 * 
	 * @param name
	 * @param aClass
	 * @param parameterizedArray
	 * @return
	 */
	public LocalVariable _createArrayVariable(final String name, final ArrayClass aClass, boolean anonymous, final Object parameterizedArray);
	
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
	public Assigner _assign(ExplicitVariable variable, Parameterized val);
}

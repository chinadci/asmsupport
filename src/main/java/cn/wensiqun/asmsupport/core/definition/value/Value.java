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
package cn.wensiqun.asmsupport.core.definition.value;

import java.lang.reflect.Modifier;

import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.exception.ASMSupportException;
import cn.wensiqun.asmsupport.org.objectweb.asm.Type;

/**
 * 通常一些基本类型的常量值，String类型的值，null，Class类型，这些在编写java代码的时候都是直接可以获取的到，
 * 那么这些值在ASMSupport中我们都要将其封装成ASMSupport内部可识别的对象, 这个类就是对这种内部可识别对象的类型。
 * 
 * @author wensiqun(at)163.com
 * 
 */
public class Value implements IValue {

    private AClass cls;
    private Object value;

    /**
     * boolean值
     * 
     * @param value
     */
    private Value(Boolean value) {
        this.cls = AClassFactory.getType(boolean.class);
        setProperites(value);
    }

    /**
     * byte值
     * 
     * @param value
     */
    private Value(Byte value) {
        this.cls = AClassFactory.getType(byte.class);
        setProperites(value);
    }

    /**
     * short value
     * 
     * @param value
     */
    private Value(Short value) {
        this.cls = AClassFactory.getType(short.class);
        setProperites(value);
    }

    /**
     * char value
     * 
     * @param value
     */
    private Value(Character value) {
        this.cls = AClassFactory.getType(char.class);
        setProperites(value);
    }

    /**
     * int value
     * 
     * @param value
     */
    private Value(Integer value) {
        this.cls = AClassFactory.getType(int.class);
        setProperites(value);
    }

    /**
     * long value
     * 
     * @param value
     */
    private Value(Long value) {
        this.cls = AClassFactory.getType(long.class);
        setProperites(value);
    }

    /**
     * float value
     * 
     * @param value
     */
    private Value(Float value) {
        this.cls = AClassFactory.getType(float.class);
        setProperites(value);
    }

    /**
     * double value
     * 
     * @param value
     */
    private Value(Double value) {
        this.cls = AClassFactory.getType(double.class);
        setProperites(value);
    }

    /**
     * string value
     * 
     * @param value
     */
    private Value(String value) {
        this.cls = AClassFactory.getType(String.class);
        setProperites(value);
    }

    private Value() {
    }

    private Value(AClass aclass) {
        this.cls = AClassFactory.getType(Class.class);
        setProperites(aclass);
    }

    private void setProperites(Object value) {
        // this.type = aclass.getType();
        this.value = value;
    }

    /**
     * 传入AClass 根据AClass获取默认值
     * 
     * @param aclass
     * @return
     */
    public static Value defaultValue(AClass aclass) {

        if (aclass.getName().equals(int.class.getName())) {
            return new Value(0);

        } else if (aclass.getName().equals(short.class.getName())) {
            return new Value((short) 0);

        } else if (aclass.getName().equals(byte.class.getName())) {
            return new Value((byte) 0);

        } else if (aclass.getName().equals(boolean.class.getName())) {
            return new Value(false);

        } else if (aclass.getName().equals(long.class.getName())) {
            return new Value((long) 0);

        } else if (aclass.getName().equals(double.class.getName())) {
            return new Value(0d);

        } else if (aclass.getName().equals(char.class.getName())) {
            return new Value((char) 0);

        } else if (aclass.getName().equals(float.class.getName())) {
            return new Value(0f);

        } else {
            Value v = new Value();
            v.cls = aclass;
            v.value = null;
            return v;
        }
    }

    /**
     * Get {@code null} value
     * 
     * @param type
     * @return
     */
    public static Value getNullValue(Class<?> type) {
        return getNullValue(AClassFactory.getType(type));
    }

    /**
     * Get {@code null} value
     * 
     * @param type
     * @return
     */
    public static Value getNullValue(AClass type) {
        if (type.isPrimitive()) {
            throw new IllegalArgumentException("the type of null value cannot be primitive type!");
        }
        return defaultValue(type);
    }

    /**
     * Get {@code int} value
     * 
     * @param val
     * @return
     */
    public static Value value(Integer obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code short} value
     * 
     * @param val
     * @return
     */
    public static Value value(Short obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code byte} value
     * 
     * @param val
     * @return
     */
    public static Value value(Byte obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code boolean} value
     * 
     * @param val
     * @return
     */
    public static Value value(Boolean obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code long} value
     * 
     * @param val
     * @return
     */
    public static Value value(Long obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code double} value
     * 
     * @param val
     * @return
     */
    public static Value value(Double obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code char} value
     * 
     * @param val
     * @return
     */
    public static Value value(Character obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code float} value
     * 
     * @param val
     * @return
     */
    public static Value value(Float obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code class} value
     * 
     * @param val
     * @return
     */
    public static Value value(AClass obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code class} value
     * 
     * @param val
     * @return
     */
    public static Value value(Class<?> obj) {
        return value((Object) obj);
    }

    /**
     * Get {@code null} value
     * 
     * @param val
     * @return
     */
    public static Value value(String obj) {
        return value((Object) obj);
    }

    private static Value value(Object obj) {
        if (obj == null) {
            throw new NullPointerException(
                    "Cannot support null value for this method, use nullValue(AClass type) method to get null value if you want get null!");
        }
        if (obj instanceof Integer) {

            return new Value((Integer) obj);

        } else if (obj instanceof Short) {

            return new Value((Short) obj);

        } else if (obj instanceof Byte) {

            return new Value((Byte) obj);

        } else if (obj instanceof Boolean) {

            return new Value((Boolean) obj);

        } else if (obj instanceof Long) {

            return new Value((Long) obj);

        } else if (obj instanceof Double) {

            return new Value((Double) obj);

        } else if (obj instanceof Character) {

            return new Value((Character) obj);

        } else if (obj instanceof Float) {

            return new Value((Float) obj);

        } else if (obj instanceof AClass) {

            return new Value((AClass) obj);

        } else if (obj instanceof String) {

            return new Value((String) obj);

        } else if (obj instanceof Class) {

            return new Value(AClassFactory.getType((Class<?>) obj));

        }
        throw new ASMSupportException("Cannot support type " + obj.getClass() + " for this method!");
    }

    /**
     * get a Value object, according arguments
     * 
     * for examples:
     * 
     * number(AClassFactory.defType(double.class), 1) will return a Value object that value is
     * 1D, and type is Double
     * 
     * @param type
     * @param val
     * @return
     */
    public static Value number(AClass type, int val) {
        switch (type.getType().getSort()) {
        case Type.CHAR:
            return value((char) val);
        case Type.BYTE:
            return value((byte) val);
        case Type.SHORT:
            return value((short) val);
        case Type.INT:
            return value(val);
        case Type.FLOAT:
            return value((float) val);
        case Type.LONG:
            return value((long) val);
        case Type.DOUBLE:
            return value((double) val);
        }
        return null;
    }

    /**
     * convert current value to new type.
     * 
     * @param type
     */
    public void convert(AClass type) {
        convert(this, type);
    }

    /**
     * get converted value
     * 
     * @param type
     */
    public Value getConvert(AClass type) {
        Value newVal = value(value);
        convert(newVal, type);
        return newVal;
    }

    /**
     * convert value to new type
     * 
     * @param val
     * @param type
     */
    private void convert(Value val, AClass type) {
        if (cls.equals(type)) {
            return;
        }
        String valStr = value.toString();
        switch (type.getType().getSort()) {
        case Type.BOOLEAN:
            val.refactor(type, Boolean.parseBoolean(valStr));
            break;
        case Type.CHAR:
            val.refactor(type, (char) Integer.parseInt(valStr));
            break;
        case Type.BYTE:
            val.refactor(type, Byte.parseByte(valStr));
            break;
        case Type.SHORT:
            val.refactor(type, Short.parseShort(valStr));
            break;
        case Type.INT:
            val.refactor(type, Integer.parseInt(valStr));
            break;
        case Type.FLOAT:
            val.refactor(type, Float.parseFloat(valStr));
            break;
        case Type.LONG:
            val.refactor(type, Long.parseLong(valStr));
            break;
        case Type.DOUBLE:
            val.refactor(type, Double.parseDouble(valStr));
            break;
        }
    }

    /**
     * 
     * @param type
     * @param val
     */
    private void refactor(AClass type, Object val) {
        this.cls = type;
        this.setProperites(val);
    }

    /**
     * 
     * 
     * @param val
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int numberCompare(Value comp) {
        if (!isNumber())
            throw new ASMSupportException(this + " is not a number value.");
        if (!comp.isNumber())
            throw new ASMSupportException(comp + " is not a number value.");

        int compOrder = cls.getCastOrder() - comp.cls.getCastOrder();
        Object v1;
        Object v2;

        if (compOrder == 0) {
            v1 = value;
            v2 = comp.value;
        } else if (compOrder > 0) {
            v1 = value;
            v2 = comp.getConvert(cls).value;
        } else {
            v1 = getConvert(comp.cls).value;
            v2 = comp.value;
        }

        return ((Comparable) v1).compareTo(v2);
    }

    public boolean isNumber() {
        int castOrder = cls.getType().getSort();
        if (castOrder >= Type.BYTE && castOrder <= Type.DOUBLE) {
            return true;
        }
        return false;
    }

    public boolean isBoolean() {
        return cls.getType().getSort() == Type.SHORT;
    }

    @Override
    public void loadToStack(ProgramBlockInternal block) {
        if (value == null) {
            block.getInsnHelper().push(this.getType());
            return;
        }

        if (this.cls.getName().equals(int.class.getName())) {

            block.getInsnHelper().push((Integer) value);

        } else if (this.cls.getName().equals(short.class.getName())) {

            block.getInsnHelper().push((Short) value);

        } else if (this.cls.getName().equals(byte.class.getName())) {

            block.getInsnHelper().push((Byte) value);

        } else if (this.cls.getName().equals(boolean.class.getName())) {

            block.getInsnHelper().push((Boolean) value);

        } else if (this.cls.getName().equals(long.class.getName())) {

            block.getInsnHelper().push((Long) value);

        } else if (this.cls.getName().equals(double.class.getName())) {

            block.getInsnHelper().push((Double) value);

        } else if (this.cls.getName().equals(char.class.getName())) {

            block.getInsnHelper().push((Character) value);

        } else if (this.cls.getName().equals(float.class.getName())) {

            block.getInsnHelper().push((Float) value);

        } else if (this.cls.getName().equals(String.class.getName())) {

            block.getInsnHelper().push(value.toString());

        } else if (this.cls.getName().equals(Class.class.getName())) {

            block.getInsnHelper().pushClass(((AClass) value).getType());

        }
    }

    @Override
    public void asArgument() {
        // Do nothing
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (!(obj instanceof Value)) {
            return false;
        }

        Value objval = (Value) obj;
        AClass curtyp = getResultType();
        AClass objtyp = objval.getResultType();

        if (value == null && objval.value == null) {
            if (curtyp.isChildOrEqual(objtyp) || objtyp.isChildOrEqual(curtyp)) {
                return true;
            } else if (Modifier.isAbstract(curtyp.getModifiers()) && Modifier.isFinal(objtyp.getModifiers())) {
                return false;
            } else {
                return !((Modifier.isAbstract(objtyp.getModifiers()) && Modifier.isFinal(curtyp.getModifiers())));
            }
        } else if (value == null && objval.value != null || objval.value == null && value != null) {
            return false;
        } else {
            return value.equals(objval);
        }
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    public Type getType() {
        return this.cls.getType();
    }

    public Object getValue() {
        return value;
    }

    @Override
    public AClass getResultType() {
        return cls;
    }

    @Override
    public String toString() {
        return value + " [type:" + cls + "]";
    }
}

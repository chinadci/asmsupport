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
package cn.wensiqun.asmsupport.core.operator.numerical.crement;

import cn.wensiqun.asmsupport.core.Crementable;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.definition.value.Value;
import cn.wensiqun.asmsupport.core.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.operator.Operators;
import cn.wensiqun.asmsupport.core.operator.numerical.AbstractNumerical;
import cn.wensiqun.asmsupport.core.utils.AClassUtils;
import cn.wensiqun.asmsupport.org.objectweb.asm.Type;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractCrement extends AbstractNumerical {

    private Crementable factor;

    /**
     * indicate the operators position,
     * 
     * true : like i++ false : like ++i;
     */
    private boolean post;

    protected AbstractCrement(ProgramBlockInternal block, Crementable factor, String operator, boolean post) {
        super(block);
        this.factor = factor;
        this.operator = operator;
        this.post = post;
    }

    @Override
    public void loadToStack(ProgramBlockInternal block) {
        execute();
    }

    @Override
    public void asArgument() {
        block.removeExe(this);
    }

    @Override
    protected void factorToStack() {

    }

    @Override
    protected void initAdditionalProperties() {
        targetClass = factor.getResultType();
    }

    @Override
    protected void verifyArgument() {
        AClass fatCls = factor.getResultType();
        if (!AClassUtils.isArithmetical(fatCls)) {
            throw new ArithmeticException("cannot execute arithmetic operator whit " + fatCls);
        }
    }

    @Override
    protected void checkAsArgument() {
        factor.asArgument();
    }

    @Override
    protected void doExecute() {
        Type type = targetClass.getType();
        boolean asArgument = !block.getQueue().contains(this);

        if (factor instanceof LocalVariable && Type.INT_TYPE.equals(targetClass.getType())) {
            if (asArgument && post)
                factor.loadToStack(block);

            insnHelper.iinc(((LocalVariable) factor).getScopeLogicVar().getInitStartPos(),
                    Operators.INCREMENT.equals(operator) ? 1 : -1);

            if (asArgument && !post)
                factor.loadToStack(block);
        } else {
            AClass primitiveClass = AClassUtils.getPrimitiveAClass(targetClass);
            Type primitiveType = primitiveClass.getType();

            // factor load to stack
            factor.loadToStack(block);

            if (asArgument && post)
                insnHelper.dup(type);

            // unbox
            autoCast(targetClass, primitiveClass, true);

            // load 1 to stack
            getIncreaseValue().loadToStack(block);

            // generate xadd/xsub for decrement
            if (Operators.INCREMENT.equals(operator))
                insnHelper.add(primitiveType);
            else
                insnHelper.sub(primitiveType);

            // box and cast
            autoCast(primitiveType.getSort() <= Type.INT ? AClassFactory.getType(int.class) : primitiveClass, targetClass, true);

            if (asArgument && !post)
                insnHelper.dup(type);

            // 将栈内的值存储到全局变量中
            // 判读如果是静态变量
            insnHelper.commonPutField((ExplicitVariable) factor);
        }
    }

    private Value getIncreaseValue() {
        AClass type = factor.getResultType();
        if (type.equals(AClassFactory.getType(double.class)) || type.equals(AClassFactory.getType(Double.class))) {
            return Value.value(1d);
        } else if (type.equals(AClassFactory.getType(float.class)) || type.equals(AClassFactory.getType(Float.class))) {
            return Value.value(1f);
        } else if (type.equals(AClassFactory.getType(long.class)) || type.equals(AClassFactory.getType(Long.class))) {
            return Value.value(1L);
        } else {
            return Value.value(1);
        }
    }

}

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
package cn.wensiqun.asmsupport.core.block;

import cn.wensiqun.asmsupport.core.Crementable;
import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.asm.InstructionHelper;
import cn.wensiqun.asmsupport.core.block.control.condition.IFInternal;
import cn.wensiqun.asmsupport.core.block.control.exception.ExceptionSerialBlock;
import cn.wensiqun.asmsupport.core.block.control.exception.TryInternal;
import cn.wensiqun.asmsupport.core.block.control.loop.DoWhileInternal;
import cn.wensiqun.asmsupport.core.block.control.loop.ForEachInternal;
import cn.wensiqun.asmsupport.core.block.control.loop.Loop;
import cn.wensiqun.asmsupport.core.block.control.loop.WhileInternal;
import cn.wensiqun.asmsupport.core.block.method.AbstractMethodBody;
import cn.wensiqun.asmsupport.core.block.sync.SynchronizedInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.clazz.ArrayClass;
import cn.wensiqun.asmsupport.core.clazz.NewMemberClass;
import cn.wensiqun.asmsupport.core.definition.method.AMethod;
import cn.wensiqun.asmsupport.core.definition.value.Value;
import cn.wensiqun.asmsupport.core.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.core.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.NonStaticGlobalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.StaticGlobalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.SuperVariable;
import cn.wensiqun.asmsupport.core.definition.variable.ThisVariable;
import cn.wensiqun.asmsupport.core.definition.variable.meta.LocalVariableMeta;
import cn.wensiqun.asmsupport.core.exception.ASMSupportException;
import cn.wensiqun.asmsupport.core.exception.MethodInvokeException;
import cn.wensiqun.asmsupport.core.exception.VerifyErrorException;
import cn.wensiqun.asmsupport.core.operator.BlockEndFlag;
import cn.wensiqun.asmsupport.core.operator.InstanceofOperator;
import cn.wensiqun.asmsupport.core.operator.Return;
import cn.wensiqun.asmsupport.core.operator.StringAppender;
import cn.wensiqun.asmsupport.core.operator.Throw;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLength;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLoader;
import cn.wensiqun.asmsupport.core.operator.array.ArrayStorer;
import cn.wensiqun.asmsupport.core.operator.array.ArrayValue;
import cn.wensiqun.asmsupport.core.operator.asmdirect.GOTO;
import cn.wensiqun.asmsupport.core.operator.assign.Assigner;
import cn.wensiqun.asmsupport.core.operator.assign.LocalVariableAssigner;
import cn.wensiqun.asmsupport.core.operator.assign.NonStaticGlobalVariableAssigner;
import cn.wensiqun.asmsupport.core.operator.assign.StaticGlobalVariableAssigner;
import cn.wensiqun.asmsupport.core.operator.checkcast.CheckCast;
import cn.wensiqun.asmsupport.core.operator.logical.LogicalAnd;
import cn.wensiqun.asmsupport.core.operator.logical.LogicalOr;
import cn.wensiqun.asmsupport.core.operator.logical.LogicalXor;
import cn.wensiqun.asmsupport.core.operator.logical.Not;
import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.core.operator.method.CommonMethodInvoker;
import cn.wensiqun.asmsupport.core.operator.method.ConstructorInvoker;
import cn.wensiqun.asmsupport.core.operator.method.MethodInvoker;
import cn.wensiqun.asmsupport.core.operator.method.StaticMethodInvoker;
import cn.wensiqun.asmsupport.core.operator.numerical.OperatorFactory;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Addition;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Division;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Modulus;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Multiplication;
import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Subtraction;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.BitAnd;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.BitOr;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.BitXor;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.Reverse;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.ShiftLeft;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.ShiftRight;
import cn.wensiqun.asmsupport.core.operator.numerical.bit.UnsignedShiftRight;
import cn.wensiqun.asmsupport.core.operator.numerical.crement.PostposeDecrment;
import cn.wensiqun.asmsupport.core.operator.numerical.crement.PostposeIncrment;
import cn.wensiqun.asmsupport.core.operator.numerical.crement.PreposeDecrment;
import cn.wensiqun.asmsupport.core.operator.numerical.crement.PreposeIncrment;
import cn.wensiqun.asmsupport.core.operator.numerical.posinegative.Negative;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.Equal;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.GreaterEqual;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.GreaterThan;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.LessEqual;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.LessThan;
import cn.wensiqun.asmsupport.core.operator.numerical.relational.NotEqual;
import cn.wensiqun.asmsupport.core.operator.numerical.ternary.TernaryOperator;
import cn.wensiqun.asmsupport.core.operator.numerical.variable.LocalVariableCreator;
import cn.wensiqun.asmsupport.core.utils.ASConstant;
import cn.wensiqun.asmsupport.core.utils.common.ThrowExceptionContainer;
import cn.wensiqun.asmsupport.core.utils.lang.ArrayUtils;
import cn.wensiqun.asmsupport.core.utils.lang.StringUtils;
import cn.wensiqun.asmsupport.core.utils.memory.Scope;
import cn.wensiqun.asmsupport.core.utils.memory.ScopeLogicVariable;
import cn.wensiqun.asmsupport.org.objectweb.asm.Label;
import cn.wensiqun.asmsupport.org.objectweb.asm.Type;
import cn.wensiqun.asmsupport.standard.action.ActionSet;

/**
 * 
 * 
 * 
 * @author wensiqun(at)163.com
 * 
 */
public abstract class ProgramBlockInternal extends AbstractBlockInternal implements
        ActionSet<InternalParameterized, IFInternal, WhileInternal, DoWhileInternal, ForEachInternal, TryInternal, SynchronizedInternal> {

    /** 执行Block, 通过当前Block所创建的操作，实际是executeBlock的代理 */
    private ProgramBlockInternal executor = this;

    private ProgramBlockInternal parent;

    private Scope scope;

    protected InstructionHelper insnHelper;

    private ThrowExceptionContainer throwExceptions;

    /** 当前block是否已经返回 或者已经抛出异常了 */
    private boolean finish = false;

    /* <<<<<<<<<<<<<<<<<<< Getter Setter <<<<<<<<<<<<<<<<<<<<<<<< */

    public void setExecutor(ProgramBlockInternal exeBlock) {
        executor = exeBlock;
    }

    protected ProgramBlockInternal getExecutor() {
        return executor;
    }

    public ThrowExceptionContainer getThrowExceptions() {
        return throwExceptions;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /* >>>>>>>>>>>>>>>>>> Getter Setter >>>>>>>>>>>>>>>>>>>>>>> */

    public ProgramBlockInternal getParent() {
        return parent;
    }

    /**
     * 添加抛出的异常到方法签名中
     * 
     * @param exception
     */
    public void addException(AClass exception) {
        if (throwExceptions == null) {
            throwExceptions = new ThrowExceptionContainer();
        }
        throwExceptions.add(exception);
    }

    public void removeException(AClass exception) {
        if (throwExceptions != null) {
            throwExceptions.remove(exception);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ProgramBlockInternal) {
            return this.scope == ((ProgramBlockInternal) obj).scope;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return scope.hashCode();
    }

    /**
     * 获取当前程序块的克隆拷贝
     * 
     * @return
     */
    public ProgramBlockInternal getCopy() {
        try {
            return (ProgramBlockInternal) clone();
        } catch (CloneNotSupportedException e) {
            throw new ASMSupportException(e);
        }
    }

    /**
     * 克隆当前的程序块的执行队列到给定程序块执行队列中
     * 
     * @param targetBlock
     *            克隆至此
     */
    public void generateTo(ProgramBlockInternal targetBlock) {
        ProgramBlockInternal clone = getCopy();
        clone.setExecutor(targetBlock);
        clone.generate();
        // just trigger if the last is SerialBlock in the queue of cloneTo
        OperatorFactory.newOperator(BlockEndFlag.class, new Class[] { ProgramBlockInternal.class }, targetBlock);
    }

    protected void init() {
    };

    /**
     * override this method if want create a new block 生成操作到执行队列中去。
     */
    public abstract void generate();

    @Override
    public void prepare() {
        init();
        scope.getStart().setName(this.getClass().toString() + " start");
        scope.getEnd().setName(this.getClass().toString() + " end");

        generate();

        // just trigger if the last is SerialBlock in the queue
        OperatorFactory.newOperator(BlockEndFlag.class, new Class[] { ProgramBlockInternal.class }, getExecutor());
    }

    @Override
    public final void execute() {
        getInsnHelper().mark(scope.getStart());
        // getInsnHelper().nop();
        doExecute();
        getInsnHelper().mark(scope.getEnd());
        // getInsnHelper().nop();
    }

    protected abstract void doExecute();

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return this.scope;
    }

    public Label getStart() {
        return scope.getStart();
    }

    public Label getEnd() {
        return scope.getEnd();
    }

    public void setInsnHelper(InstructionHelper insnHelper) {
        this.insnHelper = insnHelper;
    }

    public void setParent(ProgramBlockInternal block) {
        parent = block;
        setInsnHelper(block.insnHelper);
        setScope(new Scope(getMethod().getLocals(), block.getScope()));
    }

    public AMethod getMethod() {
        return insnHelper.getMethod();
    }

    public LocalVariable[] getMethodArguments() {
        return getMethod().getArguments();
    }

    protected AbstractMethodBody getMethodBody() {
        return getMethod().getMethodBody();
    }

    /**
     * get current method owner.
     * 
     * @return
     */
    public NewMemberClass getMethodOwner() {
        return getMethod().getMethodOwner();
    }

    /**
     * Get the {@link InstructionHelper}
     * 
     * @return
     */
    public InstructionHelper getInsnHelper() {
        return insnHelper;
    }

    // *******************************************************************************************//
    // Variable Operator //
    // *******************************************************************************************//
    private final LocalVariable createOnlyVariable(final AClass aClass, final String name, boolean anonymous) {
        if (!anonymous && StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("variable must be non-null if 'anonymous' is false");
        }

        LocalVariableMeta lve = new LocalVariableMeta(
                anonymous || getMethod().isCreatingImplicitFinally() ? "anonymous" : name, 0, aClass);

        LocalVariableCreator lvc = OperatorFactory.newOperator(LocalVariableCreator.class, new Class<?>[] {
                ProgramBlockInternal.class, String.class, Type.class, Type.class }, getExecutor(), anonymous ? null
                : name, aClass.getType(), aClass.getType());
        ScopeLogicVariable slv = lvc.getScopeLogicVariable();
        slv.setCompileOrder(getMethod().nextInsNumber());
        LocalVariable lv = new LocalVariable(lve);
        lv.setScopeLogicVar(slv);
        return lv;
    }

    protected final LocalVariable getLocalAnonymousVariableModel(final AClass aClass) {
        return createOnlyVariable(aClass, "anonymous", true);
    }

    protected final LocalVariable getLocalVariableModel(final String name, final AClass aClass) {
        return createOnlyVariable(aClass, name, false);
    }

    @Override
    public LocalVariable var(String name, Class<?> type, InternalParameterized para) {
        return var(name, AClassFactory.defType(type), false, para);
    }

    @Override
    public LocalVariable var(Class<?> type, InternalParameterized para) {
        return var("", AClassFactory.defType(type), true, para);
    }

    @Override
    public LocalVariable var(String name, AClass type, InternalParameterized para) {
        return var(name, type, false, para);
    }

    @Override
    public LocalVariable var(AClass type, InternalParameterized para) {
        return var("", type, true, para);
    }

    @Override
    public final LocalVariable var(final String name, final AClass aClass, boolean anonymous, final InternalParameterized para) {
        if (aClass.isArray()) {
            throw new IllegalArgumentException(aClass
                    + " is Array type exchange to arrayvar to create the array variable");
        }
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if (para == null) {
            assign(lv, aClass.getDefaultValue());
        } else {
            assign(lv, para);
        }
        return lv;
    }

    @Override
    public final LocalVariable arrayvar2dim(final String name, final ArrayClass aClass, boolean anonymous,
            InternalParameterized... allocateDim) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if (allocateDim == null) {
            assign(lv, aClass.getDefaultValue());
        } else {
            assign(lv, makeArray(aClass, allocateDim));
        }
        return lv;
    }

    @Override
    public LocalVariable arrayvar2dim(String name, ArrayClass type, InternalParameterized... dims) {
        return this.arrayvar2dim(name, type, false, dims);
    }

    @Override
    public LocalVariable arrayvar2dim(String name, Class<?> type, InternalParameterized... dims) {
        return this.arrayvar2dim(name, defArrayType(type), false, dims);
    }

    @Override
    public LocalVariable arrayvar2dim(ArrayClass type, InternalParameterized... dims) {
        return this.arrayvar2dim("", type, true, dims);
    }

    @Override
    public LocalVariable arrayvar2dim(Class<?> arrayType, InternalParameterized... dims) {
        return this.arrayvar2dim("", defArrayType(arrayType), true, dims);
    }

    @Override
    public final LocalVariable arrayvar(final String name, final ArrayClass aClass, boolean anonymous,
            InternalParameterized value) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if (value == null) {
            assign(lv, aClass.getDefaultValue());
        } else {
            assign(lv, value);
        }
        return lv;
    }

    @Override
    public LocalVariable arrayvar(String name, ArrayClass type, InternalParameterized value) {
        return this.arrayvar(name, type, false, value);
    }

    @Override
    public LocalVariable arrayvar(String name, Class<?> type, InternalParameterized value) {
        return this.arrayvar(name, defArrayType(type), false, value);
    }

    @Override
    public LocalVariable arrayvar(ArrayClass type, InternalParameterized value) {
        return this.arrayvar("", type, true, value);
    }

    @Override
    public LocalVariable arrayvar(Class<?> type, InternalParameterized value) {
        return this.arrayvar("", defArrayType(type), true, value);
    }

    @Override
    public LocalVariable arrayvar(String name, ArrayClass aClass, boolean anonymous, Object parameterizedArray) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if (parameterizedArray == null) {
            assign(lv, aClass.getDefaultValue());
        } else {
            assign(lv, getExecutor().newarray(aClass, parameterizedArray));
        }
        return lv;
    }

    @Override
    public LocalVariable arrayvar(String name, ArrayClass type, Object parameterizedArray) {
        return this.arrayvar(name, type, false, parameterizedArray);
    }

    @Override
    public LocalVariable arrayvar(String name, Class<?> type, Object parameterizedArray) {
        return this.arrayvar(name, defArrayType(type), false, parameterizedArray);
    }

    @Override
    public LocalVariable arrayvar(ArrayClass type, Object parameterizedArray) {
        return this.arrayvar("", type, true, parameterizedArray);
    }

    @Override
    public LocalVariable arrayvar(Class<?> type, Object parameterizedArray) {
        return this.arrayvar("", defArrayType(type), true, parameterizedArray);
    }

    @Override
    public final Assigner assign(ExplicitVariable variable, InternalParameterized val) {
        if (variable instanceof LocalVariable) {
            return OperatorFactory.newOperator(LocalVariableAssigner.class, new Class<?>[] {
                    ProgramBlockInternal.class, LocalVariable.class, InternalParameterized.class }, getExecutor(), variable,
                    val);
        } else if (variable instanceof StaticGlobalVariable) {
            return OperatorFactory.newOperator(StaticGlobalVariableAssigner.class, new Class<?>[] {
                    ProgramBlockInternal.class, StaticGlobalVariable.class, InternalParameterized.class }, getExecutor(),
                    variable, val);
        } else if (variable instanceof NonStaticGlobalVariable) {
            return OperatorFactory.newOperator(NonStaticGlobalVariableAssigner.class, new Class<?>[] {
                    ProgramBlockInternal.class, NonStaticGlobalVariable.class, InternalParameterized.class }, getExecutor(),
                    variable, val);
        }
        return null;
    }

    // *******************************************************************************************//
    // Value Operator //
    // *******************************************************************************************//

    @Override
    public final ArrayValue makeArray(final ArrayClass aClass, final InternalParameterized... allocateDims) {
        return OperatorFactory.newOperator(ArrayValue.class, new Class<?>[] { ProgramBlockInternal.class,
                ArrayClass.class, InternalParameterized[].class }, getExecutor(), aClass, allocateDims);
    }

    @Override
    public ArrayValue makeArray(Class<?> arraytype, InternalParameterized... dimensions) {
        return makeArray(defArrayType(arraytype), dimensions);
    }

    @Override
    public final ArrayValue newarray(final ArrayClass aClass, final Object arrayObject) {
        return OperatorFactory.newOperator(ArrayValue.class, new Class<?>[] { ProgramBlockInternal.class,
                ArrayClass.class, Object.class }, getExecutor(), aClass, arrayObject);
    }

    @Override
    public ArrayValue newarray(Class<?> type, Object arrayObject) {
        return newarray(defArrayType(type), arrayObject);
    }

    // *******************************************************************************************//
    // Array Operator //
    // *******************************************************************************************//

    @Override
    public final ArrayLoader arrayLoad(InternalParameterized arrayReference, InternalParameterized pardim, InternalParameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class, InternalParameterized[].class }, getExecutor(), arrayReference,
                pardim, parDims);
    }

    @Override
    public final ArrayStorer arrayStore(InternalParameterized arrayReference, InternalParameterized value, InternalParameterized dim,
            InternalParameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class, InternalParameterized.class, InternalParameterized[].class }, getExecutor(),
                arrayReference, value, dim, dims);
    }

    @Override
    public final ArrayLength arrayLength(InternalParameterized arrayReference, InternalParameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized[].class }, getExecutor(), arrayReference, dims);
    }

    // *******************************************************************************************//
    // Check Cast //
    // *******************************************************************************************//

    @Override
    public final CheckCast checkcast(InternalParameterized cc, AClass to) {
        if (to.isPrimitive()) {
            throw new IllegalArgumentException("Cannot check cast to type " + to + " from " + cc.getParamterizedType());
        }
        return OperatorFactory.newOperator(CheckCast.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, AClass.class }, getExecutor(), cc, to);
    }

    @Override
    public final CheckCast checkcast(InternalParameterized cc, Class<?> to) {
        return checkcast(cc, AClassFactory.defType(to));
    }

    // *******************************************************************************************//
    // Arithmetic Operator //
    // *******************************************************************************************//

    @Override
    public final Addition add(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Addition.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Subtraction sub(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Subtraction.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Multiplication mul(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Multiplication.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Division div(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Division.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Modulus mod(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Modulus.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Negative neg(InternalParameterized factor) {
        return OperatorFactory.newOperator(Negative.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class }, getExecutor(), factor);
    }

    // *******************************************************************************************//
    // Bit Operator //
    // *******************************************************************************************//

    @Override
    public final Reverse reverse(InternalParameterized factor) {
        return OperatorFactory.newOperator(Reverse.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class }, getExecutor(), factor);
    }

    @Override
    public final BitAnd band(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(BitAnd.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final BitOr bor(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(BitOr.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final BitXor bxor(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(BitXor.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final ShiftLeft shl(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(ShiftLeft.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    public final ShiftRight shr(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(ShiftRight.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final UnsignedShiftRight ushr(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(UnsignedShiftRight.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    // *******************************************************************************************//
    // Decrement or Increment Operator //
    // *******************************************************************************************//

    @Override
    public final PreposeDecrment predec(InternalParameterized crement) {
        return OperatorFactory.newOperator(PreposeDecrment.class, new Class<?>[] { ProgramBlockInternal.class,
                Crementable.class }, getExecutor(), crement);
    }

    @Override
    public final PostposeDecrment postdec(InternalParameterized crement) {
        return OperatorFactory.newOperator(PostposeDecrment.class, new Class<?>[] { ProgramBlockInternal.class,
                Crementable.class }, getExecutor(), crement);
    }

    @Override
    public final PreposeIncrment preinc(InternalParameterized crement) {
        return OperatorFactory.newOperator(PreposeIncrment.class, new Class<?>[] { ProgramBlockInternal.class,
                Crementable.class }, getExecutor(), crement);
    }

    @Override
    public final PostposeIncrment postinc(InternalParameterized crement) {
        return OperatorFactory.newOperator(PostposeIncrment.class, new Class<?>[] { ProgramBlockInternal.class,
                Crementable.class }, getExecutor(), crement);
    }

    // *******************************************************************************************//
    // Relational Operator //
    // *******************************************************************************************//

    @Override
    public final GreaterThan gt(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(GreaterThan.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final GreaterEqual ge(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(GreaterEqual.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final LessThan lt(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(LessThan.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final LessEqual le(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(LessEqual.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final Equal eq(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(Equal.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final NotEqual ne(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(NotEqual.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    // *******************************************************************************************//
    // Logic Operator //
    // *******************************************************************************************//

    @Override
    public final LogicalAnd logicalAnd(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(LogicalAnd.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalOr logicalOr(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(LogicalOr.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalXor logicalXor(InternalParameterized factor1, InternalParameterized factor2) {
        return OperatorFactory.newOperator(LogicalXor.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class }, getExecutor(), factor1, factor2);
    }

    @Override
    public final ShortCircuitAnd and(InternalParameterized factor1, InternalParameterized factor2, InternalParameterized... otherFactors) {
        ShortCircuitAnd sca = OperatorFactory
                .newOperator(ShortCircuitAnd.class, new Class<?>[] { ProgramBlockInternal.class, InternalParameterized.class,
                        InternalParameterized.class }, getExecutor(), factor1, factor2);
        if (ArrayUtils.isNotEmpty(otherFactors)) {
            for (InternalParameterized factor : otherFactors) {
                sca = OperatorFactory.newOperator(ShortCircuitAnd.class, new Class<?>[] { ProgramBlockInternal.class,
                        InternalParameterized.class, InternalParameterized.class }, getExecutor(), sca, factor);
            }
        }
        return sca;
    }

    @Override
    public final ShortCircuitOr or(InternalParameterized factor1, InternalParameterized factor2, InternalParameterized... otherFactors) {
        ShortCircuitOr sco = OperatorFactory
                .newOperator(ShortCircuitOr.class, new Class<?>[] { ProgramBlockInternal.class, InternalParameterized.class,
                        InternalParameterized.class }, getExecutor(), factor1, factor2);
        if (ArrayUtils.isNotEmpty(otherFactors)) {
            for (InternalParameterized factor : otherFactors) {
                sco = OperatorFactory.newOperator(ShortCircuitOr.class, new Class<?>[] { ProgramBlockInternal.class,
                        InternalParameterized.class, InternalParameterized.class }, getExecutor(), sco, factor);
            }
        }
        return sco;
    }

    @Override
    public final Not no(InternalParameterized factor) {
        return OperatorFactory.newOperator(Not.class,
                new Class<?>[] { ProgramBlockInternal.class, InternalParameterized.class }, getExecutor(), factor);
    }

    @Override
    public final TernaryOperator ternary(InternalParameterized exp1, InternalParameterized exp2, InternalParameterized exp3) {
        return OperatorFactory.newOperator(TernaryOperator.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized.class, InternalParameterized.class }, getExecutor(), exp1, exp2, exp3);
    }

    // *******************************************************************************************//
    // String Operator //
    // *******************************************************************************************//

    @Override
    public final InternalParameterized stradd(InternalParameterized par1, InternalParameterized... pars) {
        return OperatorFactory.newOperator(StringAppender.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, InternalParameterized[].class }, getExecutor(), par1, pars);
    }

    // *******************************************************************************************//
    // instanceof Operator //
    // *******************************************************************************************//

    @Override
    public final InternalParameterized instanceof_(InternalParameterized obj, AClass type) {
        return OperatorFactory.newOperator(InstanceofOperator.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, AClass.class }, getExecutor(), obj, type);
    }

    @Override
    public final InternalParameterized instanceof_(InternalParameterized obj, Class<?> type) {
        return this.instanceof_(obj, defType(type));
    }

    // *******************************************************************************************//
    // method invoke Operator //
    // *******************************************************************************************//

    @Override
    public final MethodInvoker call(InternalParameterized caller, String methodName, InternalParameterized... arguments) {
        return OperatorFactory.newOperator(CommonMethodInvoker.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class, String.class, InternalParameterized[].class }, getExecutor(), caller, methodName,
                arguments);
    }

    @Override
    public MethodInvoker call(String methodName, InternalParameterized... args) {
        return call(this_(), methodName, args);
    }

    protected final void invokeVerify(AClass a) {
        if (a.isInterface()) {
            throw new MethodInvokeException("the class " + getExecutor().getMethodOwner()
                    + " is a interface and interfaces have no static methods");
        }

        if (a.isPrimitive()) {
            throw new MethodInvokeException("the class " + getExecutor().getMethodOwner()
                    + " is a primitive and primitive cannot as a method invoker owner");
        }
    }

    @Override
    public final MethodInvoker call(AClass owner, String methodName, InternalParameterized... arguments) {
        invokeVerify(owner);
        return OperatorFactory.newOperator(StaticMethodInvoker.class, new Class<?>[] { ProgramBlockInternal.class,
                AClass.class, String.class, InternalParameterized[].class }, getExecutor(), owner, methodName, arguments);
    }

    public final MethodInvoker call(Class<?> owner, String methodName, InternalParameterized... arguments) {
        return call(defType(owner), methodName, arguments);
    }

    @Override
    public final MethodInvoker new_(AClass owner, InternalParameterized... arguments) {
        invokeVerify(owner);
        return OperatorFactory.newOperator(ConstructorInvoker.class, new Class<?>[] { ProgramBlockInternal.class,
                AClass.class, InternalParameterized[].class }, getExecutor(), owner, arguments);
    }

    @Override
    public final MethodInvoker new_(Class<?> owner, InternalParameterized... arguments) {
        return this.new_(AClassFactory.defType(owner), arguments);
    }

    // *******************************************************************************************//
    // control Operator //
    // *******************************************************************************************//

    @Override
    public final IFInternal if_(IFInternal ifBlock) {
        ifBlock.setParent(getExecutor());
        getQueue().add(ifBlock);
        ifBlock.prepare();
        return ifBlock;
    }

    @Override
    public final WhileInternal while_(WhileInternal whileLoop) {
        whileLoop.setParent(getExecutor());
        getQueue().add(whileLoop);
        whileLoop.prepare();
        return whileLoop;
    }

    @Override
    public final DoWhileInternal dowhile(DoWhileInternal dowhile) {
        dowhile.setParent(getExecutor());
        getQueue().add(dowhile);
        dowhile.prepare();
        return dowhile;
    }

    @Override
    public final ForEachInternal for_(final ForEachInternal forEach) {
        forEach.setParent(getExecutor());
        getQueue().add(forEach);
        forEach.prepare();
        return forEach;
    }

    @Override
    public final void break_() {
        ProgramBlockInternal pb = getExecutor();
        while (pb != null) {
            if (pb instanceof Loop) {
                OperatorFactory.newOperator(GOTO.class, new Class<?>[] { ProgramBlockInternal.class, Label.class },
                        getExecutor(), ((Loop) pb).getBreakLabel());
                // new GOTO(getExecutor(), ((ILoop)pb).getBreakLabel());
                return;
            }
            pb = pb.getParent();
        }
        throw new InternalError("there is on loop!");
    }

    @Override
    public final void continue_() {
        ProgramBlockInternal pb = getExecutor();
        while (pb != null) {
            if (pb instanceof Loop) {
                OperatorFactory.newOperator(GOTO.class, new Class<?>[] { ProgramBlockInternal.class, Label.class },
                        getExecutor(), ((Loop) pb).getContinueLabel());
                // new GOTO(getExecutor(), ((ILoop)pb).getContinueLabel());
                return;
            }
            pb = pb.getParent();
        }
        throw new InternalError("there is on loop!");
    }

    @Override
    public final void throw_(InternalParameterized exception) {
        OperatorFactory.newOperator(Throw.class, new Class<?>[] { ProgramBlockInternal.class, InternalParameterized.class },
                getExecutor(), exception);
    }

    @Override
    public final TryInternal try_(final TryInternal t) {
        new ExceptionSerialBlock(getExecutor(), t);
        return t;
    }

    @Override
    public final SynchronizedInternal sync(SynchronizedInternal s) {
        s.setParent(getExecutor());
        getQueue().add(s);
        s.prepare();
        return s;
    }

    @Override
    public final ThisVariable this_() {
        if (getMethod().isStatic()) {
            throw new ASMSupportException("cannot use \"this\" keyword in static block");
        }
        return getMethodOwner().getThisVariable();
    }

    @Override
    public final GlobalVariable this_(String name) {
        return this_().field(name);
    }

    @Override
    public final SuperVariable super_() {
        if (getMethod().isStatic()) {
            throw new ASMSupportException("cannot use \"super\" keyword in static block");
        }
        return getMethodOwner().getSuperVariable();
    }

    @Override
    public final MethodInvoker callOrig() {
        if (getMethod().getMode() == ASConstant.METHOD_CREATE_MODE_MODIFY) {
            String originalMethodName = getMethod().getMethodMeta().getName();
            if (originalMethodName.equals(ASConstant.CLINIT)) {
                originalMethodName = ASConstant.CLINIT_PROXY;
            } else if (originalMethodName.equals(ASConstant.INIT)) {
                originalMethodName = ASConstant.INIT_PROXY;
            }
            originalMethodName += ASConstant.METHOD_PROXY_SUFFIX;
            if (getMethod().isStatic()) {
                return call(getMethod().getMethodOwner(), originalMethodName, getMethodArguments());
            } else {
                return call(this_(), originalMethodName, getMethodArguments());
            }
        } else {
            throw new ASMSupportException("This method is new and not modify!");
        }
    }

    /**
     * run return statement
     * 
     * @return
     */
    @Override
    public final Return return_() {
        if (!getMethod().getMethodMeta().getReturnType().equals(Type.VOID_TYPE)) {
            throw new VerifyErrorException("Do not specify a return type! ");
        }
        return OperatorFactory.newOperator(Return.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class }, getExecutor(), null);
    }

    /**
     * run return statement with return value
     * 
     * @param parame
     *            return value
     */
    @Override
    public final Return return_(InternalParameterized parame) {
        return OperatorFactory.newOperator(Return.class, new Class<?>[] { ProgramBlockInternal.class,
                InternalParameterized.class }, getExecutor(), parame);
    }

    @Override
    public Value val(Integer val) {
        return Value.value(val);
    }

    @Override
    public Value val(Short val) {
        return Value.value(val);
    }

    @Override
    public Value val(Byte val) {
        return Value.value(val);
    }

    @Override
    public Value val(Boolean val) {
        return Value.value(val);
    }

    @Override
    public Value val(Long val) {
        return Value.value(val);
    }

    @Override
    public Value val(Double val) {
        return Value.value(val);
    }

    @Override
    public Value val(Character val) {
        return Value.value(val);
    }

    @Override
    public Value val(Float val) {
        return Value.value(val);
    }

    @Override
    public Value val(AClass val) {
        return Value.value(val);
    }

    @Override
    public Value val(Class<?> val) {
        return Value.value(val);
    }

    @Override
    public Value val(String val) {
        return Value.value(val);
    }

    @Override
    public Value null_(AClass type) {
        return Value.getNullValue(type);
    }

    @Override
    public Value null_(Class<?> type) {
        return Value.getNullValue(AClassFactory.defType(type));
    }

    @Override
    public AClass defType(Class<?> cls) {
        return AClassFactory.defType(cls);
    }

    @Override
    public ArrayClass defArrayType(Class<?> arrayCls) {
        return AClassFactory.defArrayType(arrayCls);
    }

    @Override
    public ArrayClass defArrayType(Class<?> cls, int dim) {
        return AClassFactory.defArrayType(cls, dim);
    }

    @Override
    public ArrayClass defArrayType(AClass rootComponent, int dim) {
        return AClassFactory.defArrayType(rootComponent, dim);
    }

}

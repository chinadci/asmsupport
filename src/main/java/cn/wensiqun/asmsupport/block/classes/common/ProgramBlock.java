/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.control.condition.IF;
import cn.wensiqun.asmsupport.block.classes.control.exception.ExceptionSerialBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.control.loop.DoWhile;
import cn.wensiqun.asmsupport.block.classes.control.loop.ForEach;
import cn.wensiqun.asmsupport.block.classes.control.loop.Loop;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.operator.IBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.ArrayClass;
import cn.wensiqun.asmsupport.clazz.NewMemberClass;
import cn.wensiqun.asmsupport.definition.method.AMethod;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.definition.variable.IVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.definition.variable.SuperVariable;
import cn.wensiqun.asmsupport.definition.variable.ThisVariable;
import cn.wensiqun.asmsupport.definition.variable.meta.LocalVariableMeta;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.exception.MethodInvokeException;
import cn.wensiqun.asmsupport.exception.VerifyErrorException;
import cn.wensiqun.asmsupport.operators.BlockEndFlag;
import cn.wensiqun.asmsupport.operators.InstanceofOperator;
import cn.wensiqun.asmsupport.operators.Return;
import cn.wensiqun.asmsupport.operators.StringAppender;
import cn.wensiqun.asmsupport.operators.Throw;
import cn.wensiqun.asmsupport.operators.array.ArrayLength;
import cn.wensiqun.asmsupport.operators.array.ArrayLoader;
import cn.wensiqun.asmsupport.operators.array.ArrayStorer;
import cn.wensiqun.asmsupport.operators.array.ArrayValue;
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.assign.Assigner;
import cn.wensiqun.asmsupport.operators.assign.GlobalVariableAssigner;
import cn.wensiqun.asmsupport.operators.assign.LocalVariableAssigner;
import cn.wensiqun.asmsupport.operators.checkcast.CheckCast;
import cn.wensiqun.asmsupport.operators.logical.LogicalAnd;
import cn.wensiqun.asmsupport.operators.logical.LogicalOr;
import cn.wensiqun.asmsupport.operators.logical.LogicalXor;
import cn.wensiqun.asmsupport.operators.logical.Not;
import cn.wensiqun.asmsupport.operators.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.operators.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.operators.method.CommonMethodInvoker;
import cn.wensiqun.asmsupport.operators.method.ConstructorInvoker;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
import cn.wensiqun.asmsupport.operators.method.StaticMethodInvoker;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.Addition;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.Division;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.Modulus;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.Multiplication;
import cn.wensiqun.asmsupport.operators.numerical.arithmetic.Subtraction;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.BitAnd;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.BitOr;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.BitXor;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.Inverts;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.LeftShift;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.RightShift;
import cn.wensiqun.asmsupport.operators.numerical.bitwise.UnsignedRightShift;
import cn.wensiqun.asmsupport.operators.numerical.crement.PostposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PostposeIncrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PreposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PreposeIncrment;
import cn.wensiqun.asmsupport.operators.numerical.posinegative.Negative;
import cn.wensiqun.asmsupport.operators.relational.Equal;
import cn.wensiqun.asmsupport.operators.relational.GreaterEqual;
import cn.wensiqun.asmsupport.operators.relational.GreaterThan;
import cn.wensiqun.asmsupport.operators.relational.LessEqual;
import cn.wensiqun.asmsupport.operators.relational.LessThan;
import cn.wensiqun.asmsupport.operators.relational.NotEqual;
import cn.wensiqun.asmsupport.operators.ternary.TernaryOperator;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.operators.variable.LocalVariableCreator;
import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupport.utils.common.ThrowExceptionContainer;
import cn.wensiqun.asmsupport.utils.memory.Scope;
import cn.wensiqun.asmsupport.utils.memory.ScopeLogicVariable;

/**
 * 
 * 
 * 
 * @author wensiqun(at)gmail
 * 
 */
public abstract class ProgramBlock extends AbstractBlock implements IBlock  {

    private static Log log = LogFactory.getLog(ProgramBlock.class);

    /**执行Block, 通过当前Block所创建的操作，实际是executeBlock的代理*/
    private   ProgramBlock                executor = this;
    
    private   ProgramBlock                parent;
    
    private   Scope                       scope;
    
    protected InstructionHelper           insnHelper;
    
    private   ThrowExceptionContainer     throwExceptions;
    
    private   Label                       start;
    
    private   Label                       end;
    
    /** 当前block是否已经返回 或者已经抛出异常了 */
    private   boolean                     finish = false;
    
    /*<<<<<<<<<<<<<<<<<<< Getter Setter <<<<<<<<<<<<<<<<<<<<<<<<*/
    
    public void setExecutor(ProgramBlock exeBlock) {
        executor = exeBlock;
    }
    
    protected ProgramBlock getExecutor(){
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
    
    /* >>>>>>>>>>>>>>>>>> Getter Setter >>>>>>>>>>>>>>>>>>>>>>>*/

    public ProgramBlock getParent() {
        return parent;
    }

    /**
     * 添加抛出的异常到方法签名中
     * @param exception
     */
    public void addException(AClass exception){
        if(throwExceptions == null){
            throwExceptions = new ThrowExceptionContainer();
        }
        throwExceptions.add(exception);
    }
    
    public void removeException(AClass exception){
        if(throwExceptions != null){
            throwExceptions.remove(exception);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj instanceof ProgramBlock){
            return this.scope == ((ProgramBlock)obj).scope;
        }
        return false;
    }

    /**
     * 获取当前程序块的克隆拷贝
     * @return
     */
    public ProgramBlock getCopy(){
        try {
            return (ProgramBlock) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new ASMSupportException();
        }
    }
    
    /**
     * 克隆当前的程序块的执行队列到给定程序块执行队列中
     * @param targetBlock 克隆至此
     */
    public void generateTo(ProgramBlock targetBlock){
        ProgramBlock clone = getCopy();
        clone.setExecutor(targetBlock);
        clone.generate();
        //just trigger if the last is SerialBlock in the queue of cloneTo
        OperatorFactory.newOperator(BlockEndFlag.class, new Class[]{ProgramBlock.class}, targetBlock);
    }

    protected void init(){};
    
    /**
     * override this method if want create a new block
     * 生成操作到执行队列中去。
     */
    public abstract void generate();
    
    @Override
    public void prepare() {
        init();
        scope.getStart().setName(this.getClass().toString() + " start");
        scope.getEnd().setName(this.getClass().toString() + " end");
        
        generate();
        
        //just trigger if the last is SerialBlock in the queue
        OperatorFactory.newOperator(BlockEndFlag.class, 
                new Class[]{ProgramBlock.class}, 
                getExecutor());
    }
    
    @Override
    public final void execute()
    {
        getInsnHelper().mark(scope.getStart());
        getInsnHelper().nop();
        doExecute();
        getInsnHelper().mark(scope.getEnd());
        getInsnHelper().nop();
    }
    
    protected abstract void doExecute();

    public void setScope(Scope scope) {
        this.scope = scope;
        this.start = scope.getStart();
        this.end = scope.getEnd();
    }

    public Scope getScope() {
        return this.scope;
    }
    
    public Label getStart()
    {
        return start;
    }

    public Label getEnd()
    {
        return end;
    }

    public void setInsnHelper(InstructionHelper insnHelper) {
        this.insnHelper = insnHelper;
    }

    public void setParent(ProgramBlock block) {
        parent = block;
        setInsnHelper(block.insnHelper);
        setScope(new Scope(getMethod().getLocals(), block.getScope()));
    }
    
    public AMethod getMethod() {
        return insnHelper.getMethod();
    }
    
    public LocalVariable[] getMethodArguments(){
        return getMethod().getArguments();
    }
    
    protected GenericMethodBody getMethodBody(){
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

    public InstructionHelper getInsnHelper() {
        return insnHelper;
    }
    
    //*******************************************************************************************//
    //                                Variable Operator                                          //
    //*******************************************************************************************//
    private final LocalVariable createOnlyVariable(final AClass aClass, final String name, boolean anonymous){
        if(!anonymous && StringUtils.isBlank(name)){
            throw new IllegalArgumentException("variable must be non-null if 'anonymous' is false");
        }
        
        LocalVariableMeta lve = new LocalVariableMeta(anonymous || getMethod().isCreatingImplicitFinally() ? "anonymous" : name, 0, aClass);
        
        LocalVariableCreator lvc = OperatorFactory.newOperator(LocalVariableCreator.class, 
                new Class<?>[]{ProgramBlock.class, String.class, Type.class, Type.class}, 
                getExecutor(), anonymous ? null : name, aClass.getType(), aClass.getType());
        ScopeLogicVariable slv = lvc.getScopeLogicVariable();
        slv.setCompileOrder(getMethod().nextInsNumber());
        LocalVariable lv = new LocalVariable(lve);
        lv.setScopeLogicVar(slv);
        return lv;
    }

    protected final LocalVariable getLocalAnonymousVariableModel(final AClass aClass){
        return createOnlyVariable(aClass, "anonymous", true);
    }
    
    protected final LocalVariable getLocalVariableModel(final String name, final AClass aClass){
        return createOnlyVariable(aClass, name, false);
    }

    @Override
    public final LocalVariable _createVariable(final String name, final AClass aClass, boolean anonymous, final Parameterized para) {
        if(aClass.isArray()){
            throw new IllegalArgumentException(aClass + " is Array type exchange to createArrayVariable to create the array variable");
        }
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(para == null){
            _assign(lv, aClass.getDefaultValue());
        }else{
            _assign(lv, para);
        }
        return lv;
    }
    
    @Override
    public final LocalVariable _createArrayVariableWithAllocateDimension(final String name, final ArrayClass aClass, boolean anonymous, Parameterized... allocateDim) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(allocateDim == null){
            _assign(lv, aClass.getDefaultValue());
        }else{
            _assign(lv, _newArray(aClass, allocateDim));
        }
        return lv;
    }
    
    @Override
    public final LocalVariable _createArrayVariable(final String name, final ArrayClass aClass, boolean anonymous, Parameterized value) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(value == null){
            _assign(lv, aClass.getDefaultValue());
        }else{
            _assign(lv, value);
        }
        return lv;
    }

    @Override
    public LocalVariable _createArrayVariable(String name, ArrayClass aClass, boolean anonymous, Object parameterizedArray) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(parameterizedArray == null){
            _assign(lv, aClass.getDefaultValue());
        }else{
            _assign(lv, getExecutor()._newArrayWithValue(aClass, parameterizedArray));
        }
        return lv;
    }
    
    
    @Override
    public final Assigner _assign(ExplicitVariable variable, Parameterized val){
        if(variable instanceof LocalVariable){
            return OperatorFactory.newOperator(LocalVariableAssigner.class,
                    new Class<?>[]{ProgramBlock.class, LocalVariable.class, Parameterized.class}, 
                    getExecutor(), (LocalVariable) variable, val);
        }else if(variable instanceof GlobalVariable){
            return OperatorFactory.newOperator(GlobalVariableAssigner.class,
                            new Class<?>[]{ProgramBlock.class, GlobalVariable.class, Parameterized.class}, 
                            getExecutor(), (GlobalVariable) variable, val);
        }
        return null;
    }
    
    //*******************************************************************************************//
    //                                Value Operator                                             //
    //*******************************************************************************************//
    
    @Override
    public final ArrayValue _newArray(final ArrayClass aClass, final Parameterized... allocateDims){
        return OperatorFactory.newOperator(ArrayValue.class, 
                new Class<?>[]{ProgramBlock.class, ArrayClass.class, Parameterized[].class}, 
                getExecutor(), aClass, allocateDims);
    }
    
    @Override
    public final ArrayValue _newArrayWithValue(final ArrayClass aClass, final Object arrayObject){
        return OperatorFactory.newOperator(ArrayValue.class, 
                new Class<?>[]{ProgramBlock.class, ArrayClass.class, Object.class}, 
                getExecutor(), aClass, arrayObject);
    }

    @Override
    public final ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[] values){
        return _newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][] values){
        return _newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][][] values){
        return _newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue _newArrayWithValue(final ArrayClass aClass, final Parameterized[][][][] values){
        return _newArrayWithValue(aClass, (Object)values);
    }
    
    
    //*******************************************************************************************//
    //                                Array Operator                                             //
    //*******************************************************************************************//  
    
    @Override
    public final ArrayLoader _arrayLoad(IVariable arrayReference, Parameterized pardim, Parameterized... parDims){
        return OperatorFactory.newOperator(ArrayLoader.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, pardim, parDims);
    }
    
    @Override
    public final ArrayLoader _arrayLoad(MethodInvoker arrayReference, Parameterized pardim, Parameterized... parDims){
        return OperatorFactory.newOperator(ArrayLoader.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, pardim, parDims);
    }
    
    @Override
    public ArrayLoader _arrayLoad(ArrayLoader arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(ArrayValue arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(Assigner arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, pardim, parDims);
    }

    @Override
    public final ArrayStorer _arrayStore(IVariable arrayReference, Parameterized value,
            Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, value, dim, dims);
    }

    @Override
    public final  ArrayStorer _arrayStore(MethodInvoker arrayReference, Parameterized value,
            Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, value, dim, dims);
    }

    
    @Override
    public ArrayStorer _arrayStore(ArrayLoader arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(ArrayValue arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(Assigner arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
                getExecutor(), arrayReference, value, dim, dims);
    }

    @Override
    public final ArrayLength _arrayLength(IVariable arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), arrayReference, dims);
    }


    @Override
    public ArrayLength _arrayLength(MethodInvoker arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(ArrayLoader arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(ArrayValue arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(Assigner arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), arrayReference, dims);
    }    
    
    //*******************************************************************************************//
    //                                 Check Cast                                                //
    //*******************************************************************************************//


    @Override
    public final CheckCast _checkcast(Parameterized cc, AClass to){
        if(to.isPrimitive()){
                throw new IllegalArgumentException("cannot cache cast to type : " + to);
          }
        return OperatorFactory.newOperator(CheckCast.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, AClass.class}, 
                getExecutor(), cc, to);
        //return new CheckCast(getExecuteBlock(), cc, to);
    }
    
    //*******************************************************************************************//
    //                                Arithmetic Operator                                        //
    //*******************************************************************************************//

    @Override
    public final Addition _add(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Addition.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Subtraction _sub(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Subtraction.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Multiplication _mul(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Multiplication.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Division _div(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Division.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Modulus _mod(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Modulus.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Negative _neg(Parameterized factor){
        return OperatorFactory.newOperator(Negative.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class},
                getExecutor(), factor);
    }
    
    //*******************************************************************************************//
    //                                       Bit Operator                                        //
    //*******************************************************************************************//

    @Override
    public final Inverts _inverts(Parameterized factor){
        return OperatorFactory.newOperator(Inverts.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class},
                getExecutor(), factor);
    }

    @Override
    public final BitAnd _bitAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitAnd.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    } 

    @Override
    public final BitOr _bitOr(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitOr.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    } 

    @Override
    public final BitXor _bitXor(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitXor.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    } 

    @Override
    public final LeftShift _leftShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LeftShift.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }
    
    public final RightShift _rightShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(RightShift.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final UnsignedRightShift _unsignedRightShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(UnsignedRightShift.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }
    
    //*******************************************************************************************//
    //                    Decrement or Increment Operator                                        //
    //*******************************************************************************************//

    @Override
    public final PreposeDecrment _preDec(Crementable crement){
        return OperatorFactory.newOperator(PreposeDecrment.class, 
                new Class<?>[]{ProgramBlock.class, Crementable.class},
                getExecutor(), crement);
    }

    @Override
    public final PostposeDecrment _postDec(Crementable crement){
        return OperatorFactory.newOperator(PostposeDecrment.class, 
                new Class<?>[]{ProgramBlock.class, Crementable.class},
                getExecutor(), crement);
    }

    @Override
    public final PreposeIncrment _preInc(Crementable crement){
        return OperatorFactory.newOperator(PreposeIncrment.class, 
                new Class<?>[]{ProgramBlock.class, Crementable.class},
                getExecutor(), crement);
    }

    @Override
    public final PostposeIncrment _postInc(Crementable crement){
        return OperatorFactory.newOperator(PostposeIncrment.class, 
                new Class<?>[]{ProgramBlock.class, Crementable.class},
                getExecutor(), crement);
    }

    //*******************************************************************************************//
    //                            Relational Operator                                            //
    //*******************************************************************************************//

    @Override
    public final GreaterThan _greaterThan(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(GreaterThan.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final GreaterEqual _greaterEqual(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(GreaterEqual.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final LessThan _lessThan(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LessThan.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final LessEqual _lessEqual(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LessEqual.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Equal _equals(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Equal.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }

    @Override
    public final NotEqual _notEquals(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(NotEqual.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
                getExecutor(), factor1, factor2);
    }


    //*******************************************************************************************//
    //                            Logic Operator                                                 //
    //*******************************************************************************************//

    @Override
    public final LogicalAnd _logicalAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalAnd.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalOr _logicalOr(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalOr.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalXor _logicalXor(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalXor.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), factor1, factor2);
    }

    @Override
    public final ShortCircuitAnd _conditionalAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(ShortCircuitAnd.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), factor1, factor2);
    }

    @Override
    public final ShortCircuitOr _conditionalOr(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(ShortCircuitOr.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), factor1, factor2);
    }

    @Override
    public final Not _not(Parameterized factor){
        return OperatorFactory.newOperator(Not.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, 
                getExecutor(), factor);
    }

    @Override
    public final TernaryOperator _ternary(Parameterized exp1, Parameterized exp2, Parameterized exp3){
        return OperatorFactory.newOperator(TernaryOperator.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class}, 
                getExecutor(), exp1, exp2, exp3);
    }
    

    //*******************************************************************************************//
    //                                  String Operator                                          //
    //*******************************************************************************************//

    @Override
    public final Parameterized _append(Parameterized par1, Parameterized... pars){
        return OperatorFactory.newOperator(StringAppender.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
                getExecutor(), par1, pars);
    }
    
    //*******************************************************************************************//
    //                                  instanceof Operator                                      //
    //*******************************************************************************************//

    @Override
    public final Parameterized _instanceof(Parameterized obj, AClass type){
        return OperatorFactory.newOperator(InstanceofOperator.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, AClass.class}, 
                getExecutor(), obj, type);
    }

    
    //*******************************************************************************************//
    //                                  method invoke Operator                                      //
    //*******************************************************************************************//

    @Override
    public final MethodInvoker _invoke(Parameterized caller, String methodName, Parameterized... arguments){
        return OperatorFactory.newOperator(CommonMethodInvoker.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class, String.class, Parameterized[].class}, 
                getExecutor(), caller, methodName, arguments);
    }

    protected final void invokeVerify(AClass a){
        if(a.isInterface()){
            throw new MethodInvokeException("the class " + getExecutor().getMethodOwner() + " is a interface and interfaces have no static methods");
        }
        
        if(a.isPrimitive()){
            throw new MethodInvokeException("the class " + getExecutor().getMethodOwner() + " is a primitive and primitive cannot as a method invoker owner");
        }
    }

    @Override
    public final MethodInvoker _invokeStatic(AClass owner, String methodName, Parameterized... arguments) {
        invokeVerify(owner);
        return OperatorFactory.newOperator(StaticMethodInvoker.class, 
                new Class<?>[]{ProgramBlock.class, AClass.class, String.class, Parameterized[].class}, 
                getExecutor(), owner, methodName, arguments);
    }
    
    public final MethodInvoker _new(AClass owner, Parameterized... arguments){
        invokeVerify(owner);
        return OperatorFactory.newOperator(ConstructorInvoker.class, 
                new Class<?>[]{ProgramBlock.class, AClass.class, Parameterized[].class}, 
                getExecutor(), owner, arguments);
    }
    
    //*******************************************************************************************//
    //                                  control Operator                                      //
    //*******************************************************************************************//

    @Override
    public final IF _if(IF ifBlock){
        ifBlock.setParent(getExecutor());
        getQueue().add(ifBlock);
        ifBlock.prepare();
        return ifBlock;
    }

    @Override
    public final While _while(While whileLoop){
        whileLoop.setParent(getExecutor());
        getQueue().add(whileLoop);
        whileLoop.prepare();
        return whileLoop;
    }

    @Override
    public final DoWhile _dowhile(DoWhile dowhile){
        dowhile.setParent(getExecutor());
        getQueue().add(dowhile);
        dowhile.prepare();
        return dowhile;
    }

    @Override
    public final ForEach _for(final ForEach forEach){
        forEach.setParent(getExecutor());
        getQueue().add(forEach);
        forEach.prepare();
        return forEach;
    }

    @Override
    public final void _break(){
        ProgramBlock pb = getExecutor();
        while(pb != null){
            if(pb instanceof Loop){
                OperatorFactory.newOperator(GOTO.class, 
                        new Class<?>[]{ProgramBlock.class, Label.class}, 
                        getExecutor(), ((Loop)pb).getBreakLabel());
                //new GOTO(getExecutor(), ((ILoop)pb).getBreakLabel());
                return;
            }
            pb = pb.getParent();
        }
        throw new InternalError("there is on loop!");
    }

    @Override
    public final void _continue(){
        ProgramBlock pb = getExecutor();
        while(pb != null){
            if(pb instanceof Loop){
                OperatorFactory.newOperator(GOTO.class, 
                        new Class<?>[]{ProgramBlock.class, Label.class}, 
                        getExecutor(), ((Loop)pb).getContinueLabel());
                //new GOTO(getExecutor(), ((ILoop)pb).getContinueLabel());
                return;
            }
            pb = pb.getParent();
        }
        throw new InternalError("there is on loop!");
    }

    @Override
    public final void _throw(Parameterized exception){
        OperatorFactory.newOperator(Throw.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, getExecutor(), exception);
     }
    
    @Override
    public final Try _try(final Try t){
        new ExceptionSerialBlock(getExecutor(), t);
        return t;
     }

    @Override
     public final Synchronized _sync(Synchronized s){
        s.setParent(getExecutor());
        getQueue().add(s);
        s.prepare();
        return s;
     }
    
    @Override
    public final ThisVariable _this() {
        if(getMethod().isStatic()){
            throw new ASMSupportException("cannot use \"this\" keyword in static block");
        }
        return getMethodOwner().getThisVariable();
    }
    
    @Override
    public final SuperVariable _super() {
        if(getMethod().isStatic()){
            throw new ASMSupportException("cannot use \"super\" keyword in static block");
        }
        return getMethodOwner().getSuperVariable();
    }
    
    @Override
    public final MethodInvoker _invokeOriginalMethod(){
        if(getMethod().getMode() == ASConstant.METHOD_CREATE_MODE_MODIFY){
            String originalMethodName = getMethod().getMethodMeta().getName();
            if(originalMethodName.equals(ASConstant.CLINIT)){
                originalMethodName = ASConstant.CLINIT_PROXY;
            }else if(originalMethodName.equals(ASConstant.INIT)){
                originalMethodName = ASConstant.INIT_PROXY;
            }
            originalMethodName += ASConstant.METHOD_PROXY_SUFFIX;
            if(getMethod().isStatic()){
                return _invokeStatic(getMethod().getMethodOwner(), originalMethodName, getMethodArguments());
            }else{
                return _invoke(_this(), originalMethodName, getMethodArguments());
            }
        }else{
            throw new ASMSupportException("This method is new and not modify!");
        }
    }
    
    /**
     * run return statement
     * @return
     */
    @Override
    public final Return _return() {
        if (!getMethod().getMethodMeta().getReturnType().equals(Type.VOID_TYPE)) {
            throw new VerifyErrorException("Do not specify a return type! ");
        }
        return OperatorFactory.newOperator(Return.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, getExecutor(), null);
    }

    /**
     * run return statement with return value
     * 
     * @param parame return value
     */
    @Override
    public final Return _return(Parameterized parame) {
        return OperatorFactory.newOperator(Return.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, getExecutor(), parame);
    }
    
}

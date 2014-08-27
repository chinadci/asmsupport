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
import cn.wensiqun.asmsupport.block.classes.control.loop.Loop;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.operator.IBlockOperators;
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
import cn.wensiqun.asmsupport.operators.numerical.crement.AfterDecrement;
import cn.wensiqun.asmsupport.operators.numerical.crement.AfterIncrement;
import cn.wensiqun.asmsupport.operators.numerical.crement.BeforeDecrement;
import cn.wensiqun.asmsupport.operators.numerical.crement.BeforeIncrement;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PostposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PostposeIncrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PreposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PreposeIncrment;
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
public abstract class ProgramBlock extends AbstractBlock implements IBlockOperators  {

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
    public final LocalVariable createVariable(final String name, final AClass aClass, boolean anonymous, final Parameterized para) {
		if(aClass.isArray()){
			throw new IllegalArgumentException(aClass + " is Array type exchange to createArrayVariable to create the array variable");
		}
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(para == null){
            assign(lv, aClass.getDefaultValue());
        }else{
            assign(lv, para);
        }
        return lv;
    }
	
    @Override
    public final LocalVariable createArrayVariableWithAllocateDimension(final String name, final ArrayClass aClass, boolean anonymous, Parameterized... allocateDim) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(allocateDim == null){
            assign(lv, aClass.getDefaultValue());
        }else{
            assign(lv, newArray(aClass, allocateDim));
        }
        return lv;
    }
    
    @Override
	public final LocalVariable createArrayVariable(final String name, final ArrayClass aClass, boolean anonymous, Parameterized value) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(value == null){
            assign(lv, aClass.getDefaultValue());
        }else{
            assign(lv, value);
        }
        return lv;
	}

	@Override
	public LocalVariable createArrayVariable(String name, ArrayClass aClass, boolean anonymous, Object parameterizedArray) {
        LocalVariable lv = createOnlyVariable(aClass, name, anonymous);
        if(parameterizedArray == null){
            assign(lv, aClass.getDefaultValue());
        }else{
            assign(lv, getExecutor().newArrayWithValue(aClass, parameterizedArray));
        }
        return lv;
	}
    
    
    @Override
    public final Assigner assign(ExplicitVariable variable, Parameterized val){
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
    public final ArrayValue newArray(final ArrayClass aClass, final Parameterized... allocateDims){
    	return OperatorFactory.newOperator(ArrayValue.class, 
    			new Class<?>[]{ProgramBlock.class, ArrayClass.class, Parameterized[].class}, 
    			getExecutor(), aClass, allocateDims);
    }
    
    @Override
    public final ArrayValue newArrayWithValue(final ArrayClass aClass, final Object arrayObject){
    	return OperatorFactory.newOperator(ArrayValue.class, 
    			new Class<?>[]{ProgramBlock.class, ArrayClass.class, Object.class}, 
    			getExecutor(), aClass, arrayObject);
    }

    @Override
    public final ArrayValue newArrayWithValue(final ArrayClass aClass, final Parameterized[] values){
    	return newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue newArrayWithValue(final ArrayClass aClass, final Parameterized[][] values){
    	return newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue newArrayWithValue(final ArrayClass aClass, final Parameterized[][][] values){
    	return newArrayWithValue(aClass, (Object)values);
    }

    @Override
    public final ArrayValue newArrayWithValue(final ArrayClass aClass, final Parameterized[][][][] values){
    	return newArrayWithValue(aClass, (Object)values);
    }
    
    
    //*******************************************************************************************//
    //                                Array Operator                                             //
    //*******************************************************************************************//  
    
    @Override
    public final ArrayLoader arrayLoad(IVariable arrayReference, Parameterized pardim, Parameterized... parDims){
        return OperatorFactory.newOperator(ArrayLoader.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, pardim, parDims);
    }
    
    @Override
    public final ArrayLoader arrayLoad(MethodInvoker arrayReference, Parameterized pardim, Parameterized... parDims){
        return OperatorFactory.newOperator(ArrayLoader.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, pardim, parDims);
    }
    
    @Override
	public ArrayLoader arrayLoad(ArrayLoader arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, pardim, parDims);
	}

	@Override
	public ArrayLoader arrayLoad(ArrayValue arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, pardim, parDims);
	}

	@Override
	public ArrayLoader arrayLoad(Assigner arrayReference, Parameterized pardim, Parameterized... parDims) {
        return OperatorFactory.newOperator(ArrayLoader.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, pardim, parDims);
	}

	@Override
    public final ArrayStorer arrayStore(IVariable arrayReference, Parameterized value,
            Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, value, dim, dims);
    }

    @Override
    public final  ArrayStorer arrayStore(MethodInvoker arrayReference, Parameterized value,
            Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, value, dim, dims);
    }

    
    @Override
	public ArrayStorer arrayStore(ArrayLoader arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, value, dim, dims);
	}

	@Override
	public ArrayStorer arrayStore(ArrayValue arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, value, dim, dims);
	}

	@Override
	public ArrayStorer arrayStore(Assigner arrayReference, Parameterized value, Parameterized dim, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayStorer.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class, Parameterized[].class},
        		getExecutor(), arrayReference, value, dim, dims);
	}

	@Override
    public final ArrayLength arrayLength(IVariable arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), arrayReference, dims);
    }


	@Override
	public ArrayLength arrayLength(MethodInvoker arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), arrayReference, dims);
	}

	@Override
	public ArrayLength arrayLength(ArrayLoader arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), arrayReference, dims);
	}

	@Override
	public ArrayLength arrayLength(ArrayValue arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), arrayReference, dims);
	}

	@Override
	public ArrayLength arrayLength(Assigner arrayReference, Parameterized... dims) {
        return OperatorFactory.newOperator(ArrayLength.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), arrayReference, dims);
	}	
    
    //*******************************************************************************************//
    //                                 Check Cast                                                //
    //*******************************************************************************************//


	@Override
    public final CheckCast checkCast(Parameterized cc, AClass to){
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
    public final Addition add(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Addition.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Subtraction sub(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Subtraction.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Multiplication mul(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Multiplication.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Division div(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Division.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Modulus mod(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Modulus.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Negative neg(Parameterized factor){
        return OperatorFactory.newOperator(Negative.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class},
        		getExecutor(), factor);
    }
    
    //*******************************************************************************************//
    //                                       Bit Operator                                        //
    //*******************************************************************************************//

    @Override
    public final Inverts inverts(Parameterized factor){
        return OperatorFactory.newOperator(Inverts.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class},
        		getExecutor(), factor);
    }

    @Override
    public final BitAnd bitAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitAnd.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    } 

    @Override
    public final BitOr bitOr(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitOr.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    } 

    @Override
    public final BitXor bitXor(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(BitXor.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    } 

    @Override
    public final LeftShift leftShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LeftShift.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }
    
    public final RightShift rightShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(RightShift.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final UnsignedRightShift unsignedRightShift(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(UnsignedRightShift.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }
    
    //*******************************************************************************************//
    //                    Decrement or Increment Operator                                        //
    //*******************************************************************************************//

    @Override
    public final PreposeDecrment preDec(Crementable crement){
        return OperatorFactory.newOperator(PreposeDecrment.class, 
        		new Class<?>[]{ProgramBlock.class, Crementable.class},
        		getExecutor(), crement);
    }

    @Override
    public final PostposeDecrment postDec(Crementable crement){
        return OperatorFactory.newOperator(PostposeDecrment.class, 
        		new Class<?>[]{ProgramBlock.class, Crementable.class},
        		getExecutor(), crement);
    }

    @Override
    public final PreposeIncrment preInc(Crementable crement){
        return OperatorFactory.newOperator(PreposeIncrment.class, 
        		new Class<?>[]{ProgramBlock.class, Crementable.class},
        		getExecutor(), crement);
    }

    @Override
    public final PostposeIncrment postInc(Crementable crement){
        return OperatorFactory.newOperator(PostposeIncrment.class, 
        		new Class<?>[]{ProgramBlock.class, Crementable.class},
        		getExecutor(), crement);
    }

    //*******************************************************************************************//
    //                            Relational Operator                                            //
    //*******************************************************************************************//

    @Override
    public final GreaterThan greaterThan(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(GreaterThan.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final GreaterEqual greaterEqual(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(GreaterEqual.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final LessThan lessThan(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LessThan.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final LessEqual lessEqual(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LessEqual.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Equal equal(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(Equal.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final NotEqual notEqual(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(NotEqual.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class},
        		getExecutor(), factor1, factor2);
    }


    //*******************************************************************************************//
    //                            Logic Operator                                                 //
    //*******************************************************************************************//

    @Override
    public final LogicalAnd logicalAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalAnd.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalOr logicalOr(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalOr.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final LogicalXor logicalXor(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(LogicalXor.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final ShortCircuitAnd conditionalAnd(Parameterized factor1, Parameterized factor2){
        return OperatorFactory.newOperator(ShortCircuitAnd.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final ShortCircuitOr conditionalOr(Parameterized factor1, Parameterized factor2){
    	return OperatorFactory.newOperator(ShortCircuitOr.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), factor1, factor2);
    }

    @Override
    public final Not not(Parameterized factor){
    	return OperatorFactory.newOperator(Not.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class}, 
        		getExecutor(), factor);
    }

    @Override
    public final TernaryOperator ternary(Parameterized exp1, Parameterized exp2, Parameterized exp3){
    	return OperatorFactory.newOperator(TernaryOperator.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized.class, Parameterized.class}, 
        		getExecutor(), exp1, exp2, exp3);
    }
    

    //*******************************************************************************************//
    //                                  String Operator                                          //
    //*******************************************************************************************//

    @Override
    public final Parameterized append(Parameterized par1, Parameterized... pars){
    	return OperatorFactory.newOperator(StringAppender.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, Parameterized[].class}, 
        		getExecutor(), par1, pars);
    }
    
    //*******************************************************************************************//
    //                                  instanceof Operator                                      //
    //*******************************************************************************************//

    @Override
    public final Parameterized instanceOf(Parameterized obj, AClass type){
        return OperatorFactory.newOperator(InstanceofOperator.class, 
        		new Class<?>[]{ProgramBlock.class, Parameterized.class, AClass.class}, 
        		getExecutor(), obj, type);
    }

    
    //*******************************************************************************************//
    //                                  method invoke Operator                                      //
    //*******************************************************************************************//

    @Override
    public final MethodInvoker invoke(Parameterized caller, String methodName, Parameterized... arguments){
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
    public final MethodInvoker invokeStatic(AClass owner, String methodName, Parameterized... arguments) {
        invokeVerify(owner);
        return OperatorFactory.newOperator(StaticMethodInvoker.class, 
        		new Class<?>[]{ProgramBlock.class, AClass.class, String.class, Parameterized[].class}, 
        		getExecutor(), owner, methodName, arguments);
    }
    
    public final MethodInvoker invokeConstructor(AClass owner, Parameterized... arguments){
        invokeVerify(owner);
        return OperatorFactory.newOperator(ConstructorInvoker.class, 
        		new Class<?>[]{ProgramBlock.class, AClass.class, Parameterized[].class}, 
        		getExecutor(), owner, arguments);
    }
    
    //*******************************************************************************************//
    //                                  control Operator                                      //
    //*******************************************************************************************//

    @Override
    public final IF ifThen(IF ifBlock){
        ifBlock.setParent(getExecutor());
    	getQueue().add(ifBlock);
        ifBlock.prepare();
        return ifBlock;
    	
        /*addExe(ifBlock);
        ifBlock.setParentExes(getQueue());
        subBlockPrepare(ifBlock);
        return ifBlock;*/
    }

    @Override
    public final While whileDo(While whileLoop){
        /*addExe(wl);
        subBlockPrepare(wl);
        return wl;*/
    	whileLoop.setParent(getExecutor());
    	getQueue().add(whileLoop);
    	whileLoop.prepare();
        return whileLoop;
    }

    /*@Override
    public final WhileLoop dowhile(DoWhileLoop dwl){
        addExe(dwl);
        subBlockPrepare(dwl);
        return dwl;
    }

    @Override
    public final ForEachLoop forEach(final ForEachLoop forEach){
        addExe(forEach);
        subBlockPrepare(forEach);
        return forEach;
    }*/

    @Override
    public final void breakOut(){
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
    public final void continueOut(){
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
    public final void throwException(Parameterized exception){
    	//returned = true;
        OperatorFactory.newOperator(Throw.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, getExecutor(), exception);
    }
    
    @Override
    public final Try tryDo(final Try t){
        new ExceptionSerialBlock(getExecutor(), t);
        return t;
    }

    /*@Override
	public final Synchronized syn(Synchronized s){
		addExe(s);
        subBlockPrepare(s);
		return s;
	}*/
    
    @Override
    public final ThisVariable getThis() {
    	if(getMethod().isStatic()){
    		throw new ASMSupportException("cannot use \"this\" keyword in static block");
    	}
        return getMethodOwner().getThisVariable();
    }
    
    @Override
    public final SuperVariable getSuper() {
    	if(getMethod().isStatic()){
    		throw new ASMSupportException("cannot use \"super\" keyword in static block");
    	}
        return getMethodOwner().getSuperVariable();
    }
    
    @Override
    public final MethodInvoker invokeOriginalMethod(){
    	if(getMethod().getMode() == ASConstant.METHOD_CREATE_MODE_MODIFY){
    		String originalMethodName = getMethod().getMethodMeta().getName();
    		if(originalMethodName.equals(ASConstant.CLINIT)){
    			originalMethodName = ASConstant.CLINIT_PROXY;
    		}else if(originalMethodName.equals(ASConstant.INIT)){
    			originalMethodName = ASConstant.INIT_PROXY;
    		}
			originalMethodName += ASConstant.METHOD_PROXY_SUFFIX;
    		if(getMethod().isStatic()){
    			return invokeStatic(getMethod().getMethodOwner(), originalMethodName, getMethodArguments());
    		}else{
        		return invoke(getThis(), originalMethodName, getMethodArguments());
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
    public final Return runReturn() {
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
    public final Return runReturn(Parameterized parame) {
        return OperatorFactory.newOperator(Return.class, 
                new Class<?>[]{ProgramBlock.class, Parameterized.class}, getExecutor(), parame);
    }
    
}

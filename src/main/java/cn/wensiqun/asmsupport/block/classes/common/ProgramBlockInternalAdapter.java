package cn.wensiqun.asmsupport.block.classes.common;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.IFInternal;
import cn.wensiqun.asmsupport.block.classes.control.exception.TryInternal;
import cn.wensiqun.asmsupport.block.classes.control.loop.DoWhileInternal;
import cn.wensiqun.asmsupport.block.classes.control.loop.ForEachInternal;
import cn.wensiqun.asmsupport.block.classes.control.loop.WhileInternal;
import cn.wensiqun.asmsupport.block.interfaces.operator.IBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.ArrayClass;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.IVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.definition.variable.SuperVariable;
import cn.wensiqun.asmsupport.definition.variable.ThisVariable;
import cn.wensiqun.asmsupport.operators.Return;
import cn.wensiqun.asmsupport.operators.array.ArrayLength;
import cn.wensiqun.asmsupport.operators.array.ArrayLoader;
import cn.wensiqun.asmsupport.operators.array.ArrayStorer;
import cn.wensiqun.asmsupport.operators.array.ArrayValue;
import cn.wensiqun.asmsupport.operators.assign.Assigner;
import cn.wensiqun.asmsupport.operators.checkcast.CheckCast;
import cn.wensiqun.asmsupport.operators.logical.LogicalAnd;
import cn.wensiqun.asmsupport.operators.logical.LogicalOr;
import cn.wensiqun.asmsupport.operators.logical.LogicalXor;
import cn.wensiqun.asmsupport.operators.logical.Not;
import cn.wensiqun.asmsupport.operators.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.operators.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.operators.method.MethodInvoker;
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

public class ProgramBlockInternalAdapter extends ByteCodeExecutor implements IBlock
{

    private ProgramBlockInternal target;
    
    
    public ProgramBlockInternalAdapter(ProgramBlockInternal block)
    {
        target = block;
    }

    @Override
    public void prepare()
    {
        
    }

    @Override
    public void execute()
    {
        
    }
    
    @Override
    public ThisVariable _this()
    {
        return target._this();
    }

    @Override
    public SuperVariable _super()
    {
        return target._super();
    }

    @Override
    public LocalVariable _createVariable(String name, AClass aClass, boolean anonymous, Parameterized para)
    {
        return target._createVariable(name, aClass, anonymous, para);
    }

    @Override
    public LocalVariable _createArrayVariableWithAllocateDimension(String name, ArrayClass aClass, boolean anonymous,
        Parameterized... allocateDim)
    {
        return target._createArrayVariableWithAllocateDimension(name, aClass, anonymous, allocateDim);
    }

    @Override
    public LocalVariable _createArrayVariable(String name, ArrayClass aClass, boolean anonymous, Parameterized value)
    {
        return target._createArrayVariable(name, aClass, anonymous, value);
    }

    @Override
    public LocalVariable _createArrayVariable(String name, ArrayClass aClass, boolean anonymous,
        Object parameterizedArray)
    {
        return target._createArrayVariable(name, aClass, anonymous, parameterizedArray);
    }

    @Override
    public Assigner _assign(ExplicitVariable variable, Parameterized val)
    {
        return target._assign(variable, val);
    }

    @Override
    public MethodInvoker _invoke(Parameterized objRef, String methodName, Parameterized... arguments)
    {
        return target._invoke(objRef, methodName, arguments);
    }

    @Override
    public MethodInvoker _invokeStatic(AClass owner, String methodName, Parameterized... arguments)
    {
        
        return target._invokeStatic(owner, methodName, arguments);
    }

    @Override
    public MethodInvoker _new(AClass owner, Parameterized... arguments)
    {
        return target._new(owner, arguments);
    }

    @Override
    public MethodInvoker _invokeOriginalMethod()
    {
        return target._invokeOriginalMethod();
    }

    @Override
    public ArrayValue _newArray(ArrayClass aClass, Parameterized... allocateDims)
    {
        return target._newArray(aClass, allocateDims);
    }

    @Override
    public ArrayValue _newArrayWithValue(ArrayClass aClass, Object arrayObject)
    {
        return target._newArrayWithValue(aClass, arrayObject);
    }

    @Override
    public ArrayValue _newArrayWithValue(ArrayClass aClass, Parameterized[] values)
    {
        return target._newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue _newArrayWithValue(ArrayClass aClass, Parameterized[][] values)
    {
        return target._newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue _newArrayWithValue(ArrayClass aClass, Parameterized[][][] values)
    {
        return target._newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue _newArrayWithValue(ArrayClass aClass, Parameterized[][][][] values)
    {
        return target._newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayLoader _arrayLoad(ArrayValue arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target._arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(IVariable arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target._arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(MethodInvoker arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target._arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(Assigner arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target._arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader _arrayLoad(ArrayLoader arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target._arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayStorer _arrayStore(ArrayValue arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target._arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(IVariable arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target._arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(MethodInvoker arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target._arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(Assigner arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target._arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer _arrayStore(ArrayLoader arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target._arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayLength _arrayLength(ArrayValue arrayReference, Parameterized... dims)
    {
        return target._arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(IVariable arrayReference, Parameterized... dims)
    {
        return target._arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(MethodInvoker arrayReference, Parameterized... dims)
    {
        return target._arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(Assigner arrayReference, Parameterized... dims)
    {
        return target._arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength _arrayLength(ArrayLoader arrayReference, Parameterized... dims)
    {
        return target._arrayLength(arrayReference, dims);
    }

    @Override
    public Addition _add(Parameterized factor1, Parameterized factor2)
    {
        return target._add(factor1, factor2);
    }

    @Override
    public Subtraction _sub(Parameterized factor1, Parameterized factor2)
    {
        return target._sub(factor1, factor2);
    }

    @Override
    public Multiplication _mul(Parameterized factor1, Parameterized factor2)
    {
        return target._mul(factor1, factor2);
    }

    @Override
    public Division _div(Parameterized factor1, Parameterized factor2)
    {
        return target._div(factor1, factor2);
    }

    @Override
    public Modulus _mod(Parameterized factor1, Parameterized factor2)
    {
        return target._mod(factor1, factor2);
    }

    @Override
    public Inverts _inverts(Parameterized factor)
    {
        return target._inverts(factor);
    }

    @Override
    public BitAnd _bitAnd(Parameterized factor1, Parameterized factor2)
    {
        return target._bitAnd(factor1, factor2);
    }

    @Override
    public BitOr _bitOr(Parameterized factor1, Parameterized factor2)
    {
        return target._bitOr(factor1, factor2);
    }

    @Override
    public BitXor _bitXor(Parameterized factor1, Parameterized factor2)
    {
        return target._bitXor(factor1, factor2);
    }

    @Override
    public LeftShift _leftShift(Parameterized factor1, Parameterized factor2)
    {
        return target._leftShift(factor1, factor2);
    }

    @Override
    public RightShift _rightShift(Parameterized factor1, Parameterized factor2)
    {
        return target._rightShift(factor1, factor2);
    }

    @Override
    public UnsignedRightShift _unsignedRightShift(Parameterized factor1, Parameterized factor2)
    {
        return target._unsignedRightShift(factor1, factor2);
    }


	@Override
	public PreposeDecrment _preDec(Crementable crement) {
		return target._preDec(crement);
	}

	@Override
	public PostposeDecrment _postDec(Crementable crement) {
		return target._postDec(crement);
	}

	@Override
	public PreposeIncrment _preInc(Crementable crement) {
		return target._preInc(crement);
	}

	@Override
	public PostposeIncrment _postInc(Crementable crement) {
		return target._postInc(crement);
	}
	

    @Override
    public GreaterThan _greaterThan(Parameterized factor1, Parameterized factor2)
    {
        return target._greaterThan(factor1, factor2);
    }

    @Override
    public GreaterEqual _greaterEqual(Parameterized factor1, Parameterized factor2)
    {
        return target._greaterEqual(factor1, factor2);
    }

    @Override
    public LessThan _lessThan(Parameterized factor1, Parameterized factor2)
    {
        return target._lessThan(factor1, factor2);
    }

    @Override
    public LessEqual _lessEqual(Parameterized factor1, Parameterized factor2)
    {
        return target._lessEqual(factor1, factor2);
    }

    @Override
    public Equal _equals(Parameterized factor1, Parameterized factor2)
    {
        return target._equals(factor1, factor2);
    }

    @Override
    public NotEqual _notEquals(Parameterized factor1, Parameterized factor2)
    {
        return target._notEquals(factor1, factor2);
    }

    @Override
    public LogicalAnd _logicalAnd(Parameterized factor1, Parameterized factor2)
    {
        return target._logicalAnd(factor1, factor2);
    }

    @Override
    public LogicalOr _logicalOr(Parameterized factor1, Parameterized factor2)
    {
        return target._logicalOr(factor1, factor2);
    }

    @Override
    public LogicalXor _logicalXor(Parameterized factor1, Parameterized factor2)
    {
        return target._logicalXor(factor1, factor2);
    }

    @Override
    public ShortCircuitAnd _conditionalAnd(Parameterized factor1, Parameterized factor2)
    {
        return target._conditionalAnd(factor1, factor2);
    }

    @Override
    public ShortCircuitOr _conditionalOr(Parameterized factor1, Parameterized factor2)
    {
        return target._conditionalOr(factor1, factor2);
    }

    @Override
    public Not _not(Parameterized factor)
    {
        return target._not(factor);
    }

	@Override
	public IFInternal _if(IFInternal ifBlock) {
		return target._if(ifBlock);
	}
    
    

    @Override
    public WhileInternal _while(WhileInternal whileLoop)
    {
        return target._while(whileLoop);
    }

    /*@Override
    public WhileLoop dowhile(DoWhileLoop doWhileLoop)
    {
        
        return proxy.dowhile(doWhileLoop);
    }

    @Override
    public ForEachLoop forEach(ForEachLoop forEach)
    {
        
        return proxy.forEach(forEach);
    }*/

    @Override
    public TryInternal _try(TryInternal tryPara)
    {
        return target._try(tryPara);
    }

    /*@Override
    public Synchronized syn(Synchronized sync)
    {
        return proxy.syn(sync);
    }*/

    @Override
    public CheckCast _checkcast(Parameterized cc, AClass to)
    {
        return target._checkcast(cc, to);
    }

    @Override
    public Negative _neg(Parameterized factor)
    {
        return target._neg(factor);
    }

    @Override
    public TernaryOperator _ternary(Parameterized exp1, Parameterized exp2, Parameterized exp3)
    {
        return target._ternary(exp1, exp2, exp3);
    }

    @Override
    public Parameterized _append(Parameterized par1, Parameterized... pars)
    {
        return target._append(par1, pars);
    }

    @Override
    public Parameterized _instanceof(Parameterized obj, AClass type)
    {
        return target._instanceof(obj, type);
    }

    @Override
    public void _break()
    {
        target._break();
    }

    @Override
    public void _continue()
    {
        target._continue();
    }

    @Override
    public void _throw(Parameterized exception)
    {
        target._throw(exception);
    }

    @Override
    public Return _return()
    {
        return target._return();
    }

    @Override
    public Return _return(Parameterized parame)
    {
        return target._return(parame);
    }

	@Override
	public SynchronizedInternal _sync(SynchronizedInternal sync) {
		return target._sync(sync);
	}

	@Override
	public DoWhileInternal _dowhile(DoWhileInternal doWhile) {
		return target._dowhile(doWhile);
	}

	@Override
	public ForEachInternal _for(ForEachInternal forEach) {
		return target._for(forEach);
	}

}

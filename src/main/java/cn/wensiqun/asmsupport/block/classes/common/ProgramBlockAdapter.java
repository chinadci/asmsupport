package cn.wensiqun.asmsupport.block.classes.common;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.condition.IF;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.interfaces.operator.IBlockOperators;
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

public class ProgramBlockAdapter extends ByteCodeExecutor implements IBlockOperators
{

    private ProgramBlock target;
    
    
    public ProgramBlockAdapter(ProgramBlock block)
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
    public ThisVariable getThis()
    {
        return target.getThis();
    }

    @Override
    public SuperVariable getSuper()
    {
        return target.getSuper();
    }

    @Override
    public LocalVariable createVariable(String name, AClass aClass, boolean anonymous, Parameterized para)
    {
        return target.createVariable(name, aClass, anonymous, para);
    }

    @Override
    public LocalVariable createArrayVariableWithAllocateDimension(String name, ArrayClass aClass, boolean anonymous,
        Parameterized... allocateDim)
    {
        return target.createArrayVariableWithAllocateDimension(name, aClass, anonymous, allocateDim);
    }

    @Override
    public LocalVariable createArrayVariable(String name, ArrayClass aClass, boolean anonymous, Parameterized value)
    {
        return target.createArrayVariable(name, aClass, anonymous, value);
    }

    @Override
    public LocalVariable createArrayVariable(String name, ArrayClass aClass, boolean anonymous,
        Object parameterizedArray)
    {
        return target.createArrayVariable(name, aClass, anonymous, parameterizedArray);
    }

    @Override
    public Assigner assign(ExplicitVariable variable, Parameterized val)
    {
        return target.assign(variable, val);
    }

    @Override
    public MethodInvoker invoke(Parameterized objRef, String methodName, Parameterized... arguments)
    {
        return target.invoke(objRef, methodName, arguments);
    }

    @Override
    public MethodInvoker invokeStatic(AClass owner, String methodName, Parameterized... arguments)
    {
        
        return target.invokeStatic(owner, methodName, arguments);
    }

    @Override
    public MethodInvoker invokeConstructor(AClass owner, Parameterized... arguments)
    {
        return target.invokeConstructor(owner, arguments);
    }

    @Override
    public MethodInvoker invokeOriginalMethod()
    {
        return target.invokeOriginalMethod();
    }

    @Override
    public ArrayValue newArray(ArrayClass aClass, Parameterized... allocateDims)
    {
        return target.newArray(aClass, allocateDims);
    }

    @Override
    public ArrayValue newArrayWithValue(ArrayClass aClass, Object arrayObject)
    {
        return target.newArrayWithValue(aClass, arrayObject);
    }

    @Override
    public ArrayValue newArrayWithValue(ArrayClass aClass, Parameterized[] values)
    {
        return target.newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue newArrayWithValue(ArrayClass aClass, Parameterized[][] values)
    {
        return target.newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue newArrayWithValue(ArrayClass aClass, Parameterized[][][] values)
    {
        return target.newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayValue newArrayWithValue(ArrayClass aClass, Parameterized[][][][] values)
    {
        return target.newArrayWithValue(aClass, values);
    }

    @Override
    public ArrayLoader arrayLoad(ArrayValue arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target.arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader arrayLoad(IVariable arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target.arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader arrayLoad(MethodInvoker arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target.arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader arrayLoad(Assigner arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target.arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayLoader arrayLoad(ArrayLoader arrayReference, Parameterized pardim, Parameterized... parDims)
    {
        return target.arrayLoad(arrayReference, pardim, parDims);
    }

    @Override
    public ArrayStorer arrayStore(ArrayValue arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target.arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer arrayStore(IVariable arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target.arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer arrayStore(MethodInvoker arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target.arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer arrayStore(Assigner arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target.arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayStorer arrayStore(ArrayLoader arrayReference, Parameterized value, Parameterized dim,
        Parameterized... dims)
    {
        return target.arrayStore(arrayReference, value, dim, dims);
    }

    @Override
    public ArrayLength arrayLength(ArrayValue arrayReference, Parameterized... dims)
    {
        return target.arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength arrayLength(IVariable arrayReference, Parameterized... dims)
    {
        return target.arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength arrayLength(MethodInvoker arrayReference, Parameterized... dims)
    {
        return target.arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength arrayLength(Assigner arrayReference, Parameterized... dims)
    {
        return target.arrayLength(arrayReference, dims);
    }

    @Override
    public ArrayLength arrayLength(ArrayLoader arrayReference, Parameterized... dims)
    {
        return target.arrayLength(arrayReference, dims);
    }

    @Override
    public Addition add(Parameterized factor1, Parameterized factor2)
    {
        return target.add(factor1, factor2);
    }

    @Override
    public Subtraction sub(Parameterized factor1, Parameterized factor2)
    {
        return target.sub(factor1, factor2);
    }

    @Override
    public Multiplication mul(Parameterized factor1, Parameterized factor2)
    {
        return target.mul(factor1, factor2);
    }

    @Override
    public Division div(Parameterized factor1, Parameterized factor2)
    {
        return target.div(factor1, factor2);
    }

    @Override
    public Modulus mod(Parameterized factor1, Parameterized factor2)
    {
        return target.mod(factor1, factor2);
    }

    @Override
    public Inverts inverts(Parameterized factor)
    {
        return target.inverts(factor);
    }

    @Override
    public BitAnd bitAnd(Parameterized factor1, Parameterized factor2)
    {
        return target.bitAnd(factor1, factor2);
    }

    @Override
    public BitOr bitOr(Parameterized factor1, Parameterized factor2)
    {
        return target.bitOr(factor1, factor2);
    }

    @Override
    public BitXor bitXor(Parameterized factor1, Parameterized factor2)
    {
        return target.bitXor(factor1, factor2);
    }

    @Override
    public LeftShift leftShift(Parameterized factor1, Parameterized factor2)
    {
        return target.leftShift(factor1, factor2);
    }

    @Override
    public RightShift rightShift(Parameterized factor1, Parameterized factor2)
    {
        return target.rightShift(factor1, factor2);
    }

    @Override
    public UnsignedRightShift unsignedRightShift(Parameterized factor1, Parameterized factor2)
    {
        return target.unsignedRightShift(factor1, factor2);
    }


	@Override
	public PreposeDecrment preDec(Crementable crement) {
		return target.preDec(crement);
	}

	@Override
	public PostposeDecrment postDec(Crementable crement) {
		return target.postDec(crement);
	}

	@Override
	public PreposeIncrment preInc(Crementable crement) {
		return target.preInc(crement);
	}

	@Override
	public PostposeIncrment postInc(Crementable crement) {
		return target.postInc(crement);
	}
	

    @Override
    public GreaterThan greaterThan(Parameterized factor1, Parameterized factor2)
    {
        return target.greaterThan(factor1, factor2);
    }

    @Override
    public GreaterEqual greaterEqual(Parameterized factor1, Parameterized factor2)
    {
        return target.greaterEqual(factor1, factor2);
    }

    @Override
    public LessThan lessThan(Parameterized factor1, Parameterized factor2)
    {
        return target.lessThan(factor1, factor2);
    }

    @Override
    public LessEqual lessEqual(Parameterized factor1, Parameterized factor2)
    {
        return target.lessEqual(factor1, factor2);
    }

    @Override
    public Equal equal(Parameterized factor1, Parameterized factor2)
    {
        return target.equal(factor1, factor2);
    }

    @Override
    public NotEqual notEqual(Parameterized factor1, Parameterized factor2)
    {
        return target.notEqual(factor1, factor2);
    }

    @Override
    public LogicalAnd logicalAnd(Parameterized factor1, Parameterized factor2)
    {
        return target.logicalAnd(factor1, factor2);
    }

    @Override
    public LogicalOr logicalOr(Parameterized factor1, Parameterized factor2)
    {
        return target.logicalOr(factor1, factor2);
    }

    @Override
    public LogicalXor logicalXor(Parameterized factor1, Parameterized factor2)
    {
        return target.logicalXor(factor1, factor2);
    }

    @Override
    public ShortCircuitAnd conditionalAnd(Parameterized factor1, Parameterized factor2)
    {
        return target.conditionalAnd(factor1, factor2);
    }

    @Override
    public ShortCircuitOr conditionalOr(Parameterized factor1, Parameterized factor2)
    {
        return target.conditionalOr(factor1, factor2);
    }

    @Override
    public Not not(Parameterized factor)
    {
        return target.not(factor);
    }

	@Override
	public IF ifThen(IF ifBlock) {
		return target.ifThen(ifBlock);
	}
    
    

    @Override
    public While whileDo(While whileLoop)
    {
        return target.whileDo(whileLoop);
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
    public Try tryDo(Try tryPara)
    {
        return target.tryDo(tryPara);
    }

    /*@Override
    public Synchronized syn(Synchronized sync)
    {
        return proxy.syn(sync);
    }*/

    @Override
    public CheckCast checkCast(Parameterized cc, AClass to)
    {
        return target.checkCast(cc, to);
    }

    @Override
    public Negative neg(Parameterized factor)
    {
        return target.neg(factor);
    }

    @Override
    public TernaryOperator ternary(Parameterized exp1, Parameterized exp2, Parameterized exp3)
    {
        return target.ternary(exp1, exp2, exp3);
    }

    @Override
    public Parameterized append(Parameterized par1, Parameterized... pars)
    {
        return target.append(par1, pars);
    }

    @Override
    public Parameterized instanceOf(Parameterized obj, AClass type)
    {
        return target.instanceOf(obj, type);
    }

    @Override
    public void breakOut()
    {
        target.breakOut();
    }

    @Override
    public void continueOut()
    {
        target.continueOut();
    }

    @Override
    public void throwException(Parameterized exception)
    {
        target.throwException(exception);
    }

    @Override
    public Return runReturn()
    {
        return target.runReturn();
    }

    @Override
    public Return runReturn(Parameterized parame)
    {
        return target.runReturn(parame);
    }

}

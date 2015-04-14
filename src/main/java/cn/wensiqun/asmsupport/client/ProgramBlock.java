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
package cn.wensiqun.asmsupport.client;

import java.lang.reflect.Array;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.ArrayClass;
import cn.wensiqun.asmsupport.core.clazz.NewMemberClass;
import cn.wensiqun.asmsupport.core.definition.value.Value;
import cn.wensiqun.asmsupport.core.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.core.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.definition.variable.SuperVariable;
import cn.wensiqun.asmsupport.core.definition.variable.ThisVariable;
import cn.wensiqun.asmsupport.core.operator.Return;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLength;
import cn.wensiqun.asmsupport.core.operator.array.ArrayLoader;
import cn.wensiqun.asmsupport.core.operator.array.ArrayStorer;
import cn.wensiqun.asmsupport.core.operator.array.ArrayValue;
import cn.wensiqun.asmsupport.core.operator.assign.Assigner;
import cn.wensiqun.asmsupport.core.operator.checkcast.CheckCast;
import cn.wensiqun.asmsupport.core.operator.logical.Not;
import cn.wensiqun.asmsupport.core.operator.method.MethodInvoker;
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
import cn.wensiqun.asmsupport.standard.action.ActionSet;

public class ProgramBlock<B extends ProgramBlockInternal> implements
		ActionSet<ClientParameterized, IF, While, DoWhile, ForEach, Try, Synchronized> {

	B target;

	public LocalVariable[] getMethodArguments() {
		return target.getMethodArguments();
	}

	/**
	 * get current method owner.
	 * 
	 * @return
	 */
	public NewMemberClass getMethodOwner() {
		return target.getMethodOwner();
	}

	@Override
	public ThisVariable this_() {
		return target.this_();
	}

	@Override
	public GlobalVariable this_(String name) {
		return target.this_(name);
	}

	@Override
	public SuperVariable super_() {
		return target.super_();
	}

	@Override
	public LocalVariable var(String name, Class<?> type, ClientParameterized para) {
		return target.var(name, type, para.target);
	}

	@Override
	public LocalVariable var(Class<?> type, ClientParameterized para) {
		return target.var(type, para.target);
	}

	@Override
	public LocalVariable var(String name, AClass type, ClientParameterized para) {
		return target.var(name, type, para.target);
	}

	@Override
	public LocalVariable var(AClass type, ClientParameterized para) {
		return target.var(type, para.target);
	}

	@Override
	public LocalVariable var(String name, AClass aClass, boolean anonymous, ClientParameterized para) {
		return target.var(name, aClass, anonymous, para.target);
	}

	@Override
	public LocalVariable arrayvar2dim(String name, ArrayClass aClass,
			boolean anonymous, ClientParameterized... allocateDim) {
		return target.arrayvar2dim(name, aClass, anonymous, this.client2Internal(allocateDim));
	}

	@Override
	public LocalVariable arrayvar2dim(String name, ArrayClass type,
			ClientParameterized... dims) {
		return target.arrayvar2dim(name, type, this.client2Internal(dims));
	}

	@Override
	public LocalVariable arrayvar2dim(String name, Class<?> type,
			ClientParameterized... dims) {
		return target.arrayvar2dim(name, type, this.client2Internal(dims));
	}

	@Override
	public LocalVariable arrayvar2dim(ArrayClass type, ClientParameterized... dims) {
		return target.arrayvar2dim(type, this.client2Internal(dims));
	}

	@Override
	public LocalVariable arrayvar2dim(Class<?> arrayType, ClientParameterized... dims) {
		return target.arrayvar2dim(arrayType, this.client2Internal(dims));
	}

	@Override
	public LocalVariable arrayvar(String name, ArrayClass aClass,
			boolean anonymous, ClientParameterized value) {
		return target.arrayvar(name, aClass, anonymous, value.target);
	}

	@Override
	public LocalVariable arrayvar(String name, ArrayClass aClass,
			boolean anonymous, Object parameterizedArray) {
		return target.arrayvar(name, aClass, anonymous, client2Internal(parameterizedArray));
	}

	@Override
	public LocalVariable arrayvar(String name, ArrayClass type, ClientParameterized value) {
		return target.arrayvar(name, type, value.target);
	}

	@Override
	public LocalVariable arrayvar(String name, Class<?> type, ClientParameterized value) {
		return target.arrayvar(name, type, value.target);
	}

	@Override
	public LocalVariable arrayvar(ArrayClass type, ClientParameterized value) {
		return target.arrayvar(type, value.target);
	}

	@Override
	public LocalVariable arrayvar(Class<?> type, ClientParameterized value) {
		return target.arrayvar(type, value.target);
	}

	@Override
	public LocalVariable arrayvar(String name, ArrayClass type, Object parameterizedArray) {
		return target.arrayvar(name, type, client2Internal(parameterizedArray));
	}

	@Override
	public LocalVariable arrayvar(String name, Class<?> type, Object parameterizedArray) {
		return target.arrayvar(name, type, client2Internal(parameterizedArray));
	}

	@Override
	public LocalVariable arrayvar(ArrayClass type, Object parameterizedArray) {
		return target.arrayvar(type, client2Internal(parameterizedArray));
	}

	@Override
	public LocalVariable arrayvar(Class<?> type, Object parameterizedArray) {
		return target.arrayvar(type, client2Internal(parameterizedArray));
	}

	@Override
	public Assigner assign(ExplicitVariable variable, ClientParameterized val) {
		return target.assign(variable, val.target);
	}

	@Override
	public MethodInvoker call(ClientParameterized objRef, String methodName,
			ClientParameterized... arguments) {
		return target.call(objRef.target, methodName, this.client2Internal(arguments));
	}

	@Override
	public MethodInvoker call(String methodName, ClientParameterized... args) {
		return target.call(methodName, this.client2Internal(args));
	}

	@Override
	public MethodInvoker call(AClass owner, String methodName, ClientParameterized... arguments) {
		return target.call(owner, methodName, this.client2Internal(arguments));
	}
    
	@Override
    public final MethodInvoker call(Class<?> owner, String methodName, ClientParameterized... arguments) {
    	return target.call(owner, methodName, this.client2Internal(arguments));
    }

	@Override
	public MethodInvoker new_(Class<?> owner, ClientParameterized... arguments) {
		return target.new_(owner, this.client2Internal(arguments));
	}

	@Override
	public MethodInvoker new_(AClass owner, ClientParameterized... arguments) {
		return target.new_(owner, this.client2Internal(arguments));
	}

	@Override
	public MethodInvoker callOrig() {
		return target.callOrig();
	}

	@Override
	public ArrayValue makeArray(ArrayClass aClass,
			ClientParameterized... allocateDims) {
		return target.makeArray(aClass, this.client2Internal(allocateDims));
	}

	@Override
	public ArrayValue makeArray(Class<?> arraytype, ClientParameterized... dimensions) {
		return target.makeArray(arraytype, this.client2Internal(dimensions));
	}

	@Override
	public ArrayValue newarray(ArrayClass aClass, Object arrayObject) {
		return target.newarray(aClass, client2Internal(arrayObject));
	}

	@Override
	public ArrayValue newarray(Class<?> type, Object arrayObject) {
		return target.newarray(type, client2Internal(arrayObject));
	}

	@Override
	public ArrayLoader arrayLoad(ClientParameterized arrayReference,
			ClientParameterized pardim, ClientParameterized... parDims) {
		return target.arrayLoad(arrayReference.target, pardim.target, client2Internal(parDims));
	}
	
	@Override
	public ArrayStorer arrayStore(ClientParameterized arrayReference,
			ClientParameterized value, ClientParameterized dim, ClientParameterized... dims) {
		return target.arrayStore(arrayReference.target, value.target, dim.target, client2Internal(dims));
	}

	@Override
	public ArrayLength arrayLength(ClientParameterized arrayReference, ClientParameterized... dims) {
		return target.arrayLength(arrayReference.target, client2Internal(dims));
	}
	
	@Override
	public ClientAdd add(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientAdd(target.add(factor1.target, factor2.target));
	}

	@Override
	public ClientSub sub(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientSub(target.sub(factor1.target, factor2.target));
	}

	@Override
	public ClientMul mul(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientMul(target.mul(factor1.target, factor2.target));
	}

	@Override
	public ClientDiv div(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientDiv(target.div(factor1.target, factor2.target));
	}

	@Override
	public ClientMod mod(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientMod(target.mod(factor1.target, factor2.target));
	}

	@Override
	public Reverse reverse(ClientParameterized factor) {
		return target.reverse(factor.target);
	}

	@Override
	public BitAnd band(ClientParameterized factor1, ClientParameterized factor2) {
		return target.band(factor1.target, factor2.target);
	}

	@Override
	public BitOr bor(ClientParameterized factor1, ClientParameterized factor2) {
		return target.bor(factor1.target, factor2.target);
	}

	@Override
	public BitXor bxor(ClientParameterized factor1, ClientParameterized factor2) {
		return target.bxor(factor1.target, factor2.target);
	}

	@Override
	public ShiftLeft shl(ClientParameterized factor1, ClientParameterized factor2) {
		return target.shl(factor1.target, factor2.target);
	}

	@Override
	public ShiftRight shr(ClientParameterized factor1, ClientParameterized factor2) {
		return target.shr(factor1.target, factor2.target);
	}

	@Override
	public UnsignedShiftRight ushr(ClientParameterized factor1, ClientParameterized factor2) {
		return target.ushr(factor1.target, factor2.target);
	}

	@Override
	public PreposeDecrment predec(ClientParameterized crement) {
		return target.predec(crement.target);
	}

	@Override
	public PostposeDecrment postdec(ClientParameterized crement) {
		return target.postdec(crement.target);
	}

	@Override
	public PreposeIncrment preinc(ClientParameterized crement) {
		return target.preinc(crement.target);
	}

	@Override
	public PostposeIncrment postinc(ClientParameterized crement) {
		return target.postinc(crement.target);
	}

	@Override
	public GreaterThan gt(ClientParameterized factor1, ClientParameterized factor2) {
		return target.gt(factor1.target, factor2.target);
	}

	@Override
	public GreaterEqual ge(ClientParameterized factor1, ClientParameterized factor2) {
		return target.ge(factor1.target, factor2.target);
	}

	@Override
	public LessThan lt(ClientParameterized factor1, ClientParameterized factor2) {
		return target.lt(factor1.target, factor2.target);
	}

	@Override
	public LessEqual le(ClientParameterized factor1, ClientParameterized factor2) {
		return target.le(factor1.target, factor2.target);
	}

	@Override
	public Equal eq(ClientParameterized factor1, ClientParameterized factor2) {
		return target.eq(factor1.target, factor2.target);
	}

	@Override
	public NotEqual ne(ClientParameterized factor1, ClientParameterized factor2) {
		return target.ne(factor1.target, factor2.target);
	}

	@Override
	public ClientLogicalAnd logicalAnd(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientLogicalAnd(target.logicalAnd(factor1.target, factor2.target));
	}

	@Override
	public ClientLogicalOr logicalOr(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientLogicalOr(target.logicalOr(factor1.target, factor2.target));
	}

	@Override
	public ClientLogicalXor logicalXor(ClientParameterized factor1, ClientParameterized factor2) {
		return new ClientLogicalXor(target.logicalXor(factor1.target, factor2.target));
	}

	@Override
	public ClientShortCircuitAnd and(ClientParameterized factor1, ClientParameterized factor2,
			ClientParameterized... otherFactor) {
		return new ClientShortCircuitAnd(target.and(factor1.target, factor2.target, client2Internal(otherFactor)));
	}

	@Override
	public ClientShortCircuitOr or(ClientParameterized factor1, ClientParameterized factor2,
			ClientParameterized... otherFactor) {
		return new ClientShortCircuitOr(target.or(factor1.target, factor2.target, client2Internal(otherFactor)));
	}

	@Override
	public Not no(ClientParameterized factor) {
		return target.no(factor.target);
	}

	@Override
	public CheckCast checkcast(ClientParameterized cc, AClass to) {
		return target.checkcast(cc.target, to);
	}

	@Override
	public CheckCast checkcast(ClientParameterized cc, Class<?> to) {
		return target.checkcast(cc.target, to);
	}

	@Override
	public Negative neg(ClientParameterized factor) {
		return target.neg(factor.target);
	}

	@Override
	public TernaryOperator ternary(ClientParameterized exp1, ClientParameterized exp2,
			ClientParameterized exp3) {
		return target.ternary(exp1.target, exp2.target, exp3.target);
	}

	@Override
	public ClientStringAppender stradd(ClientParameterized par1, ClientParameterized... pars) {
		return new ClientStringAppender(target.stradd(par1.target, client2Internal(pars)));
	}

	@Override
	public ClientInstanceof instanceof_(ClientParameterized obj, AClass type) {
		return new ClientInstanceof(target.instanceof_(obj.target, type));
	}

	@Override
	public ClientParameterized instanceof_(ClientParameterized obj, Class<?> type) {
		return new ClientParameterized(target.instanceof_(obj.target, type));
	}

	@Override
	public void break_() {
		target.break_();
	}

	@Override
	public void continue_() {
		target.continue_();
	}

	@Override
	public ClientThrow throw_(ClientParameterized exception) {
		return new ClientThrow(target.throw_(exception.target));
	}

	@Override
	public Return return_() {
		return target.return_();
	}

	@Override
	public ClientReturn return_(ClientParameterized para) {
		return new ClientReturn(target.return_(para.target));
	}

	@Override
	public IF if_(IF ifBlock) {
		target.if_(ifBlock.target);
		return ifBlock;
	}

	@Override
	public While while_(While whileLoop) {
		target.while_(whileLoop.target);
		return whileLoop;
	}

	@Override
	public DoWhile dowhile(DoWhile doWhile) {
		target.dowhile(doWhile.target);
		return doWhile;
	}

	@Override
	public ForEach for_(ForEach forEach) {
		target.for_(forEach.target);
		return forEach;
	}

	@Override
	public Try try_(Try tryClient) {
		target.try_(tryClient.target);
		return tryClient;
	}

	@Override
	public Synchronized sync(Synchronized sync) {
		target.sync(sync.target);
		return sync;
	}

	@Override
	public Value val(Integer val) {
		return target.val(val);
	}

	@Override
	public Value val(Short val) {
		return target.val(val);
	}

	@Override
	public Value val(Byte val) {
		return target.val(val);
	}

	@Override
	public Value val(Boolean val) {
		return target.val(val);
	}

	@Override
	public Value val(Long val) {
		return target.val(val);
	}

	@Override
	public Value val(Double val) {
		return target.val(val);
	}

	@Override
	public Value val(Character val) {
		return target.val(val);
	}

	@Override
	public Value val(Float val) {
		return target.val(val);
	}

	@Override
	public Value val(AClass val) {
		return target.val(val);
	}

	@Override
	public Value val(Class<?> val) {
		return target.val(val);
	}

	@Override
	public Value val(String val) {
		return target.val(val);
	}

	@Override
	public Value null_(AClass type) {
		return target.null_(type);
	}

	@Override
	public Value null_(Class<?> type) {
		return target.null_(type);
	}

	@Override
	public AClass defType(Class<?> cls) {
		return target.defType(cls);
	}

	@Override
	public ArrayClass defArrayType(Class<?> arrayCls) {
		return target.defArrayType(arrayCls);
	}

	@Override
	public ArrayClass defArrayType(Class<?> cls, int dim) {
		return target.defArrayType(cls, dim);
	}

	@Override
	public ArrayClass defArrayType(AClass rootComponent, int dim) {
		return target.defArrayType(rootComponent, dim);
	}
	
	/**
	 * Convert ClientParameterized to InternalParameterized
	 * 
	 * @param pars
	 * @return
	 */
	InternalParameterized[] client2Internal(ClientParameterized... pars) {
		if(pars == null) {
			return null;
		}
		InternalParameterized[] paras = new InternalParameterized[pars.length];
		for(int i=0; i<pars.length; i++) {
			paras[i] = pars[i].target;
		}
		return paras;
	}
	
	/**
	 * Convert multiple dimension ClientParameterized array to  InternalParameterized array.
	 * 
	 * @param clientArray
	 * @return
	 */
	Object client2Internal(Object clientArray) {
	    if(clientArray == null) {
	        throw new NullPointerException("Client is null.");
	    }
	    if(clientArray.getClass().isArray()) {
            int len = Array.getLength(clientArray);
	        Object[] internalArray = new Object[len];
	        for(int i=0; i<len; i++) {
	            internalArray[i] = client2Internal(Array.get(clientArray, i));
	        }
	        return internalArray;
	    } else if (clientArray instanceof ClientParameterized){
	        return ((ClientParameterized)clientArray).target;
	    }
	    throw new IllegalArgumentException();
	}
	

}

package example.operators;


import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBodyInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreatorInternal;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import example.AbstractExample;

public class CrementOperatorGenerate extends AbstractExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClassCreatorInternal creator = new ClassCreatorInternal(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.operators.CrementOperatorGenerateExample", null, null);
		
		/*
		 * 对应java代码
         * public void demonstrate() {
         *     System.out.println("******************************demonstrate***************************");
         *     int a = 1;
         *     int b = 2;
         *     int c;
         *     int d;
         *     c = ++b;
         *     d = a++;
         *     c++;
         *     System.out.println("a = " + a);
         *     System.out.println("b = " + b);
         *     System.out.println("c = " + c);
         *     System.out.println("d = " + d);
         * }
		 */
		creator.createMethod("demonstrate", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBodyInternal() {
			@Override
			public void body(LocalVariable... argus) {
				_invoke(systemOut, "println", Value.value("******************************demonstrate***************************"));
				
				//int a = 1;
			    LocalVariable a = _createVariable("a", AClass.INT_ACLASS, false, Value.value(1));
			    //int b = 2;
			    LocalVariable b = _createVariable("b", AClass.INT_ACLASS, false, Value.value(2));
			    //int c = ++b;
			    LocalVariable c = _createVariable("c", AClass.INT_ACLASS, false, _preInc(b));
			    //d = a++;
			    LocalVariable d = _createVariable("d", AClass.INT_ACLASS, false, _postInc(a));
			    //c++;
			    _postInc(c);
			    
			    //System.out.println("a = " + a);
				_invoke(systemOut, "println", _append(Value.value("a = "), a)); 
				_invoke(systemOut, "println", _append(Value.value("b = "), b)); 
				_invoke(systemOut, "println", _append(Value.value("c = "), c)); 
				_invoke(systemOut, "println", _append(Value.value("d = "), d)); 
				_return();
			}
		});
		
		/*
		 * java code:
		 * public void incrementAndDecrement(String[] argv) {
		 *    System.out.println("******************************incrementAndDecrement***************************");
         *    int count = 10;
         *    ++count;
         *    --count;
         *    System.out.println(count);
         * }
		 */
		creator.createMethod("incrementAndDecrement", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBodyInternal() {
		    @Override
		    public void body(LocalVariable... argus) {
				_invoke(systemOut, "println", Value.value("******************************incrementAndDecrement***************************"));
				//int count = 10;
				LocalVariable count = _createVariable("count", AClass.INT_ACLASS, false, Value.value(10));
				// ++count
				_preInc(count); 
				// --count;
				_postDec(count); 
				// System.out.println("count = " +  count);
				_invoke(systemOut, "println", _append(Value.value("count = "), count));
				_return();
			}
		});
		
		creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
				Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBodyInternal(){

			@Override
			public void body(LocalVariable... argus) {
				LocalVariable currentObj = _createVariable("currentObj", getMethodOwner(), false, _new(getMethodOwner()));
				_invoke(currentObj, "demonstrate");
				_invoke(currentObj, "incrementAndDecrement");
				_return();
			}
        });
		generate(creator);
	}
}

package example.operators;


import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import example.AbstractExample;

public class AssignmentGenerate extends AbstractExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.operators.AssignmentGenerateExample", null, null);

		creator.createStaticMethod("commonMethod", null, null, AClass.STRING_ACLASS, null, Opcodes.ACC_PUBLIC, new StaticMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				_return(Value.value("I'm from commonMethod"));
			}
		});
		
		creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
				Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				//创建个String变量默认赋值为null
				LocalVariable string = _createVariable("string", AClass.STRING_ACLASS, false, null);
				
				_assign(string, _invokeStatic(getMethodOwner(), "commonMethod"));
				_invoke(systemOut, "println", _append(Value.value("first asign :"), string));
				
				_assign(string, Value.value("second assing value"));
				_invoke(systemOut, "println", _append(Value.value("second asign :"), string));
				
				_return();
			}
        });
		generate(creator);
	}
}

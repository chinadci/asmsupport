package cn.wensiqun.asmsupport.block.loop;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.TesterStatics;
import example.AbstractExample;

public class WhileBlockGenerator extends AbstractExample {

	public static void main(String[] args)
    {
		ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.WhileBlockGeneratorExample", null, null);
		
		 creator.createStaticMethod("test", null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

	            @Override
	            public void body(LocalVariable... argus)
	            {
	            	final LocalVariable i  = createVariable("i", AClass.LONG_ACLASS, false, Value.value(10));
	            	final LocalVariable b  = createVariable("b", AClass.BYTE_ACLASS, false, Value.value(10));
	            	whileDo(new While(greaterThan(postDec(i), Value.value(0))){

						@Override
						public void body() {
							invokeStatic(TesterStatics.ATesterStatics, 
		                    		"actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", i));
							postInc(b);
						}
	            		
	            	});
					invokeStatic(TesterStatics.ATesterStatics, 
                    		"actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", b));
	            	runReturn();
	            }
		 });
	        
        creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
            Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){
                @Override
                public void body(LocalVariable... argus) {
                	invokeStatic(getMethodOwner(), "test");
                    runReturn();
                }
        
        });

        generate(creator);
    }
	
}

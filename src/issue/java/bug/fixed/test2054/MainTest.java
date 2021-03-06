package bug.fixed.test2054;

import java.lang.reflect.Method;

import junit.framework.Assert;
import cn.wensiqun.asmsupport.core.block.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.creator.clazz.ClassCreator;
import cn.wensiqun.asmsupport.core.definition.value.Value;
import cn.wensiqun.asmsupport.core.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.core.exception.NoSuchMethod;
import cn.wensiqun.asmsupport.org.objectweb.asm.Opcodes;

public class MainTest {

	public static void main(String[] args) throws Throwable {
		ClassCreator creator = 
				new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "fixed.Test2054", null, null);
		creator.setClassOutPutPath(".//target//issue");
		
		creator.createStaticMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", new AClass[]{AClassFactory.getType(String[].class)}, new String[]{"args"}, null, null,
                new StaticMethodBodyInternal(){

            @Override
            public void body(LocalVariable... argus) {
            	call(getType(System.class).field("out"), "println", val("Prepare to build MainObject."));
                new_(AClassFactory.getType(MyObject.class), Value.value("i'm direct pass argument."));
            	return_();
            }
        });
		Class<?> cls = creator.startup();
		Method main = cls.getMethod("main", String[].class);
		main.invoke(main, new Object[0]);
	}
	
	@org.junit.Test
	public void test() throws Exception{
		try{
			main(null);
			Assert.assertTrue(false);
		}catch(NoSuchMethod e){
	        Assert.assertTrue(true);
		}catch(Throwable e){
			Assert.assertTrue(false);
		}
	}

}

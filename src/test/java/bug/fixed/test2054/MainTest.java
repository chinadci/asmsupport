package bug.fixed.test2054;

import junit.framework.Assert;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreatorInternal;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.exception.NoSuchMethod;
import cn.wensiqun.asmsupportasm.Opcodes;
import cn.wensiqun.asmsupportgeneric.creator.IClassContext;

public class MainTest {

	public static Class<?> generate(IClassContext creator){
		creator.setClassOutPutPath(".//target//");
		Class<?> cls = creator.startup();
		if(creator instanceof ClassCreatorInternal){
			try {
				cls.getMethod("main", String[].class).invoke(cls, new Object[]{null});
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return cls;
	}
	
	public static void main(String[] args) {
		ClassCreatorInternal creator = 
				new ClassCreatorInternal(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "bug.fixed.Test2054", null, null);
        
		creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBodyInternal(){

            @Override
            public void body(LocalVariable... argus) {
                _new(AClassFactory.getProductClass(MyObject.class), Value.value("i'm direct pass argument."));
            	_return();
            }
        });
		generate(creator);
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

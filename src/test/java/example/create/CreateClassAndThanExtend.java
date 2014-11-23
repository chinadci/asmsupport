package example.create;

import java.util.Random;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.control.condition.ElseInternal;
import cn.wensiqun.asmsupport.block.classes.control.condition.IFInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBody;
import cn.wensiqun.asmsupport.block.classes.method.common.ModifiedMethodBody;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.creator.ClassModifier;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.ASConstant;
import example.AbstractExample;

public class CreateClassAndThanExtend extends AbstractExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassCreator superCreator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.create.CreateClassAndThanExtendExampleSuper", null, null);
		
		superCreator.createMethod("commonMethod", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				_invoke(systemOut, "println", Value.value("say hello!"));
				_return();
			}
		});
		

		superCreator.setClassOutPutPath(".//");
		Class superClass = superCreator.startup();
		
		final GlobalVariable out = systemOut;
		
		ClassModifier byModifyModifer = new ClassModifier(ByModify.class);
		byModifyModifer.createGlobalVariable("age", Opcodes.ACC_STATIC + Opcodes.ACC_PRIVATE, AClass.INT_ACLASS);
		byModifyModifer.createMethod("asmcreate", null,null,null,null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){
			@Override
			public void body(LocalVariable... argus) {
				_invoke(out, "println", Value.value("created by asm"));
				_return();
			}
		});
		
		byModifyModifer.modifyMethod(ASConstant.CLINIT, null, new ModifiedMethodBody(){
			@Override
			public void body(LocalVariable... argus) {
				GlobalVariable age = getMethodOwner().getGlobalVariable("age");
				_assign(age, Value.value(20));
				this._invokeOriginalMethod();
				GlobalVariable name = getMethodOwner().getGlobalVariable("name");
				_assign(name, Value.value("wensiqun"));
				_invoke(out, "println", name);
				_return();
			}
		});
		
		byModifyModifer.modifyMethod("helloWorld", null, new ModifiedMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				_invoke(out, "println", Value.value("before"));
				
				AClass randomClass = AClassFactory.getProductClass(Random.class);
				LocalVariable random = this._createVariable("random", randomClass, false, this._new(randomClass, Value.value(1L)));
				_if(new IFInternal(_invoke(random, "nextBoolean")){
					@Override
					public void body() {
						_invokeOriginalMethod();
					}

				})._else(new ElseInternal(){
					@Override
					public void body() {
						_invoke(out, "println", Value.value("call self"));
					}
					
				});
				_invoke(out, "println", Value.value("after"));
				_return();
			}
			
		});
		
		byModifyModifer.modifyMethod("String", null, new ModifiedMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				_invoke(out, "println", Value.value("before"));
				LocalVariable lv = this._createVariable(null, getOriginalMethodReturnClass(), true, _invokeOriginalMethod());
				_invoke(out, "println", Value.value("after"));	
				_return(lv);
			}
			
		});
		byModifyModifer.setClassOutPutPath("./generated");
		Class<?> ByModify = byModifyModifer.startup();
		
        ClassCreator childCreator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.create.CreateClassAndThanExtendExample", ByModify, null);
		
		/*childCreator.createMethod("commonMethod", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				invoke(systemOut, "println", Value.value("say hello!"));
				runReturn();
			}
		});*/
		

		childCreator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
				Opcodes.ACC_PUBLIC, new StaticMethodBody(){

	        @Override
			public void body(LocalVariable... argus) {
	        	_invoke(_new(getMethodOwner()), "helloWorld");
			    _return();
			}
			
		});
		
		childCreator.setClassOutPutPath(".//");
		
		//这个就是个开关。前面我们把该创建的方法变量都放到了传送带上了。调用startup
		//启动传送带，将上面的东西一个个处理给我返回一个我们需要的成品（就是class了）
		Class<?> cls = childCreator.startup();
		
		//如果创建的是非枚举类型或者非接口类型则调用main方法
		if(childCreator instanceof ClassCreator){
			try {
				cls.getMethod("main", String[].class).invoke(cls, new Object[]{null});
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		
	}

}

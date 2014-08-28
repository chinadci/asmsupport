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
                    
                    final LocalVariable intVar1  = createVariable("intVar1", AClass.INT_ACLASS, false, Value.value(10));
                    
                    whileDo(new While(greaterThan(postDec(intVar1), Value.value(0))){

                        @Override
                        public void body() {
                            invokeStatic(TesterStatics.ATesterStatics, 
                                    "actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", intVar1));
                        }
                        
                    });
                    
                    final LocalVariable intVar2  = createVariable("intVar2", AClass.INT_ACLASS, false, Value.value(10));
                    
                    whileDo(new While(greaterThan(preDec(intVar2), Value.value(0))){

                        @Override
                        public void body() {
                            invokeStatic(TesterStatics.ATesterStatics, 
                                    "actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", intVar2));
                        }
                        
                    });
	                
                    final LocalVariable byteVar  = createVariable("byteVar", AClass.BYTE_ACLASS, false, Value.value((byte)10));
                    
	            	whileDo(new While(greaterThan(postDec(byteVar), Value.value(0))){

						@Override
						public void body() {
							invokeStatic(TesterStatics.ATesterStatics, 
		                    		"actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", byteVar));
						}
	            		
	            	});
	            	
	            	final LocalVariable doubleVar  = createVariable("doubleVar", AClass.DOUBLE_ACLASS, false, Value.value(10D));
                    
                    whileDo(new While(greaterThan(preDec(doubleVar), Value.value(0))){

                        @Override
                        public void body() {
                            invokeStatic(TesterStatics.ATesterStatics, 
                                    "actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", doubleVar));
                        }
                        
                    });
                    
                    final LocalVariable shortObj  = createVariable("shortObj", AClass.SHORT_WRAP_ACLASS, false, Value.value((short)10));
                    
                    whileDo(new While(greaterThan(postDec(shortObj), Value.value((short)0))){

                        @Override
                        public void body() {
                            invokeStatic(TesterStatics.ATesterStatics, 
                                    "actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", shortObj));
                        }
                        
                    });
                    
                    final LocalVariable longObj  = createVariable("longObj", AClass.LONG_WRAP_ACLASS, false, Value.value(10L));
                    
                    whileDo(new While(greaterThan(preDec(longObj), Value.value(0))){

                        @Override
                        public void body() {
                            invokeStatic(TesterStatics.ATesterStatics, 
                                    "actuallyPrintln", invokeStatic(AClass.STRING_ACLASS, "valueOf", longObj));
                        }
                        
                    });
                    
                    
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

package cn.wensiqun.asmsupport.block.exception;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Finally;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Try;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import example.AbstractExample;

public class TryCatchFinallyBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        final AClass nullpointer = AClassFactory.getProductClass(NullPointerException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.TryCatchFinallyBlockGeneratorExample", null, null);
        
        creator.createStaticMethod("runtimeException", null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                throwException(invokeConstructor(runtime));
            }
        });
        
        creator.createStaticMethod("exception", null, null, null, new AClass[]{AClass.EXCEPTION_ACLASS}, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
            }
        });
        
        /*creator.createStaticMethod(testMethodNames.put("tryCatchFinally_tryDirectException"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invoke(systemOut, "println", Value.value("Root"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    |-Try"));
                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
				    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    |-Catch"));
                    }
                    
                }).finallyThan(new Finally(){

					@Override
					public void body() {
						invoke(systemOut, "println", Value.value("    |-Finally"));
					}
                	
                });
                runReturn();
            }
        });*/
        
        /*creator.createStaticMethod(testMethodNames.put("tryCatchFinally_runtimeInTryNoSuitableCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invoke(systemOut, "println", Value.value("Root"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    |-Try"));
                        throwException(invokeConstructor(runtime));
				    }
                    
                }).catchException(new Catch(nullpointer){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    |-Catch"));
                    }
                    
                }).finallyThan(new Finally(){

					@Override
					public void body() {
						invoke(systemOut, "println", Value.value("    |-Finally"));
					}
                	
                });
                runReturn();
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_nestedTryCatchInFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invoke(systemOut, "println", Value.value("Root"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    |-Try"));
                        throwException(invokeConstructor(runtime));
				    }
                    
                }).catchException(new Catch(nullpointer){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    |-Catch"));
                    }
                    
                }).finallyThan(new Finally(){

					@Override
					public void body() {
						invoke(systemOut, "println", Value.value("    |-Finally"));
						tryDo(new Try(){

							@Override
							public void body() {
								invoke(systemOut, "println", Value.value("        |-Try"));
							    invokeStatic(getMethodOwner(), "exception");
							}
							
						}).catchException(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								invoke(systemOut, "println", Value.value("        |-Catch"));
							}
							
						});
						
					}
                	
                });
                runReturn();
            }
        });*/
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_catchDirectException"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invoke(systemOut, "println", Value.value("Root"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    |-Try"));
                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
				    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    |-Catch"));
                        tryDo(new Try(){

							@Override
							public void body() {
								invoke(systemOut, "println", Value.value("        |-Try"));
		                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
							}
                        	
                        }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								invoke(systemOut, "println", Value.value("        |-Catch"));
		                        tryDo(new Try(){

									@Override
									public void body() {
										invoke(systemOut, "println", Value.value("            |-Try"));
				                        invokeStatic(getMethodOwner(), "exception");
									}
		                        	
		                        }).catchException(new Catch(runtime){

									@Override
									public void body(LocalVariable e) {
										invoke(systemOut, "println", Value.value("            |-Catch(RuntimeException)"));
									}
		                        	
		                        }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										invoke(systemOut, "println", Value.value("            |-Catch(Exception)"));
										throwException(invokeConstructor(runtime));
									}
		                        	
		                        });
							}
                        	
                        });
                    }
                    
                }).finallyThan(new Finally(){

					@Override
					public void body() {
						invoke(systemOut, "println", Value.value("    |-Finally"));
                        this.tryDo(new Try(){

							@Override
							public void body() {
								invoke(systemOut, "println", Value.value("        |-Try"));
								throwException(invokeConstructor(runtime));
							}
                        	
                        }).catchException(new Catch(runtime){

							@Override
							public void body(LocalVariable e) {
								invoke(systemOut, "println", Value.value("        |-Catch(RuntimeException)"));
							}
                        	
                        }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								invoke(systemOut, "println", Value.value("        |-Catch(Exception)"));
		                        
							}
                        	
                        });
					}
                	
                });
                runReturn();
            }
        });
        
        
        
        creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
            Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){
                @Override
                public void body(LocalVariable... argus) {
                    for(String name : testMethodNames)
                    {
                        noExceptionCall(this, name);
                    }
                    runReturn();
                }
        
        });
        
        generate(creator);
    }
    
    
    private static void noExceptionCall(ProgramBlock block, final String methodName)
    {
        block.invoke(systemOut, "println", Value.value("=======" + methodName));
        block.tryDo(new Try(){

            @Override
            public void body()
            {
                invokeStatic(getMethodOwner(), methodName);
            }
            
        }).catchException(new Catch(AClass.THROWABLE_ACLASS){

            @Override
            public void body(LocalVariable e)
            {}
            
        });
    }
    
}

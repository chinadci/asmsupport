package cn.wensiqun.asmsupport.block.exception;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.Finally;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.MyList;
import cn.wensiqun.asmsupport.utils.TesterStatics;
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
                _throw(_new(runtime));
            }
        });
        
        creator.createStaticMethod("exception", null, null, null, new AClass[]{AClass.EXCEPTION_ACLASS}, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _throw(_new(AClass.EXCEPTION_ACLASS));
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_tryDirectException"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _throw(_new(AClass.EXCEPTION_ACLASS));
				    }
                    
                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                    }
                    
                })._finally(new Finally(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
					}
                	
                });
                _return();
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_runtimeInTryNoSuitableCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _throw(_new(runtime));
				    }
                    
                })._catch(new Catch(nullpointer){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                    }
                    
                })._finally(new Finally(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
					}
                	
                });
                _return();
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_nestedTryCatchInFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _throw(_new(runtime));
				    }
                    
                })._catch(new Catch(nullpointer){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                    }
                    
                })._finally(new Finally(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
						_try(new Try(){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
							    _invokeStatic(getMethodOwner(), "exception");
							}
							
						})._catch(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch"));
							}
							
						});
						
					}
                	
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_tryMethodException"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
				    }
                    
                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                    }
                    
                })._finally(new Finally(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
					}
                	
                });
                _return();
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("tryCatchFinally_catchDirectException"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _throw(_new(AClass.EXCEPTION_ACLASS));
				    }
                    
                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                        _try(new Try(){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
		                        _throw(_new(AClass.EXCEPTION_ACLASS));
							}
                        	
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch"));
		                        _try(new Try(){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Try"));
				                        _invokeStatic(getMethodOwner(), "exception");
									}
		                        	
		                        })._catch(new Catch(runtime){

									@Override
									public void body(LocalVariable e) {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(RuntimeException)"));
									}
		                        	
		                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(Exception)"));
										_throw(_new(runtime));
									}
		                        	
		                        });
							}
                        	
                        });
                    }
                    
                })._finally(new Finally(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
                        this._try(new Try(){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
								_throw(_new(runtime));
							}
                        	
                        })._catch(new Catch(runtime){

							@Override
							public void body(LocalVariable e) {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
							}
                        	
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

							@Override
							public void body(LocalVariable e) {
								_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
		                        
							}
                        	
                        });
					}
                	
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("complexTryCatchFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                _throw(_new(AClass.EXCEPTION_ACLASS));
                            }
                            
                        })._catch(new Catch(runtime){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
                                _return();
                            }
                            
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
                                _invokeStatic(getMethodOwner(), "runtimeException");
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body() {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Finally"));
                            }
                            
                        });
                    }
                    
                })._catch(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(RuntimeException)"));
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                            }
                            
                        })._catch(new Catch(runtime){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
                            }
                            
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body() {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Finally"));
                            }
                            
                        });
                    }
                    
                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(Exception)"));
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body() {

                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Finally"));
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                _try(new Try(){

                                    @Override
                                    public void body()
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Try"));
                                    }
                                    
                                })._catch(new Catch(runtime){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(RuntimeException)"));
                                    }
                                });
                                _throw(_new(runtime));
                            }
                            
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Exception(Exception)"));
                            }
                            
                        });
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    ===="));
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                _try(new Try(){

                                    @Override
                                    public void body()
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Try"));
                                        _throw(_new(runtime));
                                    }
                                    
                                })._catch(new Catch(runtime){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(RuntimeException)"));
                                        _throw(e);
                                    }
                                    
                                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(Exception)"));
                                    }
                                    
                                });
                            }
                            
                        })._catch(new Catch(runtime){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
                                _return();
                            }
                            
                        })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
                                _try(new Try(){

                                    @Override
                                    public void body()
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Try"));
                                    }
                                    
                                })._catch(new Catch(runtime){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(RuntimeException)"));
                                    }
                                    
                                })._catch(new Catch(AClass.EXCEPTION_ACLASS){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Catch(Exception)"));
                                    }
                                    
                                })._finally(new Finally(){

                                    @Override
                                    public void body() {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("            |-Finally"));
                                    }
                                    
                                });
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body() {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Finally"));
                            }
                            
                        });
                    }
                    
                });
                _return();
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
                    _return();
                }
        
        });
        
        generate(creator);
    }
    
    
    private static void noExceptionCall(ProgramBlock block, final String methodName)
    {
        block._invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("=======" + methodName));
        block._try(new Try(){

            @Override
            public void body()
            {
                _invokeStatic(getMethodOwner(), methodName);
            }
            
        })._catch(new Catch(AClass.THROWABLE_ACLASS){

            @Override
            public void body(LocalVariable e)
            {}
            
        });
    }
    
}

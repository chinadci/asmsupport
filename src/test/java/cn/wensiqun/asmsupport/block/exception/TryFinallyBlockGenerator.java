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

public class TryFinallyBlockGenerator extends AbstractExample
{
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.TryFinallyBlockGeneratorExample", null, null);
        
        creator.createStaticMethod(testMethodNames.put("tryFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
        });

        creator.createStaticMethod(testMethodNames.put("nestedTryFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("start"));
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try{"));
                        _try(new Try(){
                            
                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        })._finally(new Finally(){
                            
                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    }"));
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("end"));
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("tryFinally_TryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _throw(_new(runtime));
                    }
                    
                    public void setFinish(boolean f)
                    {
                    	super.setFinish(f);
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerTryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                _throw(_new(runtime));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                _throw(_new(runtime));
                            }
                            
                        });
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterTryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        _throw(_new(runtime));
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        _throw(_new(runtime));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                _throw(_new(runtime));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                _throw(_new(runtime));
                            }
                            
                        });
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterBothError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        _throw(_new(runtime));
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        _throw(_new(runtime));
                    }
                    
                });
                _return();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothOuterFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new Try(){

                    @Override
                    public void body()
                    {
                        
                        _try(new Try(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                _throw(_new(runtime));
                            }
                            
                        })._finally(new Finally(){

                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                _throw(_new(runtime));
                            }
                            
                        });
                        
                    }
                    
                })._finally(new Finally(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        _throw(_new(runtime));
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

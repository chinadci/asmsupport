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
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
        });

        creator.createStaticMethod(testMethodNames.put("nestedTryFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("start"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try{"));
                        tryDo(new Try(){
                            
                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        }).finallyThen(new Finally(){
                            
                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    }"));
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("end"));
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("tryFinally_TryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                    public void setFinish(boolean f)
                    {
                    	super.setFinish(f);
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerTryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterTryError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        throwException(invokeConstructor(runtime));
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterBothError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                            }
                            
                        });
                        throwException(invokeConstructor(runtime));
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothOuterFinallyError"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        
                        tryDo(new Try(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        throwException(invokeConstructor(runtime));
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
        block.invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("=======" + methodName));
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

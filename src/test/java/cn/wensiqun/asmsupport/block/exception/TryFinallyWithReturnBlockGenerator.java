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

public class TryFinallyWithReturnBlockGenerator extends AbstractExample
{
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.TryFinallyWithReturnBlockGeneratorExample", null, null);
        
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
        
        creator.createStaticMethod(testMethodNames.put("tryFinally_TryReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        runReturn();
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
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerTryReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                                runReturn();
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
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerFinallyReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                                runReturn();
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
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterTryReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                        runReturn();
                        
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
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterFinallyReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                        runReturn();
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                                runReturn();
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                runReturn();
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
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_OutterBothReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                        runReturn();
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        runReturn();
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally_InnerBothOuterFinallyReturn"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

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
                                runReturn();
                            }
                            
                        }).finallyThen(new Finally(){

                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        finally_inner"));
                                runReturn();
                            }
                            
                        });
                        
                    }
                    
                }).finallyThen(new Finally(){

                    @Override
                    public void body()
                    {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    finally"));
                        runReturn();
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

package cn.wensiqun.asmsupport.utils.block.exception;

import java.util.ArrayList;
import java.util.List;

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

public class TryFinallyBlockGenerator extends AbstractExample
{
    
    public static class MyList extends ArrayList<String>{
        
        public String put(String s){
            this.add(s);
            return s;
        }
    
    }
    
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
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
                    }
                    
                });
                runReturn();
            }
        });
        
        creator.createStaticMethod(testMethodNames.put("nestedTryFinally"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                invoke(systemOut, "println", Value.value("start"));
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try{"));
                        tryDo(new Try(){
                            
                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        try_inner"));
                            }
                            
                        }).finallyThan(new Finally(){
                            
                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                            }
                            
                        });
                        invoke(systemOut, "println", Value.value("    }"));
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
                    }
                    
                });
                invoke(systemOut, "println", Value.value("end"));
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
                        invoke(systemOut, "println", Value.value("    try"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                            }
                            
                        });
                        throwException(invokeConstructor(runtime));
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                            }
                            
                        });
                        throwException(invokeConstructor(runtime));
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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
                                invoke(systemOut, "println", Value.value("        try_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThan(new Finally(){

                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("        finally_inner"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        });
                        
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    finally"));
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

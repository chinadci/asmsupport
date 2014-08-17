package cn.wensiqun.asmsupport.block.exception;

import java.util.ArrayList;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Try;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import example.AbstractExample;

public class TryCatchBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.TryCatchBlockGeneratorExample", null, null);
        
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
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_errorBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_errorAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionBeforePrintInTry_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionBeforePrintInTry_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        });        

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionAfterPrintInTry_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionAfterPrintInTry_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });


        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        });


        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invokeStatic(getMethodOwner(), "exception");
                        invoke(systemOut, "println", Value.value("    try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                runReturn();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    exception"));
                    }
                    
                });
                runReturn();
            }
        }); 
        

        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("    try"));
                        invokeStatic(getMethodOwner(), "exception");
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invokeStatic(getMethodOwner(), "runtimeException");
                        invoke(systemOut, "println", Value.value("    runtime exception"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("    exception"));
                        invokeStatic(getMethodOwner(), "runtimeException");
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

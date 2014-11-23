package cn.wensiqun.asmsupport.block.exception;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.block.classes.control.exception.CatchInternal;
import cn.wensiqun.asmsupport.block.classes.control.exception.TryInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.MyList;
import cn.wensiqun.asmsupport.utils.TesterStatics;
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
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_errorBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_errorAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatch_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionBeforePrintInTry_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionBeforePrintInTry_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        });        

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionAfterPrintInTry_runtimeExceptionBeforePrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatch_exceptionAfterPrintInTry_runtimeExceptionAfterPrintInCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });

        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });


        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        });


        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(getMethodOwner(), "exception");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                });
                _return();
            }
        }); 
        

        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                    }
                    
                });
                _return();
            }
        }); 
        

        
        
        creator.createStaticMethod(testMethodNames.put("tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch"), null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                _try(new TryInternal(){

                    @Override
                    public void body()
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    try"));
                        _invokeStatic(getMethodOwner(), "exception");
                    }
                    
                })._catch(new CatchInternal(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(getMethodOwner(), "runtimeException");
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    runtime exception"));
                    }
                    
                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    exception"));
                        _invokeStatic(getMethodOwner(), "runtimeException");
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
    
    
    private static void noExceptionCall(ProgramBlockInternal block, final String methodName)
    {
        block._invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("=======" + methodName));
        block._try(new TryInternal(){

            @Override
            public void body()
            {
                _invokeStatic(getMethodOwner(), methodName);
            }
            
        })._catch(new CatchInternal(AClass.THROWABLE_ACLASS){

            @Override
            public void body(LocalVariable e)
            {}
            
        });
    }
    
}

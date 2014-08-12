package example.block;

import org.objectweb.asm.Opcodes;

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

public class TryBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.TryBlockGeneratorExample", null, null);
        
        /*creator.createStaticMethod("testTry", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTry:try"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTry:catch"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod("testCatch", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testCatch:try"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testCatch:catch"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod("testTwoCatchForTry", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatch:try"));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatch:catch1"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatch:catch2"));
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createStaticMethod("testTwoCatchForCatch1", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch1:try"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch1:catch1"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch1:catch2"));
                    }
                    
                });
                runReturn();
            }
            
        });

        
        creator.createStaticMethod("testTwoCatchForCatch2", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch2:try"));
                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
                    }
                    
                }).catchException(new Catch(runtime){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch2:catch1"));
                    }
                    
                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                    @Override
                    public void body(LocalVariable e)
                    {
                        invoke(systemOut, "println", Value.value("testTwoCatchForCatch2:catch2"));
                    }
                    
                });
                runReturn();
            }
            
        });

        
        creator.createStaticMethod("testTryFinally", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTryFinally:try"));
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTryFinally:finally"));
                    }
                    
                });
                runReturn();
            }
            
        });*/

        
        /*creator.createStaticMethod("testTryFinallyWithError", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTryFinallyWithError:try"));
                        throwException(invokeConstructor(runtime));
                    }
                    
                }).finallyThan(new Finally(){

                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testTryFinallyWithError:finally"));
                    }
                    
                });
                runReturn();
            }
            
        });*/
        
        creator.createStaticMethod("testNestedTryFinallyWithError", null, null, null, null, Opcodes.ACC_PRIVATE, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
                tryDo(new Try(){

                    
                    public String toString()
                    {
                        return "1try";
                    }

                    @Override
                    public void body()
                    {
                        tryDo(new Try(){

                            
                            public String toString()
                            {
                                return "2try";
                            }
                            
                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("testNestedTryFinallyWithError : try_"));
                                throwException(invokeConstructor(runtime));
                            }
                            
                        }).finallyThan(new Finally(){

                            public String toString()
                            {
                                return "finally_";
                            }
                            
                            @Override
                            public void body()
                            {
                                invoke(systemOut, "println", Value.value("testNestedTryFinallyWithError : finally_"));
                            }
                            
                        });
                    }
                    
                }).finallyThan(new Finally(){



                    public String toString()
                    {
                        return "finally";
                    }
                    
                    @Override
                    public void body()
                    {
                        invoke(systemOut, "println", Value.value("testNestedTryFinallyWithError : finally"));
                    }
                    
                });
                runReturn();
            }
            
            public String toString()
            {
                return "method";
            }
            
        });
        
        
        creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
            Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){
                @Override
                public void body(LocalVariable... argus) {
                    /*invokeStatic(getMethodOwner(), "testTry");
                    invokeStatic(getMethodOwner(), "testCatch");
                    invokeStatic(getMethodOwner(), "testTwoCatchForTry");
                    invokeStatic(getMethodOwner(), "testTwoCatchForCatch1");
                    invokeStatic(getMethodOwner(), "testTwoCatchForCatch2");
                    invoke(systemOut, "println", Value.value("================================="));
                    invokeStatic(getMethodOwner(), "testTryFinally");*/
                    //invokeStatic(getMethodOwner(), "testTryFinallyWithError");
                    invokeStatic(getMethodOwner(), "testNestedTryFinallyWithError");
                    runReturn();
                }
        
        });
        
        generate(creator);
    }
    
    
}

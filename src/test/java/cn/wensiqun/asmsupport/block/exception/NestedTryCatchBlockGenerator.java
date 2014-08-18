package cn.wensiqun.asmsupport.block.exception;

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

public class NestedTryCatchBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.NestedTryCatchBlockGeneratorExample", null, null);
        
        
        creator.createStaticMethod(testMethodNames.put("fullTryCatch1"), null, null, null, null,
            Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){
                @Override
                public void body(LocalVariable... argus) {
                    invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                    tryDo(new Try(){
                        @Override
                        public void body()
                        {
                            invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                            tryDo(new Try(){

                                @Override
                                public void body()
                                {
                                    invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                    throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
                                }
                                
                            }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                                @Override
                                public void body(LocalVariable e)
                                {
                                    invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch"));
                                    throwException(e);
                                }
                                
                            });
                        }
                    }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                        @Override
                        public void body(LocalVariable e)
                        {
                            invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                        }
                        
                    });
                    invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("End"));
                    runReturn();
                }
        
        });
        
        
        creator.createStaticMethod(testMethodNames.put("fullTryCatch2"), null, null, null, null,
                Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){
                    @Override
                    public void body(LocalVariable... argus) {
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                        tryDo(new Try(){
                            @Override
                            public void body()
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                                tryDo(new Try(){

                                    @Override
                                    public void body()
                                    {
                                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
                                    }
                                    
                                }).catchException(new Catch(runtime){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
                                    }
                                    
                                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
                                        throwException(invokeConstructor(runtime));
									}
                                	
                                });
                            }
                        }).catchException(new Catch(runtime){

                            @Override
                            public void body(LocalVariable e)
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(RuntimeException)"));
                                tryDo(new Try(){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                        throwException(invokeConstructor(AClass.EXCEPTION_ACLASS));
									}
                                	
                                }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
									}
                                	
                                });
                            }
                            
                        }).catchException(new Catch(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(Exception)"));
                            }
                            
                        });
                        invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("End"));
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

package cn.wensiqun.asmsupport.block.exception;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.block.classes.control.exception.CatchInternal;
import cn.wensiqun.asmsupport.block.classes.control.exception.TryInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBodyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreatorInternal;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.MyList;
import cn.wensiqun.asmsupport.utils.TesterStatics;
import example.AbstractExample;

public class NestedTryCatchBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        final AClass runtime = AClassFactory.getProductClass(RuntimeException.class);
        
        final MyList testMethodNames = new MyList();
        ClassCreatorInternal creator = new ClassCreatorInternal(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.NestedTryCatchBlockGeneratorExample", null, null);
        
        
        creator.createStaticMethod(testMethodNames.put("fullTryCatch1"), null, null, null, null,
            Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBodyInternal(){
                @Override
                public void body(LocalVariable... argus) {
                    _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                    _try(new TryInternal(){
                        @Override
                        public void body()
                        {
                            _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                            _try(new TryInternal(){

                                @Override
                                public void body()
                                {
                                    _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                    _throw(_new(AClass.EXCEPTION_ACLASS));
                                }
                                
                            })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                                @Override
                                public void body(LocalVariable e)
                                {
                                    _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch"));
                                    _throw(e);
                                }
                                
                            });
                        }
                    })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                        @Override
                        public void body(LocalVariable e)
                        {
                            _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch"));
                        }
                        
                    });
                    _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("End"));
                    _return();
                }
        
        });
        
        
        creator.createStaticMethod(testMethodNames.put("fullTryCatch2"), null, null, null, null,
                Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBodyInternal(){
                    @Override
                    public void body(LocalVariable... argus) {
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("Root"));
                        _try(new TryInternal(){
                            @Override
                            public void body()
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Try"));
                                _try(new TryInternal(){

                                    @Override
                                    public void body()
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                        _throw(_new(AClass.EXCEPTION_ACLASS));
                                    }
                                    
                                })._catch(new CatchInternal(runtime){

                                    @Override
                                    public void body(LocalVariable e)
                                    {
                                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(RuntimeException)"));
                                    }
                                    
                                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
                                        _throw(_new(runtime));
									}
                                	
                                });
                            }
                        })._catch(new CatchInternal(runtime){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(RuntimeException)"));
                                _try(new TryInternal(){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Try"));
                                        _throw(_new(AClass.EXCEPTION_ACLASS));
									}
                                	
                                })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

									@Override
									public void body(LocalVariable e) {
										_invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("        |-Catch(Exception)"));
									}
                                	
                                });
                            }
                            
                        })._catch(new CatchInternal(AClass.EXCEPTION_ACLASS){

                            @Override
                            public void body(LocalVariable e)
                            {
                                _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("    |-Catch(Exception)"));
                            }
                            
                        });
                        _invokeStatic(TesterStatics.ATesterStatics, "actuallyPrintln", Value.value("End"));
                        _return();
                    }
            
            });
            
        
        
        creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
            Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBodyInternal(){
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

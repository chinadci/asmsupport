package cn.wensiqun.asmsupport.block.condition;

import java.util.List;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.control.condition.Else;
import cn.wensiqun.asmsupport.block.classes.control.condition.ElseIF;
import cn.wensiqun.asmsupport.block.classes.control.condition.IF;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.MyList;
import cn.wensiqun.asmsupport.utils.TesterStatics;
import example.AbstractExample;

public class ConditionBlockGenerator extends AbstractExample
{
    
    public static void main(String[] args)
    {
        
        final MyList testMethodNames = new MyList();
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.ConditionBlockGeneratorExample", null, null);
        
        creator.createStaticMethod(testMethodNames.put("test"), new AClass[]{AClass.STRING_ACLASS}, new String[]{"str"}, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

            @Override
            public void body(LocalVariable... argus)
            {
            	final LocalVariable str = argus[0];
                _if(new IF(_invoke(str, "startsWith", Value.value("A"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith A!"));
					}
                	
                });
                

                _if(new IF(_invoke(str, "startsWith", Value.value("B"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith B!"));
					}
                	
                });
                

                _if(new IF(_invoke(str, "startsWith", Value.value("C"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith C!"));
					}
                	
                });
                
                _if(new IF(_equals(Value.value(1), _invoke(str, "length"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 1!"));
					}
                	
                })._else(new Else(){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 1!"));
					}
                	
                });
                
                _if(new IF(_equals(Value.value(2), _invoke(str, "length"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 2!"));
					}
                	
                })._else(new Else(){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 2!"));
					}
                	
                });
                
                _if(new IF(_equals(Value.value(3), _invoke(str, "length"))){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 3!"));
					}
                	
                })._else(new Else(){

					@Override
					public void body() {
	                    _invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 3!"));
					}
                	
                });
                
                
                _if(new IF(_invoke(str, "endsWith", Value.value("Z"))){

					@Override
					public void body() {
						
						 _invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Z!"));
						 
						 _if(new IF(_invoke(str, "startsWith", Value.value("A"))){

							@Override
							public void body() {

								 _invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith A!"));
								
								 _if(new IF(_equals(_invoke(str, "length"), Value.value(2))){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 2!"));
									}
										 
							    })._elseif(new ElseIF(_equals(_invoke(str, "length"), Value.value(3))){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 3!"));
										
									}
							    	
							    })._else(new Else(){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is Other!"));
									}
							    	
							    });
							}
							 
						 });
						 
						 _if(new IF(_invoke(str, "startsWith", Value.value("B"))){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith B!"));
								 
								_if(new IF(_equals(_invoke(str, "length"), Value.value(2))){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 2!"));
										_if(new IF(_equals(_invoke(str, "charAt", Value.value(1)), Value.value('1'))){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '1'!"));
											}
											
										})._else(new Else(){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is 'Other'!"));
											}
											
										});
									}
									
								})._elseif(new ElseIF(_equals(_invoke(str, "length"), Value.value(3))){

									@Override
									public void body() {

										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 3!"));
										
										_if(new IF(_equals(_invoke(str, "charAt", Value.value(1)), Value.value('1'))){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '1'!"));
											}
											
										})._elseif(new ElseIF(_equals(_invoke(str, "charAt", Value.value(1)), Value.value('2'))){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '2'!"));
											}
											
										})._elseif(new ElseIF(_equals(_invoke(str, "charAt", Value.value(1)), Value.value('3'))){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '3'!"));
											}
											
										})._else(new Else(){

											@Override
											public void body() {
												_invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is 'Other'!"));
											}
											
										});
									}
									
								})._else(new Else(){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is Other!"));
									}
									
								});
								
							}
							 
						 });
					}
                	
                })._elseif(new ElseIF(_invoke(str, "endsWith", Value.value("Y"))){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Y!"));
						_if(new IF(_invoke(str, "startsWith", Value.value("A"))){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith A!"));
							}
							
						});
					
						_if(new IF(_invoke(str, "startsWith", Value.value("B"))){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith B!"));
							}
							
						});
					}
                	
                })._else(new Else(){

					@Override
					public void body() {
						_invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Other!"));
						
						_if(new IF(_invoke(str, "endsWith", Value.value("X"))){

							@Override
							public void body() {

								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith X!"));
							}
							
						})._elseif(new ElseIF(_invoke(str, "endsWith", Value.value("W"))){

							@Override
							public void body() {

								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith W!"));
							}
							
						})._else(new Else(){

							@Override
							public void body() {
								_invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith Other!"));
								
								_if(new IF(_invoke(str, "endsWith", Value.value("V"))){

									@Override
									public void body() {

										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith V!"));
									}
									
								})._elseif(new ElseIF(_invoke(str, "endsWith", Value.value("U"))){

									@Override
									public void body() {

										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith U!"));
									}
									
								})._else(new Else(){

									@Override
									public void body() {
										_invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith Other!"));
										
										
										
									}
									
								});
								
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
                	List<String> list = ConditionBlockGeneratorSample.allPossiable();
                	for(String str : list)
                	{
                		_invokeStatic(getMethodOwner(), "test", Value.value(str));
                	}
                	//invokeStatic(getMethodOwner(), "test", Value.value("A"));
                	
                    _return();
                }
        
        });
        
        generate(creator);
    }
    
}

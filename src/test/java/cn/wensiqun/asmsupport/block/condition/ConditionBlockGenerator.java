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
                ifThen(new IF(invoke(str, "startsWith", Value.value("A"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith A!"));
					}
                	
                });
                

                ifThen(new IF(invoke(str, "startsWith", Value.value("B"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith B!"));
					}
                	
                });
                

                ifThen(new IF(invoke(str, "startsWith", Value.value("C"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    startsWith C!"));
					}
                	
                });
                
                ifThen(new IF(equal(Value.value(1), invoke(str, "length"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 1!"));
					}
                	
                }).elseThen(new Else(){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 1!"));
					}
                	
                });
                
                ifThen(new IF(equal(Value.value(2), invoke(str, "length"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 2!"));
					}
                	
                }).elseThen(new Else(){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 2!"));
					}
                	
                });
                
                ifThen(new IF(equal(Value.value(3), invoke(str, "length"))){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is 3!"));
					}
                	
                }).elseThen(new Else(){

					@Override
					public void body() {
	                    invokeStatic(TesterStatics.ATesterStatics, 
	                    		"actuallyPrintln", Value.value("    length is not 3!"));
					}
                	
                });
                
                
                ifThen(new IF(invoke(str, "endsWith", Value.value("Z"))){

					@Override
					public void body() {
						
						 invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Z!"));
						 
						 ifThen(new IF(invoke(str, "startsWith", Value.value("A"))){

							@Override
							public void body() {

								 invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith A!"));
								
								 ifThen(new IF(equal(invoke(str, "length"), Value.value(2))){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 2!"));
									}
										 
							    }).elseIF(new ElseIF(equal(invoke(str, "length"), Value.value(3))){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 3!"));
										
									}
							    	
							    }).elseThen(new Else(){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is Other!"));
									}
							    	
							    });
							}
							 
						 });
						 
						 ifThen(new IF(invoke(str, "startsWith", Value.value("B"))){

							@Override
							public void body() {
								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith B!"));
								 
								ifThen(new IF(equal(invoke(str, "length"), Value.value(2))){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 2!"));
										ifThen(new IF(equal(invoke(str, "charAt", Value.value(1)), Value.value('1'))){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '1'!"));
											}
											
										}).elseThen(new Else(){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is 'Other'!"));
											}
											
										});
									}
									
								}).elseIF(new ElseIF(equal(invoke(str, "length"), Value.value(3))){

									@Override
									public void body() {

										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is 3!"));
										
										ifThen(new IF(equal(invoke(str, "charAt", Value.value(1)), Value.value('1'))){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '1'!"));
											}
											
										}).elseIF(new ElseIF(equal(invoke(str, "charAt", Value.value(1)), Value.value('2'))){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '2'!"));
											}
											
										}).elseIF(new ElseIF(equal(invoke(str, "charAt", Value.value(1)), Value.value('3'))){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is '3'!"));
											}
											
										}).elseThen(new Else(){

											@Override
											public void body() {
												invokeStatic(TesterStatics.ATesterStatics, 
														 "actuallyPrintln", Value.value("                charAt 1 is 'Other'!"));
											}
											
										});
									}
									
								}).elseThen(new Else(){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            length is Other!"));
									}
									
								});
								
							}
							 
						 });
					}
                	
                }).elseIF(new ElseIF(invoke(str, "endsWith", Value.value("Y"))){

					@Override
					public void body() {
						invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Y!"));
						ifThen(new IF(invoke(str, "startsWith", Value.value("A"))){

							@Override
							public void body() {
								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith A!"));
							}
							
						});
					
						ifThen(new IF(invoke(str, "startsWith", Value.value("B"))){

							@Override
							public void body() {
								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        startsWith B!"));
							}
							
						});
					}
                	
                }).elseThen(new Else(){

					@Override
					public void body() {
						invokeStatic(TesterStatics.ATesterStatics, 
								 "actuallyPrintln", Value.value("    endsWith Other!"));
						
						ifThen(new IF(invoke(str, "endsWith", Value.value("X"))){

							@Override
							public void body() {

								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith X!"));
							}
							
						}).elseIF(new ElseIF(invoke(str, "endsWith", Value.value("W"))){

							@Override
							public void body() {

								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith W!"));
							}
							
						}).elseThen(new Else(){

							@Override
							public void body() {
								invokeStatic(TesterStatics.ATesterStatics, 
										 "actuallyPrintln", Value.value("        endsWith Other!"));
								
								ifThen(new IF(invoke(str, "endsWith", Value.value("V"))){

									@Override
									public void body() {

										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith V!"));
									}
									
								}).elseIF(new ElseIF(invoke(str, "endsWith", Value.value("U"))){

									@Override
									public void body() {

										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith U!"));
									}
									
								}).elseThen(new Else(){

									@Override
									public void body() {
										invokeStatic(TesterStatics.ATesterStatics, 
												 "actuallyPrintln", Value.value("            endsWith Other!"));
										
										
										
									}
									
								});
								
							}
							
						});
						
					}
                	
                });
                	
                	
                
                runReturn();
            }
        });
        
        creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
            Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){
                @Override
                public void body(LocalVariable... argus) {
                	List<String> list = ConditionBlockGeneratorSample.allPossiable();
                	for(String str : list)
                	{
                		invokeStatic(getMethodOwner(), "test", Value.value(str));
                	}
                	//invokeStatic(getMethodOwner(), "test", Value.value("A"));
                	
                    runReturn();
                }
        
        });
        
        generate(creator);
    }
    
}

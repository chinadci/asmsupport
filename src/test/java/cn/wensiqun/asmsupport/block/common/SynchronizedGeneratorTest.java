package cn.wensiqun.asmsupport.block.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.Assert;

import org.junit.Test;
import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.Synchronized;
import cn.wensiqun.asmsupport.block.classes.control.exception.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;
import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBody;
import cn.wensiqun.asmsupport.block.classes.method.init.InitBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import example.AbstractExample;

public class SynchronizedGeneratorTest extends AbstractExample {

	@Test
	public void test() {
		/*try {
			main("");
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}*/
		String prefix = "prefix";
		int excepted = 100;
		int actually = 100;
		assertEquals(prefix, excepted, actually);
	}
	
	public static void assertEquals(Object prefix, Object excepted, Object actually) {
		System.out.println("Prefix [" + prefix +  "] --- excepted : " + excepted + " actually : " + actually);
		Assert.assertEquals(excepted, actually);
	}
	
	/*public static void assertEquals(int prefix, int excepted, int actually) {
		System.out.println("Prefix [" + prefix +  "] --- excepted : " + excepted + " actually : " + actually);
		Assert.assertEquals(excepted, actually);
	}*/
	
    public static void main(String... args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        

        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.SynchronizedGeneratorExample", null, null);
        
        creator.createGlobalVariable("lock", Opcodes.ACC_PRIVATE, AClass.OBJECT_ACLASS);
        
        creator.createGlobalVariable("list", Opcodes.ACC_PUBLIC, AClassFactory.getProductClass(List.class));
        
        creator.createConstructor(null, null, new InitBody() {

            @Override
            public void body(LocalVariable... argus) {
            	  invokeSuperConstructor(argus);
                assign(getThis().getGlobalVariable("lock"), invokeConstructor(AClass.OBJECT_ACLASS));
                assign(getThis().getGlobalVariable("list"), invokeConstructor(AClassFactory.getProductClass(ArrayList.class)));
				  this.runReturn();
            }
            
        }, Opcodes.ACC_PUBLIC);
        
        creator.createMethod("syncThis", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){

            @Override
            public void body(LocalVariable... argus) {
                final GlobalVariable list = getThis().getGlobalVariable("list");
                syn(new Synchronized(getThis()){
                    @Override
                    public void body(Parameterized e) {
                        final LocalVariable i = createVariable("i", AClass.INT_ACLASS, false, Value.value(0));
                        whileDo(new While(lessThan(i, Value.value(10))){

                            @Override
                            public void body() {
                                invoke(list, "add", i);
                                postInc(i);
                            }
                            
                        });
                    }
                    
                });
                runReturn();
            }
            
        });
        
        creator.createMethod("syncLock", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){
            
            @Override
            public void body(LocalVariable... argus) {
                final GlobalVariable list = getThis().getGlobalVariable("list");
                syn(new Synchronized(getThis().getGlobalVariable("lock")){
                    @Override
                    public void body(Parameterized e) {
                        final LocalVariable i = createVariable("i", AClass.INT_ACLASS, false, Value.value(0));
                        whileDo(new While(lessThan(i, Value.value(10))){

                            @Override
                            public void body() {
                                invoke(list, "add", i);
                                postInc(i);
                            }
                            
                        });
                    }
                    
                });
                runReturn();
            }
            
        });

        Class<?> syncGenExamp = generate(creator, false);
        Class<?> thisThread = createThread(AClassFactory.getProductClass(syncGenExamp), "This");
        Class<?> lockThread = createThread(AClassFactory.getProductClass(syncGenExamp), "Lock");
        Class<?> junitTestCls = createTestJunit(AClassFactory.getProductClass(syncGenExamp), 
        		AClassFactory.getProductClass(thisThread), AClassFactory.getProductClass(lockThread));
        Object junitTestObj = junitTestCls.newInstance();
        Method testSyncThis = junitTestCls.getMethod("testSyncThis");
        Method testSyncLock = junitTestCls.getMethod("testSyncLock");
        testSyncThis.invoke(junitTestObj);
        testSyncThis.invoke(junitTestObj);
    }
    
    private static Class<?> createThread(AClass synchronizedGeneratorExampleClass, final String name) {
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , 
        		"generated.block.Sync" + name + "ThreadExample", 
        		Thread.class, null);
        
        creator.createGlobalVariable("sgst", Opcodes.ACC_PRIVATE, synchronizedGeneratorExampleClass);
        
        creator.createConstructor(new AClass[]{synchronizedGeneratorExampleClass}, new String[]{"sgst"}, new InitBody(){

			@Override
			public void body(LocalVariable... argus) {
          	   invokeSuperConstructor();
				assign(getThis().getGlobalVariable("sgst"), argus[0]);
				this.runReturn();
			}
        	
        }, Opcodes.ACC_PUBLIC);
        
        creator.createMethod("run", null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){

			@Override
			public void body(LocalVariable... argus) {
				this.tryDo(new Try(){

					@Override
					public void body() {
						invokeStatic(AClassFactory.getProductClass(Thread.class), "sleep", Value.value(100));
					    invoke(getThis().getGlobalVariable("sgst"), "sync" + name);
					}
					
				}).catchException(new Catch(AClassFactory.getProductClass(InterruptedException.class)) {

					@Override
					public void body(LocalVariable e) {
					}
					
				});
				runReturn();
			}
        	
        });
        return generate(creator, false);
    }
    
    private static Class<?> createTestJunit(AClass syncCls, AClass thisThread, AClass lockThread) {
    	ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , 
        		"generated.block.SynchronizedGeneratorExampleTestJunit", 
        		null, null);
    	createTestSyncMethod(creator, "This", syncCls, thisThread);
    	createTestSyncMethod(creator, "Lock", syncCls, lockThread);
    	
    	
    	return generate(creator, false);
    }
    
    private static void createTestSyncMethod(ClassCreator creator, final String name, final AClass syncCls, final AClass threadClass) {


        creator.createMethod("testSync" + name, null, null, null, null, Opcodes.ACC_PUBLIC, new CommonMethodBody(){
            
            @Override
            public void body(LocalVariable... argus) {
                 final LocalVariable sgst = createVariable("sgst", syncCls, false, invokeConstructor(syncCls));
                
                 final LocalVariable es = createVariable(
                        "es", 
                        AClassFactory.getProductClass(ExecutorService.class),
                        false, 
                        invokeStatic(AClassFactory.getProductClass(Executors.class), "newFixedThreadPool", Value.value(10))
                );
                 final LocalVariable objs = createVariable(
                        "objs", 
                        AClassFactory.getProductClass(List.class),
                        false, 
                        invokeConstructor(AClassFactory.getProductClass(ArrayList.class))
                );
                 final LocalVariable i = createVariable(
                        "i", 
                        AClass.INT_ACLASS,
                        false, 
                        Value.value(0)
               );
                this.whileDo(new While(lessThan(i, Value.value(10))){
						@Override
						public void body() {
							invoke(objs, "add", invoke(es, "submit", invokeConstructor(threadClass, sgst)));
							postInc(i);
						}
					});
                invoke(es, "shutdown");
                whileDo(new While(not(invoke(es, "isTerminated"))){
						@Override
						public void body() {
							
						}
					});
                invokeStatic(AClassFactory.getProductClass(SynchronizedGeneratorTest.class), "assertEquals",
                		Value.value("Assert.assertEquals(100, sgst.list.size())"), Value.value(100), invoke(sgst.getGlobalVariable("list"), "size"));
                
                assign(i, Value.value(0));
                whileDo(new While(lessThan(i, Value.value(100))) {

					@Override
					public void body() {
		                invokeStatic(AClassFactory.getProductClass(SynchronizedGeneratorTest.class), "assertEquals",
		                		i,
		                		mod(i, Value.value(10)), 
		                		invoke(checkCast(invoke(sgst.getGlobalVariable("list"), "get", i), AClass.INTEGER_WRAP_ACLASS), "intValue"));
						postInc(i);
					}
                	
                });
                
                runReturn();
            }
            
        });
    }
    
}

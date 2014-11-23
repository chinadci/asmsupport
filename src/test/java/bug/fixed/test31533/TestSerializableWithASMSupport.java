package bug.fixed.test31533;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.method.common.CommonMethodBodyInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.creator.ClassCreatorInternal;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public class TestSerializableWithASMSupport {
   
    public static void main(String[] args) throws Exception{

    	ClassCreatorInternal creator = 
				new ClassCreatorInternal(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "bug.fixed.Test31533", null, new Class<?>[]{Serializable.class});
        
    	creator.createGlobalVariable("name", Opcodes.ACC_PRIVATE, AClass.STRING_ACLASS);
		
    	creator.createMethod("setName", 
    			new AClass[]{AClass.STRING_ACLASS}, new String[]{"name"}, 
    			null, null, Opcodes.ACC_PUBLIC, new CommonMethodBodyInternal(){

					@Override
					public void body(LocalVariable... argus) {
						_assign(_this().getGlobalVariable("name"), argus[0]);
						_return();
					}
    		
    	});
    	

    	creator.createMethod("getName", 
    			null, null, 
    			AClass.STRING_ACLASS, null, Opcodes.ACC_PUBLIC, new CommonMethodBodyInternal(){

					@Override
					public void body(LocalVariable... argus) {
						_return(_this().getGlobalVariable("name"));
					}
    		
    	});
    	

    	creator.createMethod("toString", 
    			null, null, 
    			AClass.STRING_ACLASS, null, Opcodes.ACC_PUBLIC, new CommonMethodBodyInternal(){

					@Override
					public void body(LocalVariable... argus) {
						_return(_append(Value.value("User [name="), _this().getGlobalVariable("name")));
					}
    		
    	});
    	
		creator.setClassOutPutPath(".//target//");
		Class<?> cls = creator.startup();
		
		
    	Object user = cls.newInstance();
        Method m = cls.getMethod("setName", new Class[]{String.class});
    	m.invoke(user, "asmsupport");
        
       /*****************序列化***************/
       Utils.writeObject(user);
       System.out.println("before:" + user);
       
       /*****************反序列化***************/

       InputStream is = new FileInputStream(new File(".//target//bug.fixed.Test31533_tmp"));
	   CustomObjectInputStream ois = new CustomObjectInputStream(is);
       Object user2 = ois.readObject();
       System.out.println("after:" + user2);

    }
    
    @org.junit.Test
	public void test() throws Exception{
		main(null);
	}
}

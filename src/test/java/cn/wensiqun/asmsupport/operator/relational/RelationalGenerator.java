package cn.wensiqun.asmsupport.operator.relational;

import org.objectweb.asm.Opcodes;

import cn.wensiqun.asmsupport.block.classes.control.loop.WhileInternal;
import cn.wensiqun.asmsupport.block.classes.method.common.StaticMethodBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.creator.ClassCreator;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.TesterStatics;
import example.AbstractExample;

public class RelationalGenerator extends AbstractExample 
{

    public static void main(String[] args)
    {
        ClassCreator creator = new ClassCreator(Opcodes.V1_5, Opcodes.ACC_PUBLIC , "generated.block.RelationalGeneratorExample", null, null);
        
        creator.createStaticMethod("test", null, null, null, null, Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, new StaticMethodBody(){

               @Override
               public void body(LocalVariable... argus)
               {
                   
                   
                   _return();
               }
        });
           
       creator.createStaticMethod("main", new AClass[]{AClassFactory.getProductClass(String[].class)}, new String[]{"args"}, null, null,
           Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, new StaticMethodBody(){
               @Override
               public void body(LocalVariable... argus) {
                   _invokeStatic(getMethodOwner(), "test");
                   _return();
               }
       
       });

       generate(creator);
    }

}

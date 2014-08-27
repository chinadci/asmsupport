/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical.crement.v2;

import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.GlobalVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class BeforeDecrement extends AbstractCrement {
    
    protected BeforeDecrement(ProgramBlock block, Crementable factor) {
        super(block, factor);
    }


    @Override
    protected void doExecute()
    {
        
        Type type = factor.getParamterizedType().getType();
        
        //load factor
        factor.loadToStack(block);
        
        //load 
        
        
        insnHelper.sub(type);
    }

    private void doExecuteDirect()
    {
        Type type = resultClass.getType();
        if(factor instanceof LocalVariable && 
           Type.INT_TYPE.equals(type))
        {
            insnHelper.iinc(((LocalVariable)factor).getScopeLogicVar().getInitStartPos(), 1);
        }
        else
        {
            AClass primitiveClass = AClassUtils.getPrimitiveAClass(resultClass);
            if(factor instanceof GlobalVariable)
            {
                //factor load to stack
                factor.loadToStack(block);
                
                //cast and unbox
                autoCast(resultClass, primitiveClass);
                
                //load 1 to stack 
                getValue().loadToStack(block);
                
                //generate xsub for decrement
                insnHelper.sub(type);
                
                //cast and box
                autoCast(primitiveClass, resultClass);
                
                //save to variable
                GlobalVariable globalVariable = (GlobalVariable) factor;
             
                 //将栈内的值存储到全局变量中
                //判读如果是静态变量
                if(globalVariable.getStaticOwner() != null){
                    insnHelper.putStatic(globalVariable.getStaticOwner().getType(), 
                        globalVariable.getVariableMeta().getName(),
                        globalVariable.getVariableMeta().getDeclareClass().getType());
                }else if(globalVariable.getVariableOwner() != null){
                    insnHelper.putField(globalVariable.getVariableOwner().getVariableMeta().getDeclareClass().getType(), 
                        globalVariable.getVariableMeta().getName(),
                        globalVariable.getVariableMeta().getDeclareClass().getType());
                }
            }
            else
            {
                //factor load to stack
                factor.loadToStack(block);
                
                //cast and unbox
                autoCast(resultClass, primitiveClass);
                
                //load 1 to stack 
                getValue().loadToStack(block);
                
                //generate xsub for decrement
                insnHelper.sub(type);
                
                //cast and box
                autoCast(primitiveClass, resultClass);
                
                //save to variable
                LocalVariable localVariable = (LocalVariable) factor;
                
                //store variable to
                insnHelper.storeInsn(localVariable);
            }
        }
    }
    
    private void doExecuteAsArgument()
    {
        Type type = resultClass.getType();
        if(factor instanceof LocalVariable && 
           Type.INT_TYPE.equals(type))
        {
            insnHelper.iinc(((LocalVariable)factor).getScopeLogicVar().getInitStartPos(), 1);
            
            factor.loadToStack(block);
        }
        else
        {
            AClass primitiveClass = AClassUtils.getPrimitiveAClass(resultClass);
            if(factor instanceof GlobalVariable)
            {
                //factor load to stack
                factor.loadToStack(block);
                
                //cast and unbox
                autoCast(resultClass, primitiveClass);
                
                //load 1 to stack 
                getValue().loadToStack(block);
                
                //generate xsub for decrement
                insnHelper.sub(type);
                
                //cast and box
                autoCast(primitiveClass, resultClass);

                insnHelper.dup();
                
                //save to variable
                GlobalVariable globalVariable = (GlobalVariable) factor;
             
                 //将栈内的值存储到全局变量中
                //判读如果是静态变量
                if(globalVariable.getStaticOwner() != null){
                    insnHelper.putStatic(globalVariable.getStaticOwner().getType(), 
                        globalVariable.getVariableMeta().getName(),
                        globalVariable.getVariableMeta().getDeclareClass().getType());
                }else if(globalVariable.getVariableOwner() != null){
                    insnHelper.putField(globalVariable.getVariableOwner().getVariableMeta().getDeclareClass().getType(), 
                        globalVariable.getVariableMeta().getName(),
                        globalVariable.getVariableMeta().getDeclareClass().getType());
                }
            }
            else
            {
                //factor load to stack
                factor.loadToStack(block);
                
                //cast and unbox
                autoCast(resultClass, primitiveClass);
                
                //load 1 to stack 
                getValue().loadToStack(block);
                
                //generate xsub for decrement
                insnHelper.sub(type);
                
                //cast and box
                autoCast(primitiveClass, resultClass);
                
                insnHelper.dup();
                
                //save to variable
                LocalVariable localVariable = (LocalVariable) factor;
                
                //store variable to
                insnHelper.storeInsn(localVariable);
            }
        }
    }
    

    @Override
    protected void before()
    {
        
    }


    @Override
    protected void after()
    {
        
    }


}

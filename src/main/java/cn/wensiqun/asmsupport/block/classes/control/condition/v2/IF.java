package cn.wensiqun.asmsupport.block.classes.control.condition.v2;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupport.block.classes.control.EpisodeBlock;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;

public abstract class IF extends EpisodeBlock<ConditionSerialBlock> implements Body
{
    private Parameterized condition;
    
    public IF(Parameterized condition)
    {
        this.condition = condition;
        condition.asArgument();
    }
    
    @Override
    protected void init()
    {
        if(!condition.getParamterizedType().equals(AClass.BOOLEAN_WRAP_ACLASS) &&
            !condition.getParamterizedType().equals(AClass.BOOLEAN_ACLASS) ){
             throw new ASMSupportException("the condition type of if statement must be boolean or Boolean, but was " + condition.getParamterizedType());
         }
    }

    @Override
    public void generate()
    {
        body();
    }

    @Override
    public void doExecute()
    {
        Label serialEnd = getSerial().getSerialEnd();
        if(condition instanceof Jumpable){
            Jumpable jmp = (Jumpable) condition;
            jmp.setJumpLable(serialEnd);
            jmp.executeAndJump(ControlType.IF);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.EQ, serialEnd);
        }
        
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
    }

    @Override
    public String toString() {
        return "IF Block:" + condition.toString();
    }
}

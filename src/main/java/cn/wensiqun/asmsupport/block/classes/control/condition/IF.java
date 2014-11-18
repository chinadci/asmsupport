package cn.wensiqun.asmsupport.block.classes.control.condition;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;

public abstract class IF extends ConditionBranchBlock implements Body
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
    protected void doExecute()
    {
        Label endLbl = getEnd();
        if(condition instanceof Jumpable){
            Jumpable jmp = (Jumpable) condition;
            jmp.setJumpLable(endLbl);
            jmp.executeAndJump(ControlType.IF);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.EQ, endLbl);
        }
        
        for(ByteCodeExecutor exe : getQueue()){
            exe.execute();
        }
        
        if(nextBranch != null)
        {
        	insnHelper.goTo(getSerialEnd());
        }
    }

    public ElseIF _elseif(ElseIF elsIf)
    {
    	initNextBranch(elsIf);
    	return elsIf;
    }
    
    public Else _else(Else els)
    {
    	initNextBranch(els);
    	return els;
    }
    
    @Override
    public String toString() {
        return "IF Block:" + condition.toString();
    }
}

package cn.wensiqun.asmsupport.block.classes.control.condition;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;
import cn.wensiqun.asmsupportasm.Label;
import cn.wensiqun.asmsupportgeneric.branch.IElseIF;

public abstract class ElseIFInternal extends ConditionBranchBlock implements IElseIF<ElseIFInternal, ElseInternal>
{

    private Parameterized condition;
    
    public ElseIFInternal(Parameterized condition) {
        this.condition = condition;
        condition.asArgument();
    }

    @Override
    protected void init() {
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
    	insnHelper.nop();
    	if(condition instanceof Jumpable){
        	Jumpable jmp = (Jumpable) condition;
        	jmp.setJumpLable(endLbl);
        	jmp.executeAndJump(ControlType.IF);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.EQ, endLbl);
        }

    	for(Executable exe : getQueue()){
            exe.execute();
        }
    	
    	if(nextBranch != null)
        {
        	insnHelper.goTo(getSerialEnd());
        }
    }

    @Override
    public ElseIFInternal _elseif(ElseIFInternal elsIf)
    {
    	initNextBranch(elsIf);
    	return elsIf;
    }

    @Override
    public ElseInternal _else(ElseInternal els)
    {
    	initNextBranch(els);
    	return els;
    }

}

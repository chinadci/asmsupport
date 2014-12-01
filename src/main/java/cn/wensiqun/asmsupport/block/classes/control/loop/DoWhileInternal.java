package cn.wensiqun.asmsupport.block.classes.control.loop;


import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;
import cn.wensiqun.asmsupportasm.Label;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;
import cn.wensiqun.asmsupportgeneric.loop.IDoWhile;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class DoWhileInternal extends ProgramBlockInternal implements Loop, IDoWhile  {

    private Parameterized condition;

    Label conditionLbl;
    Label contentStart;
    
    public DoWhileInternal(Parameterized condition) {
        this.condition = condition;
        conditionLbl = new Label();
        contentStart = new Label();
        condition.asArgument();
    }

    @Override
    public final void generate()
    {
        body();
    }


    @Override
    public void doExecute() {
        insnHelper.mark(contentStart);
        insnHelper.nop();
        for(Executable exe : getQueue()){
            exe.execute();
        }

        insnHelper.mark(conditionLbl);

        if(condition instanceof Jumpable){
        	Jumpable jmp = (Jumpable) condition;
        	jmp.setJumpLable(contentStart);
        	jmp.executeAndJump(ControlType.LOOP);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.NE, contentStart);
        }
    }

    @Override
    protected void init() {
        if(!condition.getParamterizedType().equals(AClass.BOOLEAN_WRAP_ACLASS) &&
           !condition.getParamterizedType().equals(AClass.BOOLEAN_ACLASS) ){
            throw new ASMSupportException("the condition type of if statement must be boolean or Boolean, but was " + condition.getParamterizedType());
        }
    }
    
    @Override
    public Label getBreakLabel() {
        return getEnd();
    }

    @Override
    public Label getContinueLabel() {
        return conditionLbl;
    }


	@Override
	public String toString() {
		return "While Block:" + super.toString();
	}
}

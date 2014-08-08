package cn.wensiqun.asmsupport.block.control.condition;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.body.Body;
import cn.wensiqun.asmsupport.block.control.ControlType;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;
import cn.wensiqun.asmsupport.utils.collections.CommonLinkedList;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class If extends ConditionBranchBlock implements Body {
	
    private Parameterized condition;
    
    private ConditionBranchBlock elseOrElseIFBlock;

    /** 该程序块中所有可执行的指令 */
    private CommonLinkedList<ByteCodeExecutor> parentExes;
    
    public If(Parameterized condition) {
        super();
        condition.asArgument();
        this.condition = condition;
    }

    @Override
    public final void generateInsn()
    {
        body();
    }
    
	@Override
    public void executing() {
        insnHelper.nop();
        if(condition instanceof Jumpable){
        	Jumpable jmp = (Jumpable) condition;
        	jmp.setJumpLable(getEndLabel());
        	jmp.executeAndJump(ControlType.IF);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.EQ, getEndLabel());
        }
        
        insnHelper.nop();
        for(Executable exe : getExecuteQueue()){
            exe.execute();
        }
        
        if(elseOrElseIFBlock != null){
            insnHelper.goTo(getLastLabel());
        }
        
        insnHelper.mark(getEndLabel());
    }

    @Override
    protected void init() {
        if(!condition.getParamterizedType().equals(AClass.BOOLEAN_WRAP_ACLASS) &&
           !condition.getParamterizedType().equals(AClass.BOOLEAN_ACLASS) ){
            throw new ASMSupportException("the condition type of if statement must be boolean or Boolean, but was " + condition.getParamterizedType());
        }
    }
    
    @Override
    Label getLastLabel() {
        if(elseOrElseIFBlock == null){
            return getEndLabel();
        }else{
            return elseOrElseIFBlock.getLastLabel();
        }
    }

    public void elsethan(Else elseblock){
    	
        parentExes.add(elseblock);
        this.elseOrElseIFBlock = elseblock;
        
        subBlockPrepare(elseblock, getParent());
        
        elseblock.setPreviousBlock(this);
    }

    public ElseIf elseif(ElseIf elseIfBlock){
        
    	elseIfBlock.setParentExes(parentExes);
        
        parentExes.add(elseIfBlock);

        subBlockPrepare(elseIfBlock, getParent());
        
        elseIfBlock.setPreviousBlock(this);
        
        this.elseOrElseIFBlock = elseIfBlock;
        
        return elseIfBlock;
    }

    public void setParentExes(CommonLinkedList<ByteCodeExecutor> parentExes) {
        this.parentExes = parentExes;
    }

	@Override
	public String toString() {
		return "IF Block:" + super.toString();
	}
}

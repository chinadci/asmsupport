package cn.wensiqun.asmsupport.block.classes.control.condition;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class ConditionBranchBlock extends ProgramBlockInternal {


	protected ConditionBranchBlock nextBranch;
    
    Label getSerialEnd(){
    	if(nextBranch != null)
    	{
    		return nextBranch.getSerialEnd();
    	}
    	return getEnd();
    }
    
    protected void initNextBranch(ConditionBranchBlock block)
    {
    	nextBranch = block;
    	
    	block.setParent(getParent());
    	
    	getParent().getQueue().add(block);
    	
    	block.prepare();
    }
}

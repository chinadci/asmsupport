package cn.wensiqun.asmsupport.operators;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.ProgramBlock;

public abstract class BreakStack extends AbstractOperator {

	//private boolean affectReturn = true;

    /**
     * if exists, this label indicate start position of auto create finally block, 
     * otherwise, indicate the current operator label. 
     */
    private Label markLabel;
    
	private boolean autoCreate;
	
	public boolean isAutoCreate() {
		return autoCreate;
	}

	public void setAutoCreate(boolean autoCreate) {
		this.autoCreate = autoCreate;
	}

	protected BreakStack(ProgramBlock block) {
		super(block);
	}

	@Override
	protected final void executing() {
	    breakStackExecuting();
	}

    protected abstract void breakStackExecuting();
	
}

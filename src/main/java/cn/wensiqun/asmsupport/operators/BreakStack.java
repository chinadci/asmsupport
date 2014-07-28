package cn.wensiqun.asmsupport.operators;

import cn.wensiqun.asmsupport.block.ProgramBlock;

public abstract class BreakStack extends AbstractOperator {

	//private boolean affectReturn = true;
	
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

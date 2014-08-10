package cn.wensiqun.asmsupport.operators;

import java.util.List;

import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.ExceptionEpisodeBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.ExceptionSerialBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Finally;

public abstract class BreakStack extends AbstractOperator {

    /**
     * if exists, this label indicate start position of auto create finally block, 
     * otherwise, indicate the current operator label. 
     */
    private List<CloneFinallyLabelMap> cloneFinallyLabelList;
    
	private boolean autoCreate;

	protected BreakStack(ProgramBlock block, boolean autoCreate) {
		super(block);
		this.autoCreate = autoCreate;
	}

	public boolean isAutoCreate() {
		return autoCreate;
	}
	

	@Override
	protected final void executing() {
	    breakStackExecuting();
	}
	
    protected abstract void breakStackExecuting();

    private void checkFinallyBlock()
    {
    	ProgramBlock block = this.block;
    	while(block != null)
    	{
        	if(block instanceof Try ||
        	   block instanceof Catch)
        	{
        		ExceptionEpisodeBlock episode = ((ExceptionEpisodeBlock)block);
        		ExceptionSerialBlock serial = episode.getSerial();
        		if(serial.getFinally() != null)
        		{
            		CloneFinallyLabelMap map = new CloneFinallyLabelMap();
            		map.position = new Label();
            		map.target = serial.getFinally();
        		}
        	}
    		block = block.getParent();
    	}
    }
    
    private class CloneFinallyLabelMap
    {
    	private Label position;
    	
    	private Finally target;
    }
}

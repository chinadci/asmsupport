package cn.wensiqun.asmsupport.operators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Try;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Catch;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.ExceptionEpisodeBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.ExceptionSerialBlock;
import cn.wensiqun.asmsupport.block.classes.control.exception.v2.Finally;
import cn.wensiqun.asmsupport.definition.method.AMethod;
import cn.wensiqun.asmsupport.operators.asmdirect.Marker;

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

	@Override
    protected void addQueue()
    {
        AMethod method = block.getMethod();
	    if(!method.isCreatingImplicitFinally())
	    {
	        checkFinallyBlock();
	        if(CollectionUtils.isNotEmpty(cloneFinallyLabelList))
	        {
	            boolean oldFinallyCheck = method.isCreatingImplicitFinally();
	            method.setCreatingImplicitFinally(true);
	            for(int i= cloneFinallyLabelList.size() - 1; i>-1; i--)
	            {
	                CloneFinallyLabelMap map = cloneFinallyLabelList.get(i);
	                new Marker(block, map.position);
	                map.getTarget().generateInsnTo(block);
	            }
	            method.setCreatingImplicitFinally(oldFinallyCheck);
	        }
	    }
	    
        super.addQueue();
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
            		
            		if(cloneFinallyLabelList == null)
            		{
            		    cloneFinallyLabelList = new ArrayList<CloneFinallyLabelMap>();
            		}
            		cloneFinallyLabelList.add(map);
        		}
        	}
    		block = block.getParent();
    	}
    }
    
    public Label getImplicitFinallyLabel(Finally target)
    {
        if(CollectionUtils.isNotEmpty(cloneFinallyLabelList))
        {
            for(CloneFinallyLabelMap map : cloneFinallyLabelList)
            {
                if(target == map.target)
                {
                    return map.position;
                }
            }
        }
        return null;
    }
    
    public class CloneFinallyLabelMap
    {
    	private Label position;
    	
    	private Finally target;

        public Label getPosition()
        {
            return position;
        }

        public Finally getTarget()
        {
            return target;
        }
    	
    }
}

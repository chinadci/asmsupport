/**
 * 
 */
package cn.wensiqun.asmsupport.block.control.exception;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.block.body.Body;
import cn.wensiqun.asmsupport.block.control.EpisodeBlock;


/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class Finally extends EpisodeBlock implements Body {
    
    Label startLbl;
    Label endLbl;
    
    public Finally() {
		super();
        startLbl = new Label();
        endLbl = new Label();
	}

    @Override
    public final void generateInsn()
    {
        body();
    }
    
    @Override
    public final void executing() {
        insnHelper.mark(startLbl);
        insnHelper.nop();
        for(Executable exe : getExecuteQueue()){
            exe.execute();
        }
        insnHelper.mark(endLbl);
    }

    @Override
	public void setReturned(boolean returned) {
		super.setReturned(returned);
		getOwnerBlock().setReturned(returned);
	}

	@Override
	public String toString() {
		return "Finally Block:" + super.toString();
	}
}

/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.control.condition;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class Else extends ConditionBranchBlock implements Body {
	
	@Override
    public void executing() {
        insnHelper.nop();
        for(Executable exe : getQueue()){
            exe.execute();
        }
        insnHelper.mark(getEndLabel());
    }

    @Override
    Label getLastLabel() {
        return getEndLabel();
    }

    @Override
    public final void generateInsn()
    {
        body();
    }
    
    
}

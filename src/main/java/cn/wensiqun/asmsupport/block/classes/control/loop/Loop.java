/**
 * 
 */
package cn.wensiqun.asmsupport.block.classes.control.loop;

import org.objectweb.asm.Label;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public interface Loop {
    
    public Label getBreakLabel();
    
    public Label getContinueLabel();
}

package cn.wensiqun.asmsupport.operators.asmdirect;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.UnreachableCodeCheckSkipable;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class Marker extends ASMDirect implements UnreachableCodeCheckSkipable {
    
    private Label label;
    
    protected Marker(ProgramBlock block) {
		super(block);
	}

    protected Marker(ProgramBlock block, Label lbl) {
        super(block);
        label = lbl;
    }

    @Override
    protected void executing() {
    	if(label != null){
            block.getInsnHelper().mark(label);
    	}
    }
    
}
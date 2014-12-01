package cn.wensiqun.asmsupport.operators.asmdirect;


import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.UnreachableCodeCheckSkipable;
import cn.wensiqun.asmsupportasm.Label;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class Marker extends ASMDirect implements UnreachableCodeCheckSkipable {
    
    private Label label;
    
    protected Marker(ProgramBlockInternal block) {
		super(block);
	}

    protected Marker(ProgramBlockInternal block, Label lbl) {
        super(block);
        label = lbl;
    }

    @Override
    protected void doExecute() {
    	if(label != null){
            block.getInsnHelper().mark(label);
    	}
    }
    
}
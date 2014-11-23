package cn.wensiqun.asmsupport.operators.asmdirect;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class Store extends ASMDirect {

    private LocalVariable var;
    
    protected Store(ProgramBlockInternal block, LocalVariable var) {
        super(block);
        this.var = var;
    }

    @Override
    protected void doExecute() {
        insnHelper.storeInsn(var);
    }

}

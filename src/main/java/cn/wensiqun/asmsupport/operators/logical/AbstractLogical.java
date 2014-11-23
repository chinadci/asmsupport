package cn.wensiqun.asmsupport.operators.logical;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractLogical extends AbstractOperator implements Parameterized {

    protected boolean byOtherUsed;
    
    protected String operator;

    protected AbstractLogical(ProgramBlockInternal block) {
        super(block);
    }
    
    @Override
    public void loadToStack(ProgramBlockInternal block) {
        this.execute();
    }

    @Override
    public AClass getParamterizedType() {
        return AClass.BOOLEAN_ACLASS;
    }

    @Override
    public void asArgument() {
        byOtherUsed = true;
        block.removeExe(this);
    }
    
    protected abstract void factorToStack();

    @Override
    protected void doExecute() {
        factorToStack();
        executingProcess();
    }
    
    protected abstract void executingProcess();

}

/**
 * 
 */
package cn.wensiqun.asmsupport.core.operator;

import cn.wensiqun.asmsupport.core.Parameterized;
import cn.wensiqun.asmsupport.core.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.exception.ASMSupportException;
import cn.wensiqun.asmsupport.core.operator.method.MethodInvoker;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class StringAppender extends AbstractOperator implements Parameterized{
    
    private Parameterized[] paras;

    private boolean byOtherUsed;
    private MethodInvoker invoker;
    
    protected StringAppender(ProgramBlockInternal block, Parameterized par1, Parameterized... pars) {
        super(block);
        this.paras = new Parameterized[pars.length + 1];
        this.paras[0] = par1;
        System.arraycopy(pars, 0, this.paras, 1, pars.length);
        
        AClass strBlderCls = AClassFactory.getProductClass(StringBuilder.class);

        MethodInvoker mi = block._invoke(block._new(strBlderCls), "append", par1);
        for(Parameterized par : pars){
            mi = block._invoke(mi, "append", par);
        }
        invoker = block._invoke(mi, "toString");
    }

    @Override
    protected void verifyArgument() {
    }

    @Override
    protected void checkCrement() {
        
    }

    @Override
    protected void checkAsArgument() {
        
    }
    
    @Override
    public void execute() {
        if(byOtherUsed){
            super.execute();
        }else{
            throw new ASMSupportException("the string append operator has not been used by other operator.");
        }
    }

    @Override
    protected void doExecute() {
        if(paras.length == 1){
            paras[0].loadToStack(block);
        }else{
            invoker.loadToStack(block);
        }
    }

    @Override
    public void loadToStack(ProgramBlockInternal block) {
        this.execute();
    }

    @Override
    public AClass getParamterizedType() {
        return AClass.STRING_ACLASS;
    }

    @Override
    public void asArgument() {
        byOtherUsed = true;
        block.removeExe(this);
        if(paras.length == 1){
            paras[0].asArgument();
        }else{
            invoker.asArgument();
        }
    }

}

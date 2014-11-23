/**
 * 
 */
package cn.wensiqun.asmsupport.operators.numerical.posinegative;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.Operators;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class Negative extends AbstractPositiveNegative {

    private static Log log = LogFactory.getLog(Negative.class);
    
    protected Negative(ProgramBlockInternal block, Parameterized factor) {
        super(block, factor);
        operator = Operators.NEG;
    }
    
    @Override
    public void doExecute() {
        log.debug("run the negative operator");
        factorToStack();
        insnHelper.neg(factor.getParamterizedType().getType());
    }

}

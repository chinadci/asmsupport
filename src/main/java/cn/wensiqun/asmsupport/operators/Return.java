/**
 * 
 */
package cn.wensiqun.asmsupport.operators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

/**
 * 用于执行Return
 * @author 温斯群(Joe Wen)
 *
 */
public class Return extends BreakStack {

    private static Log log = LogFactory.getLog(Return.class);
    
    private Parameterized returner;
    
    private Type returnType;
    
    protected Return(ProgramBlock block, Parameterized returner) {
        super(block, false);
        this.returner = returner;
        if(returner != null){
            returner.asArgument();
        }
    }
    
    protected Return(ProgramBlock block, Parameterized returner, boolean autoCreate) {
        super(block, autoCreate);
        this.returner = returner;
        if(returner != null){
            returner.asArgument();
        }
    }
    
    

    @Override
    protected void verifyArgument() {
        
    }

    @Override
    protected void checkAsArgument() {
        
    }
    
    @Override
    public void breakStackExecuting() {
    	
        if(returner == null){
            log.debug("direct return from method");
            insnHelper.returnInsn();
        }else{
            returner.loadToStack(block);
            returnType = returner.getParamterizedType().getType();
            if(returnType == null){
                throw new NullPointerException("return type must be non-null!");
            }
            insnHelper.returnInsn(returnType);
        }
    }

}

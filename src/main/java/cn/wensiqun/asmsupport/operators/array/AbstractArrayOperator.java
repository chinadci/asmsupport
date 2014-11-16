/**
 * 
 */
package cn.wensiqun.asmsupport.operators.array;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.ArrayClass;
import cn.wensiqun.asmsupport.exception.ClassException;
import cn.wensiqun.asmsupport.operators.AbstractOperator;
import cn.wensiqun.asmsupport.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractArrayOperator extends AbstractOperator {

    private static Log log = LogFactory.getLog(AbstractArrayOperator.class);
    
    protected Parameterized arrayReference;
    
    protected Parameterized[] parDims;
    
    protected AbstractArrayOperator(ProgramBlock block, Parameterized arrayVar) {
        super(block);
        this.arrayReference = arrayVar;
    }

    @Override
	protected void checkAsArgument() {
        arrayReference.asArgument();
        if(parDims != null){
            for(Parameterized par : parDims){
                par.asArgument();
            }
        }
	}
    
	@Override
	protected void verifyArgument() {
		if(!(arrayReference.getParamterizedType() instanceof ArrayClass)){
        	throw new ClassException(toString() + " : the declare class of " + arrayReference.toString() + " is not a array type");
        }
		
		if(ArrayUtils.isNotEmpty(parDims)){
			for(Parameterized par : parDims){
				if(!AClassUtils.checkAssignable(par.getParamterizedType(), AClass.INT_ACLASS)) {
					throw new IllegalArgumentException("Type mismatch: cannot convert from " + par.getParamterizedType() + " to " + AClass.INT_ACLASS + "");
				}
			}
		}
		
	}

	protected void getValue(){
        InstructionHelper ih = block.getInsnHelper();
        AClass cls = arrayReference.getParamterizedType();
        if(log.isDebugEnabled()){
            log.debug("load the array reference to stack");
        }
        arrayReference.loadToStack(block);
        
        for(int i=0; i<parDims.length; i++){
            cls = ((ArrayClass) cls).getNextDimType();
            parDims[i].loadToStack(block);
            autoCast(parDims[i].getParamterizedType(), AClass.INT_ACLASS, false);
            ih.arrayLoad(cls.getType());
        }
        
    }
}

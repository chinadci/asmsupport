/**
 * 
 */
package cn.wensiqun.asmsupport.core.operator.numerical.bit;

import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.exception.ASMSupportException;
import cn.wensiqun.asmsupport.core.operator.numerical.AbstractNumerical;
import cn.wensiqun.asmsupport.core.utils.AClassUtils;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class AbstractBitwise extends AbstractNumerical {

    /**该操作是否被其他操作引用 */
    protected boolean byOtherUsed;
    
    protected AbstractBitwise(ProgramBlockInternal block) {
        super(block);
    }
    
    protected final void checkFactor(AClass ftrCls){
        if(ftrCls.isPrimitive() ||
           AClassUtils.isPrimitiveWrapAClass(ftrCls)){
           if(ftrCls.equals(AClass.BOOLEAN_ACLASS) ||
              ftrCls.equals(AClass.FLOAT_ACLASS) ||
              ftrCls.equals(AClass.DOUBLE_ACLASS) ||
              ftrCls.equals(AClass.BOOLEAN_WRAP_ACLASS) ||
              ftrCls.equals(AClass.FLOAT_WRAP_ACLASS) ||
              ftrCls.equals(AClass.DOUBLE_WRAP_ACLASS)){
               throw new ASMSupportException("this operator " + operator + " cannot support for type " + ftrCls );
           }
        }
    }

    @Override
    public final void loadToStack(ProgramBlockInternal block) {
        this.execute();
    }
    
    @Override
    public final void asArgument() {
        byOtherUsed = true;
        block.removeExe(this);
    }

    @Override
    public final void execute() {
        if(byOtherUsed){
            super.execute();
        }else{
            throw new ASMSupportException("the operator " + operator + " has not been used by other operator.");
        }
    }
    
    
}

/**
 * 
 */
package cn.wensiqun.asmsupport.operators;

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.numerical.crement.AbstractCrement;
import cn.wensiqun.asmsupport.utils.AClassUtils;


/**
 * 
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class AbstractOperator extends ByteCodeExecutor {

    protected ProgramBlock block;
    
    protected InstructionHelper insnHelper;
	
    private int compileOrder;

    protected List<AbstractCrement> allCrement;

    protected AbstractOperator(ProgramBlock block) {
    	super();
        this.insnHelper = block.getInsnHelper();
        this.block = block;
        block.addExe(this);
        allCrement = new LinkedList<AbstractCrement>();
    }
    
    public ProgramBlock getBlock() {
        return block;
    }

	@Override
    public final void prepare() {

        onStartPrepareProcess();
        
        beforeInitProperties();
        
        verifyArgument();

        checkOutCrement();
        
        afterInitProperties();
        
        onEndPrepareProcess();
    }

    protected void onStartPrepareProcess() {
    }
    
    protected void beforeInitProperties() {
    }

    protected void verifyArgument() {
    }

    protected void checkOutCrement() {
    }

    /**
     * invoke by OperatorFactory
     */
    protected void checkAsArgument() {
    }

    protected void afterInitProperties() {
    }

    protected void onEndPrepareProcess() {
    }

    @Override
    public void execute() {
        for (AbstractCrement c : allCrement) {
            c.before();
        }

        compileOrder = insnHelper.getMethod().nextInsNumber();
        executing();

        for (AbstractCrement c : allCrement) {
            c.after();
        }
    }

    protected abstract void executing();
    
    /**
     * 
     * @param from
     * @param to
     */
    protected void autoCast(AClass from, AClass to){
        if(from.isChildOrEqual(to)){
            return;
        }
        
        if(from.isPrimitive() && to.isPrimitive()){
            if(!from.equals(AClass.BOOLEAN_ACLASS) &&
               !to.equals(AClass.BOOLEAN_ACLASS) && from.getCastOrder() <= to.getCastOrder()){
                insnHelper.cast(from.getType(), to.getType());
                return;
            }            
        }else if(from.isPrimitive() && (AClassUtils.getPrimitiveWrapAClass(from).equals(to) || to.equals(AClass.OBJECT_ACLASS))){
            insnHelper.box(from.getType());
            return;
        }else if(AClassUtils.isPrimitiveWrapAClass(from) && from.equals(AClassUtils.getPrimitiveWrapAClass(to))){
            Type primType = InstructionHelper.getUnBoxedType(from.getType());
            insnHelper.unbox(from.getType());
            insnHelper.cast(primType, to.getType());
            return;
        }
        
        throw new ASMSupportException("cannot auto cast from " + from + " to " + to + " also you can use CheckCast to try again!");
        
    }
    
    public final int getCompileOrder() {
        return compileOrder;
    }


}

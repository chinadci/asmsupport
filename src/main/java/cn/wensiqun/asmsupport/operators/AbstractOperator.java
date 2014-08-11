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
        this.insnHelper = block.getInsnHelper();
        this.block = block;
        allCrement = new LinkedList<AbstractCrement>();
        addQueue();
    }
    
    public ProgramBlock getBlock() {
        return block;
    }
    
    protected void addQueue()
    {
        block.addExe(this);
    }

	@Override
    public final void prepare() {

        startingPrepare();
        
        initAdditionalProperties();
        
        verifyArgument();

        checkCrement();
        
        endingPrepare();
    }

	/**
	 * template for {@link #prepare()} method.
	 * <h4>Order :</h4>
	 * <ol>
	 *     <li><b>startPrepare</b></li>
     *     <li>initAdditionalProperties</li>
     *     <li>verifyArgument</li>
     *     <li>checkCrement</li>
     *     <li>endingPrepare</li>
	 * </ol>
	 */
    protected void startingPrepare() {}
    
    /**
     * template for {@link #prepare()} method.
     * <h4>Order :</h4>
     * <ol>
     *     <li>startPrepare</li>
     *     <li><b>initAdditionalProperties</b></li>
     *     <li>verifyArgument</li>
     *     <li>checkCrement</li>
     *     <li>endingPrepare</li>
     * </ol>
     */
    protected void initAdditionalProperties() {}

    /**
     * template for {@link #prepare()} method.
     * <h4>Order :</h4>
     * <ol>
     *     <li>startPrepare</li>
     *     <li>initAdditionalProperties</li>
     *     <li><b>verifyArgument</b></li>
     *     <li>checkCrement</li>
     *     <li>endingPrepare</li>
     * </ol>
     */
    protected void verifyArgument() {}

    /**
     * template for {@link #prepare()} method.
     * <h4>Order :</h4>
     * <ol>
     *     <li>startPrepare</li>
     *     <li>initAdditionalProperties</li>
     *     <li>verifyArgument</li>
     *     <li><b>checkCrement</b></li>
     *     <li>endingPrepare</li>
     * </ol>
     */
    protected void checkCrement() {}

    /**
     * template for {@link #prepare()} method.
     * <h4>Order :</h4>
     * <ol>
     *     <li>startPrepare</li>
     *     <li>initAdditionalProperties</li>
     *     <li>verifyArgument</li>
     *     <li>checkCrement</li>
     *     <li><b>endingPrepare</b></li>
     * </ol>
     */
    protected void endingPrepare() {}

    /**
     * invoke by OperatorFactory
     */
    protected void checkAsArgument() {}

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

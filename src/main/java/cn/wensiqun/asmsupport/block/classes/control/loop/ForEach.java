package cn.wensiqun.asmsupport.block.classes.control.loop;


import org.objectweb.asm.Label;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.clazz.ArrayClass;
import cn.wensiqun.asmsupport.definition.value.Value;
import cn.wensiqun.asmsupport.definition.variable.ExplicitVariable;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.exception.ASMSupportException;
import cn.wensiqun.asmsupport.operators.Jumpable;
import cn.wensiqun.asmsupport.operators.asmdirect.GOTO;
import cn.wensiqun.asmsupport.operators.asmdirect.Marker;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;


/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class ForEach extends ProgramBlock implements Loop, LocalVariableBody{
    
    private ExplicitVariable iteratorVar;
    
    private Parameterized condition;
    
    private Label startLbl = new Label();
    private Label conditionLbl = new Label();
    private Label continueLbl = new Label();
    private Label endLbl = new Label();
    
    public ForEach(ExplicitVariable iteratorVar) {
        super();
        this.iteratorVar = iteratorVar;
        checkMember(iteratorVar);
    }
    
    private void checkMember(ExplicitVariable member){
        AClass cls = member.getParamterizedType();
        if(!cls.isArray() &&
           !cls.isChildOrEqual(AClassFactory.getProductClass(Iterable.class))){
            throw new ASMSupportException("Can only iterate over an array or an instance of java.lang.Iterable.");
        }
    }

    @Override
    public void doExecute() {
    	
        for(Executable exe : getQueue()){
            exe.execute();
        }
        
        if(condition instanceof Jumpable){
        	Jumpable jmp = (Jumpable) condition;
        	jmp.setJumpLable(startLbl);
        	jmp.executeAndJump(ControlType.LOOP);
        }else{
            condition.loadToStack(this);
            insnHelper.unbox(condition.getParamterizedType().getType());
            insnHelper.ifZCmp(InstructionHelper.NE, startLbl);
        }
        insnHelper.mark(endLbl);
    }
    
    @Override
    public final void generate() {
        //?
        //?new NOP(getExecutor());
        if(iteratorVar.getParamterizedType().isArray()){
            final LocalVariable i = createVariable(null, AClass.INT_ACLASS, true, Value.value(0));
            
            OperatorFactory.newOperator(GOTO.class, 
            		new Class[]{ProgramBlock.class, Label.class}, 
            		getExecutor(), conditionLbl);
            //new GOTO(getExecutor(), conditionLbl);
            
            //?new NOP(getExecutor());
            

            OperatorFactory.newOperator(Marker.class, 
            		new Class[]{ProgramBlock.class, Label.class}, 
            		getExecutor(), startLbl);
            //new Marker(getExecutor(), startLbl);
            
            //?new NOP(getExecutor());
            
            LocalVariable obj = createVariable(null, ((ArrayClass)iteratorVar.getParamterizedType()).getNextDimType(), true, arrayLoad(iteratorVar, i) );
            body(obj);

            //?new Marker(getExecutor(), continueLbl);
            
            postInc(i);
            
            //?new Marker(getExecutor(), conditionLbl);
            condition = lessThan(i, arrayLength(iteratorVar));
            //((LessThan)condition).setJumpLable(startLbl);
        }else{
        	final LocalVariable itr = createVariable(null, AClass.ITERABLE_ACLASS, true, invoke(iteratorVar, "iterator"));
        	
            OperatorFactory.newOperator(GOTO.class, 
            		new Class[]{ProgramBlock.class, Label.class}, 
            		getExecutor(), conditionLbl);
            //new GOTO(getExecutor(), conditionLbl);
        	
            OperatorFactory.newOperator(Marker.class, 
            		new Class[]{ProgramBlock.class, Label.class}, 
            		getExecutor(), startLbl);
        	//new Marker(getExecutor(), startLbl);
            //?new NOP(getExecutor());

            LocalVariable obj = createVariable(null, AClass.OBJECT_ACLASS, true, invoke(itr, "next"));
            body(obj);

            //?new Marker(getExecutor(), continueLbl);
            
        	condition = invoke(itr, "hasNext");
        }
        condition.asArgument();
    }
    
    @Override
    public Label getBreakLabel() {
        return endLbl;
    }

    @Override
    public Label getContinueLabel() {
        return continueLbl;
    }

	@Override
	public String toString() {
		return "For Each Block:" + super.toString();
	}
}

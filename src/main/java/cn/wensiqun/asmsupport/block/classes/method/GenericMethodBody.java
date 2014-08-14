package cn.wensiqun.asmsupport.block.classes.method;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AnyException;
import cn.wensiqun.asmsupport.definition.method.AMethod;
import cn.wensiqun.asmsupport.definition.method.meta.AMethodMeta;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupport.definition.variable.meta.LocalVariableMeta;
import cn.wensiqun.asmsupport.operators.util.OperatorFactory;
import cn.wensiqun.asmsupport.operators.variable.LocalVariableCreator;
import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupport.utils.memory.Component;
import cn.wensiqun.asmsupport.utils.memory.Scope;
import cn.wensiqun.asmsupport.utils.memory.ScopeLogicVariable;

public abstract class GenericMethodBody extends ProgramBlock {

    private static Log log = LogFactory.getLog(GenericMethodBody.class);
    
    private List<TryCatchInfo> tryCatches;

    protected LocalVariable[] argments;
    
    public GenericMethodBody() {
		super();
        tryCatches = new ArrayList<TryCatchInfo>();
	}

	public LocalVariable[] getMethodArguments(){
		return argments;
	}

	@Override
	public final void generateInsn() {
		generateBody();
		//if this method just only contain try catch block, so here we need tigger try catch prepare again;
		//tiggerTryCatchPrepare();
	}
	
	/**
	 * generate the method body
	 */
	public abstract void generateBody();

	@Override
    protected void init() {
	    AMethod method = getMethod();
    	AMethodMeta me = method.getMethodMeta(); 
        if (!method.isStatic()) {
            OperatorFactory.newOperator(LocalVariableCreator.class, 
            		new Class<?>[]{ProgramBlock.class, String.class, Type.class, Type.class}, 
            		getExecutor(), ASConstant.THIS, me.getOwner().getType(), method.getMethodMeta().getOwner().getType());
        }

        String[] argNames = me.getArgNames();
        AClass[] argClsses = me.getArgClasses();
        argments = new LocalVariable[argNames.length];
        for (int i = 0; i < argNames.length; i++) {
            ScopeLogicVariable slv = new ScopeLogicVariable(argNames[i], getScope(), argClsses[i].getType(),
                    argClsses[i].getType());
            LocalVariableMeta lve = new LocalVariableMeta(argNames[i], 0, argClsses[i]);
            LocalVariable lv = new LocalVariable(lve);
            lv.setScopeLogicVar(slv);
            argments[i] = lv;
        }
        method.setArguments(argments);
    }
    
    @Override
    public final void executing() {
        AMethod method = getMethod();
        if (log.isDebugEnabled()) {
            StringBuilder str = new StringBuilder("create method: ------------");
            str.append(method.getMethodMeta().getMethodString());
            log.debug(str);
        }
        
        for(Executable exe : getQueue()){
            exe.execute();
        }
        
        for(TryCatchInfo tci : tryCatches){
        	if(tci.end.getOffset() - tci.start.getOffset() > 0)
        	{
                insnHelper.tryCatchBlock(tci.start, tci.end, tci.hander, tci.exception);
        	}
        	/*else
        	{
        		System.out.println((tci.end.getOffset() - tci.start.getOffset()) + " : " + tci.start.getName() + "->" + tci.end.getName());
        	}*/
        }
        
        
    }
    

    /**
     * 
     */
    public void endMethodBody() {
        checkNoReturnBlock();
        declarationVariable(getScope());
        int s = getMethod().getStack().getMaxSize();
        int l = getScope().getLocals().getSize();
        insnHelper.maxs(s, l);
    }

    /**
     * 检测时候有未返回的分支
     */
    private void checkNoReturnBlock() {

    };

    /**
     * local variable declaration.
     */
    private void declarationVariable(Scope parent) {
        List<Component> coms = parent.getComponents();
        Component com;
        Scope lastBrotherScope = null;
        for (int i = 0; i < coms.size(); i++) {
            com = coms.get(i);
            if (com instanceof ScopeLogicVariable) {
                ScopeLogicVariable slv = (ScopeLogicVariable) com;
                if(slv.isAnonymous()){
                    continue;
                }
                insnHelper.declarationVariable(slv.getName(), slv
                        .getDeclareType().getDescriptor(), null, slv.getSpecifiedStartLabel(), parent
                        .getEnd(), slv.getInitStartPos());
            } else {
                lastBrotherScope = (Scope) com;
                declarationVariable(lastBrotherScope);
            }
        }
    }
    
    public void addTryCatchInfo(Label start, Label end, Label hander, AClass exception){
        TryCatchInfo tci = new TryCatchInfo();
        tci.start = start;
        tci.end = end;
        tci.hander = hander;
        tci.exception = exception == null || exception.getType() == AnyException.ANY.getType() ? null : exception.getType();
        tryCatches.add(tci);
    }
    
    private static class TryCatchInfo{
        Label start;
        Label end;
        Label hander;
        Type exception;
    }

}
package cn.wensiqun.asmsupport.operators.variable;


import org.objectweb.asm.Type;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.AbstractOperator;
import cn.wensiqun.asmsupport.utils.lang.StringUtils;
import cn.wensiqun.asmsupport.utils.memory.ScopeLogicVariable;


public class LocalVariableCreator extends AbstractOperator {

	private ScopeLogicVariable slv;
	
	protected LocalVariableCreator(ProgramBlockInternal block, String name, Type declareClass,
            Type actuallyClass){
	    super(block);	
		block.removeExe(this);
		if(StringUtils.isBlank(name)){
	        this.slv = new ScopeLogicVariable(block.getScope(), declareClass, actuallyClass, true);
		}else{
	        this.slv = new ScopeLogicVariable(name, block.getScope(), declareClass, actuallyClass);
		}
	}

	public ScopeLogicVariable getScopeLogicVariable(){
		return slv;
	}
	
	@Override
	protected void doExecute() {}

}

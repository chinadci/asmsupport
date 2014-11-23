/**
 * 
 */
package cn.wensiqun.asmsupport.operators.relational;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.asm.InstructionHelper;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockInternal;
import cn.wensiqun.asmsupport.operators.Operators;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class LessEqual extends NumericalRelational {

    protected LessEqual(ProgramBlockInternal block, Parameterized factor1, Parameterized factor2) {
        super(block, factor1, factor2);
        this.operator = Operators.LESS_THAN_OR_EQUAL_TO;
    }

    @Override
    protected void relationalOperator() {
        insnHelper.ifCmp(targetClass.getType(), InstructionHelper.GT, falseLbl);
    }

	@Override
	protected void relationalOperatorWithInLoopCondition() {
	    insnHelper.ifCmp(targetClass.getType(), InstructionHelper.LE, falseLbl);
	}

}

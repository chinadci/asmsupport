/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package cn.wensiqun.asmsupport.core.operator.logical;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.core.operator.Operators;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalAnd;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public class LogicalAnd extends BinaryLogical implements ILogicalAnd<InternalParameterized> {
    
    protected LogicalAnd(ProgramBlockInternal block, InternalParameterized factor1, InternalParameterized factor2) {
        super(block, factor1, factor2);
        operator = Operators.BIT_AND;
    }

    @Override
    protected void executing() {
        insnHelper.bitAnd(AClass.BOOLEAN_ACLASS.getType());    
    }

	@Override
	public LogicalAnd logicalAnd(InternalParameterized para) {
		return block.logicalAnd(this, para);
	}

	@Override
	public LogicalOr logicalOr(InternalParameterized para) {
		return block.logicalOr(this, para);
	}

	@Override
	public LogicalXor logicalXor(InternalParameterized para) {
		return block.logicalXor(this, para);
	}

    @Override
    public ShortCircuitAnd and(InternalParameterized para) {
        return block.and(this, para);
    }

	@Override
	public ShortCircuitOr or(InternalParameterized para) {
        return block.or(this, para);
	}

}

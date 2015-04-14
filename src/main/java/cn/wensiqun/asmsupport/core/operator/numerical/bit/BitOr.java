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
package cn.wensiqun.asmsupport.core.operator.numerical.bit;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.operator.Operators;
import cn.wensiqun.asmsupport.standard.operators.numerical.bit.IBitOr;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class BitOr extends BinaryBitwise implements IBitOr<InternalParameterized> {

    protected BitOr(ProgramBlockInternal block, InternalParameterized factor1, InternalParameterized factor2) {
        super(block, factor1, factor2);
        this.operator = Operators.BIT_OR;
    }

    @Override
    public void innerRunExe() {
        insnHelper.bitOr(targetClass.getType());
    }

    @Override
    public BitOr bor(InternalParameterized para) {
        return block.bor(this, para);
    }

}

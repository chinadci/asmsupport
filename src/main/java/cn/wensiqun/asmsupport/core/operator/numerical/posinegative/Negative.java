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
package cn.wensiqun.asmsupport.core.operator.numerical.posinegative;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.block.ProgramBlockInternal;
import cn.wensiqun.asmsupport.core.log.Log;
import cn.wensiqun.asmsupport.core.log.LogFactory;
import cn.wensiqun.asmsupport.core.operator.Operators;

/**
 * @author 温斯群(Joe Wen)
 *
 */
public class Negative extends AbstractPositiveNegative {

    private static final Log LOG = LogFactory.getLog(Negative.class);
    
    protected Negative(ProgramBlockInternal block, InternalParameterized factor) {
        super(block, factor);
        operator = Operators.NEG;
    }
    
    @Override
    public void doExecute() {
        LOG.print("run the negative operator");
        factorToStack();
        insnHelper.neg(factor.getParamterizedType().getType());
    }

}

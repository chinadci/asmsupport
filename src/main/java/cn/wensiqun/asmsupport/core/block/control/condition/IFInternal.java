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
package cn.wensiqun.asmsupport.core.block.control.condition;

import cn.wensiqun.asmsupport.core.ByteCodeExecutor;
import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.asm.InstructionHelper;
import cn.wensiqun.asmsupport.core.clazz.AClassFactory;
import cn.wensiqun.asmsupport.core.exception.ASMSupportException;
import cn.wensiqun.asmsupport.core.operator.Jumpable;
import cn.wensiqun.asmsupport.org.objectweb.asm.Label;
import cn.wensiqun.asmsupport.standard.branch.IIF;

public abstract class IFInternal extends ConditionBranchBlock implements IIF<ElseIFInternal, ElseInternal> {
    private InternalParameterized condition;

    public IFInternal(InternalParameterized condition) {
        this.condition = condition;
        condition.asArgument();
    }

    @Override
    protected void init() {
        if (!condition.getResultType().equals(AClassFactory.getType(Boolean.class))
                && !condition.getResultType().equals(AClassFactory.getType(boolean.class))) {
            throw new ASMSupportException("the condition type of if statement must be boolean or Boolean, but was "
                    + condition.getResultType());
        }
    }

    @Override
    public void generate() {
        body();
    }

    @Override
    protected void doExecute() {
        Label posLbl = new Label();

        if (condition instanceof Jumpable) {
            ((Jumpable) condition).jumpNegative(null, posLbl, getEnd());// .executeJump(Opcodes.JUMP_NEGATIVE,
                                                                        // endLbl);
        } else {
            condition.loadToStack(this);
            insnHelper.unbox(condition.getResultType().getType());
            insnHelper.ifZCmp(InstructionHelper.EQ, getEnd());
        }

        insnHelper.mark(posLbl);
        for (ByteCodeExecutor exe : getQueue()) {
            exe.execute();
        }

        if (nextBranch != null) {
            insnHelper.goTo(getSerialEnd());
        }

    }

    @Override
    public ElseIFInternal elseif(ElseIFInternal elsIf) {
        initNextBranch(elsIf);
        return elsIf;
    }

    @Override
    public ElseInternal else_(ElseInternal els) {
        initNextBranch(els);
        return els;
    }

    @Override
    public String toString() {
        return "IF Block:" + condition.toString();
    }
}

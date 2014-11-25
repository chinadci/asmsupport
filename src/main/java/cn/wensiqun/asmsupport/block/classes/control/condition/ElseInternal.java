package cn.wensiqun.asmsupport.block.classes.control.condition;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupportgeneric.IElse;

public abstract class ElseInternal extends ConditionBranchBlock implements IElse
{

    @Override
    public void generate()
    {
        body();
    }

    @Override
    protected void doExecute()
    {
        for(Executable exe : getQueue()){
            exe.execute();
        }
    }

}

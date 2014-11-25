package cn.wensiqun.asmsupport.block.classes.control.condition;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupportgeneric.GenericElse;

public abstract class ElseInternal extends ConditionBranchBlock implements GenericElse
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

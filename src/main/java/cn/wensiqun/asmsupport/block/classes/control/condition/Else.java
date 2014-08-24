package cn.wensiqun.asmsupport.block.classes.control.condition;

import cn.wensiqun.asmsupport.Executable;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Else extends ConditionBranchBlock implements Body
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

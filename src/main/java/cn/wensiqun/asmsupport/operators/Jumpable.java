package cn.wensiqun.asmsupport.operators;


import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.ControlType;
import cn.wensiqun.asmsupportasm.Label;


public interface Jumpable extends Parameterized {

    void executeAndJump(ControlType control);
    
    void setJumpLable(Label lbl);
}

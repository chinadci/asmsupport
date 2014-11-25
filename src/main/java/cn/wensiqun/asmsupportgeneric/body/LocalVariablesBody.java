package cn.wensiqun.asmsupportgeneric.body;

import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public interface LocalVariablesBody extends IBody
{
    void body(LocalVariable... args);
}

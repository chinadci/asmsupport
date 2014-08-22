package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariablesBody;

public abstract class StaticMethodBody extends GenericMethodBody implements LocalVariablesBody {
    
	@Override
    public final void generateBody() {
	    body(argments);
    }
	

}

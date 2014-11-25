package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public abstract class StaticMethodBodyInternal extends GenericMethodBody implements LocalVariablesBody {
    
	@Override
    public final void generateBody() {
	    body(argments);
    }
	

}

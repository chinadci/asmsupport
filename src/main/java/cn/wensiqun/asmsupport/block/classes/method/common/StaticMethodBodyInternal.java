package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.AbstractMethodBody;
import cn.wensiqun.asmsupportgeneric.IStaticMethodBody;
import cn.wensiqun.asmsupportgeneric.body.LocalVariablesBody;

public abstract class StaticMethodBodyInternal extends AbstractMethodBody implements IStaticMethodBody {
    
	@Override
    public final void generateBody() {
	    body(argments);
    }
	

}

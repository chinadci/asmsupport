package cn.wensiqun.asmsupport.block.classes.method.common;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.ArgumentsBody;

public abstract class StaticMethodBody extends GenericMethodBody implements ArgumentsBody {
    
	@Override
    public final void generateBody() {
	    body(argments);
    }
	

}

package cn.wensiqun.asmsupport.block.classes.method.clinit;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class ClinitBody extends GenericMethodBody implements Body {

	@Override
	protected void init() {
		return;
	}
    
    @Override
    public final void generateBody() {
        body();
    }
	
}

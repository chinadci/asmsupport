package cn.wensiqun.asmsupport.block.classes.method.clinit;

import cn.wensiqun.asmsupport.block.classes.method.GenericMethodBody;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class ClinitBodyInternal extends GenericMethodBody implements CommonBody {

	@Override
	protected void init() {
		return;
	}
    
    @Override
    public final void generateBody() {
        body();
    }
	
}

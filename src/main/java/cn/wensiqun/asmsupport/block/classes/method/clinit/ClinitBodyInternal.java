package cn.wensiqun.asmsupport.block.classes.method.clinit;

import cn.wensiqun.asmsupport.block.classes.method.AbstractMethodBody;
import cn.wensiqun.asmsupportgeneric.IStaticBlockBody;

public abstract class ClinitBodyInternal extends AbstractMethodBody implements IStaticBlockBody {

	@Override
	protected void init() {
		return;
	}
    
    @Override
    public final void generateBody() {
        body();
    }
	
}

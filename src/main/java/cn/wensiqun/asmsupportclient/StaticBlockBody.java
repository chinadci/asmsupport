package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.clinit.ClinitBodyInternal;
import cn.wensiqun.asmsupportgeneric.method.IStaticBlockBody;

public abstract class StaticBlockBody extends ProgramBlock<ClinitBodyInternal> implements IStaticBlockBody {

	public StaticBlockBody() {
		target = new ClinitBodyInternal() {
			@Override
			public void body() {
				StaticBlockBody.this.body();
			}
		};
	}
	
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.method.clinit.ClinitBodyInternal;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class StaticBlockBody extends ProgramBlock<ClinitBodyInternal> implements CommonBody {

	public StaticBlockBody() {
		target = new ClinitBodyInternal() {
			@Override
			public void body() {
				StaticBlockBody.this.body();
			}
		};
	}
	
}

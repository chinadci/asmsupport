package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.SynchronizedInternal;
import cn.wensiqun.asmsupportgeneric.ISynchronized;
import cn.wensiqun.asmsupportgeneric.body.ParameterizedBody;

public abstract class Synchronized extends ProgramBlock<SynchronizedInternal> implements ISynchronized {

	public Synchronized(Parameterized lock) {
		target = new SynchronizedInternal(lock) {

			@Override
			public void body(Parameterized e) {
				Synchronized.this.body(e);
			}
			
		};
	}

}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.common.Synchronized;
import cn.wensiqun.asmsupport.block.interfaces.body.ParameterizedBody;

public abstract class SynchronizedClient extends ProgramBlockClient<Synchronized> implements ParameterizedBody {

	public SynchronizedClient(Parameterized lock) {
		target = new Synchronized(lock) {

			@Override
			public void body(Parameterized e) {
				SynchronizedClient.this.body(e);
			}
			
		};
	}

}

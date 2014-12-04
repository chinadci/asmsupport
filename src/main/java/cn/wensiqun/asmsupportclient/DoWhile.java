package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.Parameterized;
import cn.wensiqun.asmsupport.block.classes.control.loop.DoWhileInternal;
import cn.wensiqun.asmsupportgeneric.loop.IDoWhile;

public abstract class DoWhile extends ProgramBlock<DoWhileInternal> implements IDoWhile {
    
	public DoWhile(Parameterized condition) {
		target = new DoWhileInternal(condition) {
			@Override
			public void body() {
				DoWhile.this.body();
			}
		};
	}
}

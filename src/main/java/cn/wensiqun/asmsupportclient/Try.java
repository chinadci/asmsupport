package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.TryInternal;
import cn.wensiqun.asmsupportgeneric.GenericTry;
import cn.wensiqun.asmsupportgeneric.body.CommonBody;

public abstract class Try extends ProgramBlock<TryInternal> implements GenericTry<Catch, Finally> {

	public Try() {
		target = new TryInternal() {

			@Override
			public void body() {
				Try.this.body();
			}
			
		};
	}
	
	@Override
    public Catch _catch(Catch catchBlock)
    {
        target._catch(catchBlock.target);
        return catchBlock;
    }

	@Override
    public Finally _finally(Finally finallyClient) {
    	target._finally(finallyClient.target);
    	return finallyClient;
    }
	
}

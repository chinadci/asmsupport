package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.block.control.exception.TryInternal;
import cn.wensiqun.asmsupport.standard.excep.ITry;

public abstract class Try extends ProgramBlock<TryInternal> implements ITry<Catch, Finally> {

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

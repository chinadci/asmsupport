package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.TryInternal;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class Try extends ProgramBlock<TryInternal> implements Body {

	public Try() {
		target = new TryInternal() {

			@Override
			public void body() {
				Try.this.body();
			}
			
		};
	}
	
    public Catch _catch(Catch catchBlock)
    {
        target._catch(catchBlock.target);
        return catchBlock;
    }
    
    public Finally _finally(Finally finallyClient) {
    	target._finally(finallyClient.target);
    	return finallyClient;
    }
	
}

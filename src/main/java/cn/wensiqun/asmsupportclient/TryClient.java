package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.interfaces.body.Body;

public abstract class TryClient extends ProgramBlockClient<Try> implements Body {

	public TryClient() {
		target = new Try() {

			@Override
			public void body() {
				TryClient.this.body();
			}
			
		};
	}
	
    public CatchClient _catch(CatchClient catchBlock)
    {
        target._catch(catchBlock.target);
        return catchBlock;
    }
    
    public FinallyClient _finally(FinallyClient finallyClient) {
    	target._finally(finallyClient.target);
    	return finallyClient;
    }
	
}

package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.Catch;
import cn.wensiqun.asmsupport.block.interfaces.body.LocalVariableBody;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;

public abstract class CatchClient extends ProgramBlockClient<Catch> implements LocalVariableBody {

	public CatchClient(AClass aclass) {
		target = new Catch(aclass) {

			@Override
			public void body(LocalVariable e) {
				CatchClient.this.body(e);
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

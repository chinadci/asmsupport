package cn.wensiqun.asmsupportclient;

import cn.wensiqun.asmsupport.block.classes.control.exception.CatchInternal;
import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.definition.variable.LocalVariable;
import cn.wensiqun.asmsupportgeneric.GenericCatch;
import cn.wensiqun.asmsupportgeneric.body.LocalVariableBody;

public abstract class Catch extends ProgramBlock<CatchInternal> implements GenericCatch<Catch, Finally> {

	public Catch(AClass aclass) {
		target = new CatchInternal(aclass) {

			@Override
			public void body(LocalVariable e) {
				Catch.this.body(e);
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

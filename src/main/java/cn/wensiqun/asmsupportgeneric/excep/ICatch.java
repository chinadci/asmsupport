package cn.wensiqun.asmsupportgeneric.excep;

import cn.wensiqun.asmsupportgeneric.body.IBody;
import cn.wensiqun.asmsupportgeneric.body.LocalVariableBody;


public interface ICatch<_Catch extends IBody, _Finally extends IBody> extends LocalVariableBody {

	public _Catch _catch(_Catch catchBlock);
    
    public _Finally _finally(_Finally finallyClient);
	
}

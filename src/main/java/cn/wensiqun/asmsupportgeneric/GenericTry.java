package cn.wensiqun.asmsupportgeneric;

import cn.wensiqun.asmsupportgeneric.body.CommonBody;
import cn.wensiqun.asmsupportgeneric.body.IBody;

public interface GenericTry<_Catch extends IBody, _Finally extends IBody> extends CommonBody {

	public _Catch _catch(_Catch catchBlock);
    
    public _Finally _finally(_Finally finallyClient);
	
}

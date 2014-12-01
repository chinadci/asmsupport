package cn.wensiqun.asmsupportgeneric.branch;

import cn.wensiqun.asmsupportgeneric.body.CommonBody;
import cn.wensiqun.asmsupportgeneric.body.IBody;

public interface IIF<_ElseIF extends IBody, _Else extends IBody> extends CommonBody {

	_ElseIF _elseif(_ElseIF elseif);
	
	_Else _else(_Else els);
	
}

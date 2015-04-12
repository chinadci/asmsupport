package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.InternalParameterized;
import cn.wensiqun.asmsupport.core.clazz.AClass;
import cn.wensiqun.asmsupport.standard.Parameterized;

public class ClientParameterized implements Parameterized {

	InternalParameterized target;
	
	public ClientParameterized(InternalParameterized target) {
		this.target = target;
	}

	@Override
	public AClass getParamterizedType() {
		return target.getParamterizedType();
	}
	
}

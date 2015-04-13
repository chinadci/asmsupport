package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalOr;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalXor;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitAnd;

public class ClientShortCircuitAnd extends AbstractParameterized<ShortCircuitAnd> implements IShortCircuitAnd<ClientParameterized> {

    public ClientShortCircuitAnd(ShortCircuitAnd target) {
        super(target);
    }

    @Override
    public ClientShortCircuitAnd and(ClientParameterized para) {
        return new ClientShortCircuitAnd(target.and(para.target));
    }

    @Override
    public ClientShortCircuitOr or(ClientParameterized para) {
        return new ClientShortCircuitOr(target.or(para.target));
    }

	@Override
	public ILogicalAnd<ClientParameterized> logicalAnd(ClientParameterized para) {
		return null;
	}

	@Override
	public ILogicalOr<ClientParameterized> logicalOr(ClientParameterized para) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILogicalXor<ClientParameterized> logicalXor(ClientParameterized para) {
		// TODO Auto-generated method stub
		return null;
	}

}

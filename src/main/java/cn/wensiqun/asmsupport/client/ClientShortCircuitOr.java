package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalOr;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalXor;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitOr;

public class ClientShortCircuitOr extends AbstractParameterized<ShortCircuitOr> implements IShortCircuitOr<ClientParameterized> {

    public ClientShortCircuitOr(ShortCircuitOr target) {
        super(target);
    }

	@Override
	public ILogicalAnd<ClientParameterized> logicalAnd(ClientParameterized para) {
		return null;
	}

	@Override
	public ILogicalOr<ClientParameterized> logicalOr(ClientParameterized para) {
		return null;
	}

	@Override
	public ILogicalXor<ClientParameterized> logicalXor(ClientParameterized para) {
		return null;
	}

    @Override
    public ClientShortCircuitAnd and(ClientParameterized para) {
        return new ClientShortCircuitAnd(target.and(para.target));
    }

    @Override
    public ClientShortCircuitOr or(ClientParameterized para) {
        return new ClientShortCircuitOr(target.or(para.target));
    }

}

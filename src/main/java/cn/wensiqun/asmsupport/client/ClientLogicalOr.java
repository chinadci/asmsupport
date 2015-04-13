package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.logical.LogicalOr;
import cn.wensiqun.asmsupport.standard.operators.logical.ILogicalOr;

public class ClientLogicalOr extends AbstractParameterized<LogicalOr> implements ILogicalOr<ClientParameterized> {

	public ClientLogicalOr(LogicalOr target) {
		super(target);
	}

	@Override
	public ClientLogicalAnd logicalAnd(ClientParameterized para) {
		return new ClientLogicalAnd(target.logicalAnd(para.target));
	}

	@Override
	public ClientLogicalOr logicalOr(ClientParameterized para) {
		return new ClientLogicalOr(target.logicalOr(para.target));
	}

	@Override
	public ClientLogicalXor logicalXor(ClientParameterized para) {
		return new ClientLogicalXor(target.logicalXor(para.target));
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

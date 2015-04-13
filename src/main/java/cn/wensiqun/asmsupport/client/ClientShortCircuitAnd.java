package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitAnd;

public class ClientShortCircuitAnd extends AbstractParameterized<ShortCircuitAnd> implements IShortCircuitAnd<ClientParameterized> {

    public ClientShortCircuitAnd(ShortCircuitAnd target) {
        super(target);
    }

    @Override
    public ClientShortCircuitAnd and(ClientParameterized para) {
        return new ClientShortCircuitAnd(target.and(para.target));
    }

}

package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.logical.ShortCircuitOr;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitOr;

public class ClientShortCircuitOr extends AbstractParameterized<ShortCircuitOr> implements IShortCircuitOr<ClientParameterized> {

    public ClientShortCircuitOr(ShortCircuitOr target) {
        super(target);
    }

    @Override
    public ClientShortCircuitOr or(ClientParameterized para) {
        return new ClientShortCircuitOr(target.or(para.target));
    }

}

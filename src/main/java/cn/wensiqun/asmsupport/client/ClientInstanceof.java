package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.InstanceofOperator;
import cn.wensiqun.asmsupport.standard.operators.IInstanceof;

public class ClientInstanceof extends AbstractParameterized<InstanceofOperator> implements IInstanceof<ClientParameterized> {

    public ClientInstanceof(InstanceofOperator target) {
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
    
    
}

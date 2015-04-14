package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Addition;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IAdd;

public class ClientAdd extends AbstractParameterized<Addition> implements IAdd<ClientParameterized> {

    public ClientAdd(Addition target) {
        super(target);
    }

    @Override
    public ClientSub sub(ClientParameterized para) {
        return new ClientSub(target.sub(para.target));
    }

    @Override
    public ClientAdd add(ClientParameterized para) {
        return new ClientAdd(target.add(para.target));
    }

}

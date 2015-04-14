package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Subtraction;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.ISub;

public class ClientSub extends AbstractParameterized<Subtraction> implements ISub<ClientParameterized> {

    public ClientSub(Subtraction target) {
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

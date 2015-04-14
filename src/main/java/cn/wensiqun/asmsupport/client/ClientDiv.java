package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Division;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IDiv;

public class ClientDiv extends AbstractParameterized<Division> implements IDiv<ClientParameterized> {

    public ClientDiv(Division target) {
        super(target);
    }

    @Override
    public ClientMul mul(ClientParameterized para) {
        return new ClientMul(target.mul(para.target));
    }

    @Override
    public ClientDiv div(ClientParameterized para) {
        return new ClientDiv(target.div(para.target));
    }

    @Override
    public ClientMod mod(ClientParameterized para) {
        return new ClientMod(target.mod(para.target));
    }

}

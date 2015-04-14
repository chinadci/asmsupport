package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Multiplication;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IDiv;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IMul;

public class ClientMul extends AbstractParameterized<Multiplication> implements IMul<ClientParameterized> {

    public ClientMul(Multiplication target) {
        super(target);
    }

    @Override
    public ClientMul mul(ClientParameterized para) {
        return null;
    }

    @Override
    public IDiv<ClientParameterized> div(ClientParameterized para) {
        return null;
    }

    @Override
    public ClientMod mod(ClientParameterized para) {
        return null;
    }


}

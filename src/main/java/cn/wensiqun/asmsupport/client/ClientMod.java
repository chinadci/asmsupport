package cn.wensiqun.asmsupport.client;

import cn.wensiqun.asmsupport.core.operator.numerical.arithmetic.Modulus;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IDiv;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IMod;
import cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic.IMul;

public class ClientMod extends AbstractParameterized<Modulus> implements IMod<ClientParameterized> {

    public ClientMod(Modulus target) {
        super(target);
    }

    @Override
    public IMul<ClientParameterized> mul(ClientParameterized para) {
        return null;
    }

    @Override
    public IDiv<ClientParameterized> div(ClientParameterized para) {
        return null;
    }

    @Override
    public IMod<ClientParameterized> mod(ClientParameterized para) {
        return null;
    }


}

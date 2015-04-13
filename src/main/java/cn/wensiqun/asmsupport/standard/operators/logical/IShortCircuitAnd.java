package cn.wensiqun.asmsupport.standard.operators.logical;

import cn.wensiqun.asmsupport.standard.Parameterized;

public interface IShortCircuitAnd<_P extends Parameterized> extends Parameterized {

    /**
     * Execute && with other parameter.
     * 
     * @param para
     * @return
     */
    IShortCircuitAnd<_P> and(_P para);
    
}

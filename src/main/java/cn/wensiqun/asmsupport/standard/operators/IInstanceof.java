package cn.wensiqun.asmsupport.standard.operators;

import cn.wensiqun.asmsupport.standard.Parameterized;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitAnd;
import cn.wensiqun.asmsupport.standard.operators.logical.IShortCircuitOr;

public interface IInstanceof<_P extends Parameterized> extends Parameterized {

    /**
     * Execute && with other parameter.
     * 
     * @param para
     * @return
     */
    IShortCircuitAnd<_P> and(_P para);
    
    /**
     * Execute || with other parameter.
     * 
     * @param para
     * @return
     */
    IShortCircuitOr<_P> or(_P para);
    
}

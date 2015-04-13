package cn.wensiqun.asmsupport.standard.operators.logical;

import cn.wensiqun.asmsupport.standard.Parameterized;

public interface IShortCircuitOr<_P extends Parameterized> extends Parameterized {

    /**
     * Execute || with other parameter.
     * 
     * @param para
     * @return
     */
    IShortCircuitOr<_P> or(_P para);
    
}

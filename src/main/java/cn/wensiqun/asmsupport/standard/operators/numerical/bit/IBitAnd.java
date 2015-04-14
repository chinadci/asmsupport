package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * bit and '&' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IBitAnd<_P extends Parameterized> extends Parameterized {

    /**
     * Execute bit and between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IBitAnd<_P> band(_P para);
    
}

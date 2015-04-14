package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * bit shift right '&gt;&gt;' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IShiftRight<_P extends Parameterized> extends Parameterized {

    
    /**
     * Execute shift right between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IShiftRight<_P> shr(_P para);
}

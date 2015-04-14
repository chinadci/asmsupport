package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * bit unsigned shift right '&gt;&gt;&gt;' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IUnsignedShiftRight<_P extends Parameterized> extends Parameterized {
 
    /**
     * Execute unsigned shift right between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IUnsignedShiftRight<_P> ushr(_P para);
    
}

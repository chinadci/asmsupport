package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * bit shift left '&lt;&lt;' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IShiftLeft<_P extends Parameterized> extends Parameterized {

    /**
     * Execute shift left between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IShiftLeft<_P> shl(_P para);
 
    
}

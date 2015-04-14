package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;


/**
 * 
 * bit xor '^' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IBitXor<_P extends Parameterized> extends Parameterized {

    /**
     * Execute bit xor between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IBitXor<_P> bxor(_P para);
 
    
}

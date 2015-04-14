package cn.wensiqun.asmsupport.standard.operators.numerical.bit;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * bit or '|' standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IBitOr<_P extends Parameterized> extends Parameterized {
 
    /**
     * Execute bit or between the result of current operation with incoming parameter.
     * 
     * @param para
     * @return
     */
    IBitOr<_P> bor(_P para);
    
}

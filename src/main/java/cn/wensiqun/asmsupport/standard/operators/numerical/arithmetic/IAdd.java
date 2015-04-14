package cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic;

import cn.wensiqun.asmsupport.standard.Parameterized;


/**
 * The + operation standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IAdd<_P extends Parameterized> extends Parameterized {

    /**
     * Execute the result of current operator add to incoming parameters
     * 
     * @param para
     * @return
     */
    IAdd<_P> add(_P para);

    /**
     * Execute the result of current operator sub to incoming parameters
     * 
     * @param para
     * @return
     */
    ISub<_P> sub(_P para);
    
}

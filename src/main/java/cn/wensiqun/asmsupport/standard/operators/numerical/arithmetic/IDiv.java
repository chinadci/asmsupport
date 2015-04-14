package cn.wensiqun.asmsupport.standard.operators.numerical.arithmetic;

import cn.wensiqun.asmsupport.standard.Parameterized;


/**
 * The / operation standard interface
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IDiv<_P extends Parameterized> extends Parameterized {

    /**
     * Execute the result of current operator times with incoming parameters
     * 
     * @param para
     * @return
     */
    IMul<_P> mul(_P para);
    
    /**
     * Execute the result of current operator divided by incoming parameters
     * 
     * @param para
     * @return
     */
    IDiv<_P> div(_P para);
    
    /**
     * Execute the result of current operator modulo by incoming parameters
     * 
     * @param para
     * @return
     */
    IMod<_P> mod(_P para);
}

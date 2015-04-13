package cn.wensiqun.asmsupport.standard.operators;

import cn.wensiqun.asmsupport.standard.Parameterized;

public interface IStringAppender<_P extends Parameterized> extends Parameterized {
    
    /**
     * Append a parameter to current string appender.
     * 
     * @param arg
     * @return
     */
    public IStringAppender<_P> append(_P arg);
    
}

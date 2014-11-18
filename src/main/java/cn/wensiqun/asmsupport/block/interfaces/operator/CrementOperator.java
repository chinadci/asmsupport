package cn.wensiqun.asmsupport.block.interfaces.operator;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.operators.numerical.crement.PostposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PostposeIncrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PreposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.PreposeIncrment;


/**
 * 创建自增或自减操作
 * 
 * @author wensiqun(at)gmail
 *
 */
public interface CrementOperator {
    
    /**
     * 生成类似--i操作指令
     * 
     * @param crement
     * @return {@link PreposeDecrment}
     */
    public PreposeDecrment _preDec(Crementable crement);
    
    /**
     * 生成类似i--操作指令
     * 
     * @param crement
     * @return {@link PreposeIncrement}
     */
    public PostposeDecrment _postDec(Crementable crement);
    
    /**
     * 生成类似++i操作指令
     * 
     * @param crement
     * @return {@link PreposeIncrement}
     */
    public PreposeIncrment _preInc(Crementable crement);
    
    /**
     * 生成类似i++操作指令
     * 
     * @param crement
     * @return {@link PostposeIncrement}
     */
    public PostposeIncrment _postInc(Crementable crement);
    
}


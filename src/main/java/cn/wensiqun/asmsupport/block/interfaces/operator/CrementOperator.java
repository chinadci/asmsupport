package cn.wensiqun.asmsupport.block.interfaces.operator;

import cn.wensiqun.asmsupport.Crementable;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PostposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PostposeIncrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PreposeDecrment;
import cn.wensiqun.asmsupport.operators.numerical.crement.v2.PreposeIncrment;


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
    public PreposeDecrment preDec(Crementable crement);
    
    /**
     * 生成类似i--操作指令
     * 
     * @param crement
     * @return {@link PreposeIncrement}
     */
    public PostposeDecrment postDec(Crementable crement);
    
    /**
     * 生成类似++i操作指令
     * 
     * @param crement
     * @return {@link PreposeIncrement}
     */
    public PreposeIncrment preInc(Crementable crement);
    
    /**
     * 生成类似i++操作指令
     * 
     * @param crement
     * @return {@link PostposeIncrement}
     */
    public PostposeIncrment postInc(Crementable crement);
    
}


package cn.wensiqun.asmsupport.block.interfaces.operator;

import cn.wensiqun.asmsupport.block.classes.common.Synchronized;
import cn.wensiqun.asmsupport.block.classes.control.condition.IF;
import cn.wensiqun.asmsupport.block.classes.control.exception.Try;
import cn.wensiqun.asmsupport.block.classes.control.loop.While;


/**
 * 
 * 创建程序块
 * 
 * @author wensiqun(at)gmail
 *
 */
public interface CreateBlockOperator {

    /**
     * 创建if程序块.
     * <ul>
     * <li>通过{@link IF#_else(cn.wensiqun.asmsupport.block.control.Else)}或者
     * {@link cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf#_else(cn.wensiqun.asmsupport.block.control.Else)}
     * 创建else程序块
     * </li>
     * <li>
     * 通过{@link If#_elseif(cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf)}或者
     * {@link cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf#_elseif(cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf)}
     * 创建else if程序块
     *</li>
     * </ul>
     * 
     * @param ifs IF对象
     * @return {@link If}
     * @see IF#_else(cn.wensiqun.asmsupport.block.control.Else)
     * @see IF#_elseif(cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf)
     * @see cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf#_else(cn.wensiqun.asmsupport.block.control.Else)
     * @see cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf#_elseif(cn.wensiqun.asmsupport.block.classes.control.condition.ElseIf)
     */
    public IF _if(IF ifBlock);
    
    /**
     * 
     * 创建while循环程序块
     * 
     * @param whileLoop WhileLoop对象
     * @return {@link While}
     */
    public While _while(While whileLoop);
    
    /**
     * 创建do...while程序块
     * 
     * @param doWhileLoop DoWhileLoop对象
     * @return {@link DoWhileLoop}
     */
    //public WhileLoop dowhile(DoWhileLoop doWhileLoop);
    
    /**
     * 创建for each程序块
     * 
     * @param forEach ForEachLoop对象
     * @return {@link ForEachLoop}
     */
    //public ForEachLoop forEach(final ForEachLoop forEach);
    
    
    /**
     * 创建try程序块.
     * 
     * <ul>
     * <li>通过{@link Try#_catch(cn.wensiqun.asmsupport.block.classes.control.exception.Catch)}或者
     * {@link cn.wensiqun.asmsupport.block.classes.control.exception.Catch#_catch(cn.wensiqun.asmsupport.block.classes.control.exception.Catch)}创建
     * catch程序块
     * </li>
     * <li>通过{@link Try#_finally(cn.wensiqun.asmsupport.block.control.Finally)}或者
     * {@link cn.wensiqun.asmsupport.block.classes.control.exception.Catch#_finally(cn.wensiqun.asmsupport.block.control.Finally)}创建
     * finally程序块
     * </li>
     * </ul>
     * 
     * @param tryPara
     * @return
     */
    public Try _try(final Try tryPara);
    
    /**
     * 创建Synchronized同步块
     * @param sync Synchronized对象
     * @return {@link Synchronized}
     */
    public Synchronized _sync(Synchronized sync);
	
}

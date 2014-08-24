package cn.wensiqun.asmsupport.block.classes.control.loop;

import cn.wensiqun.asmsupport.Parameterized;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class DoWhileLoop extends WhileLoop  {

    public DoWhileLoop(Parameterized condition) {
        super(condition);
    }
    
    

	@Override
	public void doExecute() {
		
		insnHelper.goTo(conditionLbl);
		
		super.doExecute();
	}



	@Override
	public String toString() {
		return "Do While Block : " + super.toString();
	}
    
}

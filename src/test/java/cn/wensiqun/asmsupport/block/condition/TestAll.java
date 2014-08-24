package cn.wensiqun.asmsupport.block.condition;

import cn.wensiqun.asmsupport.utils.TesterStatics;
import junit.framework.Assert;

public class TestAll
{
    
    public static void main(String[] args) throws InterruptedException
    {
    	ConditionBlockGeneratorSample.main(args);
        ConditionBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
        
    }
}

package cn.wensiqun.asmsupport.block.loop;

import org.junit.Assert;

import cn.wensiqun.asmsupport.utils.TesterStatics;


public class Test {
	
	public static void main(String[] args) throws InterruptedException
    {
		WhileBlockGeneratorSample.main(args);
		System.out.println();
    	WhileBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
    }
	
    
    @org.junit.Test
    public void test() throws InterruptedException
    {
        main(null);
    }

}

package cn.wensiqun.asmsupport.block.loop;

import cn.wensiqun.asmsupport.utils.TesterStatics;


public class WhileBlockGeneratorSample {

	private static void test()
	{
		int i = 10;
        while(i-- > 0)
        {
			TesterStatics.expectedPrintln(String.valueOf(i));
        }
	}
	
	public static void main(String[] args)
    {
        test();
    }
	
}

package cn.wensiqun.asmsupport.block.loop;

import cn.wensiqun.asmsupport.utils.TesterStatics;


public class WhileBlockGeneratorSample {

	private static void testChar()
	{
		char i = 10;
        while(i-- > 0)
        {
			TesterStatics.expectedPrintln(String.valueOf(i));
        }
	}
	
	private static void testByte()
    {
        byte i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testShort()
    {
        short i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testInt()
    {
        int i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testFloat()
    {
        float i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testLong()
    {
        long i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testDouble()
    {
        double i = 10;
        while(i-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testBeforeByte()
    {
        byte i = 10;
        while(--i > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
	
	/*public static void main(String[] args)
    {
        test();
    }*/
	
}

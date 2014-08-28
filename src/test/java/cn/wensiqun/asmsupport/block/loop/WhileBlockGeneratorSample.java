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
    
    private static void testBeforeByte()
    {
        byte i = 10;
        while(--i > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
	
	private static void testAfterByte()
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
    
    private static void testBeforeInt()
    {
        int i = 10;
        while(--i > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testAfterInt()
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
    
    private static void testBeforeLong()
    {
        long i = 10;
        while(--i > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(i));
        }
    }
    
    private static void testAfterLong()
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
    
    private static void testDoubleObjectBefore()
    {
        Double d = 10D;
        while(--d > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(d));
        }
    }
    
    private static void testDoubleObjectAfter()
    {
        Double d = 10D;
        while(d-- > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(d));
        }
    }
    
    private static void test()
    {
        Long longObj = 10L;
        while(--longObj > 0)
        {
            TesterStatics.expectedPrintln(String.valueOf(longObj));
        }
    }
    
    private static byte sb;
    
    private byte nsb;
    
    private static int si;
    
    private int nsi;
    
    private static long sl;
    
    private long nsl;
    
    private static Byte sob;
    
    private Byte nsob;
    
    private static Integer soi;
    
    private Integer nsoi;
    
    private static Long sol;
    
    private Long nsol;
    
    public static void testDirectBefore(byte lb, int li, long ll, Byte lob, Integer loi, Long lol)
    {
        WhileBlockGeneratorSample s = new WhileBlockGeneratorSample();
        ++s.sb;
        ++s.nsb;
        
        ++s.si;
        ++s.nsi;
        
        ++s.sl;
        ++s.nsl;
        
        
        ++lb;
        
        ++li;
        
        ++ll;
        
        //==========================

        ++s.sob;
        ++s.nsob;
        
        ++s.soi;
        ++s.nsoi;
        
        ++s.sol;
        ++s.nsol;
        
        
        ++lob;
        
        ++loi;
        
        ++lol;
    }
    
	public static void testDirectAfter(byte lb, int li, long ll, Byte lob, Integer loi, Long lol)
	{
	    WhileBlockGeneratorSample s = new WhileBlockGeneratorSample();
        s.sb++;
        s.nsb++;
        
	    s.si++;
	    s.nsi++;
	    
	    s.sl++;
        s.nsl++;
        
        
        lb++;
        
        li++;
        
        ll++;
        
        //==========================

        s.sob++;
        s.nsob++;
        
        s.soi++;
        s.nsoi++;
        
        s.sol++;
        s.nsol++;
        
        
        lob++;
        
        loi++;
        
        lol++;
	}
	
	
}

package cn.wensiqun.asmsupport.utils;

import cn.wensiqun.asmsupport.clazz.AClass;
import cn.wensiqun.asmsupport.clazz.AClassFactory;
import cn.wensiqun.asmsupport.definition.variable.GlobalVariable;

public class TesterStatics
{
    public static final boolean printable = true;
    
    public static final StringBuilder EXPECTED = new StringBuilder();
    
    public static final StringBuilder ACTUALLY = new StringBuilder();
    
    public static final AClass ATesterStatics = AClassFactory.getProductClass(TesterStatics.class);
    
    public static final GlobalVariable GV_EXPECTED = ATesterStatics.getGlobalVariable("EXPECTED");
    
    public static final GlobalVariable GV_ACTUALLY = ATesterStatics.getGlobalVariable("ACTUALLY");
    
    
    public static void expectedPrintln(String str)
    {
        EXPECTED.append(str).append("|");
        if(printable)
        	System.out.print(str + "|");
    }

    public static void actuallyPrintln(String str)
    {
        ACTUALLY.append(str).append("|");
        if(printable)
        	System.out.print(str + "|");
    }
    
    public static void clear()
    {
        EXPECTED.delete(0, EXPECTED.length());
        ACTUALLY.delete(0, ACTUALLY.length());
    }
}

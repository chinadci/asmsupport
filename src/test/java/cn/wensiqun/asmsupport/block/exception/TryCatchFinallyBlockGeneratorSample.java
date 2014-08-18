package cn.wensiqun.asmsupport.block.exception;

import example.AbstractExample;

public class TryCatchFinallyBlockGeneratorSample extends AbstractExample
{
    private static void runtimeException(){
        throw new RuntimeException();
    }
    private static void exception() throws Exception{
        throw new Exception();
    }
    
    private static void tryCatchFinally_tryDirectException(){
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
        	throw new Exception();
    	}
    	catch(Exception e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
    	}
    }
    
    private static void tryCatchFinally_runtimeInTryNoSuitableCatch(){
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
        	throw new RuntimeException();
    	}
    	catch(NullPointerException e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
    	}
    }
    
    private static void tryCatchFinally_nestedTryCatchInFinally()
    {
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
        	throw new RuntimeException();
    	}
    	catch(NullPointerException e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
        	try
        	{
            	TesterStatics.expectedPrintln("        |-Try");
            	exception();
        	}
        	catch(Exception e)
        	{
            	TesterStatics.expectedPrintln("        |-Catch");
        	}
    	}
    }
    
    private static void tryCatchFinally_tryMethodException(){
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
        	runtimeException();
    	}
    	catch(Exception e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
    	}
    }
    
    private static void tryCatchFinally_catchDirectException(){
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
        	throw new Exception();
    	}
    	catch(Exception e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
        	try {
            	TesterStatics.expectedPrintln("        |-Try");
				throw new Exception();
			} catch (Exception e1) {
            	TesterStatics.expectedPrintln("        |-Catch");
            	try
            	{
                	TesterStatics.expectedPrintln("            |-Try");
                	exception();
            	}
            	catch(RuntimeException e2)
        		{
                	TesterStatics.expectedPrintln("            |-Catch(RuntimeException)");
        		}
        		catch(Exception e2)
        		{
                	TesterStatics.expectedPrintln("            |-Catch(Exception)");
                	throw new RuntimeException();
        		}
			}
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
    		try
    		{
            	TesterStatics.expectedPrintln("        |-Try");
            	throw new RuntimeException();
    		}
    		catch(RuntimeException e)
    		{
            	TesterStatics.expectedPrintln("        |-Catch(RuntimeException)");
    		}
    		catch(Exception e)
    		{
            	TesterStatics.expectedPrintln("        |-Catch(Exception)");
    		}
    	}
    }
    
    /*private static void tryCatchFinally_catchMethodException(){
    	TesterStatics.expectedPrintln("Root");
    	try
    	{
        	TesterStatics.expectedPrintln("    |-Try");
    	}
    	catch(Exception e)
    	{
        	TesterStatics.expectedPrintln("    |-Catch");
        	runtimeException();
    	}
    	finally
    	{
        	TesterStatics.expectedPrintln("    |-Finally");
    	}
    }
    
    private static void tryTwoCatchFinally()
    {
    	try
    	{
    		
    	}
    	catch(RuntimeException e)
    	{
    		
    	}
    	catch(Exception e)
    	{
    		
    	}
    	finally
    	{
    		
    	}
    }
    
    private static void nestedTryCatchFinally()
    {
    	try
    	{
    		try
        	{
        		
        	}
        	catch(RuntimeException e)
        	{
        		
        	}
        	catch(Exception e)
        	{
        		
        	}
        	finally
        	{
        		
        	}
    	}
    	catch(RuntimeException e)
    	{
    		try
        	{
        		
        	}
        	catch(RuntimeException e1)
        	{
        		
        	}
        	catch(Exception e1)
        	{
        		
        	}
        	finally
        	{
        		
        	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	finally
    	{
    		try
    		{
    			
    		}
    		catch(Exception e)
    		{
    			
    		}
    	}
    }*/
    
    
    public static void main(String[] args) throws InterruptedException
    {
        TesterStatics.expectedPrintln("=======tryCatchFinally_tryDirectException");
        try{tryCatchFinally_tryDirectException();}catch(Exception e){}

        TesterStatics.expectedPrintln("=======tryCatchFinally_runtimeInTryNoSuitableCatch");
        try{tryCatchFinally_runtimeInTryNoSuitableCatch();}catch(Exception e){}

        TesterStatics.expectedPrintln("=======tryCatchFinally_nestedTryCatchInFinally");
        try{tryCatchFinally_nestedTryCatchInFinally();}catch(Exception e){}
        
        TesterStatics.expectedPrintln("=======tryCatchFinally_tryMethodException");
        try{tryCatchFinally_tryMethodException();}catch(Exception e){}

        TesterStatics.expectedPrintln("=======tryCatchFinally_catchDirectException");
        try{tryCatchFinally_catchDirectException();}catch(Exception e){}

        /*TesterStatics.expectedPrintln("=======tryCatch_runtimeExceptionAfterPrintInCatch");
        try{tryCatchFinally_catchMethodException();}catch(Exception e){}*/
    }
    
}

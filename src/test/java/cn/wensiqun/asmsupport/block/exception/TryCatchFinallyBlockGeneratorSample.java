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
    
    /*private static void tryCatchFinally_tryDirectException(){
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
        	throw new Exception();
    	}
    	catch(Exception e)
    	{
        	System.out.println("    |-Catch");
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
    	}
    }
    
    private static void tryCatchFinally_runtimeInTryNoSuitableCatch(){
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
        	throw new RuntimeException();
    	}
    	catch(NullPointerException e)
    	{
        	System.out.println("    |-Catch");
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
    	}
    }
    
    private static void tryCatchFinally_nestedTryCatchInFinally()
    {
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
        	throw new RuntimeException();
    	}
    	catch(NullPointerException e)
    	{
        	System.out.println("    |-Catch");
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
        	try
        	{
            	System.out.println("        |-Try");
            	exception();
        	}
        	catch(Exception e)
        	{
            	System.out.println("        |-Catch");
        	}
    	}
    }
    
    private static void tryCatchFinally_tryMethodException(){
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
        	runtimeException();
    	}
    	catch(Exception e)
    	{
        	System.out.println("    |-Catch");
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
    	}
    }*/
    
    private static void tryCatchFinally_catchDirectException(){
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
        	throw new Exception();
    	}
    	catch(Exception e)
    	{
        	System.out.println("    |-Catch");
        	try {
            	System.out.println("        |-Try");
				throw new Exception();
			} catch (Exception e1) {
            	System.out.println("        |-Catch");
            	try
            	{
                	System.out.println("            |-Try");
                	exception();
            	}
            	catch(RuntimeException e2)
        		{
                	System.out.println("            |-Catch(RuntimeException)");
        		}
        		catch(Exception e2)
        		{
                	System.out.println("            |-Catch(Exception)");
                	throw new RuntimeException();
        		}
			}
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
    		try
    		{
            	System.out.println("        |-Try");
            	throw new RuntimeException();
    		}
    		catch(RuntimeException e)
    		{
            	System.out.println("        |-Catch(RuntimeException)");
    		}
    		catch(Exception e)
    		{
            	System.out.println("        |-Catch(Exception)");
    		}
    	}
    }
    
    /*private static void tryCatchFinally_catchMethodException(){
    	System.out.println("Root");
    	try
    	{
        	System.out.println("    |-Try");
    	}
    	catch(Exception e)
    	{
        	System.out.println("    |-Catch");
        	runtimeException();
    	}
    	finally
    	{
        	System.out.println("    |-Finally");
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
        Thread.sleep(1000);
        /*System.out.println("=======tryCatch_errorBeforePrintInTry");
        try{tryCatchFinally_tryDirectException();}catch(Exception e){}

        System.out.println("=======tryCatchFinally_runtimeInTryNoSuitableCatch");
        try{tryCatchFinally_runtimeInTryNoSuitableCatch();}catch(Exception e){}

        System.out.println("=======tryCatchFinally_nestedTryCatchInFinally");
        try{tryCatchFinally_nestedTryCatchInFinally();}catch(Exception e){}*/
        
        /*System.out.println("=======tryCatch_errorAfterPrintInTry");
        try{tryCatchFinally_tryMethodException();}catch(Exception e){}*/

        System.out.println("=======tryCatchFinally_catchDirectException");
        try{tryCatchFinally_catchDirectException();}catch(Exception e){}

        /*System.out.println("=======tryCatch_runtimeExceptionAfterPrintInCatch");
        try{tryCatchFinally_catchMethodException();}catch(Exception e){}*/
    }
    
}

package cn.wensiqun.asmsupport.block.exception;

import example.AbstractExample;

public class TryCatchBlockGeneratorSample extends AbstractExample
{
    private static void runtimeException(){
        throw new RuntimeException();
    }
    private static void exception() throws Exception{
        throw new Exception();
    }
    
    private static void tryCatch_errorBeforePrintInTry()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatch_errorAfterPrintInTry()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatch_runtimeExceptionBeforePrintInCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatch_runtimeExceptionAfterPrintInCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatch_exceptionBeforePrintInTry_runtimeExceptionBeforePrintInCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatch_exceptionBeforePrintInTry_runtimeExceptionAfterPrintInCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatch_exceptionAfterPrintInTry_runtimeExceptionBeforePrintInCatch()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatch_exceptionAfterPrintInTry_runtimeExceptionAfterPrintInCatch()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    
    //============================================================
    

    
    private static void tryCatchTwo_runtimeBeforePrintInTry()
    {
        try
        {
            runtimeException();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_runtimeAfterPrintInTry()
    {
        try
        {
            System.out.println("    try");
            runtimeException();
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_runtimeBeforePrintInExceptionCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_runtimeAfterPrintInExceptionCatch()
    {
        try
        {
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    //{
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
        }
    }
    
    //}
    
    //{
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {
            System.out.println("    try");
            exception();
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {
            System.out.println("    try");            exception();
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    //}
    
    //{
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {
            exception();
            System.out.println("    try");
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {   
            exception();
            System.out.println("    try");            
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {   
            System.out.println("    try");   
            exception();         
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {   
            System.out.println("    try");   
            exception();         
        }
        catch(RuntimeException e)
        {
            runtimeException();
            System.out.println("    runtime exception");
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch()
    {
        try
        {   
            System.out.println("    try");   
            exception();         
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            runtimeException();
            System.out.println("    exception");
        }
    }
    
    private static void tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch()
    {
        try
        {   
            System.out.println("    try");   
            exception();         
        }
        catch(RuntimeException e)
        {
            System.out.println("    runtime exception");
            runtimeException();
        }
        catch(Exception e)
        {
            System.out.println("    exception");
            runtimeException();
        }
    }
    
    //}
    
    
    public static void main(String[] args) throws InterruptedException
    {
        Thread.sleep(1000);
        System.out.println("=======tryCatch_errorBeforePrintInTry");
        try{tryCatch_errorBeforePrintInTry();}catch(Exception e){}

        System.out.println("=======tryCatch_errorAfterPrintInTry");
        try{tryCatch_errorAfterPrintInTry();}catch(Exception e){}

        System.out.println("=======tryCatch_runtimeExceptionBeforePrintInCatch");
        try{tryCatch_runtimeExceptionBeforePrintInCatch();}catch(Exception e){}

        System.out.println("=======tryCatch_runtimeExceptionAfterPrintInCatch");
        try{tryCatch_runtimeExceptionAfterPrintInCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatch_exceptionBeforePrintInTry_runtimeExceptionBeforePrintInCatch");
        try{tryCatch_exceptionBeforePrintInTry_runtimeExceptionBeforePrintInCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatch_exceptionBeforePrintInTry_runtimeExceptionAfterPrintInCatch");
        try{tryCatch_exceptionBeforePrintInTry_runtimeExceptionAfterPrintInCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatch_exceptionAfterPrintInTry_runtimeExceptionBeforePrintInCatch");
        try{tryCatch_exceptionAfterPrintInTry_runtimeExceptionBeforePrintInCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatch_exceptionAfterPrintInTry_runtimeExceptionAfterPrintInCatch");
        try{tryCatch_exceptionAfterPrintInTry_runtimeExceptionAfterPrintInCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeBeforePrintInTry");
        try{tryCatchTwo_runtimeBeforePrintInTry();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeAfterPrintInTry");
        try{tryCatchTwo_runtimeAfterPrintInTry();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry");
        try{tryCatchTwo_exceptionBeforePrintInTry();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry");
        try{tryCatchTwo_exceptionAfterPrintInTry();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeBeforePrintInExceptionCatch");
        try{tryCatchTwo_runtimeBeforePrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_runtimeAfterPrintInExceptionCatch");
        try{tryCatchTwo_runtimeAfterPrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionBeforePrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeBeforePrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeBeforePrintInRuntimeCatch();}catch(Exception e){}
        
        System.out.println("=======tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch");
        try{tryCatchTwo_exceptionAfterPrintInTry_runtimeAfterPrintInExceptionCatch_runtimeAfterPrintInRuntimeCatch();}catch(Exception e){}
    }
    
}

package cn.wensiqun.asmsupport.utils.block.exception;

import example.AbstractExample;

public class TryCathcBlockGeneratorSample extends AbstractExample
{
    private static void runtimeException(){
        throw new RuntimeException();
    }
    private static void exception() throws Exception{
        throw new Exception();
    }
    private static void commonThrowable() throws Throwable{
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
    
    public static void main(String[] args)
    {
        System.out.println("=======tryCatch_errorBeforePrintInTry");
        try{tryCatch_errorBeforePrintInTry();}catch(Exception e){}

        System.out.println("=======tryCatch_errorAfterPrintInTry");
        try{tryCatch_errorAfterPrintInTry();}catch(Exception e){}

        System.out.println("=======tryCatch_runtimeExceptionBeforePrintInCatch");
        try{tryCatch_runtimeExceptionBeforePrintInCatch();}catch(Exception e){}

        System.out.println("=======tryCatch_runtimeExceptionAfterPrintInCatch");
        try{tryCatch_runtimeExceptionAfterPrintInCatch();}catch(Exception e){}
        
        
    }
    
}

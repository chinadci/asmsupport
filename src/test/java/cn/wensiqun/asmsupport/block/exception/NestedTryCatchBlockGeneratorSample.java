package cn.wensiqun.asmsupport.block.exception;

import example.AbstractExample;

public class NestedTryCatchBlockGeneratorSample extends AbstractExample
{
    private static void runtimeException(){
        throw new RuntimeException();
    }
    private static void exception() throws Exception{
        throw new Exception();
    }
    
    private static void fullTryCatch1()
    {
        System.out.println("Root");
        try
        {
            System.out.println("    |-Try");
            try
            {
                System.out.println("        |-Try");
                throw new Exception();
            }
            catch(Exception e)
            {
                System.out.println("        |-Catch");
                throw e;
            }
        }
        catch(Exception e)
        {
            System.out.println("    |-Catch");
        }
        System.out.println("End");
    }
    
    /*private static void fullTryCatch2()
    {

        
        try
        {
            try
            {
                
            }
            catch()
            {
                
            }
            catch()
            {
                
            }
        }
        catch()
        {
            try
            {
                
            }
            catch()
            {
                
            }
            catch()
            {
                
            }
        }
        catch()
        {
            
        }
        
    }*/
    
    
    public static void main(String[] args) throws InterruptedException
    {
        Thread.sleep(1000);
        
        System.out.println("=======fullTryCatch1");
        try{fullTryCatch1();}catch(Exception e){}
    }
    
}

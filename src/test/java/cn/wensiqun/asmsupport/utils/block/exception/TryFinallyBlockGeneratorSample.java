package cn.wensiqun.asmsupport.utils.block.exception;

import example.AbstractExample;

public class TryFinallyBlockGeneratorSample extends AbstractExample
{
    private static void tryFinally()
    {
        try
        {
            System.out.println("    try");
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally()
    {
        System.out.println("start");
        try
        {
            System.out.println("    try{");
            try
            {
                System.out.println("        try_inner");
            }
            finally
            {
                System.out.println("        finally_inner");
            }
            System.out.println("    }");
        }
        finally
        {
            System.out.println("    finally");
        }
        System.out.println("end");
    }
    
    private static void tryFinally_TryError()
    {
        try
        {
            System.out.println("    try");
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_InnerTryError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                throw new RuntimeException();
            }
            finally
            {
                System.out.println("        finally_inner");
            }
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_InnerFinallyError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
            }
            finally
            {
                System.out.println("        finally_inner");
                throw new RuntimeException();
            }
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterTryError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
            }
            finally
            {
                System.out.println("        finally_inner");
            }
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterFinallyError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
            }
            finally
            {
                System.out.println("        finally_inner");
            }
        }
        finally
        {
            System.out.println("    finally");
            throw new RuntimeException();
        }
    }
    
    private static void nestedTryFinally_InnerBothError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                throw new RuntimeException();
            }
            finally
            {
                System.out.println("        finally_inner");
                throw new RuntimeException();
            }
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterBothError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
            }
            finally
            {
                System.out.println("        finally_inner");
            }
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("    finally");
            throw new RuntimeException();
        }
    }
    
    private static void nestedTryFinally_InnerBothOuterFinallyError()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                throw new RuntimeException();
            }
            finally
            {
                System.out.println("        finally_inner");
                throw new RuntimeException();
            }
        }
        finally
        {
            System.out.println("    finally");
            throw new RuntimeException();
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("=======tryFinally");
        try{tryFinally();}catch(Exception e){}
        System.out.println("=======nestedTryFinally");
        try{nestedTryFinally();}catch(Exception e){}
        System.out.println("=======tryFinally_TryError");
        try{tryFinally_TryError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerTryError");
        try{nestedTryFinally_InnerTryError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerFinallyError");
        try{nestedTryFinally_InnerFinallyError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterTryError");
        try{nestedTryFinally_OutterTryError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterFinallyError");
        try{nestedTryFinally_OutterFinallyError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerBothError");
        try{nestedTryFinally_InnerBothError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterBothError");
        try{nestedTryFinally_OutterBothError();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerBothOuterFinallyError");
        try{nestedTryFinally_InnerBothOuterFinallyError();}catch(Exception e){}
    }
    
}

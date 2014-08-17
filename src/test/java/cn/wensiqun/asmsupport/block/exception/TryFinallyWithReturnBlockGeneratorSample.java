package cn.wensiqun.asmsupport.block.exception;

import example.AbstractExample;

public class TryFinallyWithReturnBlockGeneratorSample extends AbstractExample
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
    
    private static void tryFinally_TryReturn()
    {
        try
        {
            System.out.println("    try");
            return;
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_InnerTryReturn()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                return;
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
    
    private static void nestedTryFinally_InnerFinallyReturn()
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
                return;
            }
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterTryReturn()
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
            return;
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterFinallyReturn()
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
            return;
        }
    }
    
    private static void nestedTryFinally_InnerBothReturn()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                return;
            }
            finally
            {
                System.out.println("        finally_inner");
                return;
            }
        }
        finally
        {
            System.out.println("    finally");
        }
    }
    
    private static void nestedTryFinally_OutterBothReturn()
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
            return;
        }
        finally
        {
            System.out.println("    finally");
            return;
        }
    }
    
    private static void nestedTryFinally_InnerBothOuterFinallyReturn()
    {
        try
        {
            
            try
            {
                System.out.println("        try_inner");
                return;
            }
            finally
            {
                System.out.println("        finally_inner");
                return;
            }
        }
        finally
        {
            System.out.println("    finally");
            return;
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("=======tryFinally");
        try{tryFinally();}catch(Exception e){}
        System.out.println("=======nestedTryFinally");
        try{nestedTryFinally();}catch(Exception e){}
        System.out.println("=======tryFinally_TryReturn");
        try{tryFinally_TryReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerTryReturn");
        try{nestedTryFinally_InnerTryReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerFinallyReturn");
        try{nestedTryFinally_InnerFinallyReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterTryReturn");
        try{nestedTryFinally_OutterTryReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterFinallyReturn");
        try{nestedTryFinally_OutterFinallyReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerBothReturn");
        try{nestedTryFinally_InnerBothReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_OutterBothReturn");
        try{nestedTryFinally_OutterBothReturn();}catch(Exception e){}
        System.out.println("=======nestedTryFinally_InnerBothOuterFinallyReturn");
        try{nestedTryFinally_InnerBothOuterFinallyReturn();}catch(Exception e){}
    }
    
}

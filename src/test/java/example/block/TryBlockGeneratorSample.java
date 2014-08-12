package example.block;

import example.AbstractExample;

public class TryBlockGeneratorSample extends AbstractExample
{
    
    private void testTryFinallyWithError()
    {
        try
        {
            System.out.println("testTryFinallyWithError:try");
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("testTryFinallyWithError:finally");
        }
    }
    
    private void testNestedTryFinallyWithError()
    {
        try
        {
            
            try
            {
                System.out.println("testTryFinallyWithError:try_");
                throw new RuntimeException();
            }
            finally
            {
                System.out.println("testTryFinallyWithError:finally_");
            }
        }
        finally
        {
            System.out.println("testTryFinallyWithError:finally");
        }
    }
    
    public static void main(String[] args)
    {
        TryBlockGeneratorSample s = new TryBlockGeneratorSample();
        s.testNestedTryFinallyWithError();
    }
    
}

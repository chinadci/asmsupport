package example;


public class Main1
{
    public static void main1()
    {
        try
        {
            System.out.println("100");
        }
        catch (Throwable e)
        {
            System.out.println("200");
        }
    }
    
    public static void main1_1()
    {
        try
        {
            System.out.println("100");
        }
        catch (Exception e)
        {
            System.out.println("200");
        }
        catch (Throwable e)
        {
            System.out.println("300");
        }
    }

    public static void main2()
    {
        try
        {
            System.out.println("100");
            return;
        }
        finally
        {
            System.out.println("300");
        }
    }

    public static void main2_1()
    {
        try
        {
            System.out.println("100");
        }
        finally
        {
            System.out.println("300");
        }
    }

    public static void main2_2(boolean b)
    {
        try
        {
            if(b)
            {
                System.out.println("100");
                return;
            }
            System.out.println("200");
        }
        finally
        {
            System.out.println("300");
        }
    }

    public static void main3(String[] args)
    {
        try
        {
            System.out.println("100");
        }
        catch (Exception e)
        {
            System.out.println("200");
        }
        finally
        {
            System.out.println("300");
        }
    }

    public static void main3_1(String[] args)
    {
        try
        {
            System.out.println("100");
        }
        catch (Throwable e)
        {
            System.out.println("200");
        }
        finally
        {
            System.out.println("300");
        }
    }

    public static void main3_2(String[] args)
    {
        try
        {
            System.out.println("100");
        }
        catch (RuntimeException e)
        {
            System.out.println("200");
        }
        catch (Exception e)
        {
            System.out.println("300");
        }
        finally
        {
            System.out.println("400");
        }
    }

    public static void main3_3(String[] args)
    {
        try
        {
            System.out.println("100");
        }
        catch (RuntimeException e)
        {
            System.out.println("200");
        }
        catch (Throwable e)
        {
            System.out.println("300");
        }
        finally
        {
            System.out.println("400");
        }
    }

    public static void main4(String[] args)
    {
        try
        {
            System.out.println("100");
            return;
        }
        catch (Throwable e)
        {
            System.out.println("200");
        }
        finally
        {
            System.out.println("300");
        }
        System.out.println("400");
    }

    public static void main5(String[] args)
    {
        try
        {
            try
            {
                System.out.println("10");

                System.out.println("30");
                return;
            }
            catch (Exception e10)
            {
                System.out.println("20");
            }
            finally
            {
                System.out.println("30");
            }

            try
            {
                System.out.println("10_1");
            }
            finally
            {
                System.out.println("20_1");
            }

            System.out.println("100");
            return;
        }
        catch (Throwable e)
        {
            System.out.println("200");
        }
        finally
        {
            System.out.println("300");
        }
        System.out.println("400");
    }
    
    
    public static void main6()
    {
        try
        {
            
            try
            {
                System.out.println("b_try_println");
                

                try
                {
                    System.out.println("c_try_println");
                }
                catch(Exception e)
                {
                    System.out.println("c_Exception e");
                    return;
                }
                finally
                {
                    System.out.println("c_Finally");
                }
                
                return;
            }
            catch (Exception e10)
            {
                System.out.println("b_Exception e10");
            }
            finally
            {
                System.out.println("b_finally");
            }

            System.out.println("a_try_println");
            return;
        }
        catch (Throwable e)
        {
            System.out.println("a_Throwable e");
        }
        finally
        {
            System.out.println("a_Finally");
        }
        System.out.println("400");
    }
}
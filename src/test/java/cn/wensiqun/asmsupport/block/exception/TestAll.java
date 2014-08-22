package cn.wensiqun.asmsupport.block.exception;

import junit.framework.Assert;

public class TestAll
{
    
    public static void main(String[] args) throws InterruptedException
    {
        TryFinallyBlockGeneratorSample.main(args);
        TryFinallyBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();

        
        TryFinallyWithReturnBlockGeneratorSample.main(args);
        TryFinallyWithReturnBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
        

        TryCatchBlockGeneratorSample.main(args);
        TryCatchBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
        

        NestedTryCatchBlockGeneratorSample.main(args);
        NestedTryCatchBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
        

        TryCatchFinallyBlockGeneratorSample.main(args);
        TryCatchFinallyBlockGenerator.main(args);
        Assert.assertEquals(TesterStatics.EXPECTED.toString(), TesterStatics.ACTUALLY.toString());
        TesterStatics.clear();
        
        
    }
}

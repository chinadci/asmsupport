/**
 * 
 */
package cn.wensiqun.asmsupport;



/**
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class SuperByteCodeExecutable implements ByteCodeExecutable{

    private SuperByteCodeExecutable next;
    
    private SuperByteCodeExecutable previous;
    
    protected SuperByteCodeExecutable() {}

    @Override
    public boolean hasNext()
    {
        return next != null;
    }

    @Override
    public ByteCodeExecutable next()
    {
        return next;
    }

    
    @Override
    public ByteCodeExecutable previous()
    {
        return previous;
    }

    @Override
    public void remove()
    {
        if(previous != null)
        {
            previous.next = next;
        }
        
        if(next != null)
        {
            next.previous = previous;
        }
        
        next = previous = null;
    }

    @Override
    public void setNext(ByteCodeExecutable sub)
    {
        SuperByteCodeExecutable subHead = (SuperByteCodeExecutable)sub;
        if(next == null)
        {
            this.next = subHead;
            subHead.previous = this;
            return;
        }
        
        SuperByteCodeExecutable subLast = (SuperByteCodeExecutable)sub;
        while(subLast.hasNext())
        {
            subLast = (SuperByteCodeExecutable)subLast.next();
        }

        subLast.next = next;
        next.previous = subLast;
        next = subHead;
    }
    
    
}

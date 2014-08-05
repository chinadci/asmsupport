/**
 * 
 */
package cn.wensiqun.asmsupport;

import java.util.Iterator;


/**
 * @author 温斯群(Joe Wen)
 * 
 */
public abstract class SuperByteCodeExecutable implements ByteCodeExecutable{

    private SuperByteCodeExecutable next;
    
    private SuperByteCodeExecutable previous;
    
    protected SuperByteCodeExecutable() {}

    @Override
    public Iterator<ByteCodeExecutable> iterator()
    {
        return this;
    }

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
    public void setNext(ByteCodeExecutable next)
    {
        SuperByteCodeExecutable newNext = (SuperByteCodeExecutable) next;
        SuperByteCodeExecutable oldNext = this.next;
        
        if(oldNext != null)
        {
            oldNext.previous = newNext;
            newNext.next = oldNext;
        }
        
        newNext.previous = this;
        this.next = newNext;
    }
    
}

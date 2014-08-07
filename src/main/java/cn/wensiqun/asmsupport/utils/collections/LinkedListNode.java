package cn.wensiqun.asmsupport.utils.collections;

import java.util.Iterator;

public abstract class LinkedListNode implements Iterator<LinkedListNode>
{

    private LinkedListNode next;
    
    private LinkedListNode previous;
    
    //Unsupported index operation now!
    //private int index;
    
    @Override
    public boolean hasNext()
    {
        return next != null;
    }

    @Override
    public LinkedListNode next()
    {
        return next;
    }

    public LinkedListNode previous()
    {
        return previous;
    }
    
    @Override
    public void remove()
    {
        /*
        Unsupported index operation now!
        LinkedListNode cursor = this;
        while(cursor.hasNext())
        {
            cursor = cursor.next;
            cursor.setIndex(cursor.getIndex() - 1);
        }*/
        
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
    
    void setNext(LinkedListNode subHead){
        
        if(next == null)
        {
            this.next = subHead;
            subHead.previous = this;
        }
        else
        {
            LinkedListNode subLast = subHead;
            while(subLast.hasNext())
            {
                subLast = subLast.next();
            }

            subLast.next = next;
            next.previous = subLast;
            
            next = subHead;
            subHead.previous = this;
        }
        
        /**
         * For index operator. Unsupported index operation now!
        LinkedListNode oldNext = next;
        
        next = subHead;
        subHead.previous = this;
        
        LinkedListNode cursor = next;
        while(true)
        {
            cursor.index = cursor.previous.index + 1;
            if(cursor.hasNext())
            {
                cursor = cursor.next;
            }
            else
            {
                break;
            }
        }
        
        if(oldNext != null)
        {
            cursor.next = oldNext;
            oldNext.previous = cursor;
            
            while(cursor.hasNext())
            {
                cursor = cursor.next;
                cursor.index = cursor.previous.index + 1;
            }
        }*/
        
    }
    
    void setPrevious(LinkedListNode pre)
    {
        previous = pre;
    }
    
    void replace(LinkedListNode newly)
    {
        previous.next = newly;
        newly.previous = previous;
        
        next.previous = newly;
        newly.next = next;
        
        previous = next = null;
    }

    /*
    Unsupported index operation now!
    int getIndex()
    {
        return index;
    }
    
    void setIndex(int idx){
        index = idx;
    }*/
}

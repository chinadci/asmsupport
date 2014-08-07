package cn.wensiqun.asmsupport.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.ListUtils;

public class CommonLinkedList<E extends LinkedListNode> implements LinkedList<E>
{
    
    private E head;
    
    private E last;
    
    private int size = 0;
    
/*    
 * Unsupported index operation now!
 * private LinkedListNode[][] elementData;
    
    private int[]              elementDataColumnStatus;
    
    private int gap;
    
    private boolean useIndex;
    
    public CommonLinkedList(){
        this(10, 2, true);
    }
    
    public CommonLinkedList(int initElementDataSize, int gap, boolean useIndex)
    {
        
    }
    
    private void ensureCapacity()
    {
        int ensureFirstDimSize = elementData.length;
        for(Integer status : elementDataColumnStatus)
        {
            if(status == gap + 1)
            {
                ensureFirstDimSize += gap;
            }
        }
        
        boolean b = true;
        if(ensureFirstDimSize == elementData.length)
        {
            return;
        }
        
        LinkedListNode[][] temp = new LinkedListNode[ensureFirstDimSize][];
        for(int i=0, j=0; i<elementData.length; i++)
        {
            LinkedListNode[] array = elementData[i];
            if(elementDataColumnStatus[i] == gap + 1)
            {
                for(LinkedListNode node : array)
                {
                    temp[j] = new LinkedListNode[gap + 1];
                    temp[j][0] = node;
                    j++;
                }
            }
            else
            {
                temp[j] = array;
                j++;
            }
        }
        elementData = temp;
    }*/
    
    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    @Override
    public boolean contains(Object o)
    {
        if(o instanceof LinkedListNode)
        {
            LinkedListNode node = (LinkedListNode)o;
            if(node.next() != null || node.previous() != null)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator()
    {
        return (Iterator<E>) (head == null ? ListUtils.EMPTY_LIST : head);
    }

    /*@Override
    public Object[] toArray()
    {
        Object[] array = new Object[size];
        LinkedListNode curr = head;
        for(int i=0; i<size; i++, curr = head.next())
        {
            array[i] = curr;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        LinkedListNode curr = head;
        for(int i=0; i<size; i++, curr = head.next())
        {
            a[i] = (T) curr;
        }
        return a;
    }*/

    @Override
    public boolean add(E e)
    {
        /*LinkedListNode newLast = e;
        size++;
        while(newLast.hasNext()){
            newLast = e.next();
            size++;
        }
        if(head == null)
        {
            head = e;
            last = newLast;
        }
        else
        {
            last.setNext(e);
            last = e;
        }
        return true;*/
        return commonAdd(last, e);
    }

    @Override
    public boolean remove(Object o)
    {
        if(o instanceof LinkedListNode)
        {
            LinkedListNode node = ((LinkedListNode)o);
            if(node == head)
            {
                head = (E) node.next();
            }
            
            if(node == last)
            {
                last = (E) node.previous();
            }
            
            node.remove();
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFrom(E start)
    {
        if(head == start)
        {
            clear();
        }
        else
        {
            last = (E) start.previous();
            while(start != null)
            {
                size--;
                start = (E) start.next();
            }
        }
        return true;
    }

    @Override
    public void clear()
    {
        head = last = null;
        size = 0;
    }

    @Override
    public boolean addAfter(E original, E after)
    {
        return commonAdd(original, after);
    }

    @Override
    public boolean addBefore(E original, E before)
    {
        if(original == null)
        {
            return commonAdd(original, before);
        }
        else
        {
            return commonAdd(original.previous(), before);
        }
    }
    
    /**
     * 
     * 
     * @param add  null : insert to head
     * @param after
     * @return
     */
    private boolean commonAdd(LinkedListNode orig, E add)
    {
        LinkedListNode cursor = add;
        size++;
        while(cursor.hasNext()){
            cursor = add.next();
            size++;
        }
        
        if(orig == null)
        {
            LinkedListNode oldHead = head;
            head = add;
            last.setNext(oldHead);
            
            if(oldHead == null)
                last = (E) cursor;
               
        }
        else
        {
            orig.setNext(add);
            
            if(orig == last)
                last = (E) cursor;
        }
        return true;
    }

    @Override
    public E getHead()
    {
        return (E) head;
    }

    @Override
    public E getLast()
    {
        return (E) last;
    }

    @Override
    public boolean replace(E old, E newly)
    {
        old.replace(newly);
        return true;
    }
    

}
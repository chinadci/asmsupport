package cn.wensiqun.asmsupport.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.ListUtils;

import cn.wensiqun.asmsupport.ByteCodeExecutable;

public class ByteCodeExecutableList implements List<ByteCodeExecutable>
{

    private ByteCodeExecutable head;
    
    private ByteCodeExecutable last;
    
    private int size = 0;
    
    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        if(o instanceof ByteCodeExecutable)
        {
            ByteCodeExecutable executable = (ByteCodeExecutable)o;
            if(executable.next() != null || executable.previous() != null)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<ByteCodeExecutable> iterator()
    {
        return (Iterator<ByteCodeExecutable>) (head == null ? ListUtils.EMPTY_LIST : head);
    }

    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[size];
        ByteCodeExecutable curr = head;
        for(int i=0; i<size; i++, curr = head.next())
        {
            array[i] = curr;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        ByteCodeExecutable curr = head;
        for(int i=0; i<size; i++, curr = head.next())
        {
            a[i] = (T) curr;
        }
        return a;
    }

    @Override
    public boolean add(ByteCodeExecutable e)
    {
        ByteCodeExecutable newLast = e;
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
        return true;
    }

    @Override
    public boolean remove(Object o)
    {
        if(o instanceof ByteCodeExecutable)
        {
            ((ByteCodeExecutable)o).remove();
            size--;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("unsupport the containsAll method.");
    }

    @Override
    public boolean addAll(Collection<? extends ByteCodeExecutable> c)
    {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends ByteCodeExecutable> c)
    {
        throw new UnsupportedOperationException("unsupport the containsAll addAll.");
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("unsupport the containsAll removeAll.");
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear()
    {
        
    }

    @Override
    public ByteCodeExecutable get(int index)
    {
        throw new UnsupportedOperationException("unsupport the containsAll get.");
    }

    @Override
    public ByteCodeExecutable set(int index, ByteCodeExecutable element)
    {
        throw new UnsupportedOperationException("unsupport the containsAll set.");
    }

    @Override
    public void add(int index, ByteCodeExecutable element)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ByteCodeExecutable remove(int index)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(Object o)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<ByteCodeExecutable> listIterator()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<ByteCodeExecutable> listIterator(int index)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ByteCodeExecutable> subList(int fromIndex, int toIndex)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    

}

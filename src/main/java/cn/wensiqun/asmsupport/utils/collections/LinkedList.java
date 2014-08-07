package cn.wensiqun.asmsupport.utils.collections;



public interface LinkedList<E> extends Iterable<E>, Cloneable
{

    /**
     * 
     * @return
     */
    int size();
    
    /**
     * 
     * @return
     */
    boolean isEmpty();
    
    /**
     * 
     * @param e
     * @return
     */
    boolean add(E e);
    
    /**
     * 
     * @param o
     * @return
     */
    boolean remove(Object o);
    
    /**
     * 
     * @param o
     * @return
     */
    public boolean contains(Object o);
    
    /**
     * Add element after a special node
     * 
     * @param original
     * @param after
     * @return
     */
    boolean addAfter(E original, E after);
    
    /**
     * Add element before a special node
     * 
     * @param original
     * @param after
     * @return
     */
    boolean addBefore(E original, E after);
    
    
    boolean setHead(E head);
    
    boolean setLast(E last);
    
    /**
     * 
     * @param start
     * @return
     */
    boolean removeFrom(E start);
    
    /**
     * 
     * @param old
     * @param newly
     * @return
     */
    boolean replace(E old, E newly);
    
    
    void clear();
    
    /**
     * 
     * @return
     */
    E getHead();
    
    /**
     * 
     * @return
     */
    E getLast();
    
}

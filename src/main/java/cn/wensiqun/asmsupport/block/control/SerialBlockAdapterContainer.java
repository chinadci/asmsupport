package cn.wensiqun.asmsupport.block.control;

import cn.wensiqun.asmsupport.block.ProgramBlock;
import cn.wensiqun.asmsupport.block.ProgramBlockAdapter;

public class SerialBlockAdapterContainer extends ProgramBlockAdapter
{

    /**
     * 程序块的前一个程序块
     */
    private EpisodeBlock head;
    
    /**
     * 
     */
    private EpisodeBlock tail;
    
    
    public SerialBlockAdapterContainer(ProgramBlock block, EpisodeBlock header)
    {
        super(block);
        tail = head = header;
        
    }

    public void append(EpisodeBlock block)
    {
        tail.append(block);
    }
    
    public void insert(EpisodeBlock previousBlock, EpisodeBlock afterBlock)
    {
        previousBlock.append(afterBlock);
    }
    
    public EpisodeBlock getHead()
    {
        return head;
    }


    public void setHead(EpisodeBlock head)
    {
        this.head = head;
    }


    public EpisodeBlock getTail()
    {
        return tail;
    }


    public void setTail(EpisodeBlock tail)
    {
        this.tail = tail;
    }

}

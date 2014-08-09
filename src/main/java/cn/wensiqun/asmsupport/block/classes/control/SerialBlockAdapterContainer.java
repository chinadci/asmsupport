package cn.wensiqun.asmsupport.block.classes.control;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.block.classes.common.ProgramBlockAdapter;

public class SerialBlockAdapterContainer extends ProgramBlockAdapter
{

    /**
     * 程序块的前一个程序块
     */
    private EpisodeBlock header;
    
    /**
     * 
     */
    private EpisodeBlock tail;
    
    
    public SerialBlockAdapterContainer(ProgramBlock block, EpisodeBlock header)
    {
        super(block);
        tail = this.header = header;
        
    }

    /*public void append(EpisodeBlock block)
    {
        tail.append(block);
    }
    
    public void insert(EpisodeBlock previousBlock, EpisodeBlock afterBlock)
    {
        previousBlock.append(afterBlock);
    }
    */
    public EpisodeBlock getHeader()
    {
        return header;
    }

    public void setHeader(EpisodeBlock header)
    {
        this.header = header;
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

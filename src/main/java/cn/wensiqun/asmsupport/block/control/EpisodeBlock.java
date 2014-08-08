package cn.wensiqun.asmsupport.block.control;

import cn.wensiqun.asmsupport.block.ProgramBlock;

/**
 * 表示是同一个系列的程序块，即语句块是有连续性比如：
 * try,catch,finally是一个系列,if,else if,else是一个系列
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public abstract class EpisodeBlock extends ProgramBlock {
    
	/**
	 * 程序块的前一个程序块
	 */
	private EpisodeBlock previousBlock;
	
	/**
	 * 
	 */
	private EpisodeBlock nextBlock;

	
	public void append(EpisodeBlock block)
	{
	    if(nextBlock != null)
        {
            block.nextBlock = nextBlock;
            nextBlock.previousBlock = block;
        }
	    
	    nextBlock = block;
	    block.previousBlock = this;
	    
	}


    public EpisodeBlock previousBlock()
    {
        return previousBlock;
    }


    public void setPreviousBlock(EpisodeBlock previousBlock)
    {
        this.previousBlock = previousBlock;
    }


    public EpisodeBlock nextBlock()
    {
        return nextBlock;
    }


    public void setNextBlock(EpisodeBlock nextBlock)
    {
        this.nextBlock = nextBlock;
    }

}

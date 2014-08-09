package cn.wensiqun.asmsupport.block.classes.control;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

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
	private EpisodeBlock previousEpisode;
	
	/**
	 * 
	 */
	private EpisodeBlock nextEpisode;

	
    public EpisodeBlock getPreviousEpisode()
    {
        return previousEpisode;
    }

    public void setPreviousEpisode(EpisodeBlock previousEpisode)
    {
        this.previousEpisode = previousEpisode;
    }

    
    public EpisodeBlock getNextEpisode()
    {
        return nextEpisode;
    }
    
    public void setNextEpisode(EpisodeBlock nextEpisode)
    {
        this.nextEpisode = nextEpisode;
    }

}

package cn.wensiqun.asmsupport.block;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.utils.collections.CommonLinkedList;

public abstract class AbstractBlock extends ByteCodeExecutor implements Cloneable {

    protected AbstractBlock parent;
    
    protected CommonLinkedList<ByteCodeExecutor> queue;

	public AbstractBlock(AbstractBlock parent) {
		this.parent = parent;
		this.queue = new CommonLinkedList<ByteCodeExecutor>();
	}
	
}

package cn.wensiqun.asmsupport.block.classes.common;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.utils.collections.CommonLinkedList;

public abstract class AbstractBlock extends ByteCodeExecutor implements Cloneable {

    protected CommonLinkedList<ByteCodeExecutor> queue;

	public AbstractBlock() {
		this.queue = new CommonLinkedList<ByteCodeExecutor>();
	}

	public CommonLinkedList<ByteCodeExecutor> getQueue() {
		return queue;
	}
	
}

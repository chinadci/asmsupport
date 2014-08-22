package cn.wensiqun.asmsupport.block.classes.common;

import cn.wensiqun.asmsupport.ByteCodeExecutor;
import cn.wensiqun.asmsupport.utils.collections.CommonLinkedList;

public abstract class AbstractBlock extends ByteCodeExecutor implements Cloneable {

    
    private CommonLinkedList<ByteCodeExecutor> queue;

	public AbstractBlock() {
		this.queue = new CommonLinkedList<ByteCodeExecutor>();
	}

	public CommonLinkedList<ByteCodeExecutor> getQueue() {
		return queue;
	}

    /**
     * 添加一个Executeable
     * 
     * @param exe
     */
    /*public void addExe(ByteCodeExecutor exe) {
        getQueue().add(exe);
    }*/
    /**
     * 
     * @param exe
     */
    public void removeExe(ByteCodeExecutor exe) {
        getQueue().remove(exe);
    }

    /**
     * 替换
     * @param old
     * @param newp
     */
    /*public void replaceExe(ByteCodeExecutor old, ByteCodeExecutor newp){
        getQueue().replace(old, newp);
    }*/
}

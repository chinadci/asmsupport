/**
 * 
 */
package cn.wensiqun.asmsupport;

import java.util.Iterator;

/**
 * 此接口表示可以执行。
 * @author 温斯群(Joe Wen)
 *
 */
public interface ByteCodeExecutable extends Executable, Iterator<ByteCodeExecutable> {
    
    void setNext(ByteCodeExecutable next);
    
    ByteCodeExecutable previous();
}

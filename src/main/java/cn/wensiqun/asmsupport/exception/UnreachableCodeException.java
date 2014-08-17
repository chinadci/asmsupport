package cn.wensiqun.asmsupport.exception;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;
import cn.wensiqun.asmsupport.operators.AbstractOperator;

public class UnreachableCodeException extends ASMSupportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6866532878000484233L;

	@SuppressWarnings("unused")
	private ProgramBlock block;
	
	@SuppressWarnings("unused")
	private AbstractOperator unreachableOperator;
	
	public UnreachableCodeException(ProgramBlock block, AbstractOperator unreachableOperator) {
		super();
		this.block = block;
		this.unreachableOperator = unreachableOperator;
	}

	public UnreachableCodeException(String message, Throwable cause, ProgramBlock block, AbstractOperator unreachableOperator) {
		super(message, cause);
		this.block = block;
		this.unreachableOperator = unreachableOperator;
	}

	public UnreachableCodeException(String message, ProgramBlock block, AbstractOperator unreachableOperator) {
		super(message);
		this.block = block;
		this.unreachableOperator = unreachableOperator;
	}

	public UnreachableCodeException(Throwable cause, ProgramBlock block, AbstractOperator unreachableOperator) {
		super(cause);
		this.block = block;
		this.unreachableOperator = unreachableOperator;
	}
	
	

}

package cn.wensiqun.asmsupport.block.classes.control.exception.v2;

import cn.wensiqun.asmsupport.block.classes.common.ProgramBlock;

public abstract class ExceptionEpisodeBlock extends ProgramBlock {

	private ExceptionSerialBlock serial;

	public ExceptionSerialBlock getSerial() {
		return serial;
	}

	void setSerial(ExceptionSerialBlock serial) {
		this.serial = serial;
	}
	
}

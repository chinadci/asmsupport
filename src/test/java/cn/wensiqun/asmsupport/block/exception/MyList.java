package cn.wensiqun.asmsupport.block.exception;

import java.util.ArrayList;

public class MyList extends ArrayList<String> {

	public String put(String s) {
		this.add(s);
		return s;
	}

}
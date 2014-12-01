package cn.wensiqun.asmsupport.utils.asm;

import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupportasm.FieldVisitor;

public class FieldAdapter extends FieldVisitor {

	public FieldAdapter() {
		super(ASConstant.ASM_VERSION);
	}

	public FieldAdapter(FieldVisitor fv) {
		super(ASConstant.ASM_VERSION, fv);
	}

}

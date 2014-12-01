package cn.wensiqun.asmsupport.utils.asm;

import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupportasm.ClassVisitor;

public class ClassAdapter extends ClassVisitor {

	public ClassAdapter(ClassVisitor cv) {
		super(ASConstant.ASM_VERSION, cv);
	}

	public ClassAdapter() {
		super(ASConstant.ASM_VERSION);
	}

}

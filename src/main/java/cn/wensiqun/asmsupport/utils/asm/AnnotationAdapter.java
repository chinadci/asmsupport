package cn.wensiqun.asmsupport.utils.asm;

import cn.wensiqun.asmsupport.utils.ASConstant;
import cn.wensiqun.asmsupportasm.AnnotationVisitor;

public class AnnotationAdapter extends AnnotationVisitor {

	public AnnotationAdapter(AnnotationVisitor av) {
		super(ASConstant.ASM_VERSION, av);
	}

	public AnnotationAdapter() {
		super(ASConstant.ASM_VERSION);
	}

	
	
}

package cn.wensiqun.asmsupportgeneric.creator;

import cn.wensiqun.asmsupport.clazz.AClass;

public interface IClassCreator<_StaticBlockBody, _ConstructorBody, _MethodBody, _StaticMethodBody, _FieldCreator, _MethodCreator> {
   
	_FieldCreator createGlobalVariable(String name, int modifiers, AClass fieldClass);
    
	_MethodCreator createMethod(int access, String name, AClass[] argTypes,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            _MethodBody body);
    
	_MethodCreator createStaticMethod(int access, String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            _StaticMethodBody body);

	_MethodCreator createConstructor(int access, AClass[] argTypes,
            String[] argNames, _ConstructorBody body);

	_MethodCreator createStaticBlock(_StaticBlockBody block);
}

package cn.wensiqun.asmsupportgeneric;

import cn.wensiqun.asmsupport.clazz.AClass;

public interface IClassCreator<_StaticBlockBody, _ConstructorBody, _MethodBody, _StaticMethodBody> {
   
    IFieldCreator createGlobalVariable(String name, int modifiers, AClass fieldClass);
    
    void createMethod(int access, String name, AClass[] argTypes,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            _MethodBody body);
    
    void createStaticMethod(int access, String name, AClass[] argClasses,
            String[] argNames, AClass returnClass, AClass[] exceptions,
            _StaticMethodBody body);

    void createConstructor(int access, AClass[] argTypes,
            String[] argNames, _ConstructorBody body);

    void createStaticBlock(_StaticBlockBody block);
}

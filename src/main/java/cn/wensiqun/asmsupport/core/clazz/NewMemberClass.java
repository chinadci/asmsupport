/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cn.wensiqun.asmsupport.core.clazz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.wensiqun.asmsupport.core.definition.method.AMethod;
import cn.wensiqun.asmsupport.core.definition.variable.SuperVariable;
import cn.wensiqun.asmsupport.core.definition.variable.ThisVariable;
import cn.wensiqun.asmsupport.core.definition.variable.meta.GlobalVariableMeta;
import cn.wensiqun.asmsupport.core.utils.ASConstant;


public abstract class NewMemberClass extends AClass {

    /** super key word  */
    private SuperVariable superVariable;

    /** this key word */
    private ThisVariable thisVariable;
    
    /** */
    private List<AMethod> methods;

    /**
     * store bridge method.
     * 1. overried method that return type is child of super method return type.
     * 2. generice type method(implement future)
     */
    private List<AMethod> bridgeMethod;
    
    private List<AMethod> constructors;
    
    private AMethod staticBlock;

    private Set<GlobalVariableMeta> globalVariableMetas;

    //available only create enum class
    private int enumNum;

	public NewMemberClass() {
	}

	public int getEnumNum() {
		return enumNum;
	}

	public void setEnumNum(int enumNum) {
		this.enumNum = enumNum;
	}

    public List<AMethod> getMethods() {
    	if(methods == null){
   	        methods = new ArrayList<AMethod>();
    	}
        return methods;
    }
    
    
    public List<AMethod> getBridgeMethod() {
    	if(bridgeMethod == null){
    		bridgeMethod = new ArrayList<AMethod>();
    	}
		return bridgeMethod;
	}

	
    public AMethod getStaticBlock() {
        return staticBlock;
    }

    public void setStaticBlock(AMethod staticBlock) {
        this.staticBlock = staticBlock;
    }
    
    /**
     * add method
     * 
     * @param method
     */
    public void addMethod(AMethod method) {
    	getMethods().add(method);
    }
    

    public SuperVariable getSuperVariable() {
    	if(superVariable == null){
            superVariable = new SuperVariable(this);
    	}
        return superVariable;
    }

    public ThisVariable getThisVariable() {
    	if(thisVariable == null){
            thisVariable = new ThisVariable(this);
    	}
        return thisVariable;
    }
    
    /**
     * 
     * @param constructor
     */
    public void addConstructor(AMethod constructor) {
    	getConstructors().add(constructor);
    }

	public List<AMethod> getConstructors() {
		if(constructors == null){
	        constructors = new ArrayList<AMethod>(3);
		}
		return constructors;
	}
    
    protected Set<GlobalVariableMeta> getGlobalVariableMetas() {
    	if(globalVariableMetas == null){
    	    globalVariableMetas = new HashSet<GlobalVariableMeta>();
    	}
		return globalVariableMetas;
	}
    
	/**
     * 
     * add
     * @param field
     */
    public void addGlobalVariableMeta(GlobalVariableMeta e) {
        getGlobalVariableMetas().add(e);
    }
    
    @Override
	public boolean existStaticInitBlock() {
		for(AMethod m : this.getMethods()){
			if(m.getMethodMeta().getName().equals(ASConstant.CLINIT)){
				return true;
			}
		}
    	return false;
	}

}

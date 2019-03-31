package Mxstar.Symbol;

import Mxstar.Config;

public class ArrayType extends VarType {
    public VarType baseType;
    public ArrayType() {}
    public ArrayType(VarType baseType) {
        this.baseType = baseType;
    }

    @Override public boolean match(VarType other) {
        if (other instanceof ClassType && ((ClassType)(other)).name.equals("null")) {
            return true;
        } else if (other instanceof ArrayType) {
            return baseType.match(((ArrayType)(other)).baseType);
        } else {
            return false;
        }
    }

    @Override public int getBytes() {
        return Config.REGISTER_WIDTH;
    }
}
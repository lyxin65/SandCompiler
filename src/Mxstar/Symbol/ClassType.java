package Mxstar.Symbol;

import java.util.Collection;
import java.util.LinkedList;

import Mxstar.Config;

public class ClassType extends VarType {
    public String name;
    public ClassSymbol symbol;

    public ClassType() {}
    public ClassType(String name, ClassSymbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public boolean match(VarType other) {
        if(other instanceof ClassType) {
            String otherName = ((ClassType)(other)).name;
            if((otherName.equals("null") && name.equals("string")) || (otherName.equals("string") && name.equals("null")))
                return false;
            else
                return otherName.equals("null") || name.equals("null") || ((ClassType)(other)).name.equals(name);
        } else
            return false;
    }

    @Override
    public int getBytes() {
        Collection<VarSymbol> fields = symbol.classSymbolTable.vars.values();
        return fields.size() * Config.REGISTER_WIDTH;
    }
}

package Mxstar.Symbol;

import Mxstar.Config;

public class BaseType extends VarType{
    public String name;
    public BaseSymbol symbol;

    public BaseType() {}
    public BaseType(String name, BaseSymbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public boolean match(VarType other) {
        if (other instanceof BaseType && ((BaseType)(other)).name.equals(name))
            return true;
        else
            return false;
    }

    @Override
    public int getBytes() {
        return Config.REGISTER_WIDTH;
    }
}

package Mxstar.Symbol;

import Mxstar.AST.TokenLocation;

public class VarSymbol {
    public String name;
    public VarType type;
    public TokenLocation location;
    
    public boolean isClassField;
    public boolean isGlobalVar;
    
    public VarSymbol(String name, VarType type, TokenLocation location, boolean isClassField, boolean isGlobalVar) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.isClassField = isClassField;
        this.isGlobalVar = isGlobalVar;
    }
}

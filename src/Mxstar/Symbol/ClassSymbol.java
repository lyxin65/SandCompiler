package Mxstar.Symbol;

import Mxstar.AST.TokenLocation;

public class ClassSymbol extends TypeSymbol {
    public String name;
    public TokenLocation location;
    public SymbolTable classSymbolTable;

}

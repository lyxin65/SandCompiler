package Mxstar.Symbol;

import Mxstar.AST.TokenLocation;

public class BaseSymbol extends TypeSymbol {
    public String name;
    public TokenLocation location;

    public BaseSymbol() {
    }

    public BaseSymbol(String name) {
        this.name = name;
        this.location = new TokenLocation(0, 0);
    }
}
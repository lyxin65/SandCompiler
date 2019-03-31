package Mxstar.AST;

import Mxstar.Symbol.VarType;

public abstract class Expr extends AstNode {
    public VarType type;
    public boolean modifiable;
}

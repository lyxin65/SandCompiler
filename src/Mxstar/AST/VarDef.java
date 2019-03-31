package Mxstar.AST;


import Mxstar.Symbol.VarSymbol;

public class VarDef extends Definition {
    public TypeNode typeNode = null;
    public String name = null;
    public Expr init = null;

    public VarSymbol symbol;

    public VarDef() {}
    public VarDef(TypeNode typeNode, String name, Expr init) {
        this.typeNode = typeNode;
        this.name = name;
        this.init = init;
        this.symbol = null;
    }
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

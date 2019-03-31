package Mxstar.AST;

import Mxstar.Symbol.FuncSymbol;

import java.util.LinkedList;
import java.util.List;

public class FuncDef extends Definition {
    public TypeNode retTypeNode = null;
    public String name = null;
    public List<VarDef> parameters;
    public List<Stmt> body;

    public FuncSymbol symbol;

    public String toString() {
        return name + "\n";
    }

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

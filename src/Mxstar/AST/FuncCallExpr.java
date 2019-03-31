package Mxstar.AST;

import Mxstar.Symbol.FuncSymbol;

import java.util.List;

public class FuncCallExpr extends Expr {
    public String funcName;
    public List<Expr> arguments;

    public FuncSymbol funcSymbol;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

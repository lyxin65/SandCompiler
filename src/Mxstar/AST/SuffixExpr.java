package Mxstar.AST;

public class SuffixExpr extends Expr {
    public String op;   //  can be v++, v--
    public Expr expr;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}


package Mxstar.AST;

public class UnaryExpr extends Expr {
    public String op;   //  can be v++, v--, also can be ++v, --v, ~, !
    public Expr expr;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}


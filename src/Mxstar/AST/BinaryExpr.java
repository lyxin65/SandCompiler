package Mxstar.AST;

public class BinaryExpr extends Expr {
    public String op;
    public Expr lhs;
    public Expr rhs;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

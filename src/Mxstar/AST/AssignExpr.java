package Mxstar.AST;

public class AssignExpr extends Expr {
    public Expr lhs;
    public Expr rhs;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

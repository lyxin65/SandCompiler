package Mxstar.AST;

public class ArrayExpr extends Expr {
    public Expr address;
    public Expr index;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

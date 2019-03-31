package Mxstar.AST;

public class IfStmt extends Stmt {
    public Expr condition;
    public Stmt thenStmt;
    public Stmt elseStmt;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

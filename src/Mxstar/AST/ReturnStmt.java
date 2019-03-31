package Mxstar.AST;

public class ReturnStmt extends Stmt {
    public Expr retExpr = null;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}

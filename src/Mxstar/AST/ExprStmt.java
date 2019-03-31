package Mxstar.AST;


public class ExprStmt extends Stmt {
    public Expr expr = null;

    public ExprStmt() {
    }
    public ExprStmt(Expr expr) {
        this.expr = expr;
    }

    @Override public void accept(IAstVisitor visitor) { visitor.visit(this); }

}

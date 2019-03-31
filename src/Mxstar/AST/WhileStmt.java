package Mxstar.AST;

public class WhileStmt extends Stmt {
    public Expr condition = null;
    public Stmt body = null;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

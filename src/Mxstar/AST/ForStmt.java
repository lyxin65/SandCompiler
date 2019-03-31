package Mxstar.AST;

public class ForStmt extends Stmt {
    public Stmt initStmt = null;
    public Expr condition = null;
    public Stmt updateStmt = null;
    public Stmt body = null;

    @Override public void accept(IAstVisitor visitor) { visitor.visit(this); }

}

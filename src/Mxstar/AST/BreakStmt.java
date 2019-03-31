package Mxstar.AST;

public class BreakStmt extends Stmt {
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

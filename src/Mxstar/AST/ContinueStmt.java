package Mxstar.AST;

public class ContinueStmt extends Stmt {
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

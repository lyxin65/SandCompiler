package Mxstar.AST;

public class EmptyStmt extends Stmt {
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

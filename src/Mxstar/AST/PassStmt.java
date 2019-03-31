package Mxstar.AST;

public class PassStmt extends Stmt {
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

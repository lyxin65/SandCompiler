package Mxstar.AST;

public class Definition extends AstNode {
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

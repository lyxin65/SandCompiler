package Mxstar.AST;

public class BaseTypeNode extends TypeNode {
    public String name;

    public BaseTypeNode() {
    }

    public BaseTypeNode(String name) {
        this.name = name;
    }

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

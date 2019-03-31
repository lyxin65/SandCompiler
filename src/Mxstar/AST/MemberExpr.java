package Mxstar.AST;

public class MemberExpr extends Expr {
    public Expr object;
    public ID fieldAccess;
    public FuncCallExpr methodCall;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}

package Mxstar.AST;

import java.util.List;

public class NewExpr extends Expr {
    public TypeNode typeNode;
    public List<Expr> exprDimensions;
    public int restDemension;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}

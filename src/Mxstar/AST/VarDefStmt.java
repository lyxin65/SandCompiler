package Mxstar.AST;

public class VarDefStmt extends Stmt {
    public VarDef def;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }

}

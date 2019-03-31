package Mxstar.AST;

import java.util.*;

public class BlockStmt extends Stmt {
    public List<Stmt> statements;

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

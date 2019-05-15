package Mxstar.AST;

public interface IAstVisitor {
    void visit(AstProgram node);

    void visit(Definition node);
    void visit(FuncDef node);
    void visit(ClassDef node);
    void visit(VarDef node);

    void visit(TypeNode node);
    void visit(ArrayTypeNode node);
    void visit(BaseTypeNode node);
    void visit(ClassTypeNode node);

    void visit(Stmt node);
    void visit(ForStmt node);
    void visit(WhileStmt node);
    void visit(IfStmt node);
    void visit(ContinueStmt node);
    void visit(BreakStmt node);
    void visit(ReturnStmt node);
    void visit(BlockStmt node);
    void visit(VarDefStmt node);
    void visit(ExprStmt node);
    void visit(PassStmt node);

    void visit(Expr node);
    void visit(ID node);
    void visit(LiteralExpr node);
    void visit(ArrayExpr node);
    void visit(FuncCallExpr node);
    void visit(NewExpr node);
    void visit(MemberExpr node);
    void visit(PrefixExpr node);
    void visit(SuffixExpr node);
    void visit(UnaryExpr node);
    void visit(BinaryExpr node);
    // void visit(LogicExpr node);
    void visit(AssignExpr node);
    void visit(EmptyStmt node);
}

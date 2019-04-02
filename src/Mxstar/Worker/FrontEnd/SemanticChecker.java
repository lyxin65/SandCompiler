package Mxstar.Worker.FrontEnd;

import Mxstar.AST.*;
import Mxstar.Symbol.*;
import Mxstar.Worker.ErrorRecorder;

public class SemanticChecker implements IAstVisitor {
    GlobalSymbolTable globalSymbolTable;
    ErrorRecorder recorder;
    FuncSymbol curFunc;
    int loopCnt;

    public SemanticChecker(GlobalSymbolTable globalSymbolTable, ErrorRecorder recorder) {
        this.globalSymbolTable = globalSymbolTable;
        this.recorder = recorder;
        this.loopCnt = 0;
    }

    @Override
    public void visit(ID node) {

    }

    @Override
    public void visit(Expr node) {

    }

    @Override
    public void visit(Stmt node) {

    }

    @Override
    public void visit(IfStmt node) {

    }

    @Override
    public void visit(VarDef node) {

    }

    @Override
    public void visit(ForStmt node) {

    }

    @Override
    public void visit(FuncDef node) {

    }

    @Override
    public void visit(NewExpr node) {

    }

    @Override
    public void visit(ClassDef node) {

    }

    @Override
    public void visit(ExprStmt node) {

    }

    @Override
    public void visit(PassStmt node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(ArrayExpr node) {

    }

    @Override
    public void visit(BlockStmt node) {

    }

    @Override
    public void visit(BreakStmt node) {

    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(WhileStmt node) {

    }

    @Override
    public void visit(AssignExpr node) {

    }

    @Override
    public void visit(AstProgram node) {

    }

    @Override
    public void visit(BinaryExpr node) {

    }

    @Override
    public void visit(Definition node) {

    }


    @Override
    public void visit(MemberExpr node) {

    }

    @Override
    public void visit(PrefixExpr node) {

    }

    @Override
    public void visit(ReturnStmt node) {

    }

    @Override
    public void visit(SuffixExpr node) {

    }

    @Override
    public void visit(VarDefStmt node) {

    }

    @Override
    public void visit(LiteralExpr node) {

    }

    @Override
    public void visit(BaseTypeNode node) {

    }

    @Override
    public void visit(ContinueStmt node) {

    }

    @Override
    public void visit(FuncCallExpr node) {

    }

    @Override
    public void visit(ArrayTypeNode node) {

    }

    @Override
    public void visit(ClassTypeNode node) {

    }
}

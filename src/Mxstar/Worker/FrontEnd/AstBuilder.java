package Mxstar.Worker.FrontEnd;

import Mxstar.AST.*;
import Mxstar.Parser.*;
import Mxstar.Worker.ErrorRecorder;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

import static Mxstar.Parser.MxstarParser.*;

public class AstBuilder extends MxstarBaseVisitor<Object> {
    public AstProgram astProgram;
    public ErrorRecorder recorder;

    public AstBuilder(ErrorRecorder recorder) {
        this.astProgram = new AstProgram();
        this.astProgram.location = new TokenLocation(0, 0);
        this.recorder = recorder;
    }

    public AstProgram getAstProgram() {
        return astProgram;
    }

    @Override public Object visitProgram(MxstarParser.ProgramContext ctx) {
        for (ParserRuleContext c: ctx.getRuleContexts(ParserRuleContext.class)) {
            if (c instanceof VarDefContext) {
                astProgram.add(visitVarDef((VarDefContext)c));
            } else if (c instanceof FuncDefContext) {
                astProgram.add(visitFuncDef((FuncDefContext)c));
            } else if (c instanceof ClassDefContext) {
                astProgram.add(visitClassDef((ClassDefContext)c));
            } else {
                assert false;
            }
        }
        return null;
    }
    @Override public VarDef visitVarDef(MxstarParser.VarDefContext ctx) {
        VarDef varDef = new VarDef();
        varDef.location = new TokenLocation(ctx);
        varDef.typeNode = visitExType(ctx.exType());
        varDef.name = ctx.ID().getSymbol().getText();
        if (ctx.expr() == null) {
            varDef.init = null;
        } else {
            varDef.init = (Expr) ctx.expr().accept(this);
        }
        return varDef;
    }
    @Override public FuncDef visitFuncDef(MxstarParser.FuncDefContext ctx) {
        FuncDef funcDef = new FuncDef();
        funcDef.retTypeNode = visitExType(ctx.exType());
        funcDef.name = ctx.ID().getSymbol().getText();
        funcDef.parameters = visitParaList(ctx.paraList());
        funcDef.body = ((BlockStmt)visitBlock(ctx.block())).statements;
        funcDef.location = funcDef.retTypeNode.location;
        return funcDef;
    }
    @Override public List<VarDef> visitParaList(MxstarParser.ParaListContext ctx) {
        List<VarDef> parameters = new LinkedList<>();
        if(ctx != null) {
            for (ParaContext c : ctx.para()) {
                parameters.add((VarDef) c.accept(this));
            }
        }
        return parameters;
    }
    @Override public ClassDef visitClassDef(MxstarParser.ClassDefContext ctx) {
        ClassDef classDef = new ClassDef();
        classDef.name = ctx.ID().getSymbol().getText();
        classDef.location = new TokenLocation(ctx);
        classDef.methods = new LinkedList<>();
        classDef.fields = new LinkedList<>();

        // constructor
        if (ctx.constructDef() != null) {
            for (ConstructDefContext c : ctx.constructDef()) {
                if (classDef.constructor == null) {
                    classDef.constructor = visitConstructDef(ctx.constructDef(0));
                } else {
                    recorder.addRecord(new TokenLocation(c), "class can have at most one constructor");
                }
            }
        }

        // fields
        if (ctx.varDef() != null) {
            for (VarDefContext c : ctx.varDef()) {
                classDef.fields.add(visitVarDef(c));
            }
        }

        // methods
        if (ctx.funcDef() != null) {
            for (FuncDefContext c : ctx.funcDef()) {
                classDef.methods.add(visitFuncDef(c));
            }
        }

        return classDef;
    }
    @Override public FuncDef visitConstructDef(MxstarParser.ConstructDefContext ctx) {
        FuncDef con = new FuncDef();
        con.location = new TokenLocation(ctx);
        con.retTypeNode = new BaseTypeNode("void");
        con.name = ctx.ID().getSymbol().getText();
        con.parameters = visitParaList(ctx.paraList());
        con.body = ((BlockStmt) visitBlock(ctx.block())).statements;
        return con;
    }
    @Override public VarDef visitPara(MxstarParser.ParaContext ctx) {
        VarDef varDef = new VarDef();
        varDef.location = new TokenLocation(ctx);
        varDef.typeNode = (TypeNode)ctx.exType().accept(this);
        varDef.name = ctx.ID().getSymbol().getText();
        varDef.init = null;
        return varDef;
    }
    @Override public TypeNode visitBaseType(MxstarParser.BaseTypeContext ctx) {
        BaseTypeNode baseTypeNode = new BaseTypeNode();
        baseTypeNode.location = new TokenLocation(ctx);
        baseTypeNode.name = ctx.getText();
        return baseTypeNode;
    }
    @Override public TypeNode visitExType(MxstarParser.ExTypeContext ctx) {
        if (!ctx.getText().contains("[")) {
            return visitAtomType(ctx.atomType());

        } else {
            // array type
            ArrayTypeNode node = new ArrayTypeNode();
            node.location = new TokenLocation(ctx);
            node.baseType = visitAtomType(ctx.atomType());
            node.dimension = ctx.empty().size();
            return node;
        }
    }
    @Override public TypeNode visitAtomType(MxstarParser.AtomTypeContext ctx) {
        if (ctx.baseType() == null) {
            ClassTypeNode node = new ClassTypeNode();
            node.className = ctx.type.getText();
            node.location = new TokenLocation(ctx);
            return node;
        } else {
            return visitBaseType(ctx.baseType());
        }
    }
    @Override public Stmt visitBlock(MxstarParser.BlockContext ctx) {
        BlockStmt blockStmt = new BlockStmt();
        List<Stmt> stmts = new LinkedList<>();
        if (ctx.stmt() != null) {
            for (StmtContext c: ctx.stmt()) {
                if (c instanceof VarDefStmtContext) {
                    stmts.add(visitVarDefStmt((VarDefStmtContext) c));
                } else {
                    stmts.add((Stmt) c.accept(this));
                }
            }
        }
        blockStmt.location = new TokenLocation(ctx);
        blockStmt.statements = stmts;
        return blockStmt;
    }
    @Override public Stmt visitBlockStmt(MxstarParser.BlockStmtContext ctx) {
        return visitBlock(ctx.block());
    }
    @Override public Stmt visitExprStmt(MxstarParser.ExprStmtContext ctx) {
        ExprStmt exprStmt = new ExprStmt();
        exprStmt.location = new TokenLocation(ctx);
        exprStmt.expr = (Expr) ctx.expr().accept(this);
        return exprStmt;
    }
    @Override public Stmt visitVarDefStmt(MxstarParser.VarDefStmtContext ctx) {
        VarDefStmt varDefStmt = new VarDefStmt();
        varDefStmt.varDef = visitVarDef(ctx.varDef());
        varDefStmt.location = new TokenLocation(ctx);
        return varDefStmt;
    }
    @Override public Stmt visitIfStmt(MxstarParser.IfStmtContext ctx) {
        IfStmt ifStmt = new IfStmt();
        ifStmt.location = new TokenLocation(ctx);
        ifStmt.condition = (Expr) ctx.expr().accept(this);
        ifStmt.thenStmt = (Stmt) ctx.stmt(0).accept(this);
        if (ctx.stmt(1) != null) {
            ifStmt.elseStmt = (Stmt) ctx.stmt(1).accept(this);
        }
        return ifStmt;
    }
    @Override public Stmt visitForStmt(MxstarParser.ForStmtContext ctx) {
        ForStmt forStmt = new ForStmt();
        forStmt.location = new TokenLocation(ctx);
        if (ctx.init != null) {
            forStmt.initStmt = new ExprStmt((Expr) ctx.init.accept(this));
        }
        if (ctx.cond != null) {
            forStmt.condition = (Expr) ctx.cond.accept(this);
        }
        if (ctx.outit != null) {
            forStmt.updateStmt = new ExprStmt((Expr) ctx.outit.accept(this));
        }
        forStmt.body = (Stmt) ctx.stmt().accept(this);
        return forStmt;
    }
    @Override public Stmt visitWhileStmt(MxstarParser.WhileStmtContext ctx) {
        WhileStmt whileStmt = new WhileStmt();
        whileStmt.location = new TokenLocation(ctx);
        whileStmt.condition = (Expr) ctx.expr().accept(this);
        whileStmt.body = (Stmt) ctx.stmt().accept(this);
        return whileStmt;
    }
    @Override public Stmt visitReturnStmt(MxstarParser.ReturnStmtContext ctx) {
        ReturnStmt returnStmt = new ReturnStmt();
        returnStmt.location = new TokenLocation(ctx);
        if (ctx.expr() != null) {
            returnStmt.retExpr = (Expr) ctx.expr().accept(this);
        }
        return returnStmt;
    }
    @Override public Stmt visitBreakStmt(MxstarParser.BreakStmtContext ctx) {
        BreakStmt breakStmt = new BreakStmt();
        breakStmt.location = new TokenLocation(ctx);
        return breakStmt;
    }
    @Override public Stmt visitContinueStmt(MxstarParser.ContinueStmtContext ctx) {
        ContinueStmt continueStmt = new ContinueStmt();
        continueStmt.location = new TokenLocation(ctx);
        return continueStmt;
    }
    @Override public Stmt visitPassStmt(MxstarParser.PassStmtContext ctx) {
        PassStmt passStmt = new PassStmt();
        passStmt.location = new TokenLocation(ctx);
        return passStmt;
    }
    @Override public List<Expr> visitExprList(MxstarParser.ExprListContext ctx) {
        List<Expr> exprs = new LinkedList<>();
        if (ctx.expr() != null) {
            for (ExprContext c : ctx.expr()) {
                exprs.add((Expr) c.accept(this));
            }
        }
        return exprs;
    }
    @Override public Expr visitNewExpr(MxstarParser.NewExprContext ctx) {
        return visitNewObject(ctx.newObject());

    }
    @Override public Expr visitNewObject(MxstarParser.NewObjectContext ctx) {
        NewExpr newExpr = new NewExpr();
        newExpr.location = new TokenLocation(ctx);
        newExpr.typeNode = visitAtomType(ctx.atomType());
        newExpr.exprDimensions = new LinkedList<>();
        if (ctx.expr() != null) {
            for (ExprContext c : ctx.expr()) {
                newExpr.exprDimensions.add((Expr) c.accept(this));
            }
        }
        if (ctx.empty() != null) {
            newExpr.restDimension = ctx.empty().size();
        } else {
            newExpr.restDimension = 0;
        }
        return newExpr;
    }

    @Override public Expr visitThisExpr(MxstarParser.ThisExprContext ctx) {
        return new ID(ctx.token);
    }

    @Override public Expr visitMemberExpr(MxstarParser.MemberExprContext ctx) {
        MemberExpr memberExpr = new MemberExpr();
        memberExpr.object = (Expr) ctx.expr().accept(this);
        if (ctx.funcCall() != null) {
            memberExpr.methodCall = visitFuncCall(ctx.funcCall());
        } else {
            memberExpr.fieldAccess = new ID(ctx.ID().getSymbol());
        }
        return memberExpr;
    }

    @Override public Expr visitPrefixExpr(MxstarParser.PrefixExprContext ctx) {
        UnaryExpr prefixExpr = new UnaryExpr();
        prefixExpr.location = new TokenLocation(ctx);
        prefixExpr.expr = (Expr) ctx.expr().accept(this);
        switch(ctx.op.getText()) {
            case "++":
                prefixExpr.op = "++v";
                break;
            case "--":
                prefixExpr.op = "--v";
                break;
            default:
                prefixExpr.op = ctx.op.getText();
        }
        return prefixExpr;
    }
    @Override public Expr visitSuffixExpr(MxstarParser.SuffixExprContext ctx) {
        UnaryExpr suffixExpr = new UnaryExpr();
        suffixExpr.location = new TokenLocation(ctx);
        suffixExpr.expr = (Expr) ctx.expr().accept(this);
        switch(ctx.op.getText()) {
            case "++":
                suffixExpr.op = "v++";
                break;
            case "--":
                suffixExpr.op = "v--";
                break;
            default:
                suffixExpr.op = ctx.op.getText();
        }
        return suffixExpr;
    }

    @Override public Expr visitBinaryExpr(MxstarParser.BinaryExprContext ctx) {
        BinaryExpr binaryExpr = new BinaryExpr();
        binaryExpr.location = new TokenLocation(ctx);
        binaryExpr.op = ctx.op.getText();
        binaryExpr.lhs = (Expr)ctx.expr(0).accept(this);
        binaryExpr.rhs = (Expr)ctx.expr(1).accept(this);
        return binaryExpr;
    }

    @Override public FuncCallExpr visitFuncCallExpr(MxstarParser.FuncCallExprContext ctx) {
        return visitFuncCall(ctx.funcCall());
    }

    @Override public Expr visitSubExpr(MxstarParser.SubExprContext ctx) {
        return (Expr)ctx.expr().accept(this);
    }

    @Override public Expr visitVarExpr(MxstarParser.VarExprContext ctx) {
        return new ID(ctx.token);
    }


    @Override public Expr visitLiteralExpr(MxstarParser.LiteralExprContext ctx) {
        return visitLiteral(ctx.literal());
    }
/*
    @Override public Expr visitLogicExpr(MxstarParser.LogicExprContext ctx) {
        LogicExpr logicExpr = new LogicExpr();
        logicExpr.location = new TokenLocation(ctx);
        logicExpr.lhs = (Expr) ctx.expr(0).accept(this);
        logicExpr.rhs = (Expr) ctx.expr(1).accept(this);
        logicExpr.op = ctx.op.getText();
        return logicExpr;
    }
*/
    @Override public Expr visitArrayExpr(MxstarParser.ArrayExprContext ctx) {
        ArrayExpr arrayExpr = new ArrayExpr();
        arrayExpr.location = new TokenLocation(ctx);
        arrayExpr.address = (Expr)ctx.expr(0).accept(this);
        if (arrayExpr.address instanceof NewExpr && ctx.expr(0).stop.getText().equals("]")) {
            recorder.addRecord(arrayExpr.address.location, "can not use new a[n][i] to express (new a[n])[i]");
        }
        arrayExpr.index = (Expr)ctx.expr(1).accept(this);
        return arrayExpr;
    }

    @Override public Expr visitAssignExpr(MxstarParser.AssignExprContext ctx) {
        AssignExpr assignExpr = new AssignExpr();
        assignExpr.location = new TokenLocation(ctx);
        assignExpr.lhs = (Expr)ctx.expr(0).accept(this);
        assignExpr.rhs = (Expr)ctx.expr(1).accept(this);
        return assignExpr;
    }

    @Override public FuncCallExpr visitFuncCall(MxstarParser.FuncCallContext ctx) {
        FuncCallExpr funcCallExpr = new FuncCallExpr();
        funcCallExpr.location = new TokenLocation(ctx);
        funcCallExpr.funcName = ctx.ID().getSymbol().getText();
        if (ctx.exprList() != null) {
            funcCallExpr.arguments = visitExprList(ctx.exprList());
        } else {
            funcCallExpr.arguments = new LinkedList<>();
        }
        return funcCallExpr;
    }

    @Override public Expr visitLiteral(MxstarParser.LiteralContext ctx) {
        return new LiteralExpr(ctx.token);
    }
}

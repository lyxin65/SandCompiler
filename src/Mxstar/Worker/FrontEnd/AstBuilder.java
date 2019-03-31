package Mxstar.Worker.FrontEnd;

import Mxstar.AST.*;
import Mxstar.Parser.*;
import Mxstar.Symbol.ArrayType;
import Mxstar.Worker.ErrorRecorder;

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
		for (VarDefContext c: ctx.varDef()) {
			astProgram.add(visitVarDef(c));
		}
		for (FuncDefContext c: ctx.funcDef()) {
			astProgram.add(visitFuncDef(c));
		}
		for (ClassDefContext c: ctx.classDef()) {
			astProgram.add(visitClassDef(c));
		}
		return null;
    }
	@Override public VarDef visitVarDef(MxstarParser.VarDefContext ctx) {
    	VarDef varDef = new VarDef();
    	varDef.location = new TokenLocation(ctx);
    	varDef.typeNode = null;
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
    	funcDef.body = visitBlock(ctx.block());
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
	@Override public Object visitClassDef(MxstarParser.ClassDefContext ctx) {
		ClassDef classDef = new ClassDef();
		classDef.name = ctx.ID().getSymbol().getText();
		classDef.location = new TokenLocation(ctx);
		classDef.methods = new LinkedList<>();
		classDef.fields = new LinkedList<>();

		// constructor
		for (ConstructDefContext c: ctx.constructDef()) {
			if (classDef.constructor == null) {
				classDef.constructor = visitConstructDef(ctx.constructDef(0));
			} else {
				recorder.addRecord(new TokenLocation(c), "class can have at most one constructor");
			}
		}

		// fields
		for (VarDefContext c: ctx.varDef()) {
			classDef.fields.add(visitVarDef(c));
		}

		// methods
		for (FuncDefContext c: ctx.funcDef()) {
			classDef.methods.add(visitFuncDef(c));
		}

		return classDef;
	}
	@Override public FuncDef visitConstructDef(MxstarParser.ConstructDefContext ctx) {
		FuncDef con = new FuncDef;
		con.location = new TokenLocation(ctx);
		con.retTypeNode = new BaseTypeNode("void");
		con.name = ctx.ID().getSymbol().getText();
		con.parameters = visitParaList(ctx.paraList());
		con.body = visitBlock(ctx.block());
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
	@Override public BaseTypeNode visitBaseType(MxstarParser.BaseTypeContext ctx) {
		BaseTypeNode baseTypeNode = new BaseTypeNode();
		baseTypeNode.location = new TokenLocation(ctx);
		baseTypeNode.name = ctx.getText();
		return baseTypeNode;
	}
	@Override public TypeNode visitExType(MxstarParser.ExTypeContext ctx) {
    	if (!ctx.getText().contains("[")) {
			if (ctx.baseType() == null) {
				ClassTypeNode node = new ClassTypeNode();
				node.className = ctx.getText();
				node.location = new TokenLocation(ctx);
				return node;
			} else {
				return visitBaseType(ctx.baseType());
			}
		} else {
    		// array type
			ArrayTypeNode node = new ArrayTypeNode();
			node.location = new TokenLocation(ctx);
			node.baseType = null;
			if (ctx.baseType() == null) {
				ClassTypeNode basenode = new ClassTypeNode();
				basenode.className = ctx.getText();
				basenode.location = new TokenLocation(ctx);
				node.baseType = basenode;
			} else {
				node.baseType = visitBaseType(ctx.baseType());
			}
			int cnt = 0;
			for (char c: ctx.getText()) {
				if (c == '[') {
					cnt++;
				}
			}
			node.dimension = cnt;
			return node;
		}
	}
	@Override public List<Stmt> visitBlock(MxstarParser.BlockContext ctx) {
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
	}
	@Override public List<Stmt> visitBlockStmt(MxstarParser.BlockStmtContext ctx) {
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
    	varDefStmt.def = visitVarDef(ctx.varDef());
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
    	return whileStmt;
	}
	@Override public Stmt visitReturnStmt(MxstarParser.ReturnStmtContext ctx) {
    	ReturnStmt returnStmt = new ReturnStmt();
    	returnStmt.location = new TokenLocation(ctx);
    	returnStmt.retExpr = (Expr) ctx.expr().accept(this);
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
    	for (ExprContext c: ctx.expr()) {
    		exprs.add((Expr) c.accept(this));
		}
    	return exprs;
	}
	@Override public Object visitNewExpr(MxstarParser.NewExprContext ctx) {
    	NewExpr newExpr = new NewExpr();
    	newExpr.location = new TokenLocation(ctx);
    	newExpr.typeNode = visitExType(ctx.);
	}

	@Override public Object visitThisExpr(MxstarParser.ThisExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitMemberExpr(MxstarParser.MemberExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitSuffixExpr(MxstarParser.SuffixExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitBinaryExpr(MxstarParser.BinaryExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitFuncCallExpr(MxstarParser.FuncCallExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitSubExpr(MxstarParser.SubExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitVarExpr(MxstarParser.VarExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitPrefixExpr(MxstarParser.PrefixExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitLiteralExpr(MxstarParser.LiteralExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitLogicExpr(MxstarParser.LogicExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitArrExpr(MxstarParser.ArrExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitAssignExpr(MxstarParser.AssignExprContext ctx) { return visitChildren(ctx); }

	@Override public Object visitErrorObject(MxstarParser.ErrorObjectContext ctx) { return visitChildren(ctx); }

	@Override public Object visitArrayObject(MxstarParser.ArrayObjectContext ctx) { return visitChildren(ctx); }

	@Override public Object visitSingleObject(MxstarParser.SingleObjectContext ctx) { return visitChildren(ctx); }

	@Override public Object visitFuncCall(MxstarParser.FuncCallContext ctx) { return visitChildren(ctx); }

	@Override public Object visitConstInt(MxstarParser.ConstIntContext ctx) { return visitChildren(ctx); }

	@Override public Object visitConstString(MxstarParser.ConstStringContext ctx) { return visitChildren(ctx); }

	@Override public Object visitBoolLiteral(MxstarParser.BoolLiteralContext ctx) { return visitChildren(ctx); }
	@Override public Object visitNullLiteral(MxstarParser.NullLiteralContext ctx) { return visitChildren(ctx); }
}

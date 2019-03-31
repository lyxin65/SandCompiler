// Generated from Mxstar.g4 by ANTLR 4.7.1
package Mxstar.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxstarParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxstarParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#paraList}.
	 * @param ctx the parse tree
	 */
	void enterParaList(MxstarParser.ParaListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#paraList}.
	 * @param ctx the parse tree
	 */
	void exitParaList(MxstarParser.ParaListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constructDef}.
	 * @param ctx the parse tree
	 */
	void enterConstructDef(MxstarParser.ConstructDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constructDef}.
	 * @param ctx the parse tree
	 */
	void exitConstructDef(MxstarParser.ConstructDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#para}.
	 * @param ctx the parse tree
	 */
	void enterPara(MxstarParser.ParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#para}.
	 * @param ctx the parse tree
	 */
	void exitPara(MxstarParser.ParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(MxstarParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(MxstarParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#exType}.
	 * @param ctx the parse tree
	 */
	void enterExType(MxstarParser.ExTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#exType}.
	 * @param ctx the parse tree
	 */
	void exitExType(MxstarParser.ExTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxstarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxstarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(MxstarParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(MxstarParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterVarDefStmt(MxstarParser.VarDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitVarDefStmt(MxstarParser.VarDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxstarParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxstarParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPassStmt(MxstarParser.PassStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passStmt}
	 * labeled alternative in {@link MxstarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPassStmt(MxstarParser.PassStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MxstarParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MxstarParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(MxstarParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(MxstarParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpr(MxstarParser.SuffixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpr(MxstarParser.SuffixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallExpr(MxstarParser.FuncCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallExpr(MxstarParser.FuncCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(MxstarParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(MxstarParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(MxstarParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(MxstarParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(MxstarParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(MxstarParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(MxstarParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(MxstarParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(MxstarParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(MxstarParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrExpr(MxstarParser.ArrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrExpr(MxstarParser.ArrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code errorObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void enterErrorObject(MxstarParser.ErrorObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code errorObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void exitErrorObject(MxstarParser.ErrorObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void enterArrayObject(MxstarParser.ArrayObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void exitArrayObject(MxstarParser.ArrayObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void enterSingleObject(MxstarParser.SingleObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleObject}
	 * labeled alternative in {@link MxstarParser#newObject}.
	 * @param ctx the parse tree
	 */
	void exitSingleObject(MxstarParser.SingleObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(MxstarParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(MxstarParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constInt}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterConstInt(MxstarParser.ConstIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constInt}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitConstInt(MxstarParser.ConstIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constString}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterConstString(MxstarParser.ConstStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constString}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitConstString(MxstarParser.ConstStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(MxstarParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(MxstarParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(MxstarParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(MxstarParser.NullLiteralContext ctx);
}
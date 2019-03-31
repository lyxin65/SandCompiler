package Mxstar.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class TokenLocation {
    public final int row;
    public final int col;

    public TokenLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public TokenLocation(Token token) {
        row = token.getLine();
        col = token.getCharPositionInLine();
    }
    public TokenLocation(ParserRuleContext ctx) {
        this(ctx.start);
    }
    public TokenLocation(AstNode node) {
        this.row = node.location.row;
        this.col = node.location.col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}

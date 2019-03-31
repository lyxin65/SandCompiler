package Mxstar.AST;

import Mxstar.Symbol.VarSymbol;
import org.antlr.v4.runtime.Token;

public class ID extends Expr {
    public String name;

    public VarSymbol symbol;

    public ID(Token token) {
        if(token != null) {
            this.name = token.getText();
            this.location = new TokenLocation(token);
        }
    }

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

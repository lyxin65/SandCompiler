package Mxstar.AST;

import org.antlr.v4.runtime.Token;

import static Mxstar.Parser.MxstarParser.*;

public class LiteralExpr extends Expr {
    public String typeName;
    public String value;

    public LiteralExpr(Token token) {
        location = new TokenLocation(token);
        switch(token.getType()) {
            case CINT:
                typeName = "int";
                value = token.getText();
                break;
            case NULL_LITERAL:
                typeName = "null";
                value = token.getText();
                break;
            case BOOL_LITERAL:
                typeName = "bool";
                value = token.getText();
                break;
            default:    //case CSTRING:
                typeName = "string";
                value = escape(token.getText());
        }
    }

    private String escape(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = string.length();
        for(int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if(c == '\\') {
                char nc = string.charAt(i + 1);
                switch(nc) {
                    case 'n':
                        stringBuilder.append('\n');
                        break;
                    case 't':
                        stringBuilder.append('\t');
                        break;
                    case '\\':
                        stringBuilder.append('\\');
                        break;
                    case '"':
                        stringBuilder.append('"');
                        break;
                    default:
                        stringBuilder.append(nc);
                }
                i++;
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}


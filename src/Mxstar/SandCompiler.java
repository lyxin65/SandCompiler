package Mxstar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Mxstar.Parser.*;
import java.io.*;

public class SandCompiler {
    public static void main(String[] args) throws IOException {
//        System.out.println("begin");
        InputStream is = System.in;
        ANTLRInputStream input = new ANTLRInputStream(is);
        MxstarLexer lexer = new MxstarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MxstarParser parser = new MxstarParser(tokens);
        ParseTree tree = parser.program(); // calc is the starting rule

        System.out.println("LISP:");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

//        System.out.println("Visitor:");
//        EvalVisitor evalByVisitor = new EvalVisitor();
//        evalByVisitor.visit(tree);
//        System.out.println();

//        System.out.println("Listener:");
//        ParseTreeWalker walker = new ParseTreeWalker();
//        Evaluator evalByListener = new Evaluator();
//        walker.walk(evalByListener, tree);
    }
}

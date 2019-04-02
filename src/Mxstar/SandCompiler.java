package Mxstar;

import Mxstar.Symbol.SymbolTable;
import Mxstar.Worker.ErrorRecorder;
import Mxstar.Worker.FrontEnd.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Mxstar.Parser.*;
import Mxstar.AST.*;
import Mxstar.Symbol.*;
import java.io.*;

import static java.lang.System.err;
import static java.lang.System.exit;

public class SandCompiler {
    public static void main(String[] args) throws IOException {
//        System.out.println("begin");
        InputStream is = System.in;
        ANTLRInputStream input = new ANTLRInputStream(is);
        MxstarLexer lexer = new MxstarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ErrorRecorder recorder = new ErrorRecorder();

        MxstarParser parser = new MxstarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener(recorder));
        ParseTree tree = parser.program(); // program is the starting rule

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        System.out.println("LISP:");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

        // build AST
        AstBuilder astBuilder = new AstBuilder(recorder);
        astBuilder.visit(tree);
        System.out.println();

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        AstProgram astProgram = astBuilder.getAstProgram();

        // build symbol table
        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder(recorder);
        astProgram.accept(symbolTableBuilder);

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        GlobalSymbolTable globalSymbolTable = symbolTableBuilder.globalSymbolTable;
        SemanticChecker semanticChecker = new SemanticChecker(globalSymbolTable, recorder);

        astProgram.accept(semanticChecker);

        if(recorder.errorOccured()) {
            recorder.printTo(System.out);
            exit(1);
        }


//        System.out.println("Listener:");
//        ParseTreeWalker walker = new ParseTreeWalker();
//        Evaluator evalByListener = new Evaluator();
//        walker.walk(evalByListener, tree);
    }
}

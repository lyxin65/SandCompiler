package Mxstar;

import Mxstar.Worker.ErrorRecorder;
import Mxstar.Worker.FrontEnd.*;
import Mxstar.Worker.BackEnd.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Mxstar.Parser.*;
import Mxstar.AST.*;
import Mxstar.Symbol.*;
import Mxstar.IR.*;
import java.io.*;

import static java.lang.System.err;
import static java.lang.System.exit;

public class SandCompiler {
    public static void main(String[] args) throws IOException {
        // InputStream is = System.in;
        InputStream is = new FileInputStream("program.cpp");
        ANTLRInputStream input = new ANTLRInputStream(is);
        MxstarLexer lexer = new MxstarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RegisterSet.init();

        ErrorRecorder recorder = new ErrorRecorder();

        MxstarParser parser = new MxstarParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener(recorder));
        ParseTree tree = parser.program(); // program is the starting rule

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        // build AST
        AstBuilder astBuilder = new AstBuilder(recorder);
        astBuilder.visit(tree);
        System.out.println();

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        AstProgram ast = astBuilder.getAstProgram();

        // build symbol table
        SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder(recorder);
        ast.accept(symbolTableBuilder);

        if (recorder.errorOccured()) {
            recorder.printTo(System.err);
            exit(1);
        }

        // semantic check
        GlobalSymbolTable globalSymbolTable = symbolTableBuilder.globalSymbolTable;
        SemanticChecker semanticChecker = new SemanticChecker(globalSymbolTable, recorder);

        ast.accept(semanticChecker);

        if (recorder.errorOccured()) {
            recorder.printTo(System.out);
            exit(1);
        }

        // build IR with VirtualRegister
        IRBuilder irBuilder = new IRBuilder(globalSymbolTable);
        ast.accept(irBuilder);
        IRProgram ir = irBuilder.ir;
        
        IRCorrector irCorrector = new IRCorrector();
        ir.accept(irCorrector);

        if (Config.printIR) {
            IRPrinter irPrinter = new IRPrinter();
            irPrinter.showNasm = false;
            irPrinter.showHeader = false;
            irPrinter.visit(ir);
            irPrinter.printTo(System.err);
        }

        //  IR with VirtualRegister -> IR with PhysicalRegister
        SimpleGraphAllocator simpleGraphAllocator = new SimpleGraphAllocator(ir);
        simpleGraphAllocator.run();

        if (Config.printIRAfterAllocator) {
            IRPrinter irPrinter = new IRPrinter();
            irPrinter.showNasm = false;
            irPrinter.showHeader = false;
            irPrinter.visit(ir);
            irPrinter.printTo(System.err);
        }

        // IR with PhysicalRegister and StackFrame
        StackFrameBuilder stackFrameBuilder = new StackFrameBuilder(ir);
        stackFrameBuilder.run();

        if (Config.printIRWithFrame) {
            IRPrinter irPrinter = new IRPrinter();
            irPrinter.showNasm = true;
            irPrinter.showHeader = false;
            irPrinter.visit(ir);
            irPrinter.printTo(System.err);
        }


        if (Config.printToAsmFile) {
            IRPrinter irPrinter = new IRPrinter();
            irPrinter.showNasm = true;
            irPrinter.showHeader = true;
            irPrinter.visit(ir);
            irPrinter.printTo(new PrintStream("program.asm"));
        }

    }
}

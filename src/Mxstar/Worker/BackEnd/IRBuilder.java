package Mxstar.Worker.BackEnd;

import Mxstar.AST.*;
import Mxstar.Config;
import Mxstar.IR.BasicBlock;
import Mxstar.IR.Function;
import Mxstar.IR.IRProgram;
import Mxstar.IR.RegisterSet;
import Mxstar.IR.Instruction.*;
import Mxstar.IR.Operand.*;
import Mxstar.Symbol.*;

import java.util.*;

import static Mxstar.IR.RegisterSet.*;

public class IRBuilder implements IAstVisitor {
    private GlobalSymbolTable gst;

    private BasicBlock curBB;
    private Stack<BasicBlock> loopConditionBB;
    private Stack<BasicBlock> loopAfterBB;
    private Function curFunction;
    private ClassSymbol curClassSymbol;
    private VirtualRegister curThisPointer;

    private HashMap<String, Function> functionMap;
    private HashMap<String, FuncDef> funcDefMap;

    // for short-cut boolean calculate
    private HashMap<Expr, BasicBlock> trueBBMap, falseBBMap;
    private HashMap<Expr, Operand> exprMap;
    private HashMap<Expr, Address> assignMap;

    // for inline optimization
    private boolean inParameter;
    private boolean inClassDef;
    private boolean inInline;
    private LinkedList<HashMap<VarSymbol, VirtualRegister>> inlineVariableRegisterStack;
    private LinkedList<BasicBlock> inlineFuncAfterBBStack;
    private HashMap<FuncSymbol, Integer> operationsCountMap;

    private static Function library_print;
    private static Function library_println;
    private static Function library_getString;
    private static Function library_getInt;
    private static Function library_toString;
    private static Function library_string_length;
    private static Function library_string_substring;
    private static Function library_string_parseInt;
    private static Function library_string_ord;
    private static Function library_hasValue;
    private static Function library_getValue;
    private static Function library_setValue;
    private static Function library_stringConcate;
    private static Function library_stringCompare;
    private static Function external_malloc;
    private static Function library_init;

    public IRProgram ir;

    private void initLibraryFunctions() {
        library_print = new Function(Function.Type.Library, "print", false);
        functionMap.put("print", library_print);
        library_println = new Function(Function.Type.Library, "println", false);
        functionMap.put("println", library_println);
        library_getString = new Function(Function.Type.Library, "getString", false);
        functionMap.put("getString", library_getString);
        library_getInt = new Function(Function.Type.Library, "getInt", true);
        functionMap.put("getInt", library_getInt);
        library_toString = new Function(Function.Type.Library, "toString", true);
        functionMap.put("toString", library_toString);
        library_string_length = new Function(Function.Type.Library, "string_length", true);
        functionMap.put("string.length", library_string_length);
        library_string_substring = new Function(Function.Type.Library, "string_substring", true);
        functionMap.put("string.substring", library_string_substring);
        library_string_parseInt = new Function(Function.Type.Library, "string_parseInt", true);
        functionMap.put("string.parseInt", library_string_parseInt);
        library_string_ord = new Function(Function.Type.Library, "string_ord", true);
        functionMap.put("string.ord", library_string_ord);

        library_stringConcate = new Function(Function.Type.Library, "stringConcate", true);
        library_stringCompare = new Function(Function.Type.Library, "stringCompare", true);
        library_hasValue = new Function(Function.Type.Library, "hasValue", true);
        library_getValue = new Function(Function.Type.Library, "getValue", true);
        library_setValue = new Function(Function.Type.Library, "setValue", true);

        library_init = new Function(Function.Type.Library, "init", true);

        external_malloc = new Function(Function.Type.External, "malloc", true);
    }

    public IRBuilder(GlobalSymbolTable gst) {
        this.gst = gst;
        this.ir = new IRProgram();
        this.loopAfterBB = new Stack<>();
        this.loopConditionBB = new Stack<>();
        this.functionMap = new HashMap<>();
        this.funcDefMap = new HashMap<>();
        this.trueBBMap = new HashMap<>();
        this.falseBBMap = new HashMap<>();
        this.exprMap = new HashMap<>();
        this.assignMap = new HashMap<>();
        this.inParameter = false;
        this.inClassDef = false;
        this.inlineVariableRegisterStack = new LinkedList<>();
        this.inlineFuncAfterBBStack = new LinkedList<>();
        this.operationsCountMap = new HashMap<>();
        initLibraryFunctions();
    }

    private boolean isVoidType(VarType type) {
        return type instanceof BaseType && ((BaseType) type).name.equals("void");
    }

    private boolean intType(VarType type) {
        return type instanceof BaseType && ((BaseType) type).name.equals("int");
    }

    private boolean isBoolType(VarType type) {
        return type instanceof BaseType && ((BaseType) type).name.equals("bool");
    }

    private boolean isStringType(VarType type) {
        return type instanceof ClassType && ((ClassType) type).name.equals("string");
    }

    private void buildInitFunction(AstProgram node) {
        ir.functions.add(library_init);
        curFunction = library_init;
        library_init.usedGV = new HashSet<>(gst.globalInitUsedVars);
        BasicBlock enterBB = new BasicBlock(curFunction, "enterBB");
        curBB = curFunction.enterBB = enterBB;
        for (VarDef vd : node.globalVars) {
            if (vd.init == null) {
                continue;
            }
            assign(vd.init, vd.symbol.virtualRegister);
        }
        curBB.append(new Call(curBB, vrax, functionMap.get("main")));
        curBB.append(new Return(curBB));
        curFunction.leaveBB = curBB;
        curFunction.finishBuild();
    }

    @Override
    public void visit(AstProgram node) {
        // VR for global vars
        for (VarDef vd : node.globalVars) {
            StaticData data = new StaticData(vd.name, Config.REGISTER_WIDTH);
            VirtualRegister vr = new VirtualRegister(vd.name);
            vr.spillPlace = new Memory(data);
            ir.staticData.add(data);
            vd.symbol.virtualRegister = vr;
        }

        // define function
        LinkedList<FuncDef> funcDefs = new LinkedList<>();
        funcDefs.addAll(node.functions);
        for (ClassDef cd : node.classes) {
            if (cd.constructor != null) {
                funcDefs.add(cd.constructor);
            }
            funcDefs.addAll(cd.methods);
        }
        for (FuncDef fd : funcDefs) {
            funcDefMap.put(fd.symbol.name, fd);
        }
        for (FuncDef fd : funcDefs) {
            if (functionMap.containsKey(fd.symbol.name)) { // lib functions
                continue;
            }
            functionMap.put(fd.symbol.name, new Function(Function.Type.UserDefined, fd.symbol.name, !isVoidType(fd.symbol.returnType)));
        }
        for (FuncDef fd : node.functions) {
            fd.accept(this);
        }
        for (ClassDef cd : node.classes) {
            cd.accept(this);
        }

        for (Function func : functionMap.values()) {
            if (func.type == Function.Type.UserDefined) {
                func.finishBuild();
            }
        }

        // add back opt
        // for (FuncDef fd: node.functions) {
        // if (deserveBackOptimization(fd)) {
        // addValueBackOptimizeCode(fd);
        // }
        // }

        buildInitFunction(node);
    }

    /*
     * private boolean deserveBackOptimization(FuncDef funcDef) { Function function
     * = functionMap.get(funcDef.symbol.name); if (funcDef.name.equals("func"))
     * return false; // hack it! return Config.useBackupOptimization &&
     * funcDef.symbol.isGlobalFunction && !function.hasOutput &&
     * function.recursiveUsedGlobalVariables.isEmpty() && function.parameters.size()
     * == 1 && intType(funcDef.parameters.get(0).symbol.type); }
     * 
     * private void addValueBackOptimizeCode(FuncDef funcDef) { if
     * (!deserveBackOptimization(funcDef)) return; Function function =
     * functionMap.get(funcDef.symbol.name); BasicBlock bb = new
     * BasicBlock(function, "backopt_entry"); BasicBlock hitBB = new
     * BasicBlock(function, "backopt_hit"); VirtualRegister argu = new
     * VirtualRegister(""); bb.append(new Push(bb, vrdi)); bb.append(new Move(bb,
     * argu, vrdi)); bb.append(new Call(bb, vrax, library_hasValue, new
     * FunctionAddress(function), argu)); bb.append(new Pop(bb, vrdi));
     * bb.append(new CJump(bb, vrax, CJump.CompareOp.NE, new Immediate(0), hitBB,
     * function.enterBB)); hitBB.append(new Call(hitBB, vrax, library_getValue, new
     * FunctionAddress(function), argu)); hitBB.append(new Jump(hitBB,
     * function.leaveBB)); BasicBlock leaveBB = function.leaveBB;
     * leaveBB.prepend(new Call(leaveBB, vrax, library_setValue, new
     * FunctionAddress(function), argu, vrax)); function.enterBB = bb;
     * function.finishBuild(); }
     */

    @Override
    public void visit(Definition node) {
        assert false;
    }

    @Override
    public void visit(ClassDef node) {
        curClassSymbol = node.symbol;
        inClassDef = true;
        if (node.constructor != null) {
            node.constructor.accept(this);
        }
        for (FuncDef fd : node.methods) {
            fd.accept(this);
        }
        inClassDef = false;
    }

    @Override
    public void visit(FuncDef node) {
        curFunction = functionMap.get(node.symbol.name);
        curBB = curFunction.enterBB = new BasicBlock(curFunction, "enter");

        /**
         * Processes in a function: save arguments in physical registers to virtual
         * registers load global variables in memory to virtual registers function body
         * save global variables in virtual registers to memory
         *
         * callee and caller register saving code are added in StackFrameBuilder
         */

        /** declare virtual registers for parameters */
        if (inClassDef) {
            VirtualRegister vthis = new VirtualRegister("");
            curFunction.parameters.add(vthis);
            curThisPointer = vthis;
        }
        inParameter = true;
        for (VarDef vd : node.parameters) {
            vd.accept(this);
        }
        inParameter = false;

        /* copy the arguments in physical registers and memory to virtual registers */
        for (int i = 0; i < curFunction.parameters.size(); i++) {
            if (i < 6) {
                curBB.append(new Move(curBB, curFunction.parameters.get(i), RegisterSet.vargs.get(i)));
            } else {
                curBB.append(new Move(curBB, curFunction.parameters.get(i), curFunction.parameters.get(i).spillPlace));
            }
        }

        /* load global variable at first */
        for (VarSymbol vs : node.symbol.usedGV) {
            curBB.append(new Move(curBB, vs.virtualRegister, vs.virtualRegister.spillPlace));
        }

        /* add default return */
        for (Stmt stmt : node.body) {
            stmt.accept(this);
        }
        if (!(curBB.tail instanceof Return)) {
            if (isVoidType(node.symbol.returnType)) {
                curBB.append(new Return(curBB));
            } else {
                curBB.append(new Move(curBB, vrax, new Immediate(0)));
                curBB.append(new Return(curBB));
            }
        }

        /* gather all return instructions to one */
        LinkedList<Return> returnInsts = new LinkedList<>();
        for (BasicBlock bb : curFunction.basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                if (inst instanceof Return)
                    returnInsts.add((Return) inst);
            }
        }

        BasicBlock leaveBB = new BasicBlock(curFunction, "leaveBB");
        for (Return retInst : returnInsts) {
            retInst.prepend(new Jump(retInst.bb, leaveBB));
            retInst.remove();
        }
        leaveBB.append(new Return(leaveBB));
        curFunction.leaveBB = leaveBB;

        /* save global variable */
        IRInstruction retInst = curFunction.leaveBB.tail;
        for (VarSymbol vs : node.symbol.usedGV) {
            retInst.prepend(new Move(retInst.bb, vs.virtualRegister.spillPlace, vs.virtualRegister));
        }

        functionMap.put(node.symbol.name, curFunction);
        ir.functions.add(curFunction);
    }

    private void boolAssign(Expr expr, Address vr) {
        BasicBlock trueBB = new BasicBlock(curFunction, "true");
        BasicBlock falseBB = new BasicBlock(curFunction, "false");
        BasicBlock mergeBB = new BasicBlock(curFunction, "merge");
        trueBBMap.put(expr, trueBB);
        falseBBMap.put(expr, falseBB);
        expr.accept(this);
        trueBB.append(new Move(trueBB, vr, new Immediate(1)));
        falseBB.append(new Move(falseBB, vr, new Immediate(0)));
        trueBB.append(new Jump(trueBB, mergeBB));
        falseBB.append(new Jump(falseBB, mergeBB));
        curBB = mergeBB;
    }

    private void assign(Expr expr, Address vr) {
        if (isBoolType(expr.type)) {
            boolAssign(expr, vr);
        } else {
            assignMap.put(expr, vr);
            expr.accept(this);
            Operand res = exprMap.get(expr);
            if (res != vr) {
                curBB.append(new Move(curBB, vr, res));
            }
        }
    }

    @Override
    public void visit(VarDef node) {
        assert curFunction != null;
        VirtualRegister vr = new VirtualRegister(node.name);
        if (inInline) {
            inlineVariableRegisterStack.getLast().put(node.symbol, vr);
            if (node.init != null) {
                assign(node.init, vr);
            }
        } else {
            if (inParameter) {
                if (curFunction.parameters.size() >= 6) {
                    vr.spillPlace = new StackSlot(vr.hint);
                }
                curFunction.parameters.add(vr);
            }
            node.symbol.virtualRegister = vr;
            if (node.init != null) {
                assign(node.init, vr);
            }
        }
    }

    @Override
    public void visit(TypeNode node) {
        assert false;
    }

    @Override
    public void visit(ArrayTypeNode node) {
        assert false;
    }

    @Override
    public void visit(BaseTypeNode node) {
        assert false;
    }

    @Override
    public void visit(ClassTypeNode node) {
        assert false;
    }

    @Override
    public void visit(Stmt node) {
        assert false;
    }

    @Override
    public void visit(ForStmt node) {
        if (node.initStmt != null) {
            node.initStmt.accept(this);
        }
        BasicBlock bodyBB = new BasicBlock(curFunction, "forBoby");
        BasicBlock afterBB = new BasicBlock(curFunction, "afterBoby");
        BasicBlock condBB = new BasicBlock(curFunction, "forCond");
        BasicBlock updateBB = new BasicBlock(curFunction, "forUpdate");
        curBB.append(new Jump(curBB, condBB));
        loopConditionBB.push(updateBB);
        loopAfterBB.push(condBB);
        if (node.condition != null) {
            trueBBMap.put(node.condition, bodyBB);
            falseBBMap.put(node.condition, afterBB);
            curBB = condBB;
            node.condition.accept(this);
        } else {
            // always true
        }
        curBB = bodyBB;
        node.body.accept(this);
        curBB.append(new Jump(curBB, updateBB));
        if (node.updateStmt != null) {
            curBB = updateBB;
            node.updateStmt.accept(this);
            curBB.append(new Jump(curBB, condBB));
        }
        curBB = afterBB;
        loopAfterBB.pop();
        loopConditionBB.pop();
    }

    @Override
    public void visit(WhileStmt node) {
        BasicBlock condBB = new BasicBlock(curFunction, "whileCondBB");
        BasicBlock bodyBB = new BasicBlock(curFunction, "whileBodyBB");
        BasicBlock afterBB = new BasicBlock(curFunction, "whileAfterBB");
        curBB.append(new Jump(curBB, condBB));
        loopConditionBB.push(condBB);
        loopAfterBB.push(afterBB);
        curBB = condBB;
        trueBBMap.put(node.condition, bodyBB);
        falseBBMap.put(node.condition, afterBB);
        node.condition.accept(this);
        curBB = bodyBB;
        node.body.accept(this);
        curBB.append(new Jump(curBB, condBB));
        curBB = afterBB;
        loopAfterBB.pop();
        loopConditionBB.pop();
    }

    @Override
    public void visit(IfStmt node) {
        BasicBlock thenBB = new BasicBlock(curFunction, "ifThenBB");
        BasicBlock afterBB = new BasicBlock(curFunction, "ifAfterBB");
        BasicBlock elseBB = node.elseStmt == null ? afterBB : new BasicBlock(curFunction, "ifElseBB");
        trueBBMap.put(node.condition, thenBB);
        falseBBMap.put(node.condition, elseBB);
        node.condition.accept(this);
        curBB = thenBB;
        node.thenStmt.accept(this);
        curBB.append(new Jump(curBB, afterBB));
        if (node.elseStmt != null) {
            curBB = elseBB;
            node.elseStmt.accept(this);
            curBB.append(new Jump(curBB, afterBB));
        }
        curBB = afterBB;
    }

    @Override
    public void visit(ContinueStmt node) {
        curBB.append(new Jump(curBB, loopConditionBB.peek()));
    }

    @Override
    public void visit(BreakStmt node) {
        curBB.append(new Jump(curBB, loopAfterBB.peek()));
    }

    @Override
    public void visit(ReturnStmt node) {
        if (node.retExpr != null) {
            if (isBoolType(node.retExpr.type)) {
                boolAssign(node.retExpr, vrax);
            } else {
                node.retExpr.accept(this);
                curBB.append(new Move(curBB, vrax, exprMap.get(node.retExpr)));
            }
        }
        if (inInline) {
            curBB.append(new Jump(curBB, inlineFuncAfterBBStack.getLast()));
        } else {
            curBB.append(new Return(curBB));
        }
    }

    @Override
    public void visit(BlockStmt node) {
        for (Stmt stmt : node.statements) {
            stmt.accept(this);
        }
        assert false;
    }

    @Override
    public void visit(VarDefStmt node) {
        node.varDef.accept(this);
    }

    @Override
    public void visit(ExprStmt node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(Expr node) {
        assert false;
    }

    @Override
    public void visit(ID node) {
        Operand operand;
        if (node.name.equals("this")) { // this pointer
            operand = curThisPointer;
        } else if (node.symbol.isClassField) { // this.xxx
            String fieldName = node.name;
            int offset = curClassSymbol.classSymbolTable.getVarOffset(fieldName);
            operand = new Memory(curThisPointer, new Immediate(offset));
        } else {
            if (inInline) {
                operand = inlineVariableRegisterStack.getLast().get(node.symbol);
            } else {
                operand = node.symbol.virtualRegister;
            }
            if (node.symbol.isGlobalVar) {
                curFunction.usedGV.add(node.symbol);
            }
        }
        if (trueBBMap.containsKey(node)) {
            curBB.append(new CJump(curBB, operand, CJump.CompareOp.NE, new Immediate(0), trueBBMap.get(node),
                    falseBBMap.get(node)));
        } else {
            exprMap.put(node, operand);
        }
    }

    @Override
    public void visit(LiteralExpr node) {
        Operand operand;
        switch (node.typeName) {
        case "int":
            operand = new Immediate(Integer.valueOf(node.value));
            break;
        case "string": // TODO
            StaticData data = new StaticData("staticString", node.value);
            ir.staticData.add(data);
            operand = data;
            break;
        case "bool":
            curBB.append(new Jump(curBB, node.value.equals("true") ? trueBBMap.get(node) : falseBBMap.get(node)));
            return;
        case "null":
            operand = new Immediate(0);
            break;
        default:
            assert false;
            operand = null;
            break;
        }
        exprMap.put(node, operand);
    }

    @Override
    public void visit(ArrayExpr node) {
        node.address.accept(this);
        Operand baseAddr = exprMap.get(node.address);
        node.index.accept(this);
        Operand index = exprMap.get(node.index);

        VirtualRegister base;
        if (baseAddr instanceof Register) {
            base = (VirtualRegister) baseAddr;
        } else {
            base = new VirtualRegister("");
            curBB.append(new Move(curBB, base, baseAddr));
        }
        Memory memory;

        if (index instanceof Immediate) {
            memory = new Memory(base,
                    new Immediate(((Immediate) index).value * Config.REGISTER_WIDTH + Config.REGISTER_WIDTH));
        } else if (index instanceof Register) {
            memory = new Memory(base, (Register) index, Config.REGISTER_WIDTH, new Immediate(Config.REGISTER_WIDTH));
        } else if (index instanceof Memory) {
            VirtualRegister vr = new VirtualRegister("");
            curBB.append(new Move(curBB, vr, index));
            memory = new Memory(base, vr, Config.REGISTER_WIDTH, new Immediate(Config.REGISTER_WIDTH));
        } else {
            memory = null;
            assert false;
        }
        if (trueBBMap.containsKey(node)) {
            curBB.append(new CJump(curBB, memory, CJump.CompareOp.NE, new Immediate(0), trueBBMap.get(node),
                    falseBBMap.get(node)));
        } else {
            exprMap.put(node, memory);
        }
    }

    @Override
    public void visit(FuncCallExpr node) {
        LinkedList<Operand> args = new LinkedList<>();
        if (!node.funcSymbol.isGlobalFunc) {
            args.add(curThisPointer);
        }
        for (int i = 0; i < node.arguments.size(); i++) {
            Expr expr = node.arguments.get(i);
            expr.accept(this);
            args.add(exprMap.get(expr));
        }
        if (deserveInline(node.funcSymbol.name)) {
            doInline(node.funcSymbol.name, args);
        } else {
            curBB.append(new Call(curBB, vrax, functionMap.get(node.funcSymbol.name), args));
        }
        if (trueBBMap.containsKey(node)) {
            curBB.append(new CJump(curBB, vrax, CJump.CompareOp.NE, new Immediate(0), trueBBMap.get(node),
                    falseBBMap.get(node)));
        } else {
            if (!isVoidType(node.funcSymbol.returnType)) {
                VirtualRegister vr = new VirtualRegister("");
                curBB.append(new Move(curBB, vr, vrax));
                exprMap.put(node, vr);
            }
        }
    }

    private Operand allocateArray(LinkedList<Operand> dims, int baseBytes, Function constructor) {
        if (dims.size() == 0) {
            if (baseBytes == 0) {
                return new Immediate(0);
            } else {
                VirtualRegister retAddr = new VirtualRegister("");
                curBB.append(new Call(curBB, vrax, external_malloc, new Immediate(baseBytes)));
                curBB.append(new Move(curBB, retAddr, vrax));
                if (constructor != null) {
                    curBB.append(new Call(curBB, vrax, constructor, retAddr));
                } else {
                    if (baseBytes == Config.REGISTER_WIDTH) {
                        curBB.append(new Move(curBB, new Memory(retAddr), new Immediate(0)));
                    } else if (baseBytes == Config.REGISTER_WIDTH * 2) {
                        curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.ADD, retAddr,
                                new Immediate(Config.REGISTER_WIDTH)));
                        curBB.append(new Move(curBB, new Memory(retAddr), new Immediate(0)));
                        curBB.append(new BinaryInst(curBB, BinaryInst.BinaryOp.SUB, retAddr,
                                new Immediate(Config.REGISTER_WIDTH)));
                    } else {
                    }
                }
                return retAddr;
            }
        } else {
            VirtualRegister addr = new VirtualRegister("");
            VirtualRegister size = new VirtualRegister("");
            VirtualRegister bytes = new VirtualRegister("");
            curBB.append(new Move(curBB, size, dims.get(0)));
            curBB.append(new Lea(curBB, bytes, new Memory(size, Config.REGISTER_WIDTH,
                    new Immediate(Config.REGISTER_WIDTH))));
            curBB.append(new Call(curBB, vrax, external_malloc, bytes));
            curBB.append(new Move(curBB, addr, vrax));
            curBB.append(new Move(curBB, new Memory(addr), size));

            BasicBlock condBB = new BasicBlock(curFunction, "allocCondBB");
            BasicBlock bodyBB = new BasicBlock(curFunction, "allocBodyBB");
            BasicBlock afterBB = new BasicBlock(curFunction, "allocAfterBB");
            curBB.append(new Jump(curBB, condBB));
            condBB.append(new CJump(condBB, size, CJump.CompareOp.G, new Immediate(0), bodyBB, afterBB));
            curBB = bodyBB;
            // ???

            if (dims.size() == 1) {
                Operand pointer = allocateArray(new LinkedList<>(), baseBytes, constructor);
                curBB.append(new Move(curBB, new Memory(addr, size, Config.REGISTER_WIDTH), pointer));
            } else {
                LinkedList<Operand> remainDims = new LinkedList<>();
                for (int i = 1; i < dims.size(); i++) {
                    remainDims.add(dims.get(i));
                }
                Operand pointer = allocateArray(remainDims, baseBytes, constructor);
                curBB.append(new Move(curBB, new Memory(addr, size, Config.REGISTER_WIDTH), pointer));
            }
            curBB.append(new UnaryInst(curBB, UnaryInst.UnaryOp.DEC, size));
            curBB.append(new Jump(curBB, condBB));
            curBB = afterBB;
            return addr;
        }
    }

    @Override
    public void visit(NewExpr node) {
        Function constructor;
        if (node.restDimension == 0) {
            if (node.type instanceof ClassType) {
                ClassType classType = (ClassType) node.type;
                if (classType.name.equals("string")) {
                    constructor = null;
                } else {
                    FuncSymbol fs = classType.symbol.classSymbolTable.getFuncSymbol(classType.name);
                    if (fs == null) {
                        constructor = null;
                    } else {
                        constructor = functionMap.get(fs.name);
                    }
                }
            } else {
                constructor = null;
            }
        } else {
            constructor = null;
        }
        LinkedList<Operand> dims = new LinkedList<>();
        for (Expr expr : node.exprDimensions) {
            expr.accept(this);
            dims.add(exprMap.get(expr));
        }
        if (node.restDimension > 0 || node.typeNode instanceof BaseTypeNode) {
            Operand pointer = allocateArray(dims, 0, null);
            exprMap.put(node, pointer);
        } else {
            int bytes = node.type instanceof ClassType && ((ClassType) node.type).name.equals("string")
                    ? Config.REGISTER_WIDTH
                    : node.type.getBytes();
            Operand pointer = allocateArray(dims, bytes, constructor);
            exprMap.put(node, pointer);
        }
    }

    private int countOperationsLE(List<Expr> exprs) {
        int count = 0;
        for (Expr expr : exprs)
            count += countOperations(expr);
        return count;
    }

    private int countOperations(Expr expr) {
        if (expr == null)
            return 0;
        int count = 0;
        if (expr instanceof ArrayExpr) {
            count += countOperations(((ArrayExpr) expr).address);
            count += countOperations(((ArrayExpr) expr).index);
        } else if (expr instanceof FuncCallExpr) {
            count += countOperationsLE(((FuncCallExpr) expr).arguments);
        } else if (expr instanceof NewExpr) {
            count += countOperationsLE(((NewExpr) expr).exprDimensions);
        } else if (expr instanceof UnaryExpr) {
            count += countOperations(((UnaryExpr) expr).expr);
            count += 1;
        } else if (expr instanceof MemberExpr) {
            if (((MemberExpr) expr).fieldAccess != null)
                count += 1;
            else
                count += countOperations(((MemberExpr) expr).methodCall);
        } else if (expr instanceof BinaryExpr) {
            count += countOperations(((BinaryExpr) expr).lhs);
            count += countOperations(((BinaryExpr) expr).rhs);
        } else if (expr instanceof AssignExpr) {
            count += countOperations(((AssignExpr) expr).lhs);
            count += countOperations(((AssignExpr) expr).rhs);
        } else {
            count += 1;
        }
        return count;
    }

    private int countOperations(Stmt stmt) {
        if (stmt == null)
            return 0;
        int count = 0;
        if (stmt instanceof IfStmt) {
            count += countOperations(((IfStmt) stmt).thenStmt);
            count += countOperations(((IfStmt) stmt).elseStmt);
        } else if (stmt instanceof WhileStmt) {
            count += countOperations(((WhileStmt) stmt).condition);
            count += countOperations(((WhileStmt) stmt).body);
        } else if (stmt instanceof ForStmt) {
            count += countOperations(((ForStmt) stmt).initStmt);
            count += countOperations(((ForStmt) stmt).condition);
            count += countOperations(((ForStmt) stmt).updateStmt);
            count += countOperations(((ForStmt) stmt).body);
        } else if (stmt instanceof BlockStmt) {
            count += countOperations(((BlockStmt) stmt).statements);
        } else if (stmt instanceof ReturnStmt) {
            count += countOperations(((ReturnStmt) stmt).retExpr);
        } else if (stmt instanceof ExprStmt) {
            count += countOperations(((ExprStmt) stmt).expr);
        } else if (stmt instanceof VarDefStmt) {
            count += countOperations(((VarDefStmt) stmt).varDef.init);
        } else {
            count += 1;
        }
        return count;
    }

    private int countOperations(List<Stmt> stmts) {
        int count = 0;
        for (Stmt s : stmts)
            count += countOperations(s);
        return count;
    }

    private boolean deserveInline(String name) {
        if (!Config.useInlineOptimization)
            return false;
        if (!funcDefMap.containsKey(name)) // library function
            return false;
        FuncDef funcDef = funcDefMap.get(name);
        if (!funcDef.symbol.usedGV.isEmpty()) // used global variable
            return false;
        if (!funcDef.symbol.isGlobalFunc) // is a method
            return false;
        List<Stmt> body = funcDef.body;
        if (!operationsCountMap.containsKey(funcDef.symbol))
            operationsCountMap.put(funcDef.symbol, countOperations(body));
        if (operationsCountMap.get(funcDef.symbol) >= Config.inlineOperationsThreshold)
            return false;
        if (inlineVariableRegisterStack.size() >= Config.inlineMaxDepth)
            return false;
        return true;
    }

    private void doInline(String name, LinkedList<Operand> arguments) {
        FuncDef funcDef = funcDefMap.get(name);
        inlineVariableRegisterStack.addLast(new HashMap<>());
        LinkedList<VirtualRegister> vrArguments = new LinkedList<>();
        for (Operand op : arguments) {
            VirtualRegister vr = new VirtualRegister("");
            curBB.append(new Move(curBB, vr, op));
            vrArguments.add(vr);
        }
        if (funcDef.parameters != null) {
            for (int i = 0; i < funcDef.parameters.size(); i++) {
                inlineVariableRegisterStack.getLast().put(funcDef.parameters.get(i).symbol, vrArguments.get(i));
            }
        }
        BasicBlock inlineFuncBodyBB = new BasicBlock(curFunction, "inlineFuncBodyBB");
        BasicBlock inlineFuncAfterBB = new BasicBlock(curFunction, "inlineFuncAfterBB");
        inlineFuncAfterBBStack.addLast(inlineFuncAfterBB);

        curBB.append(new Jump(curBB, inlineFuncBodyBB));
        curBB = inlineFuncBodyBB;
        VirtualRegister result = null;

        boolean oldInline = inInline;
        inInline = true;

        for (Stmt st : funcDef.body)
            st.accept(this);

        if (!(curBB.tail instanceof Jump))
            curBB.append(new Jump(curBB, inlineFuncAfterBB));

        curBB = inlineFuncAfterBB;

        inlineFuncAfterBBStack.removeLast();
        inlineVariableRegisterStack.removeLast();
        inInline = oldInline;
    }

    @Override
    public void visit(MemberExpr node) {
        VirtualRegister baseAddr = new VirtualRegister("");
        node.object.accept(this);
        curBB.append(new Move(curBB, baseAddr, exprMap.get(node.object)));

        if (node.object.type instanceof ArrayType) { // for size
            exprMap.put(node, new Memory(baseAddr));
        } else if (node.object.type instanceof ClassType) {
            ClassType classType = (ClassType) node.object.type;
            Operand operand;
            if (node.fieldAccess != null) {
                String fieldName = node.fieldAccess.name;
                int offset = classType.symbol.classSymbolTable.getVarOffset(fieldName);
                operand = new Memory(baseAddr, new Immediate(offset));
            } else {
                Function function = functionMap.get(node.methodCall.funcSymbol.name);
                LinkedList<Operand> arguments = new LinkedList<>();
                arguments.add(baseAddr);
                for (Expr expr : node.methodCall.arguments) {
                    expr.accept(this);
                    Operand arg = exprMap.get(expr);
                    arguments.add(arg);
                }
                if (deserveInline(node.methodCall.funcSymbol.name)) {
                    doInline(node.methodCall.funcSymbol.name, arguments);
                } else {
                    curBB.append(new Call(curBB, vrax, function, arguments));
                }
                if (!isVoidType(node.methodCall.funcSymbol.returnType)) {
                    VirtualRegister retValue = new VirtualRegister("");
                    curBB.append(new Move(curBB, retValue, vrax));
                    operand = retValue;
                } else {
                    operand = null;
                }
            }
            if (trueBBMap.containsKey(node)) {
                curBB.append(new CJump(curBB, operand, CJump.CompareOp.NE, new Immediate(0), trueBBMap.get(node),
                        falseBBMap.get(node)));
            } else {
                exprMap.put(node, operand);
            }
        } else {
            assert false;
        }
    }

    @Override
    public void visit(UnaryExpr node) {
        if (node.op.equals("!")) {
            trueBBMap.put(node.expr, falseBBMap.get(node));
            falseBBMap.put(node.expr, trueBBMap.get(node));
            node.expr.accept(this);
            return;
        }
        node.expr.accept(this);
        Operand operand = exprMap.get(node.expr);
        switch (node.op) {
        case "v++":
        case "v--": {
            assert operand instanceof Address;
            VirtualRegister oldValue = new VirtualRegister("");
            curBB.append(new Move(curBB, oldValue, operand));
            curBB.append(new UnaryInst(curBB, node.op.equals("v++") ? UnaryInst.UnaryOp.INC : UnaryInst.UnaryOp.DEC,
                    (Address) operand));
            exprMap.put(node, oldValue);
        }
            break;
        case "++v":
        case "--v":
            assert operand instanceof Address;
            curBB.append(new UnaryInst(curBB, node.op.equals("++v") ? UnaryInst.UnaryOp.INC : UnaryInst.UnaryOp.DEC,
                    (Address) operand));
            exprMap.put(node, operand);
            break;
        case "+":
            exprMap.put(node, operand);
            break;
        case "-":
        case "~": {
            VirtualRegister vr = new VirtualRegister("");
            curBB.append(new Move(curBB, vr, operand));
            curBB.append(new UnaryInst(curBB, node.op.equals("-") ? UnaryInst.UnaryOp.NEG : UnaryInst.UnaryOp.NOT, vr));
            exprMap.put(node, vr);
        }
            break;
        default:
            assert false;
        }
    }

    private Operand doStringConcate(Expr lhs, Expr rhs) {
        Address result = new VirtualRegister("");
        lhs.accept(this);
        Operand olhs = exprMap.get(lhs);
        rhs.accept(this);
        Operand orhs = exprMap.get(rhs);
        VirtualRegister vr;
        if (olhs instanceof Memory && !(olhs instanceof StackSlot)) {
            vr = new VirtualRegister("");
            curBB.append(new Move(curBB, vr, olhs));
            olhs = vr;
        }
        if (orhs instanceof Memory && !(orhs instanceof StackSlot)) {
            vr = new VirtualRegister("");
            curBB.append(new Move(curBB, vr, orhs));
            orhs = vr;
        }
        curBB.append(new Call(curBB, vrax, library_stringConcate, olhs, orhs));
        curBB.append(new Move(curBB, result, vrax));
        return result;
    }

    private Operand doArithmeticBinary(String op, Address dest, Expr lhs, Expr rhs) {
        BinaryInst.BinaryOp bop = null;
        boolean isSpecial = false;
        boolean isRevertable = false;
        switch (op) {
        case "*":
            bop = BinaryInst.BinaryOp.MUL;
            isSpecial = true;
            break;
        case "/":
            bop = BinaryInst.BinaryOp.DIV;
            isSpecial = true;
            break;
        case "%":
            bop = BinaryInst.BinaryOp.MOD;
            isSpecial = true;
            break;
        case "+":
            bop = BinaryInst.BinaryOp.ADD;
            isRevertable = true;
            break;
        case "-":
            bop = BinaryInst.BinaryOp.SUB;
            break;
        case ">>":
            bop = BinaryInst.BinaryOp.SAR;
            break;
        case "<<":
            bop = BinaryInst.BinaryOp.SAL;
            break;
        case "&":
            bop = BinaryInst.BinaryOp.AND;
            isRevertable = true;
            break;
        case "|":
            bop = BinaryInst.BinaryOp.OR;
            isRevertable = true;
            break;
        case "^":
            bop = BinaryInst.BinaryOp.XOR;
            isRevertable = true;
            break;
        }
        lhs.accept(this);
        Operand olhs = exprMap.get(lhs);
        rhs.accept(this);
        Operand orhs = exprMap.get(rhs);
        Address result = new VirtualRegister("");

        if (!isSpecial) {
            if (olhs == dest) {
                result = dest;
                if (op.equals("<<") || op.equals(">>")) {
                    curBB.append(new Move(curBB, vrcx, orhs));
                    curBB.append(new BinaryInst(curBB, bop, result, vrcx));
                } else {
                    curBB.append(new BinaryInst(curBB, bop, result, orhs));
                }
            } else if (isRevertable && orhs == dest) {
                result = dest;
                curBB.append(new BinaryInst(curBB, bop, result, olhs));
            } else {
                if (op.equals("<<") || op.equals(">>")) {
                    curBB.append(new Move(curBB, result, olhs));
                    curBB.append(new Move(curBB, vrcx, orhs));
                    curBB.append(new BinaryInst(curBB, bop, result, vrcx));
                } else {
                    curBB.append(new Move(curBB, result, olhs));
                    curBB.append(new BinaryInst(curBB, bop, result, orhs));
                }
            }
        } else {
            if (op.equals("*")) {
                curBB.append(new Move(curBB, vrax, olhs));
                curBB.append(new BinaryInst(curBB, bop, null, orhs));
                curBB.append(new Move(curBB, result, vrax));
            } else { // op.equals("/") || op.equals("%")
                curBB.append(new Move(curBB, vrax, olhs));
                curBB.append(new Cdq(curBB));
                curBB.append(new BinaryInst(curBB, bop, null, orhs));
                if (op.equals("/")) {
                    curBB.append(new Move(curBB, result, vrax));
                } else {
                    curBB.append(new Move(curBB, result, vrdx));
                }
            }
        }
        return result;
    }

    private void doLogicalBinary(String op, Expr lhs, Expr rhs, BasicBlock trueBB, BasicBlock falseBB) {
        BasicBlock checkSecondBB = new BasicBlock(curFunction, "secondConditionBB");
        if (op.equals("&&")) {
            falseBBMap.put(lhs, falseBB);
            trueBBMap.put(lhs, checkSecondBB);
        } else {
            trueBBMap.put(lhs, trueBB);
            falseBBMap.put(lhs, checkSecondBB);
        }
        lhs.accept(this);
        curBB = checkSecondBB;
        trueBBMap.put(rhs, trueBB);
        falseBBMap.put(rhs, falseBB);
        rhs.accept(this);
    }

    private void doRelationBinary(String op, Expr lhs, Expr rhs, BasicBlock trueBB, BasicBlock falseBB) {
        lhs.accept(this);
        Operand olhs = exprMap.get(lhs);
        rhs.accept(this);
        Operand orhs = exprMap.get(rhs);

        CJump.CompareOp cop = null;
        switch (op) {
        case ">":
            cop = CJump.CompareOp.G;
            break;
        case "<":
            cop = CJump.CompareOp.L;
            break;
        case ">=":
            cop = CJump.CompareOp.GE;
            break;
        case "<=":
            cop = CJump.CompareOp.LE;
            break;
        case "==":
            cop = CJump.CompareOp.E;
            break;
        case "!=":
            cop = CJump.CompareOp.NE;
            break;
        }
        if (lhs.type instanceof ClassType && ((ClassType) lhs.type).name.equals("string")) { // str (<|<=|>|>=|==|!=)
                                                                                             // str
            VirtualRegister scr = new VirtualRegister("");
            curBB.append(new Call(curBB, vrax, library_stringCompare, olhs, orhs));
            curBB.append(new Move(curBB, scr, vrax));
            curBB.append(new CJump(curBB, scr, cop, new Immediate(0), trueBB, falseBB));
        } else {
            if (olhs instanceof Memory && orhs instanceof Memory) {
                VirtualRegister vr = new VirtualRegister("");
                curBB.append(new Move(curBB, vr, olhs));
                olhs = vr;
            }
            curBB.append(new CJump(curBB, olhs, cop, orhs, trueBB, falseBB));
        }
    }

    @Override
    public void visit(BinaryExpr node) {
        switch (node.op) {
        case "*":
        case "/":
        case "%":
        case "+":
        case "-":
        case ">>":
        case "<<":
        case "&":
        case "|":
        case "^":
            if (node.op.equals("+") && isStringType(node.type)) {
                exprMap.put(node, doStringConcate(node.lhs, node.rhs));
            } else {
                exprMap.put(node, doArithmeticBinary(node.op, assignMap.get(node), node.lhs, node.rhs));
            }
            break;
        case "<":
        case ">":
        case "==":
        case ">=":
        case "<=":
        case "!=":
            doRelationBinary(node.op, node.lhs, node.rhs, trueBBMap.get(node), falseBBMap.get(node));
            break;
        case "&&":
        case "||":
            doLogicalBinary(node.op, node.lhs, node.rhs, trueBBMap.get(node), falseBBMap.get(node));
            break;
        default:
            assert false;
        }
    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        Operand lvalue = exprMap.get(node.lhs);
        assert lvalue instanceof Address;
        assign(node.rhs, (Address) lvalue);
    }

    @Override
    public void visit(EmptyStmt node) {
        return;
    }


    @Override
    public void visit(PassStmt node) {
        return;
    }

    @Override
    public void visit(PrefixExpr node) {
        assert false;
    }

    @Override
    public void visit(SuffixExpr node) {
        assert false;
    }
}

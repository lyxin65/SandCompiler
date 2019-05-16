package Mxstar.Worker.FrontEnd;

import Mxstar.AST.*;
import Mxstar.Symbol.*;
import Mxstar.Worker.ErrorRecorder;

public class SemanticChecker implements IAstVisitor {
    GlobalSymbolTable gst;
    ErrorRecorder recorder;
    FuncSymbol curFunc;
    int loopCount;

    public SemanticChecker(GlobalSymbolTable gst, ErrorRecorder recorder) {
        this.gst = gst;
        this.recorder = recorder;
        this.loopCount = 0;
    }

    @Override
    public void visit(AstProgram node) {
        for (VarDef d: node.globalVars) {
            d.accept(this);
        }
        for (FuncDef d: node.functions) {
            d.accept(this);
        }
        for (ClassDef d: node.classes) {
            d.accept(this);
        }
        FuncSymbol mainfunc = gst.getFuncSymbol("main");
        if (mainfunc == null) {
            recorder.addRecord(node.location, "missing main function");
        } else {
            if (mainfunc.returnType instanceof BaseType
                    && ((BaseType)mainfunc.returnType).name.equals("int")) {
                if (mainfunc.parameterTypes.size() > 0) {
                    recorder.addRecord(node.location, "main function cannot have parameters");
                } else {
                    // accept
                }
            } else {
                recorder.addRecord(node.location, "main function return value is not int");
            }
        }
    }

    @Override
    public void visit(ID node) {
        if (node.name.equals("this")) {
            node.modifiable = false;
        } else {
            node.modifiable = true;
        }
    }

    @Override
    public void visit(Expr node) {
        assert false;
    }

    @Override
    public void visit(Stmt node) {
        assert false;
    }

    @Override
    public void visit(IfStmt node) {
        node.condition.accept(this);
        checkBooleanExpression(node.condition);
        node.thenStmt.accept(this);
        if(node.elseStmt!= null)
            node.elseStmt.accept(this);
    }

    @Override
    public void visit(VarDef node) {
        if (node.init != null) {
            if (!node.symbol.type.match(node.init.type)) {
                recorder.addRecord(node.location, "type conflict with defined variable");
            }
        }
    }

    private boolean isStringType(VarType type) {
        return type instanceof ClassType && ((ClassType)type).name.equals("string");
    }

    private boolean isIntType(VarType type) {
        return type instanceof BaseType && ((BaseType)type).name.equals("int");
    }

    private boolean isBoolType(VarType type) {
        return type instanceof BaseType && ((BaseType)type).name.equals("bool");
    }

    private void checkBooleanExpression(Expr expr) {
        if (!isBoolType(expr.type)) {
            recorder.addRecord(expr.location, "invalid loop control");
        }
    }

    @Override
    public void visit(ForStmt node) {
        if (node.initStmt != null) {
            node.initStmt.accept(this);
        }
        if (node.condition != null) {
            node.condition.accept(this);
            checkBooleanExpression(node.condition);
        }
        if (node.updateStmt != null) {
            node.updateStmt.accept(this);
        }
        loopCount++;
        node.body.accept(this);
        loopCount--;
    }

    @Override
    public void visit(FuncDef node) {
        curFunc = node.symbol;
        for (Stmt s: node.body) {
            s.accept(this);
        }
    }


    @Override
    public void visit(WhileStmt node) {
        node.condition.accept(this);
        checkBooleanExpression(node.condition);
        loopCount++;
        node.body.accept(this);
        loopCount--;
    }


    @Override
    public void visit(NewExpr node) {
        for (Expr e: node.exprDimensions) {
            e.accept(this);
        }
        node.modifiable = true;
    }

    @Override
    public void visit(ClassDef node) {
        for (FuncDef d: node.methods) {
            d.accept(this);
        }
        if (node.constructor != null) {
            node.constructor.accept(this);
            if (!node.constructor.name.equals(node.name)) {
                recorder.addRecord(node.location, "constructor must have the same name with class");
            }
        }
    }

    @Override
    public void visit(ExprStmt node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(PassStmt node) {
        // pass
    }

    @Override
    public void visit(TypeNode node) {
        assert false;
    }

    @Override
    public void visit(ArrayExpr node) {
        node.address.accept(this);
        node.index.accept(this);
        node.modifiable = true;
    }

    @Override
    public void visit(BlockStmt node) {
        for (Stmt s: node.statements) {
            s.accept(this);
        }
    }

    @Override
    public void visit(BreakStmt node) {
        if (loopCount == 0) {
            recorder.addRecord(node.location, "break out of loop");
        }
    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if(!node.lhs.type.match(node.rhs.type))
            recorder.addRecord(node.location, "type conflict between lhs and rhs of the assign expression");
        if(!node.lhs.modifiable)
            recorder.addRecord(node.location, "lhs of assign expression is not modifiable");
        node.modifiable = false;
    }


    @Override
    public void visit(BinaryExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if(!node.lhs.type.match(node.rhs.type)) {
            recorder.addRecord(node.location, "type conflict of the binary operands");
        } else {
            boolean isInt = isIntType(node.lhs.type);
            boolean isBool = isBoolType(node.lhs.type);
            boolean isString = isStringType(node.lhs.type);
            boolean typeError = false;
            switch(node.op) {
                //  only for int
                case "*": case "/": case "%": case "-": case "<<": case ">>": case "&": case "|": case "^":
                    if(!isInt)
                        typeError = true;
                    break;
                //  for string and int
                case "<=": case ">": case "<": case ">=": case "+":
                    if(!isInt && !isString)
                        typeError = true;
                    break;
                //  for bool
                case "&&": case "||":
                    if(!isBool)
                        typeError = true;
                    break;
                //  for anything
                case "==": case "!=":
                    break;
                default:
                    assert false;
            }
            if(typeError) {
                recorder.addRecord(node.location, "the type can not do this operation");
            }
        }
        node.modifiable = false;
    }

    @Override
    public void visit(Definition node) {
        assert false;
    }

    @Override
    public void visit(MemberExpr node) {
        node.object.accept(this);
        if(node.object.type instanceof ArrayType) {
            node.modifiable = false;
        } else {
            if (node.methodCall != null) {
                node.methodCall.accept(this);
                node.modifiable = node.methodCall.modifiable;
            } else {
                node.modifiable = true;
            }
        }
    }

    @Override
    public void visit(ReturnStmt node) {
        VarType requiredType = curFunc.returnType;
        BaseType voidType = new BaseType("void", gst.getBaseSymbol("void"));
        if(requiredType.match(voidType) && node.retExpr != null) {
            recorder.addRecord(node.location, "cannot return void value");
        }
        VarType retType;
        if(node.retExpr == null) {
            retType = voidType;
        } else {
            retType = node.retExpr.type;
        }
        if(!requiredType.match(retType)) {
            recorder.addRecord(node.location, "return value dismatch");
        }
    }

    @Override
    public void visit(UnaryExpr node) {
        assert false;
        node.expr.accept(this);
        boolean modifiableError = false;
        boolean typeError = false;
        boolean isInt = isIntType(node.expr.type);
        boolean isBool = isBoolType(node.expr.type);
        switch (node.op) {
            case "++v":
            case "--v":
                if (!node.expr.modifiable)
                    modifiableError = true;
                if (!isInt)
                    typeError = true;
                node.modifiable = true;
                break;
            case "!":
                if (!isBool)
                    typeError = true;
                node.modifiable = false;
                break;
            case "~":
            case "-":
                if (!isInt)
                    typeError = true;
                node.modifiable = false;
                break;
            case "v++":
            case "v--":
                if (!node.expr.modifiable)
                    modifiableError = true;
                if (!isInt)
                    typeError = true;
                node.modifiable = false;
                break;
            default:
                assert false;
        }
        if (typeError) {
            recorder.addRecord(node.location, "the type can not do this operation");
        } else if (modifiableError) {
            recorder.addRecord(node.location, "the expression is not modifiable");
        }
    }

    @Override
    public void visit(PrefixExpr node) {
        assert false;
        node.expr.accept(this);
        boolean modifiableError = false;
        boolean typeError = false;
        boolean isInt = isIntType(node.expr.type);
        boolean isBool = isBoolType(node.expr.type);
        switch(node.op) {
            case "++v": case "--v":
                if(!node.expr.modifiable)
                    modifiableError = true;
                if(!isInt)
                    typeError = true;
                node.modifiable = true;
                break;
            case "!":
                if(!isBool)
                    typeError = true;
                node.modifiable = false;
                break;
            case "~":
                if(!isInt)
                    typeError = true;
                node.modifiable = false;
                break;
            case "-":
                if(!isInt)
                    typeError = true;
                node.modifiable = false;
                break;
            default:
                assert false;
        }
        if(typeError) {
            recorder.addRecord(node.location, "the type can not do this operation");
        } else if(modifiableError) {
            recorder.addRecord(node.location, "the expression is not modifiable");
        }
    }

    @Override
    public void visit(SuffixExpr node) {
        assert false;
        node.expr.accept(this);
        boolean modifiableError = false;
        boolean typeError = false;
        boolean isInt = isIntType(node.expr.type);
        boolean isBool = isBoolType(node.expr.type);
        switch(node.op) {
            case "v++": case "v--":
                if(!node.expr.modifiable)
                    modifiableError = true;
                if(!isInt)
                    typeError = true;
                node.modifiable = false;
                break;
            default:
                assert false;
        }
        if(typeError) {
            recorder.addRecord(node.location, "the type can not do this operation");
        } else if(modifiableError) {
            recorder.addRecord(node.location, "the expression is not modifiable");
        }
    }

    @Override
    public void visit(VarDefStmt node) {
        node.varDef.accept(this);
    }

    @Override
    public void visit(LiteralExpr node) {
        node.modifiable = false;
    }
/*
    @Override
    public void visit(LogicExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if(!node.lhs.type.match(node.rhs.type)) {
            recorder.addRecord(node.location, "type conflict of the binary operands");
        } else {
            boolean isInt = isIntType(node.lhs.type);
            boolean isBool = isBoolType(node.lhs.type);
            boolean isString = isStringType(node.lhs.type);
            boolean typeError = false;
            switch(node.op) {
                //  for bool
                case "&&": case "||":
                    if(!isBool)
                        typeError = true;
                    break;
                default:
                    assert false;
            }
            if(typeError) {
                recorder.addRecord(node.location, "the type can not do this operation");
            }
        }
        node.modifiable = false;
    }
*/
    @Override
    public void visit(BaseTypeNode node) {

    }

    @Override
    public void visit(ContinueStmt node) {
        if (loopCount == 0) {
            recorder.addRecord(node.location, "continue out of loop");
        }
    }

    @Override
    public void visit(FuncCallExpr node) {
        int parameterCount = node.funcSymbol.parameterTypes.size();
        int inClass = (node.funcSymbol.parameterNames.size() > 0
                && node.funcSymbol.parameterNames.get(0).equals("this") ? 1 : 0);
        if(node.arguments.size() + inClass != parameterCount) {
            recorder.addRecord(node.location, "invalid number of arguments");
        } else {
            for (int i = 0; i < node.arguments.size(); i++) {
                node.arguments.get(i).accept(this);
                if(!node.arguments.get(i).type.match(node.funcSymbol.parameterTypes.get(i + inClass))) {
                    recorder.addRecord(node.arguments.get(i).location,
                            "the type of this parameter is conflict with required type");
                }
            }
        }
        node.modifiable = false;
    }

    @Override
    public void visit(ArrayTypeNode node) {

    }

    @Override
    public void visit(ClassTypeNode node) {

    }
}

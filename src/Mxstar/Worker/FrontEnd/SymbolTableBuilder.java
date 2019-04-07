package Mxstar.Worker.FrontEnd;

import Mxstar.AST.*;
import Mxstar.Symbol.*;
import Mxstar.Worker.ErrorRecorder;

import java.util.Arrays;
import java.util.HashMap;

/*

    build the Symbol table, and check use before declaration error

 */
public class SymbolTableBuilder implements IAstVisitor {
    public ErrorRecorder errorRecorder;
    public GlobalSymbolTable globalSymbolTable;
    public SymbolTable currentSymbolTable;
    public FuncSymbol curFunc;
    private String name;
    public HashMap<SymbolTable,ClassSymbol> symbolTableToClassSymbol;

    public SymbolTableBuilder(ErrorRecorder errorRecorder) {
        this.errorRecorder = errorRecorder;
        this.globalSymbolTable = new GlobalSymbolTable();
        this.currentSymbolTable = globalSymbolTable;
        this.symbolTableToClassSymbol = new HashMap<>();
    }
    private void enter(SymbolTable symbolTable) {
        currentSymbolTable = symbolTable;
    }
    private void leave() {
        currentSymbolTable = currentSymbolTable.parent;
        assert(currentSymbolTable != null);
    }
    private VarType resolveVarType(TypeNode node) {
        if (node instanceof BaseTypeNode) {
            BaseSymbol symbol = globalSymbolTable.getBaseSymbol(((BaseTypeNode) node).name);
            if (symbol != null) {
                return new BaseType(symbol.name, symbol);
            } else {
                return null;
            }
        } else if (node instanceof ClassTypeNode) {
            ClassSymbol symbol = globalSymbolTable.getClassSymbol(((ClassTypeNode) node).className);
            if (symbol != null) {
                return new ClassType(symbol.name, symbol);
            } else {
                return null;
            }
        } else if (node instanceof ArrayTypeNode) {
            ArrayTypeNode oldArray = (ArrayTypeNode)node;
            VarType baseType;
            if (oldArray.dimension == 1) {
                baseType = resolveVarType(oldArray.baseType);
                if (oldArray.baseType instanceof BaseTypeNode && ((BaseTypeNode) oldArray.baseType).name.equals("void")) {
                    errorRecorder.addRecord(oldArray.location, "can not create an array with type void");
                }
            } else {
                ArrayTypeNode newArray = new ArrayTypeNode();
                newArray.baseType = oldArray.baseType;
                newArray.dimension = oldArray.dimension - 1;
                baseType = resolveVarType(newArray);
            }
            if (baseType != null) {
                return new ArrayType(baseType);
            } else {
                return null;
            }
        } else {
            assert false;
            return null;
        }
    }

    private VarSymbol resolveVarSymbol(String name, SymbolTable symbolTable) {
        VarSymbol symbol = symbolTable.getVarSymbol(name);
        if (symbol != null) {
            return symbol;
        } else {
            if (symbolTable.parent != null) {
                return resolveVarSymbol(name, symbolTable.parent);
            } else {
                return null;
            }
        }
    }
    private VarSymbol resolveVarSymbol(String name) {
        return resolveVarSymbol(name, currentSymbolTable);
    }
    private FuncSymbol resolveFuncSymbol(String name, SymbolTable symbolTable) {
        FuncSymbol symbol = symbolTable.getFuncSymbol(name);
        if (symbol != null) {
            return symbol;
        } else {
            if (symbolTable.parent != null) {
                return resolveFuncSymbol(name, symbolTable.parent);
            } else {
                return null;
            }
        }
    }
    private FuncSymbol resolveFuncSymbol(String name) {
        this.name = name;
        return resolveFuncSymbol(name, currentSymbolTable);
    }

    private void registerClass(ClassDef classDef) {
        if (globalSymbolTable.getClassSymbol(classDef.name) != null) {
            errorRecorder.addRecord(classDef.location, "the class has been defined");
            return;
        }
        if (globalSymbolTable.getFuncSymbol(classDef.name) != null) {
            errorRecorder.addRecord(classDef.location, "the class name conflict with the name of a func");
            return;
        }
        ClassSymbol symbol = new ClassSymbol();
        symbol.name = classDef.name;
        symbol.location = classDef.location;
        symbol.classSymbolTable = new SymbolTable(globalSymbolTable);
        classDef.symbol = symbol;
        symbolTableToClassSymbol.put(symbol.classSymbolTable, symbol);
        globalSymbolTable.putClassSymbol(symbol.name, symbol);
    }
    private void registerClassFuncs(ClassDef classDef) {
        ClassSymbol symbol = globalSymbolTable.getClassSymbol(classDef.name);
        enter(symbol.classSymbolTable);
        if (classDef.constructor != null)
            registerFunc(classDef.constructor, symbol);
        for (FuncDef d : classDef.methods)
            registerFunc(d, symbol);
        leave();
    }
    private void defineClassFields(ClassDef classDef) {
        ClassSymbol symbol = globalSymbolTable.getClassSymbol(classDef.name);
        enter(symbol.classSymbolTable);
        for (VarDef d : classDef.fields)
            defineVar(d);
        leave();
    }
    private void defineClassFuncs(ClassDef classDef) {
        ClassSymbol symbol = globalSymbolTable.getClassSymbol(classDef.name);
        enter(symbol.classSymbolTable);
        if (classDef.constructor != null)
            defineFunc(classDef.constructor, symbol);
        for (FuncDef d : classDef.methods)
            defineFunc(d, symbol);
        leave();
    }
    private void registerFunc(FuncDef funcDef, ClassSymbol classSymbol) {
        if (currentSymbolTable.getFuncSymbol(funcDef.name) != null) {
            errorRecorder.addRecord(funcDef.location, "the func has been defined");
            return;
        }
        if (classSymbol == null && globalSymbolTable.getClassSymbol(funcDef.name) != null) {
            errorRecorder.addRecord(funcDef.location, "the function conflicts with the name of class");
            return;
        }
        FuncSymbol symbol = new FuncSymbol();
        symbol.name = (classSymbol == null ? "" : classSymbol.name + ".") + funcDef.name;
        symbol.isGlobalFunc = (classSymbol == null);
        symbol.location = funcDef.location;
        symbol.returnType = resolveVarType(funcDef.retTypeNode);
        if (symbol.returnType == null) {
            errorRecorder.addRecord(funcDef.retTypeNode.location, "can not resolve type");
        }
        symbol.funcSymbolTable = null;
        if (classSymbol != null) {
            symbol.parameterNames.add("this");
            symbol.parameterTypes.add(new ClassType(classSymbol.name, classSymbol));
        }
        for (VarDef d : funcDef.parameters) {
            symbol.parameterNames.add(d.name);
            VarType type = resolveVarType(d.typeNode);
            if (type == null) {
                errorRecorder.addRecord(d.location, "can not resolve the type of parameter");
            }
            symbol.parameterTypes.add(type);
        }
        funcDef.symbol = symbol;
        currentSymbolTable.putFuncSymbol(funcDef.name, symbol);
    }
    private void defineVar(VarDef d) {
        VarType type = resolveVarType(d.typeNode);
        if (d.init != null)
            d.init.accept(this);
        if (type != null) {
            if (currentSymbolTable.getVarSymbol(d.name) != null) {
                errorRecorder.addRecord(d.location, "the variable has been defined");
            } else {
                if (type instanceof BaseType && ((BaseType) type).name.equals("void")
                        || type instanceof ClassType && ((ClassType) type).name.equals("null"))
                    errorRecorder.addRecord(d.location, "can not define a class with type null or void");
                boolean isClassField = symbolTableToClassSymbol.containsKey(currentSymbolTable);
                boolean isGlobalVar = currentSymbolTable == globalSymbolTable;
                d.symbol = new VarSymbol(d.name, type, d.location, isClassField, isGlobalVar);
                currentSymbolTable.putVarSymbol(d.name, d.symbol);
                if (isGlobalVar && d.init != null)
                    globalSymbolTable.globalInitUsedVars.add(d.symbol);
            }
        } else {
            errorRecorder.addRecord(d.typeNode.location, "can not resolve the type");
        }
    }
    private void defineFunc(FuncDef funcDef, ClassSymbol classSymbol) {
        FuncSymbol funcSymbol = currentSymbolTable.getFuncSymbol(funcDef.name);
        curFunc = funcSymbol;

        funcSymbol.funcSymbolTable = new SymbolTable(currentSymbolTable);
        enter(funcSymbol.funcSymbolTable);
        if (classSymbol != null)
            defineVar(new VarDef(new ClassTypeNode(classSymbol.name), "this", null));
        for (VarDef d: funcDef.parameters)
            defineVar(d);
        for (Stmt s: funcDef.body)
            s.accept(this);
        leave();
        curFunc = null;
        funcSymbol.finish();
    }

    @Override
    public void visit(AstProgram node) {
        for (ClassDef d : node.classes)
            registerClass(d);
        for (ClassDef d : node.classes)
            registerClassFuncs(d);
        for (FuncDef d : node.funcs)
            registerFunc(d, null);
        if (errorRecorder.errorOccured()) return;
        for (ClassDef d : node.classes)
            defineClassFields(d);
        for (Definition d : node.definitions) {
            if (d instanceof VarDef)
                defineVar((VarDef)d);
            else if (d instanceof ClassDef)
                defineClassFuncs((ClassDef)d);
            else
                defineFunc((FuncDef) d, null);
        }
        /*
        for (VarDef d : node.globalVars)
            defineVar(d);
        for (ClassDef d : node.classes)
            defineClassFuncs(d);
        for (FuncDef d : node.funcs)
            defineFunc(d, null);
        */
    }

    @Override
    public void visit(Definition node) {
        assert false;
    }

    @Override public void visit(FuncDef node) { }
    @Override public void visit(ClassDef node) { }
    @Override public void visit(VarDef node) {
        defineVar(node);
    }

    @Override public void visit(TypeNode node) { }
    @Override public void visit(ArrayTypeNode node) { }
    @Override public void visit(BaseTypeNode node) { }

    @Override public void visit(ClassTypeNode node) { }

    @Override
    public void visit(Stmt node) {
        assert false;
    }

    @Override
    public void visit(PassStmt node) {
    }

    @Override
    public void visit(ForStmt node) {
        if (node.initStmt != null) node.initStmt.accept(this);
        if (node.condition != null) node.condition.accept(this);
        if (node.updateStmt != null) node.updateStmt.accept(this);
        node.body.accept(this);
    }

    @Override
    public void visit(WhileStmt node) {
        node.condition.accept(this);
        node.body.accept(this);
    }

    @Override
    public void visit(IfStmt node) {
        node.condition.accept(this);
        node.thenStmt.accept(this);
        if (node.elseStmt != null) {
            node.elseStmt.accept(this);
        }
    }

    @Override
    public void visit(ContinueStmt node) {
    }

    @Override
    public void visit(BreakStmt node) {
    }

    @Override
    public void visit(ReturnStmt node) {
        if (node.retExpr != null) {
            node.retExpr.accept(this);
        }
    }

    @Override
    public void visit(BlockStmt node) {
        SymbolTable symbolTable = new SymbolTable(currentSymbolTable);
        enter(symbolTable);
        for (Stmt s : node.statements)
            s.accept(this);
        leave();
    }

    @Override
    public void visit(VarDefStmt node) {
        node.def.accept(this);
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
        VarSymbol symbol = resolveVarSymbol(node.name);
        if (symbol == null) {
            errorRecorder.addRecord(node.location, "can not resolve variable '" + node.name + "'");
            node.type = null;
            return;
        }
        node.symbol = symbol;
        node.type = symbol.type;
        if (symbol.isGlobalVar) {
            if (curFunc == null) {
                globalSymbolTable.globalInitUsedVars.add(symbol);
            } else {
                curFunc.usedGlobalVars.add(symbol);
                curFunc.withSideEffect = true;
            }
        }
    }

    @Override
    public void visit(LiteralExpr node) {
        switch (node.typeName) {
            case "int":
                node.type = new BaseType("int", globalSymbolTable.getBaseSymbol("int"));
                break;
            case "null":
                node.type = new ClassType("null", globalSymbolTable.getClassSymbol("null"));
                break;
            case "bool":
                node.type = new BaseType("bool", globalSymbolTable.getBaseSymbol("bool"));
                break;
            case "string":
                node.type = new ClassType("string", globalSymbolTable.getClassSymbol("string"));
                break;
            default:
                assert false;
        }
    }

    @Override
    public void visit(ArrayExpr node) {
        node.address.accept(this);
        node.index.accept(this);
        if (node.address.type instanceof ArrayType)
            node.type = ((ArrayType) node.address.type).baseType;
        else {
            node.type = null;
            errorRecorder.addRecord(node.location, "array index expression with an no-array object");
        }
    }

    @Override
    public void visit(FuncCallExpr node) {
        FuncSymbol funcSymbol = resolveFuncSymbol(node.funcName);
        if (funcSymbol == null) {
            errorRecorder.addRecord(node.location, "can not resolve func '" + node.funcName + "'");
            return;
        }
        for (Expr e : node.arguments) {
            e.accept(this);
        }
        node.type = funcSymbol.returnType;
        node.funcSymbol = funcSymbol;
        if (curFunc != null) {
            curFunc.calleeSet.add(funcSymbol);
        }
    }

    @Override
    public void visit(NewExpr node) {
        for (Expr e : node.exprDimensions)
            e.accept(this);
        int dimension = node.exprDimensions.size() + node.restDemension;
        node.type = resolveVarType(node.typeNode);
        if (node.type == null) {
            errorRecorder.addRecord(node.typeNode.location, "can not resolve the type");
            node.type = null;
            return;
        }
        if (dimension == 0 && node.typeNode instanceof BaseTypeNode && ((BaseTypeNode) node.typeNode).name.equals("void")) {
            errorRecorder.addRecord(node.location, "can not new a void");
        }
        for (int i = 0; i < dimension; i++) {
            node.type = new ArrayType(node.type);
        }
    }

    @Override
    public void visit(MemberExpr node) {
        node.object.accept(this);
        if (node.object.type instanceof BaseType) {
            errorRecorder.addRecord(node.object.location, "the expression is not a class instance");
            node.type = null;
            return;
        }
        if (node.object.type instanceof ArrayType) {
            ArrayType arrayType = (ArrayType)node.object.type;
            if (node.methodCall == null || !node.methodCall.funcName.equals("size")) {
                errorRecorder.addRecord(node.methodCall.location, "array type can only call size method");
                node.type = null;
            } else {
                node.type = new BaseType("int", globalSymbolTable.getBaseSymbol("int"));
            }
        } else {
            ClassType classType = (ClassType) node.object.type;
            if (classType == null) return;
            if (node.fieldAccess != null) {
                node.fieldAccess.symbol = resolveVarSymbol(node.fieldAccess.name, classType.symbol.classSymbolTable);
                if (node.fieldAccess.symbol == null) {
                    errorRecorder.addRecord(node.fieldAccess.location,
                            "class '" + classType.name + "' has not field '" + node.fieldAccess.name + "'");
                    node.type = null;
                    return;
                }
                node.fieldAccess.type = node.fieldAccess.symbol.type;
                node.type = node.fieldAccess.type;
            } else {
                try {
                    node.methodCall.funcSymbol = resolveFuncSymbol(node.methodCall.funcName, classType.symbol.classSymbolTable);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                if (node.methodCall.funcSymbol == null) {
                    errorRecorder.addRecord(node.methodCall.location,
                            "class '" + classType.name + "' has not method '" + node.methodCall.funcName + "'");
                    node.type = null;
                    return;
                }
                node.methodCall.type = node.methodCall.funcSymbol.returnType;
                node.type = node.methodCall.type;
                for (Expr e : node.methodCall.arguments)
                    e.accept(this);
            }
        }
    }

    @Override
    public void visit(PrefixExpr node) {
        node.expr.accept(this);
        node.type = node.expr.type;
    }

    @Override
    public void visit(SuffixExpr node) {
        node.expr.accept(this);
        node.type = node.expr.type;
    }

    private boolean isRelationOperator(String op) {
        switch (op) {
            case "==": case "!=": case "<": case "<=": case ">": case ">=":
                return true;
            default:
                return false;
        }
    }
    @Override
    public void visit(BinaryExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if (isRelationOperator(node.op)) {
            node.type = new BaseType("bool", globalSymbolTable.getBaseSymbol("bool"));
        } else {
            node.type = node.lhs.type;
        }
    }

    @Override
    public void visit(LogicExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        if (isRelationOperator(node.op)) {
            node.type = new BaseType("bool", globalSymbolTable.getBaseSymbol("bool"));
        } else {
            node.type = node.lhs.type;
        }
    }

    @Override
    public void visit(AssignExpr node) {
        node.lhs.accept(this);
        node.rhs.accept(this);
        node.type = new BaseType("void", globalSymbolTable.getBaseSymbol("void"));
    }

    @Override
    public void visit(EmptyStmt node) { }
}

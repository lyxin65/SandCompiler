package Mxstar.Symbol;

import Mxstar.AST.TokenLocation;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalSymbolTable extends SymbolTable {
    public Map<String, ClassSymbol> classes;
    public Map<String, BaseSymbol> baseVars;
    public HashSet<VarSymbol> globalInitUsedVars;

    public GlobalSymbolTable() {
        super(null);
        classes = new LinkedHashMap<>();
        baseVars = new LinkedHashMap<>();
        globalInitUsedVars = new HashSet<>();
        addMstarDefaultTypes();
    }

    public void putBaseSymbol(String name, BaseSymbol symbol) {
        baseVars.put(name, symbol);
    }
    public BaseSymbol getBaseSymbol(String name) {
        return baseVars.get(name);
    }
    public ClassSymbol getClassSymbol(String name) {
        return classes.get(name);
    }
    public void putClassSymbol(String name, ClassSymbol symbol) {
        classes.put(name, symbol);
    }

    private VarType voidType() {
        return new BaseType("void", baseVars.get("void"));
    }
    private VarType intType() {
        return new BaseType("int", baseVars.get("int"));
    }
    private VarType stringType() {
        return new ClassType("string", classes.get("string"));
    }
    private FuncSymbol stringLength() {
        FuncSymbol f = new FuncSymbol();
        f.name = "string.length";
        f.isGlobalFunc = true;
        f.withSideEffect = false;
        f.location = new TokenLocation(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.returnType = intType();
        return f;
    }
    private FuncSymbol stringSubstring() {
        FuncSymbol f = new FuncSymbol();
        f.name = "string.substring";
        f.isGlobalFunc = true;
        f.withSideEffect = false;
        f.returnType = stringType();
        f.location = new TokenLocation(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.parameterTypes.add(intType());
        f.parameterNames.add("left");
        f.parameterTypes.add(intType());
        f.parameterNames.add("right");
        return f;
    }
    private FuncSymbol stringParseInt() {
        FuncSymbol f = new FuncSymbol();
        f.name = "string.parseInt";
        f.isGlobalFunc = true;
        f.withSideEffect = false;
        f.location = new TokenLocation(0, 0);
        f.returnType = intType();
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        return f;
    }
    private FuncSymbol stringOrd() {
        FuncSymbol f = new FuncSymbol();
        f.name = "string.ord";
        f.isGlobalFunc = true;
        f.withSideEffect = false;
        f.location = new TokenLocation(0, 0);
        f.returnType = intType();
        f.parameterTypes.add(stringType());
        f.parameterNames.add("this");
        f.parameterTypes.add(intType());
        f.parameterNames.add("pos");
        return f;
    }
    private FuncSymbol globalPrint() {
        FuncSymbol f = new FuncSymbol();
        f.name = "print";
        f.isGlobalFunc = true;
        f.withSideEffect = true;
        f.returnType = voidType();
        f.location = new TokenLocation(0, 0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("str");
        return f;
    }
    private FuncSymbol globalPrintln() {
        FuncSymbol f = new FuncSymbol();
        f.name = "println";
        f.isGlobalFunc = true;
        f.withSideEffect = true;
        f.returnType = voidType();
        f.location = new TokenLocation(0 ,0);
        f.parameterTypes.add(stringType());
        f.parameterNames.add("str");
        return f;
    }
    private FuncSymbol globalGetString() {
        FuncSymbol f = new FuncSymbol();
        f.name = "getString";
        f.withSideEffect = true;
        f.isGlobalFunc = true;
        f.returnType = stringType();
        f.location = new TokenLocation(0, 0);
        return f;
    }
    private FuncSymbol globalGetInt() {
        FuncSymbol f = new FuncSymbol();
        f.name = "getInt";
        f.withSideEffect = true;
        f.isGlobalFunc = true;
        f.returnType = intType();
        f.location = new TokenLocation(0, 0);
        return f;
    }
    private FuncSymbol globalToString() {
        FuncSymbol f = new FuncSymbol();
        f.name = "toString";
        f.isGlobalFunc = true;
        f.withSideEffect = false;
        f.returnType = stringType();
        f.location = new TokenLocation(0, 0);
        f.parameterTypes.add(intType());
        f.parameterNames.add("i");
        return f;
    }
    private void addDefaultBases() {
        baseVars.put("int", new BaseSymbol("int"));
        baseVars.put("void", new BaseSymbol("void"));
        baseVars.put("bool", new BaseSymbol("bool"));
    }
    private void addDefaultNull() {
        ClassSymbol nullSymbol = new ClassSymbol();
        nullSymbol.name = "null";
        nullSymbol.location = new TokenLocation(0,0);
        nullSymbol.classSymbolTable = new SymbolTable(this);
        putClassSymbol("null", nullSymbol);
    }
    private void addDefaultString() {
        ClassSymbol stringClass = new ClassSymbol();
        putClassSymbol("string", stringClass);
        SymbolTable st = new SymbolTable(this);
        st.putFuncSymbol("length", stringLength());
        st.putFuncSymbol("substring", stringSubstring());
        st.putFuncSymbol("parseInt", stringParseInt());
        st.putFuncSymbol("ord", stringOrd());
        stringClass.name = "string";
        stringClass.location = new TokenLocation(0, 0);
        stringClass.classSymbolTable = st;
    }
    private void addDefaultFuncs() {
        putFuncSymbol("print", globalPrint());
        putFuncSymbol("println", globalPrintln());
        putFuncSymbol("getString", globalGetString());
        putFuncSymbol("getInt", globalGetInt());
        putFuncSymbol("toString", globalToString());
    }
    private void addMstarDefaultTypes() {
        addDefaultBases();
        addDefaultNull();
        addDefaultString();
        addDefaultFuncs();
    }
}

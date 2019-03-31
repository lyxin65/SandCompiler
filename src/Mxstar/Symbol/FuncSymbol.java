package Mxstar.Symbol;

import Mxstar.AST.TokenLocation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FuncSymbol {
    public String name;
    public TokenLocation location;
    public VarType returnType;
    public List<VarType> parameterTypes;
    public List<String> parameterNames;
    public SymbolTable FuncSymbolTable;
    public HashSet<VarSymbol> usedGlobalVars;
    public boolean isGlobalFunc;
    public boolean withSideEffect;
    public HashSet<FuncSymbol> calleeSet;

    private HashSet<FuncSymbol> visited;

    public FuncSymbol() {
        this.parameterTypes = new LinkedList<>();
        this.parameterNames = new LinkedList<>();
        this.usedGlobalVars = new HashSet<>();
        this.calleeSet = new HashSet<>();
        this.visited = new HashSet<>();
    }

    private void dfsSideEffect(FuncSymbol fs) {
        if(withSideEffect) return;
        if(visited.contains(fs)) return;
        visited.add(fs);
        for(FuncSymbol sfs : fs.calleeSet) {
            if(sfs.withSideEffect) {
                withSideEffect = true;
                break;
            }
        }
    }

    private boolean isBaseType(VarType vt) {
        return vt instanceof BaseType;
    }

    public void finish() {
        for(VarType vt : parameterTypes) {
            if(!isBaseType(vt))
                withSideEffect = true;
        }
        visited.clear();
        dfsSideEffect(this);
    }
}

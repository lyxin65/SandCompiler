package Mxstar.Symbol;

import Mxstar.Config;
import org.antlr.v4.misc.OrderedHashMap;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    public Map<String,VarSymbol> vars;
    public Map<String,FuncSymbol> funcs;
    public SymbolTable parent;
    public List<SymbolTable> children;
    public Map<String, Integer> offsets;
    private Integer currentOffset;


    public SymbolTable(SymbolTable parent) {
        this.vars = new LinkedHashMap<>();
        this.funcs = new LinkedHashMap<>();
        this.parent = parent;
        this.children = new LinkedList<>();
        this.offsets = new OrderedHashMap<>();
        this.currentOffset = 0;
    }
    public VarSymbol getVarSymbol(String name) {
        return vars.get(name);
    }
    public void putVarSymbol(String name, VarSymbol VarSymbol) {
        vars.put(name, VarSymbol);
        offsets.put(name, currentOffset);
        currentOffset += Config.REGISTER_WIDTH;
    }
    public int getVarOffset(String name) {
        return offsets.get(name);
    }
    public FuncSymbol getFuncSymbol(String name) { return funcs.get(name); }
    public void putFuncSymbol(String name, FuncSymbol symbol) {
        funcs.put(name, symbol);
    }
}

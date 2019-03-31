package Mxstar.AST;

import Mxstar.Symbol.*;

import java.util.*;

public class ClassDef extends Definition {
    public String name = null;
    public List<VarDef> fields;
    public List<FuncDef> methods;
    public FuncDef constructor;

    public ClassSymbol symbol;

    public String toString() {
        return name + "\n";
    }

    @Override public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}


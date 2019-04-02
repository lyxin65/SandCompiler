package Mxstar.AST;

import java.util.*;

public class AstProgram extends AstNode {
    public List<FuncDef> funcs;
    public List<ClassDef> classes;
    public List<VarDef> globalVars;
    public List<Definition> definitions;

    public AstProgram() {
        this.funcs = new LinkedList<>();
        this.classes = new LinkedList<>();
        this.globalVars = new LinkedList<>();
        this.definitions = new LinkedList<>();
    }

    public void add(FuncDef a) {
        funcs.add(a);
        definitions.add(a);
    }

    public void add(ClassDef a) {
        classes.add(a);
        definitions.add(a);
    }

    public void add(VarDef a) {
        globalVars.add(a);
        definitions.add(a);
    }

    public void addAll(List<VarDef> a) {
        globalVars.addAll(a);
        definitions.addAll(a);
    }

    public void accept(IAstVisitor visitor) {
        visitor.visit(this);
    }
}

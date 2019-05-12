package Mxstar.Worker.FrontEnd;

import java.util.*;
import javafx.util.Pair;

enum VarDefStatus { GlobalVar, FuncParam, ClassObj, LocalVar }

public class IRBuilder implements IAstVisitor {
    public LinearIR linearCode;

    private FuncFrame curFunc;
    private ArrayList<Quad> curCodeList;

    // label related
    private int labelCnt;                   // the counter of temporary names;
    private List<Integer> nextStatLabel;    // the tempo names need to jump to next quad;
    private Stack<Integer> ifElseEndLabel;  // the end of if-else structure;
    private int quadLabelCnt;               // the counter of exist label names;
    private LabelTable uset;
    private Stack<Integer> brkLabel;        // break label
    private Stack<Integer> ctnLabel;        // continue label

    private VarDefStatus varState;

    private GlobalSymbolTable gst;

    private HashMap<String, int> classObj;
    private HashSet<String> classObjStr;
    private int classObjSize;

    int selfGenLoopCnt;

    public IRBuilder() {
        linearCode = new LinearIR();
        labelCnt = 0;
        nextStatLabel = new ArrayList<>();
		brkLabel = new Stack<>();
		ctnLabel = new Stack<>();
		uset = new LabelTable();
		curCodeList = new ArrayList<>();
		ifElseEndLabel = new Stack<>();
        gst = new GlobalSymbolTable();
		selfGenLoopCnt = 0;
		classObjStr = new HashSet<>();;
    }

    public LinearIR generateIR(AstNode root) {
        visit(root);
        return linearCode;
    }

    /*
     * private functions
     * */

    private void insertFunc(FuncFrame f) {
        updateLabel(uset, curCodeList);
        setMemPosition(curCodeList);
        curFunc.buildCFG(curCodeList);
        curCodeList.clear();
        linearCode.insertTextFunc(f);
    }

    private void insertInit(FuncFrame f) {
        updateLabel(uset, curCodeList);
        setMemPosition(curCodeList);
        curFunc.buildCFG(curCodeList);
        curCodeList.clear();
        linearCode.insertTextFunc(f);
    }

    private Register changeOpr2Reg(Operand r) {
        if (r instanceof Register) {
            return (Register)r;
        }
        Register reg = new Register(getTempName(isAddrBase(r.get())));
        insertQuad(new MoveQuad("mov", reg, r.copy()));
        return reg.copy();
    }

    private ArrayList<Quad> paramsQuads = new ArrayList<>();
    private void insertQuad(Quad inst) {
        if (inst.getOp() == "cmp") {
            if (inst.getR1() instanceof MemAccess) {
                inst.changeR1(changeOpr2Reg(inst.getR1()));
            }
            if (inst.getR2() instanceof MemAccess) {
                inst.changeR2(changeOpr2Reg(inst.getR2()));
            }
        }
        if (nextStatLabel.size() > 0) {
            String Label = nextLabel();
            inst.setLabel(label);
            for (int data: nextStatLabel) {
                uset.set(data, label);
            }
            nextStatLabel.clear();
        }
        if (inst instanceof ParamQuad) {
            paramsQuads.add(inst);
        } else if (inst instanceof CallQuad) {
            for (int i = 0; i < paramsQuads.size(); i++) {
                curCodeList.add(paramsQuads.get(i));
            }
            paramsQuads.clear();
            curCodeList.add(inst);
        } else {
            curCodeList.add(inst);
        }
    }

    private void updateLabel(LabelTable labels, ArrayList<Quad> code) {
        for (int i = 0; i < code.size(); i++) {
            Quad quad = code.get(i);
            quad.updateLabel(labels);
        }
    }

    private void setMemPosition(ArrayList<Quad> code) {
        for (int i = 0; i < code.size(); i++) {
            Quad c = code.get(i);
            Operand r1 = c.getR1(), r2 = c.getR2(), r3 = c.getR3();
            if (r1 instanceof Register) {
                String s = r1.get();
                if (!isTempReg(s)) {
                    ((Register)r1).setMemPos(s);
                }
            }
            if (r2 instanceof Register) {
                String s = r2.get();
                if (!isTempReg(s)) {
                    ((Register)r2).setMemPos(s);
                }
            }
            if (r3 instanceof Register) {
                String s = r3.get();
                if (!isTempReg(s)) {
                    ((Register)r3).setMemPos(s);
                }
            }
        }
    }

    private void updateNextStatLabel(int t) {
        nextStatLabel.add(t);
        uset.add(t);
    }

    private String nextLabel() {
        String str = "lb" + Integer.toString(quadLabelCnt++);
        return str;
    }

    private String classFuncLabel(String className, String funcName) {
        return "C_" + className + "_" + funcName;
    }

    private String funcLabel(String funcName) {
        return funcName.equal("main") ? "main" : "F_" + funcName;
    }

    private String initFuncLabel(String funcName) {
        return "F_init_" + funcName;
    }

    private Stringh getTempName(boolean isBase) {
        return (isBase ? "A_" : "V_") + Integer.toString(labelCnt++);
    }

    private void visitChild(AstNode node) {
        if (node == null) return;
        for (int i = 0; i < node.sons.size(); i++) {
            visit(node.sons.get(i));
        }
    }

    private void generateCondition(AstNode node, int labelTrue, int labelFalse) {
        if (node.
    }

    /*
     * end of private functions
     * */

    
}

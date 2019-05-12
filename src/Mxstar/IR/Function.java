package Mxstar.IR;

import Mxstar.IR.Instruction.*;
import Mxstar.IR.Operand.PhysicalRegister;
import Mxstar.IR.Operand.Register;
import Mxstar.IR.Operand.VirtualRegister;
import Mxstar.Symbol.VarSymbol;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Function {
    public enum Type {
        External, Library, UserDefined
    }
    public Type type;
    public String name;
    public boolean hasReturnValue;
    public boolean hasOutput;
    public BasicBlock enterBB;
    public BasicBlock leaveBB;

    public LinkedList<BasicBlock> basicblocks;
    public LinkedList<BasicBlock> rPostOrder;
    public LinkedList<BasicBlock> rPostOrderOnrCFG;
    public LinkedList<VirtualRegister> parameters;

    public HashSet<VarSymbol> usedGV;
    public HashSet<VarSymbol> allUsedGV;
    public HashSet<PhysicalRegister> usedPR;
    public HashSet<PhysicalRegister> allUsedPR;

    public HashSet<Function> callee;

    private HashSet<BasicBlock> visitedBB;
    private HashSet<Function> visitedFunc;

    public Function(Type type, String name, boolean hasReturnValue) {
        this.type = type;
        this.name = name;
        this.hasReturnValue = hasReturnValue;
        this.hasOutput = false;
        this.basicblocks = new LinkedList<>();
        this.rPostOrder = new LinkedList<>();
        this.rPostOrderOnrCFG = new LinkedList<>();
        this.parameters = new LinkedList<>();
        this.usedGV = new HashSet<>();
        this.allUsedGV = new HashSet<>();
        this.usedPR = new HashSet<>();
        this.allUsedPR = new HashSet<>();
        this.callee = new HashSet<>();
        this.visitedBB = new HashSet<>();
        this.visitedFunc = new HashSet<>();
        if (type != Type.UserDefined && !name.equals("init")) {
            for (PhysicalRegister pr : RegisterSet.allRegs) {
                if (pr.name.equals("rbp")) continue;
                if (pr.name.equals("rsp")) continue;
                this.usedPR.add(pr);
                this.allUsedPR.add(pr);
            }
        }
    }

    private void dfsrPostOrder(BasicBlock node) {
        if (visitedBB.contains(node)) {
            return;
        }
        visitedBB.add(node);
        for  (BasicBlock bb: node.succ) {
            dfsrPostOrder(bb);
        }
        rPostOrder.addFirst(node);
    }

    private void dfsrPostOrderonrCFG(BasicBlock node) {
        if (visitedBB.contains(node)) {
            return;
        }
        visitedBB.add(node);
        for (BasicBlock bb: node.pred) {
            dfsrPostOrderonrCFG(bb);
        }
        rPostOrderOnrCFG.add(bb);
    }

    private void dfsAllUsedGV(Function node) {
        if (visitedFunc.contains(node)) {
            return;
        }
        visitedFunc.add(node);
        for (Function func: node.callee) {
            dfsAllUsedGV(func);
        }
        allUsedGV.addAll(node.usedGV);
    }

    private void dfsAllUsedPR(Function node) {
        if (visitedFunc.contains(node)) {
            return;
        }
        visitedFunc.add(node);
        for (Function func: node.callee) {
            dfsAllUsedPR(func);
        }
        allUsedPR.addAll(node.usedPR);
    }

    private boolean isSpecialBinaryOp(BinaryInst.BinaryOp op) {
        return op == BinaryInst.BinaryOp.MUL || op == BinaryInst.BinaryOp.DIV || op == BinaryInst.BinaryOp.MOD;
    }

    public void finishBuild() {
        for (BasicBlock bb: basicblocks) {
            bb.succ.clear();
            bb.pred.clear();
        }
        for (BasicBlock bb: basicblocks) {
            if (bb.tail instanceof CJump) {
                bb.succ.add(((CJump)bb.tail).thenBB);
                bb.succ.add(((CJump)bb.tail).elseBB);
            } else if (bb.tail instanceof Jump) {
                bb.succ.add(((Jump)bb.tail).targetBB);
            }
            for (BasicBlock suc: bb.succ) {
                suc.pred.add(bb);
            }
        }

        // trans the CJump instruction
        for (BasicBlock bb: basicblocks) {
            if (bb.tail instanceof CJump) {
                CJump cJump = (CJump)bb.tail;
                if (cJump.thenBB.pred.size() < cJump.elseBB.pred.size()) {
                    cJump.op = cJump.getNegativeCompareOp();
                    BasicBlock temp = cJump.thenBB;
                    cJump.thenBB = cJump.elseBB;
                    cJump.elseBB = temp;
                }
            }
        }

        visitedBB.clear();
        rPostOrder.clear();
        dfsrPostOrder(enterBB);

        visitedBB.clear();
        rPostOrderOnrCFG.clear();
        dfsrPostOrderonrCFG(leaveBB);

        visitedFunc.clear();
        dfsAllUsedGV(this);
    }

    public void finishAllocate() {
        for (BasicBlock bb : basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                if (inst instanceof Return) {
                    continue;
                }
                if (inst instanceof Call) {
                    usedPR.addAll(RegisterSet.callerSave);
                } else if (inst instanceof BinaryInst && isSpecialBinaryOp(((BinaryInst)inst).op) {
                    if (((BinaryInst)inst).src instanceof Register) {
                        usedPR.add((PhysicalRegister)((BinaryInst)inst).src);
                    }
                    usedPR.add(RegisterSet.rax);
                    usedPR.add(RegisterSet.rdx);
                } else {
                    usedPR.addAll(trans(inst.getUseRegs()));
                    usedPR.addAll(trans(inst.getDefRegs()));
                }
            }
        }
        visitedFunc.clear();
        dfsAllUsedPR(this);
    }

    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}

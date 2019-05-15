package Mxstar.IR.Instruction;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.IIRVisitor;
import Mxstar.IR.Operand.*;

import java.util.*;

public class UnaryInst extends IRInstruction {
    public enum UnaryOp {
        NEG, NOT, INC, DEC
    }

    public UnaryOp op;
    public Address dest;

    public UnaryInst(BasicBlock bb, UnaryOp op, Address dest) {
        super(bb);
        this.op = op;
        this.dest = dest;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (dest instanceof Memory) {
            regs.addAll(((Memory)dest).getUseRegs());
        } else if (dest instanceof Register) {
            regs.add((Register)dest);
        }
        return regs;
    }

    @Override
    public LinkedList<StackSlot> getStackSlots() {
        return defaultGetStackSlots(dest);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (dest instanceof Register && renameMap.containsKey(dest)) {
            dest = renameMap.get(dest);
        } else if (dest instanceof Memory) {
            dest = ((Memory)dest).copy();
            ((Memory)dest).renameUseReg(renameMap);
        }
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (dest instanceof Register && renameMap.containsKey(dest)) {
            dest = renameMap.get(dest);
        }
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (dest instanceof Register) {
            regs.add((Register)dest);
        }
        return regs;
    }

    @Override
    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}


package Mxstar.IR.Instruction;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.IIRVisitor;
import Mxstar.IR.Operand.*;

import java.util.*;

public class Pop extends IRInstruction {
    public Address dest;

    public Pop(BasicBlock bb, Address dest) {
        super(bb);
        this.dest = dest;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        LinkedList<Register> regs = new LinkedList<>();
        if (dest instanceof Memory) {
            regs.addAll(((Memory)dest).getUseRegs());
        }
        return regs;
    }

    @Override
    public LinkedList<StackSlot> getStackSlots() {
        return defaultGetStackSlots(dest);
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        if (dest instanceof Memory) {
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

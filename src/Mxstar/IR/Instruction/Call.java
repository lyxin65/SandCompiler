package Mxstar.IR.Instruction;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.Function;
import Mxstar.IR.IIRVisitor;
import Mxstar.IR.Operand.*;
import Mxstar.IR.RegisterSet;

import java.util.*;

public class Call extends IRInstruction {
    public Address dest;
    public Function func;
    public LinkedList<Operand> args;

    private void update() {
        Function caller = super.bb.function;
        caller.callee.add(func);
        if (func.name.equals("print") || func.name.equals("println")) {
            super.bb.function.hasOutput = true;
        }
    }

    public Call(BasicBlock bb, Address dest, Function func, Operand... args) {
        super(bb);
        this.dest = dest;
        this.func = func;
        this.args = new LinkedList<>(Arrays.asList(args));
        update();
    }

    public Call(BasicBlock bb, Address dest, Function func, LinkedList<Operand> args) {
        super(bb);
        this.dest = dest;
        this.func = func;
        this.args = new LinkedList<>(args);
        update();
    }

    public LinkedList<Register> getCallUsed() {
        LinkedList<Register> registers = new LinkedList<>();
        for (Operand opr: args) {
            if (opr instanceof Memory) {
                registers.addAll(((Memory)opr).getUseRegs());
            } else if (opr instanceof VirtualRegister) {
                registers.add((Register)opr);
            }
        }
        return registers;
    }

    @Override
    public LinkedList<Register> getUseRegs() {
        return new LinkedList<>(RegisterSet.vargs.subList(0, Integer.min(6, args.size())));
    }

    @Override
    public LinkedList<Register> getDefRegs() {
        return new LinkedList<>(RegisterSet.vcallerSave);
    }

    @Override
    public LinkedList<StackSlot> getStackSlots() {
        LinkedList<StackSlot> slots = new LinkedList<>();
        slots.addAll(defaultGetStackSlots(dest));
        for (Operand opr: args) {
            if (opr instanceof StackSlot) {
                slots.add((StackSlot)opr);
            }
        }
        return slots;
    }

    @Override
    public void renameUseReg(HashMap<Register, Register> renameMap) {
        // pass
    }

    @Override
    public void renameDefReg(HashMap<Register, Register> renameMap) {
        if (dest instanceof Register && renameMap.containsKey(dest)) {
            dest = renameMap.get(dest);
        }
    }

    @Override
    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}

package Mxstar.IR.Instruction;

public abstract class IRInstruction {
    public BasicBlock bb;
    public IRInstruction prev;
    public IRInstruction next;

    public IRInstruction() {
        this.bb = null;
    }

    public IRInstruction(BasicBlock bb) {
        this.bb = bb;
    }

    /*
     * prev this
     * =>
     * prev inst this
     * */
    public void prepend(IRInstruction inst) {
        if (prev == null) {
            inst.next = this;
            this.prev = inst;
            bb.head = inst;
        } else {
            prev.next = inst;
            inst.prev = prev;
            inst.next = this;
            this.prev = inst;
        }
    }

    /*
     * this next
     * =>
     * this inst next
     * */
    public void append(IRInstruction inst) {
        if (next == null) {
            this.next = inst;
            inst.prev = this;
            bb.tail = inst;
        } else {
            inst.next = this.next;
            this.next.prev = inst;
            this.next = inst;
            inst.prev = this;
        }
    }

    public void remove() {
        if (prev == null && next == null) {
            bb.head = bb.tail = null;
        } else if (prev == null) {
            bb.head = this.next;
            this.next.prev = null;
        } else if (next == null) {
            bb.tail = this.prev;
            this.prev.next = null;
        } else {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

    public void replace(IRInstruction inst) {
        if (prev == null && next == null) {
            bb.head = bb.tail = inst;
            inst.prev = inst.next = null;
        } else if (prev == null) {
            bb.head = inst;
            inst.prev = null;
            this.next.prev = inst;
            inst.next = this.next;
        } else if (next == null) {
            inst.prev = this.prev;
            this.prev.next = inst;
            bb.tail = inst;
            inst.next = null;
        } else {
            inst.prev = this.prev;
            this.prev.next = inst;
            inst.next = this.next;
            this.next.prev = inst;
        }
    }

    public abstract void renameUseReg(HashMap<Register, Register> renameMap);
    public abstract void renameDefReg(HashMap<Register, Register> renameMap);
    public abstract LinkedList<Register> getDefRegs();
    public abstract LinkedList<Register> getUseRegs();
    public abstract LinkedList<StackSlot> getStackSlots();

    LinkedList<StackSlot> defaultGetStackSlots(Operand... oprs) {
        LinkedList<StackSlot> slots = new LinkedList<>();
        for (Operand opr: oprs) {
            if (opr instanceof StackSlot) {
                slots.add((StackSlot)opr);
            }
        }
        return slots;
    }

    public abstract void accept(IIRVisitor visitor);
}

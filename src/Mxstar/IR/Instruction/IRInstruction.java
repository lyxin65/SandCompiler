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

    public void append(IRInstruction inst) {
        if (next == null) {
        }
    }
}


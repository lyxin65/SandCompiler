package Mxstar.IR;

import Mxstar.IR.Instruction.CJump;
import Mxstar.IR.Instruction.Jump;
import Mxstar.IR.Instruction.Return;
import Mxstar.IR.Instruction.IRInstruction;

import java.util.LinkedList;

public class BasicBlock {
    public String hint;
    public Function function;
    public IRInstruction head;
    public IRInstruction tail;

    public LinkedList<BasicBlock> pred = null;
    public LinkedList<BasicBlock> succ = null;

    private static int globalBlockID = 0;
    public int blockID;

    public BasicBlock(Function function, String hint) {
        this.function = function;
        this.hint = hint;
        this.pred = new LinkedList<>();
        this.succ = new LinkedList<>();
        function.basicblocks.add(this);
        blockID = globalBlockID++;
    }

    public void prepend(IRInstruction inst) {
        head.prepend(inst);
    }

    public void append(IRInstruction inst) {
        if (tail instanceof CJump || tail instanceof Jump || tail instanceof Return) {
            return;
        }
        if (head == null) {
            inst.pre = inst.nxt = null;
            head = tail = inst;
        } else {
            tail.append(inst);
        }
    }

    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}

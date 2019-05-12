package Mxstar.IR.Operand;

import Mxstar.IR.IIRVisitor;

public class PhysicalRegister extends Register {
    public String name;

    @Override
    public void accept(IIRVisitor visitor) {
        visitor.visit(this);
    }
}


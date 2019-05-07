package Mxstar.IR;

import Mxstar.IR.Instruction.*;
import Mxstar.IR.Operand.*;

import Mxstar.Symbol.VarSymbol;

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
    public LinkedList<BasicBlock> reversePostOrder;
    public LinkedList<BasicBlock> reversePostOrderOnReverseCFG;
    public LinkedList<VirtualRegister> parameters;

    public HashSet<VarSymbol> usedGlobalVars;
    public HashSet<VarSymbol> recursiveUsedGlobalVars;
    public HashSet<PhysicalRegister> usedPhysicalRegsiters;
    public HashSet<PhysicalRegister> recursiveUsedPRegisters;

}

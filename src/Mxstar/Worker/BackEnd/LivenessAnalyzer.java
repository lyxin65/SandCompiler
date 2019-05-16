package Mxstar.Worker.BackEnd;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.Function;
import Mxstar.IR.Instruction.Call;
import Mxstar.IR.Instruction.IRInstruction;
import Mxstar.IR.Instruction.Move;
import Mxstar.IR.Operand.Register;
import Mxstar.IR.Operand.VirtualRegister;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.BiConsumer;

public class LivenessAnalyzer {

    public static class Graph {
        private HashMap<VirtualRegister, HashSet<VirtualRegister>> g;

        Graph() {
            g = new HashMap<>();
        }

        Graph(Graph g) {
            this.g = new HashMap<>();
            for (VirtualRegister reg : g.getAllRegisters())
                this.g.put(reg, new HashSet<>(g.getAdj(reg)));
        }

        void addRegiser(VirtualRegister vr) {
            if (g.containsKey(vr)) return;
            g.put(vr, new HashSet<>());
        }

        void addRegisers(Collection<VirtualRegister> vrs) {
            for (VirtualRegister reg : vrs)
                addRegiser(reg);
        }

        void addEdge(VirtualRegister a, VirtualRegister b) {
            if (a == b) return;
            g.get(a).add(b);
            g.get(b).add(a);
        }

        void delEdge(VirtualRegister a, VirtualRegister b) {
            if (g.containsKey(a) && g.get(a).contains(b)) {
                g.get(a).remove(b);
                g.get(b).remove(a);
            }
        }

        void delRegister(VirtualRegister vr) {
            for (VirtualRegister reg : getAdj(vr))
                g.get(reg).remove(vr);
            g.remove(vr);
        }

        int getDegree(VirtualRegister a) {
            return g.containsKey(a) ? g.get(a).size() : 0;
        }

        void clear() {
            g.clear();
        }

        void forEach(BiConsumer<VirtualRegister, VirtualRegister> consumer) {
            for (VirtualRegister reg1 : g.keySet())
                for (VirtualRegister reg2 : g.get(reg1))
                    consumer.accept(reg1, reg2);
        }

        Collection<VirtualRegister> getAdj(VirtualRegister a) {
            return g.getOrDefault(a, new HashSet<>());
        }

        Collection<VirtualRegister> getAllRegisters() {
            return g.keySet();
        }

    }

    public HashMap<BasicBlock, HashSet<VirtualRegister>> liveOut;
    public HashMap<BasicBlock, HashSet<VirtualRegister>> usedRegisters;
    public HashMap<BasicBlock, HashSet<VirtualRegister>> definedRegisters;

    private void init(Function function) {
        liveOut = new HashMap<>();
        usedRegisters = new HashMap<>();
        definedRegisters = new HashMap<>();
        for (BasicBlock bb : function.basicblocks) {
            liveOut.put(bb, new HashSet<>());
            usedRegisters.put(bb, new HashSet<>());
            definedRegisters.put(bb, new HashSet<>());
        }
    }

    private void initUsedAndDefinedRegisters(BasicBlock bb, boolean nowAfterAllocate) {
        HashSet<VirtualRegister> bbUsedRegisters = new HashSet<>();
        HashSet<VirtualRegister> bbDefinedRegisters = new HashSet<>();
        for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
            LinkedList<Register> usedRegs;
            if (inst instanceof Call && !nowAfterAllocate)
                usedRegs = ((Call) inst).getCallUsed();
            else
                usedRegs = inst.getUseRegs();
            for (VirtualRegister reg : trans(usedRegs))
                if (!bbDefinedRegisters.contains(reg))
                    bbUsedRegisters.add(reg);
            bbDefinedRegisters.addAll(trans(inst.getDefRegs()));
        }
        definedRegisters.put(bb, bbDefinedRegisters);
        usedRegisters.put(bb, bbUsedRegisters);
    }

    private boolean isMoveBetweenRegisters(IRInstruction inst) {
        if (inst instanceof Move) {
            Move move = (Move) inst;
            return move.dest instanceof VirtualRegister && move.src instanceof VirtualRegister;
        } else {
            return false;
        }
    }

    public LinkedList<VirtualRegister> trans(Collection<Register> registers) {
        LinkedList<VirtualRegister> virtualRegisters = new LinkedList<>();
        for (Register reg : registers) {
            virtualRegisters.add((VirtualRegister) reg);
        }
        return virtualRegisters;
    }

    private void calcLiveOut(Function function, boolean nowAfterAllocate) {
        init(function);

        for (BasicBlock bb : function.basicblocks)
            initUsedAndDefinedRegisters(bb, nowAfterAllocate);

        boolean changed = true;
        while (changed) {
            changed = false;
            LinkedList<BasicBlock> basicBlocks = function.rPostOrderOnrCFG;
            for (BasicBlock bb : basicBlocks) {
                int oldSize = liveOut.get(bb).size();
                for (BasicBlock succ : bb.succ) {
                    HashSet<VirtualRegister> regs = new HashSet<>(liveOut.get(succ));
                    regs.removeAll(definedRegisters.get(succ));
                    regs.addAll(usedRegisters.get(succ));
                    liveOut.get(bb).addAll(regs);
                }
                changed = changed || liveOut.get(bb).size() != oldSize;
            }
        }
    }

    public HashMap<BasicBlock, HashSet<VirtualRegister>> getLiveOut(Function function) {
        calcLiveOut(function, false);
        return liveOut;
    }

    public void getInferenceGraph(Function function, Graph inferenceGraph, Graph moveGraph) {
        calcLiveOut(function, true);

        inferenceGraph.clear();
        if (moveGraph != null)
            moveGraph.clear();

        for (BasicBlock bb : function.basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                inferenceGraph.addRegisers(trans(inst.getDefRegs()));
                inferenceGraph.addRegisers(trans(inst.getUseRegs()));
            }
        }

        for (BasicBlock bb : function.basicblocks) {
            HashSet<VirtualRegister> liveNow = new HashSet<>(liveOut.get(bb));
            for (IRInstruction inst = bb.tail; inst != null; inst = inst.prev) {
                boolean isMBR = isMoveBetweenRegisters(inst);
                for (VirtualRegister reg1 : trans(inst.getDefRegs())) {
                    for (VirtualRegister reg2 : liveNow) {
                        if (isMBR && moveGraph != null && ((Move) inst).src == reg1) {
                            moveGraph.addEdge(reg1, reg2);
                            continue;
                        }
                        inferenceGraph.addEdge(reg1, reg2);
                    }
                }
                liveNow.removeAll(trans(inst.getDefRegs()));
                liveNow.addAll(trans(inst.getUseRegs()));
            }
        }

        /* remove some invalid <reg,reg> in move g */
        if (moveGraph != null) {
            inferenceGraph.forEach(moveGraph::delEdge);
        }
    }
}

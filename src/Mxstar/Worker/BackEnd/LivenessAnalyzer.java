package Mxstar.Worker.BackEnd;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.Function;
import Mxstar.IR.Instruction.*;
import Mxstar.IR.Operand.*;

import java.util.*;

public class LivenessAnalyzer {
    public static class Graph {
        private HashMap<VirtualRegister, HashSet<VirtualRegister>> g;

        Graph() {
            g = new HashMap<>();
        }

        Graph(Graph graph) {
            g = new HashMap<>();
            for (VirtualRegister vr: graph.getAllRegsiters()) {
                g.put(vr, new HashSet<>(g.getAdj(vr)));
            }
        }

        void addRegister(VirtualRegister vr) {
            if (!g.containsKey(vr)) {
                g.put(vr, new HashSet<>());
            }
        }

        void addRegs(Collection<VirtualRegister> vrs) {
            for (VirtualRegister vr: vrs) {
                addRegister(vr);
            }
        }

        void addEdge(VirtualRegister a, VirtualRegister b) {
            if (a != b) {
                g.get(a).add(b);
                g.get(b).add(a);
            }
        }

        void delEdge(VirtualRegister a, VirtualRegister b) {
            if (g.containsKey(a) && g.containsKey(b)) {
                g.get(a).remove(b);
                g.get(b).remove(a);
            }
        }

        void delRegister(VirtualRegister a) {
            for (VirtualRegister b: getAdj(a)) {
                g.get(b).remove(a);
            }
            g.remove(a);
        }

        int getDegree(VirtualRegister a) {
            return g.containsKey(a) ? g.get(a).size() : 0;
        }

        boolean isLinked(VirtualRegister a, VirtualRegister b) {
            return g.containsKey(a) && g.get(a).containsKey(b);
        }

        void clear() {
            g.clear();
        }

        void forEach(BiConsumer<VirtualRegister, VirtualRegister> consumer) {
            for (VirtualRegister a: g.keySet()) {
                for (VirtualRegister b: g.get(a)) {
                    consumer.accept(a, b);
                }
            }
        }

        Collection<VirtualRegister> getAdj(VirtualRegister a) {
            return graph.getOrDefault(a, new HashSet<>());
        }

        Collection<VirtualRegister> getAllRegisters() {
            return graph.keySet();
        }
    }

    public HashMap<BasicBlock, HashSet<VirtualRegister>> liveOut;
    public HashMap<BasicBlock, HashSet<VirtualRegister>> usedRegs;
    public HashMap<BasicBlock, HashSet<VirtualRegister>> definedRegs;

    private void init(Mxstar.IR.Function function) {
        liveOut = new HashMap<>();
        usedRegs = new HashMap<>();
        definedRegs = new HashMap<>();
        for (BasicBlock bb : function.basicblocks) {
            liveOut.put(bb, new HashSet<>());
            usedRegs.put(bb, new HashSet<>());
            definedRegs.put(bb, new HashSet<>());
        }
    }

    private void initUsedAndDefinedRegs(BasicBlock bb, boolean nowAfteAllocate) {
        HashSet<VirtualRegister> bbUsedRegs = new HashSet<>();
        HashSet<VirtualRegister> bbDefinedRegs = new HashSet<>();
        for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
            LinkedList<Register> used;
            if (inst instanceof Call && !nowAfteAllocate) {
                used = ((Call)inst).getCallUsed();
            } else {
                used = inst.getUseRegs();
            }
            for (VirtualRegister reg: trans(used)) {
                if (!bbDefinedRegs.contains(reg)) {
                    bbUsedRegs.add(reg);
                }
            }
            bbDefinedRegs.addAll(trans(inst.getDefRegs));
        }
        usedRegs.put(bb, bbUsedRegs);
        definedRegs.put(bb, bbDefinedRegs);
    }

    private boolean isMoveBetweenRegs(IRInstruction inst) {
        if (inst instanceof Move) {
            Move move = (Move)inst;
            return move.dest instanceof VirtualRegister && move.src instanceof VirtualRegister;
        } else {
            return false;
        }
    }

    public LinkedList<VirtualRegister> trans(Collection<Register> regs) {
        LinkedList<VirtualRegister> res = new LinkedList<>();
        for (VirtualRegister reg: regs) {
            res.add((VirtualRegister)reg);
        }
        return res;
    }

    private void calcLiveOut(Mxstar.IR.Function function, boolean nowAfteAllocate) {
        init(function);

        for (BasicBlock bb: function.basicblocks) {
            initUsedAndDefinedRegs(bb, nowAfteAllocate);
        }

        boolean flag = true;
        while (flag) {
            flag = false;
            LinkedList<BasicBlock> bbs = function.rPostOrderOnrCFG;
            for (BasicBlock bb: bbs) {
                int oldSize = liveOut.get(bb).size();
                for (BasicBlock s: bb.succ) {
                    HashSet<VirtualRegister> regs = new HashSet<>(liveOut.get(s));
                    regs.removeAll(definedRegs.get(s));
                    regs.addAll(usedRegs.get(s));
                    liveOut.get(bb).addAll(regs);
                }
                flag = flag || liveOut.get(bb).size() != oldSize;
            }
        }
    }

    public HashMap<BasicBlock, HashSet<VirtualRegister>> getLiveOut(Function function) {
        calcLiveOut(function, false);
        return liveOut;
    }

    public void getInferenceGraph(Function function, Graph inferGraph, Graph moveGraph) {
        calcLiveOut(function, true);

        inferGraph.clear();
        if (moveGraph != null) {
            moveGraph.clear();
        }
        
        for (BasicBlock bb: function.basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                inferGraph.addRegs(trans(inst.getUseRegs()));
                inferGraph.addRegs(trans(inst.getDefRegs()));
            }
        }

        // calulate
        for (BasicBlock bb: function.basicblocks) {
            HashSet<VirtualRegister> liveNow = new HashSet<>(liveOut.get(bb));
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                boolean isMBR = isMoveBetweenRegs(inst);
                for (VirtualRegister a: trans(inst.getDefRegs())) {
                    for (VirtualRegister b: liveNow) {
                        if (isMBR && moveGraph != null && ((Move)inst).src == a) {
                            moveGraph.addEdge(a, b);
                        } else {
                            inferGraph.addEdge(a, b);
                        }
                    }
                }
                liveNow.removeAll(trans(inst.getDefRegs()));
                liveNow.addAll(trans(inst.getUseRegs()));
            }
        }

        if (moveGraph != null) {
            inferGraph.forEach(moveGraph::delEdge);
        }
    }

}

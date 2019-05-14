package Mxstar.Worker.BackEnd;

import Mxstar.IR.BasicBlock;
import Mxstar.IR.Function;
import Mxstar.IR.Instruction.*;
import Mxstar.IR.Operand.*;

import java.util.*; 

public SimpleGraphAllocator {
    private IRProgram ir;
    private LinkedList<PhysicalRegister> generealRegisters = new LinkedList<>();
    private int K;

    private static LivenessAnalyzer livenessAnalyzer = new LivenessAnalyzer();

    public SimpleGraphAllocator(IRProgram ir)  {
        this.ir = ir;
        for (PhysicalRegister pr: RegisterSet.allRegs) {
            if (pr.name.equals("rsp") || pr.name.equals("rbp")) {
                continue;
            }
            generealRegisters.add(pr);
        }
        K = generealRegisters.size();
    }

    private LinkedList<VirtualRegister> trans(LinkedList<Register> regs) {
        LinkedList<VirtualRegister> res = new LinkedList<>();
        for (VirtualRegister reg: regs) {
            res.add((VirtualRegister)reg);
        }
        return res;
    }

    public void run() {
        for (Function function: ir.function) {
            this.function = function;
            processFunction();
        }
    }

    Function function;
    Graph originGraph;
    Graph graph;

    HashSet<VirtualRegister> simpleSet;
    HashSet<VirtualRegister> spillSet;
    HashSet<VirtualRegister> spilledRegs;
    LinkedList<VirtualRegister> selectStack;
    HashMap<VirtualRegister, PhysicalRegister> colors;

    private void init() {
        simpleSet = new HashSet<>();
        spillSet = new HashSet<>();
        spilledRegs = new HashSet<>();
        selectStack = new LinkedList<>();
        colors = new HashMap<>();
        for (VirtualRegister vr: graph.getAllRegisters()) {
            if (graph.getDegree() < K) {
                simpleSet.add(vr);
            } else {
                spillSet.add(vr);
            }
        }
    }

    private void simpleWork() {
        VirtualRegister reg = simpleSet.iterator().next();
        LinkedList<VirtualRegister> adj = new LinkedList<>(graph.getAdj(reg));
        graph.delRegister(reg);

        for (VirtualRegister vr: adj) {
            if (graph.getDegree(vr) < K && spillSet.contains(vr)) {
                spillSet.remove(vr);
                simpleSet.add(vr);
            }
        }
        simpleSet.remove(reg);
        selectStack.addFirst(reg);
    }

    private void spill() {
        VirtualRegister todo = null;
        int rank = -2;
        for (VirtualRegister vr: spillSet) {
            int curRank = graph.getDegree(vr);
            if (vr.allocatedPhysicalRegister != null) {
                curRank = -1;
            }
            if (curRank > rank) {
                rank = curRank;
                todo = vr;
            }
        }
        graph.delRegister(todo);
        spillSet.remove(todo);
        selectStack.addFirst(todo);
    }

    private void assignColors() {
        for (VirtualRegister vr: selectStack) {
            if (vr.allocatedPhysicalRegister != null) {
                colors.put(vr, vr.allocatedPhysicalRegister);
            }
        }
        for (VirtualRegister vr: selectStack) {
            if (vr.allocatedPhysicalRegister != null) {
                continue;
            }
            HashSet<PhysicalRegister> okColors = new HashSet<>(generalRegisters);
            for (VirtualRegister neighbor: orignGraph.getAdjacents(vr)) {
                if (colors.containsKey(neighbor)) {
                    okColors.remove(colors.get(neighbor));
                }
            }
            if (okColors.isEmpty()) {
                spilledRegisers.add(vr);
            } else {
                PhysicalRegister pr = null;
                for (PhysicalRegister reg : RegisterSet.callerSave) {
                    if (okColors.contains(reg)) {
                        pr = reg;
                        break;
                    }
                }
                if (pr == null) {
                    pr = okColors.iterator().next();
                }
                colors.put(vr, pr);
            }
        }
    }

    private void rewriteFunction() {
        HashMap<VirtualRegister, Memory> spillPlaces = new HashMap<>();
        for (VirtualRegister vr: spilledRegisers) {
            if (vr.spillPlace != null) {
                spillPlaces.put(vr,  vr.spillPlace);
            } else {
                spillPlaces.put(vr, new StackSlot(vr.hint));
            }
        }
        for (BasicBlock bb: function.basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                LinkedList<VirtualRegister> used = new LinkedList<>(trans(inst.getUseRegs()));
                LinkedList<VirtualRegister> defined = new LinkedList<>(trans(inst.getDefRegs()));
                HashMap<Register, Register> renameMap = new HashMap<>();
                used.retainAll(spilledRegisers);
                defined.retainAll(spilledRegisers);
                for (VirtualRegister reg: used) {
                    if (!renameMap.containsKey(reg)) renameMap.put(reg, new VirtualRegister(""));
                }
                for (VirtualRegister reg: defined) {
                    if (!renameMap.containsKey(reg)) renameMap.put(reg, new VirtualRegister(""));
                }
                inst.renameDefReg(renameMap);
                inst.renameUseReg(renameMap);
                for (VirtualRegister reg: used) {
                    inst.prepend(new Move(inst.bb, renameMap.get(reg), spillPlaces.get(reg)));
                }
                for (VirtualRegister reg: defined) {
                    inst.append(new Move(inst.bb, spillPlaces.get(reg), renameMap.get(reg)));
                    inst = inst.next;
                }
            }
        }
    }
    
    private void replaceRegisers() {
        HashMap<Register, Register> renameMap = new HashMap<>();
        for (HashMap.Entry<VirtualRegister, PhysicalRegister> entry: colors.entrySet()) {
            renameMap.put(entry.getKey(), entry.getValue());
        }
        for (BasicBlock bb: function.basicblocks) {
            for (IRInstruction inst = bb.head; inst != null; inst = inst.next) {
                inst.renameUseReg(renameMap);
                inst.renameDefReg(renameMap);
            }
        }
    }

    private void processFunction() {
        orignGraph = new Graph();
        while (true) {
            livenessAnalyzer.getInferenceGraph(function, orignGraph, null);
            graph = new Graph(orignGraph);
            init();
            do {
                if (!simplifyWorklist.isEmpty()) simplify();
                else if (!spillWorklist.isEmpty()) spill();
            } while (!simplifyWorklist.isEmpty() || !spillWorklist.isEmpty());
            assignColors();
            if (!spilledRegisers.isEmpty()) {
                rewriteFunction();
            } else {
                replaceRegisers();
                break;
            }
        }
        function.finishAllocate();
    }
}

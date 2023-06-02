package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 消除位置計算者結果 TODO
public class EliminateCtrResult {
    private final ConstMathSlot.EliminationType eliminationType; // 消除類型
    private final List<List<Boolean>> eliminatePosition; // 消除位置
    private final List<List<Boolean>> eliminatePositionDamp; // 含破框消除位置

    public EliminateCtrResult(ConstMathSlot.EliminationType eliminationType, List<List<Boolean>> eliminatePosition, List<List<Boolean>> eliminatePositionDamp) {
        this.eliminationType = eliminationType;
        this.eliminatePosition = eliminatePosition;
        this.eliminatePositionDamp = eliminatePositionDamp;
    }

    public ConstMathSlot.EliminationType getEliminationType() {
        return eliminationType;
    }

    public List<List<Boolean>> getEliminatePosition() {
        return eliminatePosition;
    }

    public List<List<Boolean>> getEliminatePositionDamp() {
        return eliminatePositionDamp;
    }
}

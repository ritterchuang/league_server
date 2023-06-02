package org.lsj.gs.math.core.slot.progressHlrMgr.enity.input;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;

import java.util.List;

// 遊戲進度處理者參數 消除
public class ProgressHlrTotalInputCascade extends AbstractProgressHlrTotalInput{
    private final List<CascadeHlrResult> preCascadeHlrResultList; // 之前消除處理結果
    private final EliminateCtrResult currentEliminateCtrResult; // 此次消除結果

    public ProgressHlrTotalInputCascade(int processIndex, List<CascadeHlrResult> preCascadeHlrResultList, EliminateCtrResult currentEliminateCtrResult) {
        super(processIndex);
        this.preCascadeHlrResultList = preCascadeHlrResultList;
        this.currentEliminateCtrResult = currentEliminateCtrResult;
    }

    public List<CascadeHlrResult> getPreCascadeHlrResultList() {
        return preCascadeHlrResultList;
    }

    public EliminateCtrResult getCurrentEliminateCtrResult() {
        return currentEliminateCtrResult;
    }
}

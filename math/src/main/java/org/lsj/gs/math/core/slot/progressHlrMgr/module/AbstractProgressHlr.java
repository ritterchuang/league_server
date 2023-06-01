package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil.IProgressHlrUtil;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil.ProgressHlrUtil;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 抽象遊戲進度處理者
public abstract class AbstractProgressHlr implements IProgressHlr, IProgressHlrUtil {
    private final ProgressHlrUtil progressHlrUtil; // 進度處理工具包

    public AbstractProgressHlr() {
        this.progressHlrUtil = new ProgressHlrUtil();
    }

    // 計算遊戲進度處理者結果
    @Override
    public abstract ProgressHlrResult calculateProgressHlrResult(int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster);

    // 取得初始場次
    @Override
    public abstract int getDefaultRound(GameStateHlrResult beforeGameStateHlrResult);

    // 計算當前場次
    @Override
    public int calculateCurrentProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        return this.progressHlrUtil.calculateCurrentProgress(progressHlrTotalInput);
    }

    // 計算總場次
    @Override
    public abstract int calculateTotalProgress(AbstractProgressHlrTotalInput progressHlrTotalInput);

    // 計算新增場次
    @Override
    public abstract int calculateAddProgress(int totalProcess, AbstractProgressHlrTotalInput progressHlrTotalInput);

    // 計算最大場次標誌
    protected boolean calculateMaxProgress(int totalProcess, int maxProcess){
        return this.progressHlrUtil.calculateMaxProgress(totalProcess, maxProcess);
    }
}

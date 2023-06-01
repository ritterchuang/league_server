package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.ProgressHlrTotalInputRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 遊戲進度處理者 場次
public class ProgressHlrRound extends AbstractProgressHlr{
    private final ConstMathSlot.ProgressType progressType; // 遊戲進度類型
    private final ProgressConfigExtendRound configExtend; // 遊戲進度額外設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public ProgressHlrRound(ProgressConfig config, ITableUtil tableUtil) {
        this.progressType = config.getProgressType();
        this.configExtend = (ProgressConfigExtendRound) config.getProgressConfigExtend();
        this.tableUtil = tableUtil;
    }

    // 計算遊戲進度處理者結果
    @Override
    public ProgressHlrResult calculateProgressHlrResult(int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        // 1. 取得進度處理者參數
        ProgressHlrTotalInputRound inputRound = new ProgressHlrTotalInputRound(roundIndex, beforeGameStateHlrResult, preRoundHlrResultList, specialFeatureHlrResultCluster);

        // 2. 計算 當前場次
        int currentRound = super.calculateCurrentProgress(inputRound);

        // 3. 計算總場次
        int totalRound = this.calculateTotalProgress(inputRound);

        // 4. 計算增加場次
        int addRound = this.calculateAddProgress(totalRound, inputRound);

        // 5. 計算最大場次標誌
        boolean isMaxProgress = this.calculateMaxProgress(totalRound);

        // 6. 回傳結果
        return new ProgressHlrResult(
                isMaxProgress,
                this.progressType,
                new ProgressHlrResultExtendRound(
                        new RoundProgress(currentRound, addRound, totalRound)));
    }

    // 取得初始場次
    @Override
    public int getDefaultRound(GameStateHlrResult beforeGameStateHlrResult) {
        return this.configExtend.getDefaultRound();
    }

    // 計算總場次
    @Override
    public int calculateTotalProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        ProgressHlrTotalInputRound inputRound = (ProgressHlrTotalInputRound) progressHlrTotalInput;

        if (inputRound.getProcessIndex() == 0) {
            return this.getDefaultRound(inputRound.getBeforeGameStateHlrResult());
        }

        ProgressHlrResultExtendRound preRoundResult = (ProgressHlrResultExtendRound)(inputRound.getPreRoundHlrResultList().get(inputRound.getProcessIndex() - 1).getProgressHlrResult().getProgressHlrResultExtend());
        return preRoundResult.getRoundProgress().getTotalRound() + preRoundResult.getRoundProgress().getAddRound();
    }

    // 計算新增場次
    @Override
    public int calculateAddProgress(int totalProcess, AbstractProgressHlrTotalInput progressHlrTotalInput) {
        // 1. 取出特殊事件列表
        ProgressHlrTotalInputRound inputRound = (ProgressHlrTotalInputRound) progressHlrTotalInput;
        List<SpecialFeatureHlrResult> specialFeatureHlrResultList = inputRound.getCurrentSpecialFeatureHlrResultCluster().getSpecialFeatureHlrResultList();

        // 2. 依照滿足設定取出對應初始局數
        for (SpecialFeatureHlrResult specialFeatureHlrResult : specialFeatureHlrResultList) {
            ConstMathSlot.TriggerEvent triggerEvent = specialFeatureHlrResult.getTriggerEvent();

            if (ConstMathSlot.TriggerEvent.isReTrigger(triggerEvent)) {
                return Math.min(this.configExtend.getAddRound(), this.configExtend.getMaxRound() - totalProcess);
            }
        }

        // 3. 若無滿足，回傳0
        return 0;
    }

    // 計算最大場次標誌
    @Override
    public boolean calculateMaxProgress(int totalProcess) {
        return super.calculateMaxProgress(totalProcess, this.configExtend.getMaxRound());
    }
}

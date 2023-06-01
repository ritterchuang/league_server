package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.ProgressHlrTotalInputTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 遊戲進度處理者 場次觸發局
public class ProgressHlrTriggerRound extends AbstractProgressHlr{
    private final ConstMathSlot.ProgressType progressType; // 遊戲進度類型
    private final ProgressConfigExtendTriggerRound configExtend; // 遊戲進度額外設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public ProgressHlrTriggerRound(ProgressConfig config, ITableUtil tableUtil) {
        this.progressType = config.getProgressType();
        this.configExtend = (ProgressConfigExtendTriggerRound) config.getProgressConfigExtend();
        this.tableUtil = tableUtil;
    }

    // 計算遊戲進度處理者結果
    @Override
    public ProgressHlrResult calculateProgressHlrResult(int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        // 1. 取得進度處理者參數
        ProgressHlrTotalInputTriggerRound inputTriggerRound = new ProgressHlrTotalInputTriggerRound(roundIndex, beforeGameStateHlrResult, preRoundHlrResultList, specialFeatureHlrResultCluster);

        // 2. 計算當前場次
        int currentRound = super.calculateCurrentProgress(inputTriggerRound);

        // 3. 計算總場次
        int totalRound = this.calculateTotalProgress(inputTriggerRound);

        // 4. 計算增加場次
        int addRound = this.calculateAddProgress(totalRound, inputTriggerRound);

        // 5. 計算最大場次標誌
        boolean isMaxProgress = this.calculateMaxProgress(totalRound);

        // 6. 回傳結果
        return new ProgressHlrResult(
                isMaxProgress,
                this.progressType,
                new ProgressHlrResultExtendTriggerRound(
                        new RoundProgress(currentRound, addRound, totalRound)));
    }

    // 取得初始場次
    @Override
    public int getDefaultRound(GameStateHlrResult beforeGameStateHlrResult) {

        // 1. 防呆，如前一遊戲狀態特殊事件列表為空則回傳0
        if(beforeGameStateHlrResult.getRoundHlrResultList().isEmpty()){
            return 0;
        }

        // 2. 取出前一遊戲狀態特殊事件列表
        List<SpecialFeatureHlrResult> beforeGameSpecialFeatureHlrResultList = beforeGameStateHlrResult.getRoundHlrResultList().get(beforeGameStateHlrResult.getRoundHlrResultList().size() - 1).getSpecialFeatureHlrResultCluster().getSpecialFeatureHlrResultList();

        // 3. 依照滿足設定取出對應初始局數
        for (SpecialFeatureHlrResult specialFeatureHlrResult : beforeGameSpecialFeatureHlrResultList) {
            ConstMathSlot.TriggerEvent triggerEvent = specialFeatureHlrResult.getTriggerEvent();

            if (this.configExtend.getEventToDefaultRoundMap().containsKey(triggerEvent)) {
                return this.configExtend.getEventToDefaultRoundMap().get(triggerEvent);
            }
        }

        // 4. 若無滿足，回傳0
        return 0;
    }

    // 計算總場次
    @Override
    public int calculateTotalProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        ProgressHlrTotalInputTriggerRound inputTriggerRound = (ProgressHlrTotalInputTriggerRound) progressHlrTotalInput;

        if (inputTriggerRound.getProcessIndex() == 0) {
            return this.getDefaultRound(inputTriggerRound.getBeforeGameStateHlrResult());
        }

        ProgressHlrResultExtendTriggerRound preRoundResult = (ProgressHlrResultExtendTriggerRound)(inputTriggerRound.getPreRoundHlrResultList().get(inputTriggerRound.getProcessIndex() - 1).getProgressHlrResult().getProgressHlrResultExtend());
        return preRoundResult.getRoundProgress().getTotalRound() + preRoundResult.getRoundProgress().getAddRound();
    }

    // 計算新增場次
    @Override
    public int calculateAddProgress(int totalProcess, AbstractProgressHlrTotalInput progressHlrTotalInput) {
        // 1. 取出特殊事件列表
        ProgressHlrTotalInputTriggerRound inputTriggerRound = (ProgressHlrTotalInputTriggerRound) progressHlrTotalInput;
        List<SpecialFeatureHlrResult> specialFeatureHlrResultList = inputTriggerRound.getCurrentSpecialFeatureHlrResultCluster().getSpecialFeatureHlrResultList();

        // 2. 依照滿足設定取出對應初始局數
        for (SpecialFeatureHlrResult specialFeatureHlrResult : specialFeatureHlrResultList) {
            ConstMathSlot.TriggerEvent triggerEvent = specialFeatureHlrResult.getTriggerEvent();

            if (this.configExtend.getEventToAddRoundMap().containsKey(triggerEvent)) {
                int expectAddRound = this.configExtend.getEventToAddRoundMap().get(triggerEvent);
                return Math.min(expectAddRound, this.configExtend.getMaxRound() - totalProcess);
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

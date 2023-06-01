package com.lx.gs.math.games.dgry_java.module.gameStateHlr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.AbstractGameStateHlr;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import com.lx.gs.math.core.common.logic.logicSlot.ILogicSlot;
import com.lx.gs.math.core.common.logic.logicSlot.LogicSlotCascade;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtendDgryFreeGame;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendTriggerRound;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import com.lx.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import com.lx.gs.math.games.dgry_java.entity.config.GameStateConfigExtendDgryFreeGame;
import com.lx.gs.math.games.dgry_java.entity.result.gameStateResult.GameStateHlrResultExtendDgryBaseGame;
import com.lx.utils.MathUtil;

import java.util.ArrayList;

// 帝国榮耀 免費遊戲處理者
public class GameStateHlrDgryFreeGame extends AbstractGameStateHlr implements IGameStateHlr {
    private final LogicSlotCascade logic; // 牌桌邏輯
    private final GameStateConfigExtendDgryFreeGame gameStateConfigExtend; // 遊戲狀態額外設定
    private final RoundCascadeGameConfigExtendDgryFreeGame roundConfigExtend; // 消除局額外設定

    public GameStateHlrDgryFreeGame(int conditionId, int gameStateId, ITableUtilSlot tableUtil, ILogicSlot logic, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);

        this.logic = (LogicSlotCascade) logic;
        this.gameStateConfigExtend = (GameStateConfigExtendDgryFreeGame) gameStateConfig.getGameStateConfigExtend();
        this.roundConfigExtend = (RoundCascadeGameConfigExtendDgryFreeGame) ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getRoundCascadeGameConfigExtend();
    }

    // 計算遊戲狀態結果
    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {
        ArrayList<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 1. 設定初始場次
        int roundCount = this.calculateDefaultRound(super.conditionId, beforeGameStateHlrResult);

        for (int roundIndex = 0; roundIndex < roundCount; roundIndex++) {
            // 2. 產出消除結果集合
            CascadeClusterHlrResult cascadeClusterHlrResult = this.logic.getCascadeClusterHlrResult(super.conditionId, spinRequest, reelRtpType);

            // 3. 計算特殊事件結果
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = this.logic.getSpecialFeatureHlrResultCluster(
                    super.conditionId,
                    spinRequest,
                    cascadeClusterHlrResult.getCascadeHlrResultList().get(cascadeClusterHlrResult.getCascadeHlrResultList().size() - 1).getScreenGtrResult());

            // 4. 計算 局額外類型 [消除]
            ConstMathSlot.RoundCascadeGameType roundCascadeGameType = ConstMathSlot.RoundCascadeGameType.DEFAULT;

            // 5. 計算 局額外資訊
            RoundHlrResultExtend roundHlrResultExtend = new RoundHlrResultExtendDefault();

            // 6. 計算進度
            ProgressHlrResult progressHlrResult = this.calculateProgressHlrResult(super.conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);

            // 7. 計算聽牌結果
            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = this.logic.getReadyHandHlrResultUnionCluster(super.conditionId, cascadeClusterHlrResult.getCascadeHlrResultList().get(0).getScreenGtrResult().getScreenSymbol(), progressHlrResult);

            // 8. 更新場次
            roundCount = this.calculateTotalRound(roundCount, progressHlrResult);

            // 8. 計算總得分[ 表演池倍數給分 ]
            double totalWin = MathUtil.add(cascadeClusterHlrResult.getTotalWin(), specialFeatureHlrResultCluster.getTotalWin());

            // 9. 計算累積得分結果
            AccumulateWinCtrResult accumulateWinCtrResult = this.logic.getAccumulateWinCtrResult(roundIndex, totalWin, beforeGameStateHlrResult, roundHlrResultList);

            // 10. 封裝 局結果
            RoundHlrResultCascade roundHlrResultCascade = new RoundHlrResultCascade(
                    roundIndex,
                    MathUtil.moneyScale(totalWin),
                    specialFeatureHlrResultCluster,
                    progressHlrResult,
                    readyHandHlrResultUnionCluster,
                    accumulateWinCtrResult,
                    cascadeClusterHlrResult,
                    roundCascadeGameType,
                    roundHlrResultExtend
                    );

            // 11. 加入 局結果
            roundHlrResultList.add(roundHlrResultCascade);
        }

        // 12. 計算共同輸入結果
        CommonInputResult commonInputResult = this.logic.getCommonInputResult(roundHlrResultList);

        // 13. 計算遊戲輸入結果
        GameStateInputResult gameStateInputResult = this.logic.getGameStateInputResult(super.conditionId, roundHlrResultList);

        // 14. 回傳 遊戲狀態結果
        return new GameStateHlrResult(
                super.conditionId,
                gameStateResultIndex,
                super.gameStateId,
                MathUtil.moneyScale(super.calculateStateTotalWin(roundHlrResultList)),
                super.config.getGameStateType(),
                new GameStateHlrResultExtendDgryBaseGame(),
                super.config.getRoundType(),
                roundHlrResultList,
                commonInputResult,
                gameStateInputResult);
    }


    //* 場次相關 *//

    // 更新總場次
    private int calculateTotalRound(int currentTotalRound, ProgressHlrResult progressHlrResult) {
        ProgressHlrResultExtendTriggerRound progressExtendResult = (ProgressHlrResultExtendTriggerRound) progressHlrResult.getProgressHlrResultExtend();

        return currentTotalRound + progressExtendResult.getRoundProgress().getAddRound();
    }

    // 計算初始場次
    private int calculateDefaultRound(int conditionId, GameStateHlrResult beforeGameStateHlrResult) {

        if(beforeGameStateHlrResult.getConditionId() == 0) {
            // 取得進度設定中符合Trigger01條件的設定值
            return ((ProgressConfigExtendTriggerRound)(this.logic.getMathSlotConfig().getGameStateConfigCluster().getGameStateConfigList().get(conditionId-1).getRoundConfig().getProgressConfig().getProgressConfigExtend())).getEventToDefaultRoundMap().get(ConstMathSlot.TriggerEvent.TRIGGER_01);
        }

        // 1. 計算此次預設場次
        int preDefaultRound = this.logic.getDefaultRound(conditionId, beforeGameStateHlrResult);

        // 2. 計算新增場次
        int extraAddRoundCount = this.calculateExtraAddRoundCount(this.calculateTargetSymbolCount(beforeGameStateHlrResult.getRoundHlrResultList().get(0).getSpecialFeatureHlrResultCluster()));

        // 3. 回傳
        return preDefaultRound + extraAddRoundCount;
    }

    // 計算遊戲進度結果
    private ProgressHlrResult calculateProgressHlrResult(int conditionId, int roundIndex, GameStateHlrResult beforeGameStateHlrResult, ArrayList<RoundHlrResult> roundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        // 1. 計算預設值
        ProgressHlrResult preProgressHlrResult = this.logic.getProgressHlrResult(conditionId, roundIndex, beforeGameStateHlrResult, roundHlrResultList, specialFeatureHlrResultCluster);
        RoundProgress preRoundProgress = ((ProgressHlrResultExtendTriggerRound) preProgressHlrResult.getProgressHlrResultExtend()).getRoundProgress();

        // 2. 取得當前場次
        int currentRound = preRoundProgress.getCurrentRound();

        // 3. 計算增加場次
        int addRound = this.calculateProgressHlrResultAddRound(specialFeatureHlrResultCluster, preProgressHlrResult);

        // 4. 計算總場次
        int totalRound = this.calculateProgressHlrResultTotalRound(conditionId, roundIndex, preRoundProgress, beforeGameStateHlrResult);

        // 5. 回傳
        return new ProgressHlrResult(
                preProgressHlrResult.isMaxProgress(),
                preProgressHlrResult.getProgressType(),
                new ProgressHlrResultExtendTriggerRound(new RoundProgress(currentRound, addRound, totalRound)));
    }

    // 計算進度結果總場次
    private int calculateProgressHlrResultTotalRound(int conditionId, int roundIndex, RoundProgress preRoundProgress, GameStateHlrResult beforeGameStateHlrResult) {
        if (roundIndex == 0) {
            return this.calculateDefaultRound(conditionId, beforeGameStateHlrResult);
        }

        return preRoundProgress.getTotalRound();
    }

    // 計算進度結果增加場次
    private int calculateProgressHlrResultAddRound(SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster, ProgressHlrResult preProgressHlrResult) {
        // 1. 取得預 進度結果
        RoundProgress preRoundProgress = ((ProgressHlrResultExtendTriggerRound)preProgressHlrResult.getProgressHlrResultExtend()).getRoundProgress();

        // 2. 計算額外增加場次
        int extraAddRoundCount = this.calculateExtraAddRoundCount(this.calculateTargetSymbolCount(specialFeatureHlrResultCluster));

        // 3. 取得最大場次
        int maxRound = ((ProgressConfigExtendTriggerRound)super.config.getRoundConfig().getProgressConfig().getProgressConfigExtend()).getMaxRound();

        // 4. 取得總場次
        int totalRound = preRoundProgress.getTotalRound();

        // 5. 回傳
        return Math.min(preRoundProgress.getAddRound() + extraAddRoundCount, maxRound - totalRound);
    }

    // 計算額外增加場次
    private int calculateExtraAddRoundCount(int targetSymbolCount) {
        if (targetSymbolCount == this.roundConfigExtend.getMinTargetSymbolCount()) {
            return 0;
        }

        return Math.max(0, (targetSymbolCount - this.roundConfigExtend.getMinTargetSymbolCount())) * this.roundConfigExtend.getAddRound();
    }

    // 計算特殊標誌個數
    private int calculateTargetSymbolCount(SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        if (specialFeatureHlrResultCluster.getSpecialFeatureHlrResultList().size() != 1) {
            return 0;
        }

        SpecialFeatureHlrResult specialFeatureHlrResult = specialFeatureHlrResultCluster.getSpecialFeatureHlrResultList().get(0);

        int result = 0;
        for (int columnIndex = 0; columnIndex < specialFeatureHlrResult.getScreenHitPosition().size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < specialFeatureHlrResult.getScreenHitPosition().get(columnIndex).size(); rowIndex++) {
                if (specialFeatureHlrResult.getScreenHitPosition().get(columnIndex).get(rowIndex)) {
                    result++;
                }
            }
        }

        return result;
    }
}

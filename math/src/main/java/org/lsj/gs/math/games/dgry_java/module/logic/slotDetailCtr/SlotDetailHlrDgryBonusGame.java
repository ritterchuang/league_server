package org.lsj.gs.math.games.dgry_java.module.logic.slotDetailCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendTriggerRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.AbstractSlotDetailHlr;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;
import org.lsj.gs.math.games.dgry_java.entity.config.SlotDetailHlrConfigExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.result.roundResult.RoundHlrResultExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.result.slotDetail.SlotDetailDgryBonusGame;

import java.util.ArrayList;
import java.util.List;

// 虎機詳細記錄計算者 帝国榮耀 額外遊戲
public class SlotDetailHlrDgryBonusGame extends AbstractSlotDetailHlr implements ISlotDetailHlr {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtendDgryBonusGame extendConfig; // 虎機詳細記錄額外設定
    private final SlotDetailDgryUtil slotDetailDgryUtil; // 詳細記錄工具包

    public SlotDetailHlrDgryBonusGame(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil1) {
        super(slotDetailHlrConfig, tableUtil1);
        this.slotDetailType = slotDetailHlrConfig.getSlotDetailType();
        this.extendConfig = (SlotDetailHlrConfigExtendDgryBonusGame) slotDetailHlrConfig.getSlotDetailHlrConfigExtend();
        this.slotDetailDgryUtil = new SlotDetailDgryUtil();
    }

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 宣告空間
        List<SlotDetail> result = new ArrayList<>();

        // 2. 取出局資訊
        List<RoundHlrResult> roundHlrResultList = gameStateHlrResult.getRoundHlrResultList();

        // 3. 添加結果
        for (int index = 0; index < roundHlrResultList.size(); index++) {
            // 3.1 添加局結果
            RoundHlrResultCascade roundHlrResultCascade = (RoundHlrResultCascade) roundHlrResultList.get(index);
            result.add(this.calculateDetailBonusGame(roundHlrResultCascade));
        }

        // 4. 回傳
        return result;
    }

    // 計算免費遊戲詳細記錄
    private SlotDetailDgryBonusGame calculateDetailBonusGame(RoundHlrResultCascade roundHlrResultCascade) {
        // 1. 取得當前場次
        int currentRound = this.calculateCurrentRound(roundHlrResultCascade.getProgressHlrResult());

        // 2. 取得總場次
        int totalRound = this.calculateTotalRound(roundHlrResultCascade.getProgressHlrResult());

        // 3. 取得總得分
        double totalWin = roundHlrResultCascade.getTotalWin();

        // 4. 取得結果類型
        DgryBonusGameDisplayType displayResult = ((RoundHlrResultExtendDgryBonusGame)roundHlrResultCascade.getRoundHlrResultExtend()).getBonusGameResults();

        // 5. 回傳
        return new SlotDetailDgryBonusGame(currentRound, totalRound, totalWin, displayResult);
    }

    // 計算當前場次
    private int calculateCurrentRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendTriggerRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getCurrentRound();
    }

    // 計算當前場次
    private int calculateTotalRound(ProgressHlrResult progressHlrResult) {
        RoundProgress roundProgress = ((ProgressHlrResultExtendTriggerRound)(progressHlrResult.getProgressHlrResultExtend())).getRoundProgress();

        return roundProgress.getTotalRound();
    }
}
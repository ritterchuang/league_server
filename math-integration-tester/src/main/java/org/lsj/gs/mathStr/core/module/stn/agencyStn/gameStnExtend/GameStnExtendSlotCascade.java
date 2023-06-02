package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnSlot;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnSlotCascade;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 虎機遊戲統計者 消除
public class GameStnExtendSlotCascade extends AbstractGameStnExtendSlot {
    private final Map<Integer, TemplateStnSlotCascade> gameStateStnExtendMap; // 客製子彈目標統計對應表 <子彈索引, 目標索引, 基礎統計物件>

    public GameStnExtendSlotCascade(AgencyStnConfig config) {
        super(config);
        this.gameStateStnExtendMap = new HashMap<>();
    }

    // 更新統計資訊
    @Override
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 更新整體統計物件
        super.update(boardGtrResult);

        // 2. 依照 GameState 更新統計
        this.updateGameState(boardGtrResult);
    }

    // 印出統計資訊
    @Override
    public void print() {
        this.gameStateStnExtendMap.forEach(this::printGameStateStnExtendMap);
    }

    //* 印出相關 *//

    // 印出遊戲狀態資訊
    private void printGameStateStnExtendMap(int gameStateId, TemplateStnSlot templateStnSlot){
        // 1. 印出狀態資訊
        super.printGameStateTitleInfo(gameStateId, templateStnSlot);

        // 2. 印出 標誌中獎率
        super.printSymbolHitRateWithTargetTimes(templateStnSlot.getPayComboIdToHitNumberCountMap(), templateStnSlot.getTotalRoundPerGameState());

        // 3. 印出 標誌RTP
        double bet = MathUtil.divide(templateStnSlot.getTotalBet(), templateStnSlot.getTotalRound());
        super.printSymbolRtpWithTargetTimes(templateStnSlot.getPayComboIdToHitNumberWinMap(), templateStnSlot.getTotalRoundPerGameState(), bet);

        // 4. 印出 特殊事件觸發率
        super.printSpecialFeatureTriggerRate(templateStnSlot);

        // 5. 印出 特殊事件RTP
        super.printSpecialFeatureRtp(templateStnSlot);

        // 6. 印出 消除資訊
        this.printCascadeResult(templateStnSlot);
    }

    // 印出消除資訊
    private void printCascadeResult(TemplateStnSlot templateStnSlot) {
        // 1. 印出 標題
        System.out.println("[ 消除相關統計 ]");

        // 2. 印出 平均場次、觸發率
        System.out.println("[ 消除 整體資訊 ]");
        this.printCascadeOverallInfo(templateStnSlot);

        // 3. 印出 第一次消除得分資訊
        System.out.println("[ 第一次消除相關統計 ]");
        this.printFirstCascadeInfo(templateStnSlot);

        // 4. 印出 剩餘消除得分資訊
        System.out.println("[ 剩餘消除相關統計 ]");
        this.printLeftCascadeInfo(templateStnSlot);
    }

    // 印出 消除主要資訊
    private void printCascadeOverallInfo(TemplateStnSlot templateStnSlot) {
        // 1. 計算 平均消除場次、RTP資訊
        TemplateStnSlotCascade templateStnSlotCascade = (TemplateStnSlotCascade) templateStnSlot;
        double avgCascadeTimes = MathUtil.divide(templateStnSlotCascade.getTotalCascadeTimesPerGameState(), templateStnSlot.getTotalRoundPerGameState());
        double avgRound = MathUtil.divide(templateStnSlot.getTotalRoundPerGameState(), templateStnSlot.getTotalRound());
        double firstCascadeTotalRtp = MathUtil.divide(templateStnSlotCascade.getFirstCascadeWin(), templateStnSlot.getTotalBet());
        double leftCascadeTotalRtp = MathUtil.divide(templateStnSlotCascade.getLeftCascadeWin(), templateStnSlot.getTotalBet());

        // 2. 印出 消除主要標題
        System.out.printf("\t%-10s \t%-10s \t%-10s \t%-10s \t%-10s %n", "平均消除場次", "一般輪帶總RTP", "每次輪帶RTP", "消除總RTP", "每次消除RTP");

        // 3. 印出 平均消除場次、RTP資訊
        System.out.printf("\t%12f \t%12f \t%12f \t%12f \t%12f %n",
                avgCascadeTimes,
                firstCascadeTotalRtp,
                MathUtil.divide(firstCascadeTotalRtp, avgRound),
                leftCascadeTotalRtp,
                MathUtil.divide(MathUtil.divide(leftCascadeTotalRtp, avgRound), MathUtil.subtract(avgCascadeTimes, 1))
        );
    }

    // 印出 第一次消除資訊
    private void printFirstCascadeInfo(TemplateStnSlot templateStnSlot) {
        // 1. 計算 參數
        TemplateStnSlotCascade templateStnSlotCascade = (TemplateStnSlotCascade) templateStnSlot;
        double bet = MathUtil.divide(templateStnSlot.getTotalBet(), templateStnSlot.getTotalRound());

        // 2. 印出 打擊資訊
        super.printSymbolHitRateWithTargetTimes(templateStnSlotCascade.getFirstCascadePayComboIdToHitNumberCountMap(), templateStnSlot.getTotalRoundPerGameState());

        // 3. 印出 貢獻率資訊
        super.printSymbolRtpWithTargetTimes(templateStnSlotCascade.getFirstCascadePayComboIdToHitNumberWinMap(), templateStnSlot.getTotalRoundPerGameState(), bet);
    }

    // 印出 剩餘消除資訊
    private void printLeftCascadeInfo(TemplateStnSlot templateStnSlot) {
        // 1. 計算 參數
        TemplateStnSlotCascade templateStnSlotCascade = (TemplateStnSlotCascade) templateStnSlot;
        double bet = MathUtil.divide(templateStnSlot.getTotalBet(), templateStnSlot.getTotalRound());
        int targetTimes = templateStnSlotCascade.getTotalCascadeTimesPerGameState() - templateStnSlot.getTotalRoundPerGameState();

        // 2. 印出 打擊資訊
        super.printSymbolHitRateWithTargetTimes(templateStnSlotCascade.getLeftCascadePayComboIdToHitNumberCountMap(), templateStnSlot.getTotalRoundPerGameState());

        // 3. 印出 貢獻率資訊
        super.printSymbolRtpWithTargetTimes(templateStnSlotCascade.getLeftCascadePayComboIdToHitNumberWinMap(), targetTimes, bet);
    }


    //* 更新相關 *//

    // 更新狀態結果
    private void updateGameState(BoardGtrResult boardGtrResult) {
        // 1. 取出狀態結果陣列
        List<GameStateHlrResult> gameStateHlrResultList = ((GameResultExtendSlotJava) boardGtrResult.getGameResultExtend()).getGameFlowHlrResult().getGameStateHlrResultList();

        // 2. 遍歷所有結果
        for (int gameStateIndex = 0; gameStateIndex < gameStateHlrResultList.size(); gameStateIndex++) {
            // 3. 取出狀態結果
            GameStateHlrResult gameStateHlrResult = gameStateHlrResultList.get(gameStateIndex);

            // 4. 取出狀態ID
            int gameStateId = gameStateHlrResult.getGameStateId();

            // 5. 更新狀態統計者
            if (this.gameStateStnExtendMap.containsKey(gameStateId) == false) {
                TemplateStnSlotCascade templateStnSlotCascade = new TemplateStnSlotCascade();
                templateStnSlotCascade.update(boardGtrResult, gameStateIndex);
                this.gameStateStnExtendMap.put(gameStateId, templateStnSlotCascade);
            }else {
                TemplateStnSlotCascade templateStnSlotCascade = this.gameStateStnExtendMap.get(gameStateId);
                templateStnSlotCascade.update(boardGtrResult, gameStateIndex);
                this.gameStateStnExtendMap.put(gameStateId, templateStnSlotCascade);
            }
        }
    }
}

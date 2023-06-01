package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnSlot;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnSlotNormal;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 虎機遊戲統計者 一般
public class GameStnExtendSlotNormal extends AbstractGameStnExtendSlot {
    private final Map<Integer, TemplateStnSlotNormal> gameStateStnExtendMap; // 客製子彈目標統計對應表 <子彈索引, 目標索引, 基礎統計物件>

    public GameStnExtendSlotNormal(AgencyStnConfig config) {
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
    }


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
                TemplateStnSlotNormal templateStnSlotNormal = new TemplateStnSlotNormal();
                templateStnSlotNormal.update(boardGtrResult, gameStateIndex);
                this.gameStateStnExtendMap.put(gameStateId, templateStnSlotNormal);
            }else {
                TemplateStnSlotNormal templateStnSlotNormal = this.gameStateStnExtendMap.get(gameStateId);
                templateStnSlotNormal.update(boardGtrResult, gameStateIndex);
                this.gameStateStnExtendMap.put(gameStateId, templateStnSlotNormal);
            }
        }
    }
}

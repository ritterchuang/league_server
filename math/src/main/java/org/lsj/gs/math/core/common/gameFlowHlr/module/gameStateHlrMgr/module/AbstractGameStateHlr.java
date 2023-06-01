package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtendDefault;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.MathUtil;

import java.util.List;

// 抽象狀態處理者父類別
public class AbstractGameStateHlr {
    protected final int conditionId; // 條件狀態識別碼
    protected final int gameStateId; // 遊戲狀態識別碼
    protected final GameStateConfig config; // 設定檔
    protected final ITableUtilSlot tableUtil; // 牌桌工具包

    public AbstractGameStateHlr(int conditionId, int gameStateId, GameStateConfig config, ITableUtilSlot tableUtil) {
        this.conditionId = conditionId;
        this.gameStateId = gameStateId;
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // 取得局額外類型
    protected ConstMathSlot.RoundNormalGameType calculateRoundNormalGameType(RoundHlrResultExtend roundHlrResultExtend) {
        if (roundHlrResultExtend.getClass().equals(RoundHlrResultExtendDefault.class)) {
            return ConstMathSlot.RoundNormalGameType.DEFAULT;
        }

        return ((RoundConfigNormal)(this.config.getRoundConfig())).getRoundNormalGameType();
    }


    // 計算局總得分
    protected double calculateRoundTotalWin(GameCtrResult gameCtrResult, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster, RoundHlrResultExtend roundHlrResultExtend) {
        // 1. 建立空間
        double totalWin = 0.0;

        // 2. 加總畫面得分
        totalWin = MathUtil.add(totalWin, gameCtrResult.getTotalWin());

        // 3. 加總特殊事件得分
        totalWin = MathUtil.add(totalWin, specialFeatureHlrResultCluster.getTotalWin());

        // 4. 加總額外獎勵得分
        totalWin = MathUtil.add(totalWin, roundHlrResultExtend.getTotalWin());

        // 5. 回傳
        return totalWin;
    }

    // 計算狀態總得分
    protected double calculateStateTotalWin(List<RoundHlrResult> roundHlrResultList) {
        // 1. 建立空間
        double totalWin = 0.0;

        // 3. 加總特殊事件得分
        for (int roundIndex = 0; roundIndex < roundHlrResultList.size(); roundIndex++) {
            totalWin = MathUtil.add(totalWin, roundHlrResultList.get(roundIndex).getTotalWin());
        }

        // 5. 回傳
        return totalWin;
    }
}

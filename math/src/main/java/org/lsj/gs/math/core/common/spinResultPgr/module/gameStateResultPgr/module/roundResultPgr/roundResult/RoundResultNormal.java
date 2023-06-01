package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端局結果 一般
public class RoundResultNormal extends RoundResult {
    private final ScreenResult screenResult; // 遊戲畫面結果
    private final GameResult gameResult; // 遊戲算分結果
    private final ConstMathSlot.RoundNormalGameType roundNormalGameType; // 遊戲局狀態類型
    private final RoundResultExtend roundResultExtend; // 客製局結果額外資訊

    public RoundResultNormal(int roundIndex, double totalWin, ScreenResult screenResult, GameResult gameResult, SpecialFeatureResultCluster specialFeatureResultCluster, ProgressResult progressResult, ReadyHandResultCluster readyHandResultCluster, AccumulateWinResult accumulateWinResult, ConstMathSlot.RoundNormalGameType roundNormalGameType, RoundResultExtend roundResultExtend) {
        super(roundIndex, totalWin, specialFeatureResultCluster, progressResult, readyHandResultCluster, accumulateWinResult);
        this.screenResult = screenResult;
        this.gameResult = gameResult;
        this.roundNormalGameType = roundNormalGameType;
        this.roundResultExtend = roundResultExtend;
    }

    public RoundResultNormal() {
        super(0, 0, null, null, null, null);
        this.screenResult = null;
        this.gameResult = null;
        this.roundNormalGameType = null;
        this.roundResultExtend = null;
    }

    public ScreenResult getScreenResult() {
        return screenResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public ConstMathSlot.RoundNormalGameType getRoundNormalGameType() {
        return roundNormalGameType;
    }

    public RoundResultExtend getRoundResultExtend() {
        return roundResultExtend;
    }
}

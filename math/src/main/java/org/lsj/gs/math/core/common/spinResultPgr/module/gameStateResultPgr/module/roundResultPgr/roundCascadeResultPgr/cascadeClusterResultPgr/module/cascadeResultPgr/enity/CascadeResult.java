package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 消除結果
public class CascadeResult {
    private final double totalWin; // 總得分
    private final ScreenResult screenResult; // 畫面結果
    private final GameResult gameResult; // 算分結果

    private final AccumulateWinResult accumulateWinResult; // 累積得分結果
    private final ProgressResult progressResult; // 進度結果

    @JsonIgnore
    private final SpecialFeatureResultCluster specialFeatureResultCluster; // 特殊事件結果集合 TODO 有需要再傳
    @JsonIgnore
    private final ReadyHandResultCluster readyHandResultCluster; // 聽牌結果集合 TODO 有需要再傳

    private final EliminateResult eliminateResult; // 消除結果

    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeResultExtend cascadeResultExtend; // 消除額外結果

    public CascadeResult(double totalWin, ScreenResult screenResult, GameResult gameResult, SpecialFeatureResultCluster specialFeatureResultCluster, AccumulateWinResult accumulateWinResult, ProgressResult progressResult, ReadyHandResultCluster readyHandResultCluster, EliminateResult eliminateResult, ConstMathSlot.CascadeType cascadeType, CascadeResultExtend cascadeResultExtend) {
        this.totalWin = totalWin;
        this.screenResult = screenResult;
        this.gameResult = gameResult;
        this.progressResult = progressResult;
        this.accumulateWinResult = accumulateWinResult;
        this.specialFeatureResultCluster = specialFeatureResultCluster;
        this.readyHandResultCluster = readyHandResultCluster;
        this.eliminateResult = eliminateResult;
        this.cascadeType = cascadeType;
        this.cascadeResultExtend = cascadeResultExtend;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ScreenResult getScreenResult() {
        return screenResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public ProgressResult getProgressResult() {
        return progressResult;
    }

    public AccumulateWinResult getAccumulateWinResult() {
        return accumulateWinResult;
    }

    public SpecialFeatureResultCluster getSpecialFeatureResultCluster() {
        return specialFeatureResultCluster;
    }

    public ReadyHandResultCluster getReadyHandResultCluster() {
        return readyHandResultCluster;
    }

    public EliminateResult getEliminateResult() {
        return eliminateResult;
    }

    public ConstMathSlot.CascadeType getCascadeType() {
        return cascadeType;
    }

    public CascadeResultExtend getCascadeResultExtend() {
        return cascadeResultExtend;
    }
}

package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 消除處理者結果
public class CascadeHlrResult {
    private final double totalWin; // 總得分
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeHlrResultExtend cascadeHlrResultExtend; // 消除處理者額外結果
    private final ScreenGtrResult screenGtrResult; // 畫面生產者結果
    private final GameCtrResult gameCtrResult; // 得分計算者結果
    private final ProgressHlrResult progressHlrResult; // 消除進度處理者結果
    private final AccumulateWinCtrResult accumulateWinCtrResult; // 消除累積得分計算者結果
    private final SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster; // 特殊事件處理者結果集合
    private final ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster; // 聽牌事件處理者結果集合
    private final EliminateCtrResult eliminateCtrResult; // 消除位置計算者結果

    public CascadeHlrResult(double totalWin,
                            ConstMathSlot.CascadeType cascadeType,
                            CascadeHlrResultExtend cascadeHlrResultExtend,
                            ScreenGtrResult screenGtrResult,
                            GameCtrResult gameCtrResult,
                            ProgressHlrResult progressHlrResult,
                            AccumulateWinCtrResult accumulateWinCtrResult,
                            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster,
                            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster,
                            EliminateCtrResult eliminateCtrResult) {
        this.totalWin = totalWin;
        this.cascadeType = cascadeType;
        this.cascadeHlrResultExtend = cascadeHlrResultExtend;
        this.screenGtrResult = screenGtrResult;
        this.gameCtrResult = gameCtrResult;
        this.progressHlrResult = progressHlrResult;
        this.accumulateWinCtrResult = accumulateWinCtrResult;
        this.specialFeatureHlrResultCluster = specialFeatureHlrResultCluster;
        this.readyHandHlrResultUnionCluster = readyHandHlrResultUnionCluster;
        this.eliminateCtrResult = eliminateCtrResult;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ConstMathSlot.CascadeType getCascadeType() {
        return cascadeType;
    }

    public CascadeHlrResultExtend getCascadeHlrResultExtend() {
        return cascadeHlrResultExtend;
    }

    public ScreenGtrResult getScreenGtrResult() {
        return screenGtrResult;
    }

    public GameCtrResult getGameCtrResult() {
        return gameCtrResult;
    }

    public ProgressHlrResult getProgressHlrResult() {
        return progressHlrResult;
    }

    public AccumulateWinCtrResult getAccumulateWinCtrResult() {
        return accumulateWinCtrResult;
    }

    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultCluster() {
        return specialFeatureHlrResultCluster;
    }

    public ReadyHandHlrResultUnionCluster getReadyHandHlrResultUnionCluster() {
        return readyHandHlrResultUnionCluster;
    }

    public EliminateCtrResult getEliminateCtrResult() {
        return eliminateCtrResult;
    }
}

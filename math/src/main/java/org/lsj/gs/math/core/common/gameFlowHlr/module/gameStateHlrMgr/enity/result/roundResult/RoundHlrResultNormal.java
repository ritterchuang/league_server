package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 局處理結果一般
public class RoundHlrResultNormal extends RoundHlrResult {
    private final ScreenGtrResult screenGtrResult; // 遊戲畫面結果
    private final GameCtrResult gameCtrResult; // 遊戲算分結果
    private final ConstMathSlot.RoundNormalGameType roundNormalGameType; // 遊戲局狀態類型
    private final RoundHlrResultExtend roundHlrResultExtend; // 客製局結果額外資訊

    public RoundHlrResultNormal(int roundIndex, double totalWin, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster, ProgressHlrResult progressHlrResult, ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster, AccumulateWinCtrResult accumulateWinCtrResult, ScreenGtrResult screenGtrResult, GameCtrResult gameCtrResult, ConstMathSlot.RoundNormalGameType roundNormalGameType, RoundHlrResultExtend roundHlrResultExtend) {
        super(roundIndex, totalWin, specialFeatureHlrResultCluster, progressHlrResult, readyHandHlrResultUnionCluster, accumulateWinCtrResult);
        this.screenGtrResult = screenGtrResult;
        this.gameCtrResult = gameCtrResult;
        this.roundNormalGameType = roundNormalGameType;
        this.roundHlrResultExtend = roundHlrResultExtend;
    }

    public ScreenGtrResult getScreenGtrResult() {
        return screenGtrResult;
    }

    public GameCtrResult getGameCtrResult() {
        return gameCtrResult;
    }

    public ConstMathSlot.RoundNormalGameType getRoundNormalGameType() {
        return roundNormalGameType;
    }

    public RoundHlrResultExtend getRoundHlrResultExtend() {
        return roundHlrResultExtend;
    }
}

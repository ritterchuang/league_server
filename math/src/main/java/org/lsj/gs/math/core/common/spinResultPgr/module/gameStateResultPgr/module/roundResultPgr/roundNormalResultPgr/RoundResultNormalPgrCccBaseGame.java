package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundNormalResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtendDefault;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultNormal;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult.ClientReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.games.ccc_java.entity.result.roundResult.RoundHlrResultExtendCccBaseGame;
import org.lsj.gs.math.games.ccc_java.entity.result.roundResult.RoundResultExtendCccBaseGame;

// 客製局結果包裝者一般 777基礎遊戲
public class RoundResultNormalPgrCccBaseGame extends AbstractRoundResultNormalPgr {
    private final int reSpinColumnIndex = 2;

    public RoundResultNormalPgrCccBaseGame(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
        super(config, tableUtil);
    }

    // 包裝局結果
    public RoundResultNormal packageRoundResult(RoundHlrResult roundHlrResult) {
        // 1. 轉型
        RoundHlrResultNormal roundHlrResultNormal = (RoundHlrResultNormal) roundHlrResult;

        // 2. 計算畫面結果
        ScreenResult screenResult = super.packageScreenResult(roundHlrResultNormal.getScreenGtrResult());

        // 3. 計算得分結果
        GameResult gameResult = super.packageGameResult(screenResult, roundHlrResultNormal.getGameCtrResult());

        // 4. 計算特殊事件結果
        SpecialFeatureResultCluster specialFeatureResultCluster = super.packageSpecialFeatureResultCluster(screenResult, roundHlrResult.getSpecialFeatureHlrResultCluster());

        // 5. 計算聽牌結果
        ReadyHandResultCluster readyHandResultCluster = super.packageReadyHandResultCluster(roundHlrResult.getReadyHandHlrResultUnionCluster());

        // 6. 計算進度結果
        ProgressResult progressResult = super.packageProgressResult(roundHlrResult.getProgressHlrResult());

        // 7. 計算累積得分結果
        AccumulateWinResult accumulateWinResult = super.packageAccumulateWinResult(roundHlrResult.getAccumulateWinCtrResult());

        // 8. 計算額外局結果
        RoundResultExtend roundResultExtend = this.packageRoundResultExtend(roundHlrResultNormal);

        // 9. 回傳
        return new RoundResultNormal(
                roundHlrResult.getRoundIndex(),
                roundHlrResult.getTotalWin(),
                screenResult, gameResult, specialFeatureResultCluster,
                progressResult,
                readyHandResultCluster,
                accumulateWinResult,
                roundHlrResultNormal.getRoundNormalGameType(),
                roundResultExtend);
    }

    public RoundResultExtend packageRoundResultExtend(RoundHlrResultNormal roundHlrResultNormal) {
        // 1. 預設狀態回傳預設
        if (roundHlrResultNormal.getRoundNormalGameType().equals(ConstMathSlot.RoundNormalGameType.DEFAULT)) {
            return new RoundResultExtendDefault();
        }

        // 2. 轉型777運算結果
        RoundHlrResultExtendCccBaseGame roundHlrResultExtendCccBaseGame = (RoundHlrResultExtendCccBaseGame) (roundHlrResultNormal.getRoundHlrResultExtend());

        // 3. 計算客端重轉畫面
        ScreenResult clientReSpinScreenResult = this.clientScreenPgr.calculateScreenResult(roundHlrResultExtendCccBaseGame.getScreenGtrResult());

        // 4. 計算客端重轉算分
        GameResult clientGameResult = super.packageGameResult(clientReSpinScreenResult, roundHlrResultExtendCccBaseGame.getReSpinGameCtrResult());

        // 5. 回傳
        return new RoundResultExtendCccBaseGame(
                roundHlrResultExtendCccBaseGame.getTotalWin(),
                roundHlrResultExtendCccBaseGame.getReSpinStrip(),
                ((ClientReelStopResultExtendStopByDependentReelIndex)clientReSpinScreenResult.getReelResult().getReelStopResultExtend()).getReelStopIndexList().get(this.reSpinColumnIndex),
                clientGameResult);
    }
}

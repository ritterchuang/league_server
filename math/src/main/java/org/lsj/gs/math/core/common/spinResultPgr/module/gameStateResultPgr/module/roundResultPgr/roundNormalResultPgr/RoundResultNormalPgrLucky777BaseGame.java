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
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 客製局結果包裝者一般 Lucky777 基礎遊戲
public class RoundResultNormalPgrLucky777BaseGame extends AbstractRoundResultNormalPgr {

    public RoundResultNormalPgrLucky777BaseGame(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
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
        RoundResultExtend roundResultExtend = new RoundResultExtendDefault();

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
}

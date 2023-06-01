package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultCascadePgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.IRoundResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.CascadeClusterResultPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.ICascadeClusterResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultCascade;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.SpecialFeatureResultClusterPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.dgry_java.entity.result.roundResult.RoundHlrResultExtendDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.entity.result.roundResult.RoundResultExtendDgryBonusGame;

// 客製局結果包裝者消除 帝国榮耀額外遊戲
public class RoundCascadeResultPgrDgryBonusGame implements IRoundResultPgr {
    private final ClientRoundResultCascadePgrConfig config; // 設定

    private final SpecialFeatureResultClusterPgr specialFeatureResultClusterPgr; // 特殊事件結果及盒包裝者
    private final ProgressResultPgr progressResultPgr; // 遊戲進度包裝者
    private final ReadyHandResultPgr readyHandResultPgr; // 聽牌結果包裝者
    private final AccumulateWinResultPgr accumulateWinResultPgr; // 累積得分包裝者

    private final ICascadeClusterResultPgr cascadeClusterResultPgr; // 消除結果包裝者

    public RoundCascadeResultPgrDgryBonusGame(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
        this.config = (ClientRoundResultCascadePgrConfig) config;

        this.specialFeatureResultClusterPgr = new SpecialFeatureResultClusterPgr(this.config.createSpecialFeatureResultClusterPgrConfig(), tableUtil);
        this.progressResultPgr = new ProgressResultPgr();
        this.readyHandResultPgr = new ReadyHandResultPgr();
        this.accumulateWinResultPgr = new AccumulateWinResultPgr();

        this.cascadeClusterResultPgr = new CascadeClusterResultPgrFactory().create(this.config.getCascadeClusterResultPgrConfig(), this.progressResultPgr, this.accumulateWinResultPgr, tableUtil);
    }

    @Override
    public RoundResultCascade packageRoundResult(RoundHlrResult roundHlrResult) {
        // 1. 轉型
        RoundHlrResultCascade roundHlrResultCascade = (RoundHlrResultCascade) roundHlrResult;

        // 2. 計算消除集合結果
        CascadeClusterResult cascadeClusterResult = this.cascadeClusterResultPgr.packageCascadeClusterResult(((RoundHlrResultCascade) roundHlrResult).getCascadeClusterHlrResult());

        // 3. 計算特殊事件
        SpecialFeatureResultCluster specialFeatureResultCluster = new SpecialFeatureResultCluster();

        // 4. 計算聽牌
        ReadyHandResultCluster readyHandResultCluster = new ReadyHandResultCluster();

        // 5. 計算進度
        ProgressResult progressResult = this.progressResultPgr.packageProgressResult(roundHlrResult.getProgressHlrResult());

        // 6. 計算累積得分
        AccumulateWinResult accumulateWinResult = this.accumulateWinResultPgr.packageAccumulateWinResult(roundHlrResult.getAccumulateWinCtrResult());

        // 8. 計算額外局結果
        RoundResultExtend roundResultExtend = new RoundResultExtendDgryBonusGame(roundHlrResultCascade.getTotalWin(),((RoundHlrResultExtendDgryBonusGame)(roundHlrResultCascade.getRoundHlrResultExtend())).getBonusGameResults());

        // 7. 回傳
        return new RoundResultCascade(
                roundHlrResult.getRoundIndex(),
                roundHlrResult.getTotalWin(),
                specialFeatureResultCluster,
                progressResult,
                readyHandResultCluster,
                accumulateWinResult,
                cascadeClusterResult,
                roundHlrResultCascade.getRoundCascadeGameType(),
                roundResultExtend);
    }
}

package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundNormalResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultNormalPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.GameResultPgrFactory;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.IGameResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.IRoundResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.ScreenResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.SpecialFeatureResultClusterPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import com.lx.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 客製局結果包裝者抽象父類別
public abstract class AbstractRoundResultNormalPgr implements IRoundResultPgr {
    protected final ClientRoundResultNormalPgrConfig config; // 設定

    protected final ScreenResultPgr clientScreenPgr; // 畫面包裝者
    protected final IGameResultPgr gameResultPgr; // 中獎資訊包裝者
    protected final SpecialFeatureResultClusterPgr specialFeatureResultClusterPgr;

    protected final ProgressResultPgr progressResultPgr; // 遊戲進度包裝者
    protected final ReadyHandResultPgr readyHandResultPgr; // 聽牌結果包裝者
    protected final AccumulateWinResultPgr accumulateWinResultPgr; // 累積得分包裝者

    public AbstractRoundResultNormalPgr(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
        this.config = (ClientRoundResultNormalPgrConfig) config;

        this.clientScreenPgr = new ScreenResultPgr(this.config.createClientScreenPgrConfig(), tableUtil);

        this.gameResultPgr = new GameResultPgrFactory().create(this.config.createGameResultPgrConfig(), tableUtil);
        this.specialFeatureResultClusterPgr = new SpecialFeatureResultClusterPgr(this.config.createSpecialFeatureResultClusterPgrConfig(), tableUtil);

        this.progressResultPgr = new ProgressResultPgr();
        this.readyHandResultPgr = new ReadyHandResultPgr();
        this.accumulateWinResultPgr = new AccumulateWinResultPgr();
    }

    // 包裝客製畫面結果
    protected ScreenResult packageScreenResult(ScreenGtrResult screenGtrResult) {
        return this.clientScreenPgr.calculateScreenResult(screenGtrResult);
    }
    // 包裝客製算分結果

    protected GameResult packageGameResult(ScreenResult screenResult, GameCtrResult gameCtrResult) {
        return this.gameResultPgr.packageGameResult(screenResult, gameCtrResult);
    }
    // 包裝客製遊戲進度結果

    protected ProgressResult packageProgressResult(ProgressHlrResult progressHlrResult) {
        return this.progressResultPgr.packageProgressResult(progressHlrResult);
    }
    // 包裝客製遊戲進度結果

    protected ReadyHandResultCluster packageReadyHandResultCluster(ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster) {
        return this.readyHandResultPgr.packageReadyHandResultCluster(readyHandHlrResultUnionCluster);
    }
    // 包裝客製特殊事件結果

    protected SpecialFeatureResultCluster packageSpecialFeatureResultCluster(ScreenResult screenResult, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        return this.specialFeatureResultClusterPgr.packageSpecialFeatureResultCluster(screenResult, specialFeatureHlrResultCluster);
    }
    // 包裝客製累積得分結果

    protected AccumulateWinResult packageAccumulateWinResult(AccumulateWinCtrResult accumulateWinCtrResult) {
        return this.accumulateWinResultPgr.packageAccumulateWinResult(accumulateWinCtrResult);
    }
}

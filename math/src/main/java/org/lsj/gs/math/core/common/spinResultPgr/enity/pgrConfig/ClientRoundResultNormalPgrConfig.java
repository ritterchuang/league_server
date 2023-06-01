package org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.GameResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ClientScreenPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.SpecialFeatureResultClusterPgrConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

// 客端局結果包裝者設定 一般
public class ClientRoundResultNormalPgrConfig extends ClientRoundResultPgrConfig {
    private final ConstMathSlot.RoundNormalGameType roundNormalGameType; // 局一般類型

    private final ClientScreenConfig clientScreenConfig; // 客端畫面設定
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端畫面打擊設定

    private final FrameConfig frameConfig; // 畫面大小設定
    private final ReelConfig reelConfig; // 輪帶表設定
    private final DampConfig dampConfig; // 破框設定
    private final GameConfig gameConfig; // 遊戲設定

    public ClientRoundResultNormalPgrConfig(ConstMathSlot.RoundNormalGameType roundNormalGameType, ClientScreenConfig clientScreenConfig, ClientSymbolHitConfig clientSymbolHitConfig, FrameConfig frameConfig, ReelConfig reelConfig, DampConfig dampConfig, GameConfig gameConfig) {
        this.roundNormalGameType = roundNormalGameType;
        this.frameConfig = frameConfig;
        this.reelConfig = reelConfig;
        this.clientScreenConfig = clientScreenConfig;
        this.clientSymbolHitConfig = clientSymbolHitConfig;
        this.dampConfig = dampConfig;
        this.gameConfig = gameConfig;
    }

    // 產出客端畫面包裝者設定
    public ClientScreenPgrConfig createClientScreenPgrConfig() {
        return new ClientScreenPgrConfig(this.clientScreenConfig, this.frameConfig, this.reelConfig, this.dampConfig);
    }

    // 產出客端遊戲結果包裝者設定
    public GameResultPgrConfig createGameResultPgrConfig() {
        return new GameResultPgrConfig(this.clientSymbolHitConfig, this.gameConfig);
    }

    // 產出客端特殊事件包裝者設定
    public SpecialFeatureResultClusterPgrConfig createSpecialFeatureResultClusterPgrConfig() {
        return new SpecialFeatureResultClusterPgrConfig(this.clientSymbolHitConfig);
    }

    public ConstMathSlot.RoundNormalGameType getRoundNormalGameType() {
        return roundNormalGameType;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public ClientScreenConfig getClientScreenConfig() {
        return clientScreenConfig;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }
}

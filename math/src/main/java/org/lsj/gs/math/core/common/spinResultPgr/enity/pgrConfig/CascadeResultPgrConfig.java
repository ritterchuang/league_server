package org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.*;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.GameResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module.CascadeScreenResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ClientScreenPgrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.CascadeConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 消除結果包裝者設定
public class CascadeResultPgrConfig {
    private final ClientCascadeConfig config; // 客端消除設定
    private final CascadeConfig cascadeConfig; // 消除設定

    private final FrameConfig frameConfig; // 畫面大小設定
    private final ReelConfig reelConfig; // 輪帶表設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final DampConfig dampConfig; // 破框設定

    private final GameConfig gameConfig; // 遊戲設定

    public CascadeResultPgrConfig(ClientCascadeConfig config, CascadeConfig cascadeConfig, FrameConfig frameConfig, ReelConfig reelConfig, SymbolConfig symbolConfig, DampConfig dampConfig, GameConfig gameConfig) {
        this.config = config;
        this.cascadeConfig = cascadeConfig;

        this.frameConfig = frameConfig;
        this.reelConfig = reelConfig;
        this.symbolConfig = symbolConfig;
        this.dampConfig = dampConfig;

        this.gameConfig = gameConfig;
    }

    // 建立客端畫面包裝者設定
    public ClientScreenPgrConfig createClientScreenPgrConfig() {
        return new ClientScreenPgrConfig(this.config.getClientScreenConfig(), this.frameConfig, this.reelConfig, this.dampConfig);
    }

    // 建立客端消除畫面包裝者設定
    public CascadeScreenResultPgrConfig createCascadeScreenResultPgrConfig() {
        return new CascadeScreenResultPgrConfig(
                this.getCascadeClientScreenConfig(this.config), this.config.getAddSymbolOrderType(), this.dampConfig);
    }

    public GameResultPgrConfig createGameResultPgrConfig() {
        return new GameResultPgrConfig(this.config.getClientSymbolHitConfig(), this.gameConfig);
    }

    // 取得客端消除畫面設定
    private ClientScreenConfig getCascadeClientScreenConfig(ClientCascadeConfig clientCascadeConfig) {
        switch (clientCascadeConfig.getCascadeType()) {
            case MJHL: return ((ClientCascadeConfigExtendMjhl)clientCascadeConfig.getClientCascadeConfigExtend()).getCascadeClientScreenConfig();
            case DGRY: return ((ClientCascadeConfigExtendDgry)clientCascadeConfig.getClientCascadeConfigExtend()).getCascadeClientScreenConfig();
            case CJWP: return ((ClientCascadeConfigExtendCjwp)clientCascadeConfig.getClientCascadeConfigExtend()).getCascadeClientScreenConfig();
            default: return ((ClientCascadeConfigExtendDefault)clientCascadeConfig.getClientCascadeConfigExtend()).getCascadeClientScreenConfig();
        }
    }

    public ClientCascadeConfig getConfig() {
        return config;
    }

    public CascadeConfig getCascadeConfig() {
        return cascadeConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }
}

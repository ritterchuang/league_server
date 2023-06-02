package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;

// 算分結果包裝者設定
public class GameResultPgrConfig {
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端打擊設定
    private final GameConfig gameConfig; // 遊戲算分設定

    public GameResultPgrConfig(ClientSymbolHitConfig clientSymbolHitConfig, GameConfig gameConfig) {
        this.clientSymbolHitConfig = clientSymbolHitConfig;
        this.gameConfig = gameConfig;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }
}

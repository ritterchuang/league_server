package org.lsj.gs.math.core.common.spinResultPgr.config.enity;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.ClientRoundConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端遊戲狀態設定
public class ClientGameStateConfig {
    private final ConstMathSlot.GameStateType gameStateType; // 遊戲類型
    private final ConstMathSlot.RoundType roundType; // 局類型
    private final ClientRoundConfig clientRoundConfig; // 客端局設定

    public ClientGameStateConfig(ConstMathSlot.GameStateType gameStateType, ConstMathSlot.RoundType roundType, ClientRoundConfig clientRoundConfig) {
        this.gameStateType = gameStateType;
        this.roundType = roundType;
        this.clientRoundConfig = clientRoundConfig;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    public ConstMathSlot.RoundType getRoundType() {
        return roundType;
    }

    public ClientRoundConfig getClientRoundConfig() {
        return clientRoundConfig;
    }
}

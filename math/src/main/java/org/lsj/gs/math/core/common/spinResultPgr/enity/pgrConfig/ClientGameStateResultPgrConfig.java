package org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端遊戲狀態結果包裝者設定
public class ClientGameStateResultPgrConfig {
    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型
    private final ConstMathSlot.RoundType roundType; // 局狀態類型
    private final ClientRoundResultPgrConfig clientRoundResultPgrConfig; // 客端局結果包裝者設定

    public ClientGameStateResultPgrConfig(ConstMathSlot.GameStateType gameStateType, ConstMathSlot.RoundType roundType, ClientRoundResultPgrConfig clientRoundResultPgrConfig) {
        this.gameStateType = gameStateType;
        this.roundType = roundType;
        this.clientRoundResultPgrConfig = clientRoundResultPgrConfig;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    public ConstMathSlot.RoundType getRoundType() {
        return roundType;
    }

    public ClientRoundResultPgrConfig getClientRoundResultPgrConfig() {
        return clientRoundResultPgrConfig;
    }
}

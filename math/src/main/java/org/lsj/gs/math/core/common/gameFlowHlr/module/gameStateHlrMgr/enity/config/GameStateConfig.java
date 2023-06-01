package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfig;

// 客製遊戲狀態設定
public class GameStateConfig {
    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型
    @JsonIgnore
    private final GameStateConfigExtend gameStateConfigExtend; // 遊戲狀態額外設定
    @JsonIgnore
    private final GameStateInputConfig gameStateInputConfig; // 遊戲輸入設定
    private final ConstMathSlot.RoundType roundType; // 局類型
    private final RoundConfig roundConfig; // 局類型設定
    @JsonIgnore
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型

    public GameStateConfig(ConstMathSlot.GameStateType gameStateType, GameStateConfigExtend gameStateConfigExtend, GameStateInputConfig gameStateInputConfig, ConstMathSlot.RoundType roundType, RoundConfig roundConfig, ConstMathSlot.SlotDetailType slotDetailType) {
        this.slotDetailType = slotDetailType;
        this.gameStateInputConfig = gameStateInputConfig;
        this.gameStateType = gameStateType;
        this.gameStateConfigExtend = gameStateConfigExtend;
        this.roundType = roundType;
        this.roundConfig = roundConfig;
    }

    public ConstMathSlot.SlotDetailType getSlotDetailType() {
        return slotDetailType;
    }

    public GameStateInputConfig getGameStateInputConfig() {
        return gameStateInputConfig;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    public GameStateConfigExtend getGameStateConfigExtend() {
        return gameStateConfigExtend;
    }

    public ConstMathSlot.RoundType getRoundType() {
        return roundType;
    }

    public RoundConfig getRoundConfig() {
        return roundConfig;
    }
}

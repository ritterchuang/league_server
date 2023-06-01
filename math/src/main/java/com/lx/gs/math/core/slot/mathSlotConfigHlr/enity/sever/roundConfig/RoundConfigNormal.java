package com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig.RoundNormalGameConfigExtend;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import com.lx.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;

// 局設定一般
public class RoundConfigNormal extends RoundConfig {
    private final ReelConfig reelConfig; // 輪帶表設定
    private final InitialScreenConfig initialScreenConfig; // 初始畫面設定

    @JsonIgnore
    private final GameConfig gameConfig; // 算分設定
    @JsonIgnore
    private final PayTableConfig payTableConfig; // 賠率表設定
    @JsonIgnore
    private final ConstMathSlot.RoundNormalGameType roundNormalGameType; // 一般遊戲類型
    @JsonIgnore
    private final RoundNormalGameConfigExtend roundNormalGameConfigExtend; // 一般遊戲額外設定

    public RoundConfigNormal(
            ReelConfig reelConfig, InitialScreenConfig initialScreenConfig,
            FrameConfig frameConfig, SymbolConfig symbolConfig, DampConfig dampConfig, GameConfig gameConfig, PayTableConfig payTableConfig, ProgressConfig progressConfig,
            SpecialFeatureConfigCluster specialFeatureConfigCluster, ReadyHandConfigCluster readyHandConfigCluster,
            ConstMathSlot.RoundNormalGameType roundNormalGameType, RoundNormalGameConfigExtend roundNormalGameConfigExtend) {
        super(frameConfig, symbolConfig, dampConfig, progressConfig, specialFeatureConfigCluster, readyHandConfigCluster);
        this.reelConfig = reelConfig;
        this.gameConfig = gameConfig;
        this.initialScreenConfig = initialScreenConfig;
        this.roundNormalGameType = roundNormalGameType;
        this.roundNormalGameConfigExtend = roundNormalGameConfigExtend;
        this.payTableConfig = payTableConfig;
    }

    public ConstMathSlot.RoundNormalGameType getRoundNormalGameType() {
        return roundNormalGameType;
    }

    public RoundNormalGameConfigExtend getRoundNormalGameConfigExtend() {
        return roundNormalGameConfigExtend;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public PayTableConfig getPayTableConfig() {
        return payTableConfig;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public InitialScreenConfig getInitialScreenConfig() {
        return initialScreenConfig;
    }
}

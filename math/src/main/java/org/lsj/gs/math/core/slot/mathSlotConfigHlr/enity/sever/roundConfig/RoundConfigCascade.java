package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.CascadeClusterConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig.InitialScreenConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig.RoundCascadeGameConfigExtend;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;

// 局設定消除
public class RoundConfigCascade extends RoundConfig {
    private final ReelConfig reelConfig; // 消除輪帶表設定
    private final InitialScreenConfig initialScreenConfig; // 初始畫面設定

    @JsonIgnore
    private final GameConfig gameConfig; // 算分設定
    @JsonIgnore
    private final PayTableConfig payTableConfig; // 賠率表設定

    @JsonIgnore
    private final CascadeClusterConfig cascadeClusterConfig; // 消除集合設定
    @JsonIgnore
    private final ConstMathSlot.RoundCascadeGameType roundCascadeGameType; // 消除遊戲類型
    @JsonIgnore
    private final RoundCascadeGameConfigExtend roundCascadeGameConfigExtend; // 消除遊戲額外設定


    public RoundConfigCascade(
            ReelConfig reelConfig, InitialScreenConfig initialScreenConfig,
            FrameConfig frameConfig, SymbolConfig symbolConfig, DampConfig dampConfig, GameConfig gameConfig, PayTableConfig payTableConfig, ProgressConfig progressConfig,
            SpecialFeatureConfigCluster specialFeatureConfigCluster, ReadyHandConfigCluster readyHandConfigCluster, CascadeClusterConfig cascadeClusterConfig,
            ConstMathSlot.RoundCascadeGameType roundCascadeGameType, RoundCascadeGameConfigExtend roundCascadeGameConfigExtend) {
        super(frameConfig, symbolConfig, dampConfig, progressConfig, specialFeatureConfigCluster, readyHandConfigCluster);
        this.reelConfig = reelConfig;
        this.initialScreenConfig = initialScreenConfig;
        this.cascadeClusterConfig = cascadeClusterConfig;
        this.roundCascadeGameType = roundCascadeGameType;
        this.roundCascadeGameConfigExtend = roundCascadeGameConfigExtend;
        this.payTableConfig = payTableConfig;
        this.gameConfig = gameConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public InitialScreenConfig getInitialScreenConfig() {
        return initialScreenConfig;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public PayTableConfig getPayTableConfig() {
        return payTableConfig;
    }

    public CascadeClusterConfig getCascadeClusterConfig() {
        return cascadeClusterConfig;
    }

    public ConstMathSlot.RoundCascadeGameType getRoundCascadeGameType() {
        return roundCascadeGameType;
    }

    public RoundCascadeGameConfigExtend getRoundCascadeGameConfigExtend() {
        return roundCascadeGameConfigExtend;
    }
}

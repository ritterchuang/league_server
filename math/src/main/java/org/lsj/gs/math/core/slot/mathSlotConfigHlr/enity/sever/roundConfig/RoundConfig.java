package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;

//局設定父類別
public class RoundConfig {
    @JsonIgnore
    private final FrameConfig frameConfig; // 畫面設定
    @JsonIgnore
    private final SymbolConfig symbolConfig; // 標誌設定
    @JsonIgnore
    private final DampConfig dampConfig; // 破框設定
    @JsonIgnore
    private final ProgressConfig progressConfig; // 遊戲進度設定
    @JsonIgnore
    private final SpecialFeatureConfigCluster specialFeatureConfigCluster; // 特殊事件設定集合
    @JsonIgnore
    private final ReadyHandConfigCluster readyHandConfigCluster; // 遊戲進度設定

    public RoundConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, DampConfig dampConfig, ProgressConfig progressConfig, SpecialFeatureConfigCluster specialFeatureConfigCluster, ReadyHandConfigCluster readyHandConfigCluster) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
        this.dampConfig = dampConfig;
        this.progressConfig = progressConfig;
        this.specialFeatureConfigCluster = specialFeatureConfigCluster;
        this.readyHandConfigCluster = readyHandConfigCluster;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }

    public ProgressConfig getProgressConfig() {
        return progressConfig;
    }

    public SpecialFeatureConfigCluster getSpecialFeatureConfigCluster() {
        return specialFeatureConfigCluster;
    }

    public ReadyHandConfigCluster getReadyHandConfigCluster() {
        return readyHandConfigCluster;
    }
}

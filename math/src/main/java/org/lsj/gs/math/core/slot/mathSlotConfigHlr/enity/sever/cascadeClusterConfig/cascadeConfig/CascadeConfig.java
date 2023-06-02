package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtend;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminateConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfigCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfigCluster;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;

// 消除設定
public class CascadeConfig {
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeConfigExtend cascadeConfigExtend; // 消除額外設定
    private final SpecialFeatureConfigCluster specialFeatureConfigCluster; // 特殊事件設定
    private final ReadyHandConfigCluster readyHandConfigCluster; // 聽牌事件設定
    private final ProgressConfig progressConfig; // 消除進度設定
    private final EliminateConfig eliminateConfig; // 消除方式設定

    public CascadeConfig(ConstMathSlot.CascadeType cascadeType, CascadeConfigExtend cascadeConfigExtend, SpecialFeatureConfigCluster specialFeatureConfigCluster, ReadyHandConfigCluster readyHandConfigCluster, ProgressConfig progressConfig, EliminateConfig eliminateConfig) {
        this.cascadeType = cascadeType;
        this.cascadeConfigExtend = cascadeConfigExtend;
        this.specialFeatureConfigCluster = specialFeatureConfigCluster;
        this.readyHandConfigCluster = readyHandConfigCluster;
        this.progressConfig = progressConfig;
        this.eliminateConfig = eliminateConfig;
    }

    public ConstMathSlot.CascadeType getCascadeType() {
        return cascadeType;
    }

    public CascadeConfigExtend getCascadeConfigExtend() {
        return cascadeConfigExtend;
    }

    public SpecialFeatureConfigCluster getSpecialFeatureConfigCluster() {
        return specialFeatureConfigCluster;
    }

    public ReadyHandConfigCluster getReadyHandConfigCluster() {
        return readyHandConfigCluster;
    }

    public ProgressConfig getProgressConfig() {
        return progressConfig;
    }

    public EliminateConfig getEliminateConfig() {
        return eliminateConfig;
    }
}

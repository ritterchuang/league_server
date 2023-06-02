package org.lsj.gs.math.core.slot.screenGtrMgr.enity;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

// 輪帶計算器設定檔
public class ReelCtrConfig {
    private final ReelConfig reelConfig; // 輪帶軸設定
    private final DampConfig dampConfig; // 破框設定

    public ReelCtrConfig(ReelConfig reelConfig, DampConfig dampConfig) {
        this.reelConfig = reelConfig;
        this.dampConfig = dampConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }
}

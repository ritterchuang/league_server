package org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

import java.util.Map;

// 虎機詳細記錄計算者額外設定父類別
public class SlotDetailHlrConfigExtend {
    private final Map<Integer, String> symbolIdToStringMap; // <標誌識別碼, 標誌名稱> 對應表
    private final FrameConfig frameConfig; // 畫面設定
    private final ReelConfig reelConfig; // 輪帶表

    public SlotDetailHlrConfigExtend(Map<Integer, String> symbolIdToStringMap, FrameConfig frameConfig, ReelConfig reelConfig) {
        this.symbolIdToStringMap = symbolIdToStringMap;
        this.frameConfig = frameConfig;
        this.reelConfig = reelConfig;
    }

    public Map<Integer, String> getSymbolIdToStringMap() {
        return symbolIdToStringMap;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }
}

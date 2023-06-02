package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 輪帶表設定
public class ReelConfig {
    private final ConstMathSlot.ReelType reelType; // 輪帶表類型
    private final List<ReelStripBox> reelStripBoxList; // 輪帶資訊列表 [reelId] = 輪帶表

    public ReelConfig(ConstMathSlot.ReelType reelType, List<ReelStripBox> reelStripBoxList) {
        this.reelType = reelType;
        this.reelStripBoxList = reelStripBoxList;
    }

    public ConstMathSlot.ReelType getReelType() {
        return reelType;
    }

    public List<ReelStripBox> getReelStripBoxList() {
        return reelStripBoxList;
    }
}

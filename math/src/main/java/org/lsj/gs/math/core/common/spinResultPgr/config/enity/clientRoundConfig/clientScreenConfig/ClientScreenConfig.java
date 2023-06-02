package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;

// 客端畫面設定
public class ClientScreenConfig {
    private final ConstPgrSlot.ScreenOffsetType screenOffsetType; // 偏移量類型
    private final double dampZoomRatio; // 破框比例

    public ClientScreenConfig(ConstPgrSlot.ScreenOffsetType screenOffsetType, double dampZoomRatio) {
        this.screenOffsetType = screenOffsetType;
        this.dampZoomRatio = dampZoomRatio;
    }

    public ClientScreenConfig(ConstPgrSlot.ScreenOffsetType screenOffsetType) {
        this.screenOffsetType = screenOffsetType;
        this.dampZoomRatio = 0.0;
    }

    public ConstPgrSlot.ScreenOffsetType getScreenOffsetType() {
        return screenOffsetType;
    }

    public double getDampZoomRatio() {
        return dampZoomRatio;
    }
}

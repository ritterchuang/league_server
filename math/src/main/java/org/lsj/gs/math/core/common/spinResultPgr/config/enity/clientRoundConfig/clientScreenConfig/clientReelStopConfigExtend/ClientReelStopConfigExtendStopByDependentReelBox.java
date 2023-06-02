package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.clientReelStopConfigExtend;

// 客端停輪額外設定 相依滾輪
public class ClientReelStopConfigExtendStopByDependentReelBox extends ClientReelStopConfigExtend{
    private final double zoomRatio;

    public ClientReelStopConfigExtendStopByDependentReelBox(double zoomRatio) {
        this.zoomRatio = zoomRatio;
    }

    public double getZoomRatio() {
        return zoomRatio;
    }
}

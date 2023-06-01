package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 輪帶表資訊
public class ReelStripBox {
    private final int reelId; // 輪帶表識別碼
    private final int weight; // 輪帶表權重
    private final ReelConfigExtend reelConfigExtend; // 客製輪帶表額外設定
    @JsonIgnore
    private final ConstMathSlot.ReelRtpType reelRtpType; // 高低表類型
    @JsonIgnore
    private final ConstMathSlot.ReelStopType reelStopType; // 畫面停輪方式

    public ReelStripBox(int reelId, int weight, ReelConfigExtend reelConfigExtend, ConstMathSlot.ReelRtpType reelRtpType, ConstMathSlot.ReelStopType reelStopType) {
        this.reelId = reelId;
        this.weight = weight;
        this.reelConfigExtend = reelConfigExtend;
        this.reelRtpType = reelRtpType;
        this.reelStopType = reelStopType;
    }

    public int getReelId() {
        return reelId;
    }

    public int getWeight() {
        return weight;
    }

    public ReelConfigExtend getReelConfigExtend() {
        return reelConfigExtend;
    }

    public ConstMathSlot.ReelRtpType getReelRtpType() {
        return reelRtpType;
    }

    public ConstMathSlot.ReelStopType getReelStopType() {
        return reelStopType;
    }
}

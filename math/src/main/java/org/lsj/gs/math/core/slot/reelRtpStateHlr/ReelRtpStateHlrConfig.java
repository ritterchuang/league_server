package org.lsj.gs.math.core.slot.reelRtpStateHlr;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 高低表處理者設定
public class ReelRtpStateHlrConfig {
    private final Map<ConstMathSlot.ReelRtpType, Double> reelRtpTypeToDoubleMap; // 高低表設定

    public ReelRtpStateHlrConfig(Map<ConstMathSlot.ReelRtpType, Double> reelRtpTypeToDoubleMap) {
        this.reelRtpTypeToDoubleMap = reelRtpTypeToDoubleMap;
    }

    public Map<ConstMathSlot.ReelRtpType, Double> getReelRtpTypeToDoubleMap() {
        return reelRtpTypeToDoubleMap;
    }
}

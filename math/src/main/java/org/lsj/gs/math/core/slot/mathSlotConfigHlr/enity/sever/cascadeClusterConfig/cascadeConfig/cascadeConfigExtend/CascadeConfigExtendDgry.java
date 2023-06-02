package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

import java.util.List;
import java.util.Map;

// 消除額外設定 帝国榮耀
public class CascadeConfigExtendDgry extends CascadeConfigExtend{
    private final ReelConfig cascadeReelConfig; // 消除輪帶表設定
    private final List<Integer> screenMultiplier; // 畫面倍數列表
    private final Map<ConstMathSlot.ReelRtpType, List<Integer>> screenMultiplierWeight; // 畫面倍數權重

    public CascadeConfigExtendDgry(ReelConfig cascadeReelConfig, List<Integer> screenMultiplier, Map<ConstMathSlot.ReelRtpType, List<Integer>> screenMultiplierWeight) {
        this.cascadeReelConfig = cascadeReelConfig;
        this.screenMultiplier = screenMultiplier;
        this.screenMultiplierWeight = screenMultiplierWeight;
    }

    public ReelConfig getCascadeReelConfig() {
        return cascadeReelConfig;
    }

    public List<Integer> getScreenMultiplier() { return screenMultiplier; }

    public Map<ConstMathSlot.ReelRtpType, List<Integer>> getScreenMultiplierWeight() { return screenMultiplierWeight; }
}

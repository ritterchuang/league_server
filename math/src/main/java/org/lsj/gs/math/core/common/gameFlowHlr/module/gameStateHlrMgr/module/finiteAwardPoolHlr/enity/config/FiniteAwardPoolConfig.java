package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 倍數表演設定
public class FiniteAwardPoolConfig {
    private final ConstMathSlot.FiniteAwardPoolType finiteAwardPoolType; // 倍數表演類型
    private final Map<ConstMathSlot.ReelRtpType, FiniteAwardPoolConfigExtend> reelRtpTypeToConfigExtendMap; // <高低表類型, 倍數表演額外設定> 對應表

    public FiniteAwardPoolConfig(ConstMathSlot.FiniteAwardPoolType finiteAwardPoolType, Map<ConstMathSlot.ReelRtpType, FiniteAwardPoolConfigExtend> reelRtpTypeToConfigExtendMap) {
        this.finiteAwardPoolType = finiteAwardPoolType;
        this.reelRtpTypeToConfigExtendMap = reelRtpTypeToConfigExtendMap;
    }

    public ConstMathSlot.FiniteAwardPoolType getFiniteAwardPoolType() {
        return finiteAwardPoolType;
    }

    public Map<ConstMathSlot.ReelRtpType, FiniteAwardPoolConfigExtend> getReelRtpTypeToConfigExtendMap() {
        return reelRtpTypeToConfigExtendMap;
    }
}

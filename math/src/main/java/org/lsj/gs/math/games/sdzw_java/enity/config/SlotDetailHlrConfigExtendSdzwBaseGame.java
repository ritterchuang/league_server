package org.lsj.gs.math.games.sdzw_java.enity.config;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfigExtend;

import java.util.Map;

// 虎機詳細記錄計算者額外設定 聖誕任務虎機基本遊戲
public class SlotDetailHlrConfigExtendSdzwBaseGame extends SlotDetailHlrConfigExtend {
    private final SymbolConfig symbolConfig; // 標誌設定

    public SlotDetailHlrConfigExtendSdzwBaseGame(Map<Integer, String> symbolIdToStringMap, FrameConfig frameConfig, ReelConfig reelConfig, SymbolConfig symbolConfig) {
        super(symbolIdToStringMap, frameConfig, reelConfig);
        this.symbolConfig = symbolConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }
}

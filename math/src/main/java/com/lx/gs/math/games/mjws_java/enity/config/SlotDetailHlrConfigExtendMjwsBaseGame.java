package com.lx.gs.math.games.mjws_java.enity.config;

import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfigExtend;

import java.util.Map;

// 虎機詳細記錄計算者額外設定 麻將無雙基本遊戲
public class SlotDetailHlrConfigExtendMjwsBaseGame extends SlotDetailHlrConfigExtend {
    private final SymbolConfig symbolConfig; // 標誌設定

    public SlotDetailHlrConfigExtendMjwsBaseGame(Map<Integer, String> symbolIdToStringMap, FrameConfig frameConfig, ReelConfig reelConfig, SymbolConfig symbolConfig) {
        super(symbolIdToStringMap, frameConfig, reelConfig);
        this.symbolConfig = symbolConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

}

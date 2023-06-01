package com.lx.gs.math.games.pxky_java.enity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;

// 貔貅開運局額外設定
public class GameStateConfigExtendPxkyBaseGame extends GameStateConfigExtend {
    @JsonIgnore
    private final FiniteAwardPoolConfig finiteAwardPoolConfig; // 有限獎線水池設定

    public GameStateConfigExtendPxkyBaseGame(FiniteAwardPoolConfig finiteAwardPoolConfig) {
        this.finiteAwardPoolConfig = finiteAwardPoolConfig;
    }

    public FiniteAwardPoolConfig getFiniteAwardPoolConfig() {
        return finiteAwardPoolConfig;
    }
}

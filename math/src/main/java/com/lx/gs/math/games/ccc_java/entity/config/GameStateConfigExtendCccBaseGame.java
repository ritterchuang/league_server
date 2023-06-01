package com.lx.gs.math.games.ccc_java.entity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;

// 777局額外設定
public class GameStateConfigExtendCccBaseGame extends GameStateConfigExtend {
    @JsonIgnore
    private final FiniteAwardPoolConfig finiteAwardPoolConfig; // 有限獎線水池設定

    public GameStateConfigExtendCccBaseGame(FiniteAwardPoolConfig finiteAwardPoolConfig) {
        this.finiteAwardPoolConfig = finiteAwardPoolConfig;
    }

    public FiniteAwardPoolConfig getFiniteAwardPoolConfig() {
        return finiteAwardPoolConfig;
    }
}

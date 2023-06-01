package com.lx.gs.math.games.lll_java.entity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;

// 龍龍龍局額外設定
public class GameStateConfigExtendLllBaseGame extends GameStateConfigExtend {
    @JsonIgnore
    private final FiniteAwardPoolConfig finiteAwardPoolConfig; // 有限獎線水池設定

    public GameStateConfigExtendLllBaseGame(FiniteAwardPoolConfig finiteAwardPoolConfig) {
        this.finiteAwardPoolConfig = finiteAwardPoolConfig;
    }

    public FiniteAwardPoolConfig getFiniteAwardPoolConfig() {
        return finiteAwardPoolConfig;
    }
}

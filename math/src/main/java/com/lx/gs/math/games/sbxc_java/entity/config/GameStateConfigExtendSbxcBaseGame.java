package com.lx.gs.math.games.sbxc_java.entity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;

// 三倍小丑局額外設定
public class GameStateConfigExtendSbxcBaseGame extends GameStateConfigExtend {
    @JsonIgnore
    private final FiniteAwardPoolConfig finiteAwardPoolConfig; // 有限獎線水池設定

    public GameStateConfigExtendSbxcBaseGame(FiniteAwardPoolConfig finiteAwardPoolConfig) {
        this.finiteAwardPoolConfig = finiteAwardPoolConfig;
    }

    public FiniteAwardPoolConfig getFiniteAwardPoolConfig() {
        return finiteAwardPoolConfig;
    }
}

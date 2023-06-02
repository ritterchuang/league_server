package org.lsj.gs.math.games.xzcq_java.entity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;

// 小豬傳奇局額外設定
public class GameStateConfigExtendXzcqBaseGame extends GameStateConfigExtend {
    @JsonIgnore
    private final FiniteAwardPoolConfig finiteAwardPoolConfig; // 有限獎線水池設定

    public GameStateConfigExtendXzcqBaseGame(FiniteAwardPoolConfig finiteAwardPoolConfig) {
        this.finiteAwardPoolConfig = finiteAwardPoolConfig;
    }

    public FiniteAwardPoolConfig getFiniteAwardPoolConfig() {
        return finiteAwardPoolConfig;
    }
}

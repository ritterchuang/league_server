package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.OddsBox;

import java.util.List;

// 倍數表演額外設定
public class FiniteAwardPoolConfigExtend {
    private final List<OddsBox> oddsBoxList; // 倍數資訊列表

    public FiniteAwardPoolConfigExtend(List<OddsBox> oddsBoxList) {
        this.oddsBoxList = oddsBoxList;
    }

    public List<OddsBox> getOddsBoxList() {
        return oddsBoxList;
    }
}

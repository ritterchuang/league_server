package org.lsj.gs.math.games.dgry_java.entity.config;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigExtend;

import java.util.List;

// 帝国榮耀額外遊戲局額外設定(表演結果)
public class DgryBonusGameResult extends GameStateConfigExtend {
    private final List<DgryBonusGameDisplayType> outcomeList; // 結果列表
    private final double totalScore; // 得分

    public DgryBonusGameResult(List<DgryBonusGameDisplayType> outcomeList) {
        this.outcomeList = outcomeList;
        this.totalScore = outcomeList.stream().mapToDouble(DgryBonusGameDisplayType::getScore).sum();
    }

    public List<DgryBonusGameDisplayType> getOutcomeList() { return outcomeList; }

    public double getTotalScore() { return totalScore; }
}

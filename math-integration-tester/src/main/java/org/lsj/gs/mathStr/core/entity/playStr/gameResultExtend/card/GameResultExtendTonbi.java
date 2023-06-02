package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 通比類 客製遊戲結果
public class GameResultExtendTonbi extends GameResultExtend {
    private final int humanChairIndex; // 真人位置
    private final int humanBetRate; // 真人下注倍數 -1: 無下注;
    private final Map<Integer, UidScore> uidScoreMap; // 所有得分

    public GameResultExtendTonbi(int humanChairIndex, int humanBetRate, Map<Integer, UidScore> uidScoreMap) {
        this.humanChairIndex = humanChairIndex;
        this.humanBetRate = humanBetRate;
        this.uidScoreMap = uidScoreMap;
    }

    public int getHumanChairIndex() {
        return humanChairIndex;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public Map<Integer, UidScore> getUidScoreMap() {
        return uidScoreMap;
    }
}

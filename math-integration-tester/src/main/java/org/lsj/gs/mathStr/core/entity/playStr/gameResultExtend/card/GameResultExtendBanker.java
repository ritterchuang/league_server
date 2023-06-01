package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 搶莊類 客製遊戲結果
public class GameResultExtendBanker extends GameResultExtend {
    private final int humanChairIndex; // 真人位置
    private final int bankerChairIndex; // 莊家位置
    private final int humanVieBankerRate; // 真人搶莊倍數 0: 不搶;
    private final int humanBetRate; // 真人下注倍數 -1: 無下注;
    private final Map<Integer, UidScore> uidScoreMap; // 所有得分

    public GameResultExtendBanker(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, Map<Integer, UidScore> uidScoreMap) {
        this.humanChairIndex = humanChairIndex;
        this.bankerChairIndex = bankerChairIndex;
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.uidScoreMap = uidScoreMap;
    }

    public int getHumanChairIndex() {
        return humanChairIndex;
    }

    public int getBankerChairIndex() {
        return bankerChairIndex;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public Map<Integer, UidScore> getUidScoreMap() {
        return uidScoreMap;
    }
}

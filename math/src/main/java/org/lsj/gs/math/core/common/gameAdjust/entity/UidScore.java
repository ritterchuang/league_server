package org.lsj.gs.math.core.common.gameAdjust.entity;

import org.lsj.gs.math.core.common.table.entity.message.AbstractToStringStruct;

// 玩家輸贏結果
public class UidScore extends AbstractToStringStruct {
    private final int chairIndex; // 玩家座位
    private final int uid; // 玩家識別碼
    private final double bet; // 下注金額
    private final double validBet; // 有效投注
    private final double returnAward; // 總得分
    private final double score; // 淨利
    private final double fee; // 服務費

    public UidScore(int chairIndex, int uid, double bet, double validBet, double returnAward, double score, double fee) {
        this.chairIndex = chairIndex;
        this.uid = uid;
        this.bet = bet;
        this.validBet = validBet;
        this.returnAward = returnAward;
        this.score = score;
        this.fee = fee;
    }

    public int getChairIndex() {
        return chairIndex;
    }

    public int getUid() {
        return uid;
    }

    public double getBet() {
        return bet;
    }

    public double getValidBet() {
        return validBet;
    }

    public double getReturnAward() {
        return returnAward;
    }

    public double getScore() {
        return score;
    }

    public double getFee() {
        return fee;
    }
}

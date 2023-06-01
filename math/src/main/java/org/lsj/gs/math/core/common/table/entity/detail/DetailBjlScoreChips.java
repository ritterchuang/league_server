package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 所有百家下注詳細資訊
public class DetailBjlScoreChips implements IDetailPlayBaiRen {
    public final int bankerTChips;      // 庄总下注
    public final int playerTChips;      // 闲总下注
    public final int tieTChips;         // 和总下注
    public final double totalScore;     // 总输赢金额

    public DetailBjlScoreChips(int bankerTChips, int playerTChips, int tieTChips, double totalScore) {
        this.bankerTChips = bankerTChips;
        this.playerTChips = playerTChips;
        this.tieTChips = tieTChips;
        this.totalScore = totalScore;
    }
}

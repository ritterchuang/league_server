package org.lsj.gs.math.games.zjh_java.entity.detail;

// 比牌結果詳細記錄
public class DetailCompareResult {
    public final int chair; // 座位索引
    public final double score; // 淨利
    public final int[] c_hand; // 手牌牌號
    public final int ct_; // 牌型

    public DetailCompareResult(int chair, double score, int[] c_hand, int ct_) {
        this.chair = chair;
        this.score = score;
        this.c_hand = c_hand;
        this.ct_ = ct_;
    }
}

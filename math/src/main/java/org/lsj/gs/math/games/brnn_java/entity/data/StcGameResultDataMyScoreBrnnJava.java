package org.lsj.gs.math.games.brnn_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreBrnnJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreBrnnJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreBrnnJava(double money, double score, StcGameResultDataAreaScoreBrnnJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

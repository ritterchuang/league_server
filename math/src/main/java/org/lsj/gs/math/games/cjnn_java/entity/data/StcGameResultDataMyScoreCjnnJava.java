package org.lsj.gs.math.games.cjnn_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreCjnnJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreCjnnJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreCjnnJava(double money, double score, StcGameResultDataAreaScoreCjnnJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

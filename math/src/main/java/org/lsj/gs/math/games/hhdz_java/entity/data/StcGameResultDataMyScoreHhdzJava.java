package org.lsj.gs.math.games.hhdz_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreHhdzJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreHhdzJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreHhdzJava(double money, double score, StcGameResultDataAreaScoreHhdzJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

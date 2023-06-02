package org.lsj.gs.math.games.bjl_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreBjlJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreBjlJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreBjlJava(double money, double score, StcGameResultDataAreaScoreBjlJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

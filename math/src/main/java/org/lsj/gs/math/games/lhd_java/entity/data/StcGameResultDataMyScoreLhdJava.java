package org.lsj.gs.math.games.lhd_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreLhdJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreLhdJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreLhdJava(double money, double score, StcGameResultDataAreaScoreLhdJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

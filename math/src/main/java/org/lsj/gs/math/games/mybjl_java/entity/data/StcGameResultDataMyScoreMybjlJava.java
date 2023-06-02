package org.lsj.gs.math.games.mybjl_java.entity.data;

// 玩家分數資訊
public class StcGameResultDataMyScoreMybjlJava {
    public double money; // 結算後攜帶金額
    public double score; // 總得分
    public StcGameResultDataAreaScoreMybjlJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataMyScoreMybjlJava(double money, double score, StcGameResultDataAreaScoreMybjlJava[] areas) {
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

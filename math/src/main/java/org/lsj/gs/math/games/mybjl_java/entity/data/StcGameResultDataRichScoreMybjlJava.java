package org.lsj.gs.math.games.mybjl_java.entity.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 榜單玩家得分
public class StcGameResultDataRichScoreMybjlJava {
    @JsonIgnore public int chairIndex; // 座位
    public int uid; // 玩家識別碼
    public double money; // 結算後攜帶金額
    public double score; // 得分
    public StcGameResultDataAreaScoreMybjlJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataRichScoreMybjlJava(int chairIndex, int uid, double money, double score, StcGameResultDataAreaScoreMybjlJava[] areas) {
        this.chairIndex = chairIndex;
        this.uid = uid;
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

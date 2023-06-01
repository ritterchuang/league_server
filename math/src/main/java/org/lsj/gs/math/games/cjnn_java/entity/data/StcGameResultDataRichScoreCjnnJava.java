package org.lsj.gs.math.games.cjnn_java.entity.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 榜單玩家得分
public class StcGameResultDataRichScoreCjnnJava {
    @JsonIgnore public int chairIndex; // 座位
    public int uid; // 玩家識別碼
    public double money; // 結算後攜帶金額
    public double score; // 得分
    public StcGameResultDataAreaScoreCjnnJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataRichScoreCjnnJava(int chairIndex, int uid, double money, double score, StcGameResultDataAreaScoreCjnnJava[] areas) {
        this.chairIndex = chairIndex;
        this.uid = uid;
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

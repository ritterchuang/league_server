package org.lsj.gs.math.games.hhdz_java.entity.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 榜單玩家得分
public class StcGameResultDataRichScoreHhdzJava {
    @JsonIgnore public int chairIndex; // 座位
    public int uid; // 玩家識別碼
    public double money; // 結算後攜帶金額
    public double score; // 得分
    public StcGameResultDataAreaScoreHhdzJava[] areas; // 贏的區域; 當 score = 0, 為[];

    public StcGameResultDataRichScoreHhdzJava(int chairIndex, int uid, double money, double score, StcGameResultDataAreaScoreHhdzJava[] areas) {
        this.chairIndex = chairIndex;
        this.uid = uid;
        this.money = money;
        this.score = score;
        this.areas = areas;
    }
}

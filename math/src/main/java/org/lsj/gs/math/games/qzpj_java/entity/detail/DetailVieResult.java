package org.lsj.gs.math.games.qzpj_java.entity.detail;

// 搶莊結果詳細記錄
public class DetailVieResult {
    public final String flag; // 標記
    public final int banker; // 莊家座位索引
    public final int rate; // 搶莊倍數

    public DetailVieResult(String flag, int banker, int rate) {
        this.flag = flag;
        this.banker = banker;
        this.rate = rate;
    }
}

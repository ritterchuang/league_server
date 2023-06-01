package com.lx.gs.math.games.qznn_java.entity.detail;

// 下注詳細記錄
public class DetailBet {
    public final int chair; // 座位索引
    public final long time; // 經過時間(秒)
    public final String flag; // 標記
    public final int rate; // 搶莊倍數

    public DetailBet(int chairIndex, long time, String flag, int rate) {
        this.chair = chairIndex;
        this.time = time;
        this.flag = flag;
        this.rate = rate;
    }
}

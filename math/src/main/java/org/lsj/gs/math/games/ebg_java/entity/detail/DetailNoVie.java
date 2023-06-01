package org.lsj.gs.math.games.ebg_java.entity.detail;

// 未搶莊詳細記錄
public class DetailNoVie {
    public final int chair; // 座位索引
    public final long time; // 經過時間(秒)
    public final String flag; // 標記

    public DetailNoVie(int chairIndex, long time, String flag) {
        this.chair = chairIndex;
        this.time = time;
        this.flag = flag;
    }
}

package org.lsj.gs.math.core.common.table.entity.detail;

// 狀態詳細紀錄
public class DetailState {
    public final long time; // 經過時間
    public final String flag; // 標記

    public DetailState(long time, String flag) {
        this.time = time;
        this.flag = flag;
    }
}

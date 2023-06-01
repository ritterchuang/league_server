package org.lsj.gs.math.core.common.table.entity.detail;

// 遊戲開始詳細記錄
public class DetailGameBegin {
    public final String ts; // 時間戳記
    public final String flag; // 標記

    public DetailGameBegin(String ts, String flag) {
        this.ts = ts;
        this.flag = flag;
    }
}

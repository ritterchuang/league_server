package org.lsj.gs.math.core.common.table.entity.message.core.data;

// 進入房間訊息資料
public class StcEnterFieldMsgData {
    public int code; // 編碼
    public int table; // 桌號
    public int chair; // 座位索引
    public int state; // 狀態

    public StcEnterFieldMsgData(int code, int table, int chair, int state) {
        this.code = code;
        this.table = table;
        this.chair = chair;
        this.state = state;
    }
}
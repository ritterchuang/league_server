package org.lsj.gs.math.core.common.table.entity.detail.play;

// 百人遊戲詳細資訊 返還
public class DetailPlayBaiRenReturn implements IDetailPlayBaiRen{
    public final long time; // 經過時間
    public final String flag; // 標記
    public final int amount; // 押注區域

    public DetailPlayBaiRenReturn(long time, int amount) {
        this.time = time;
        this.flag = "return";
        this.amount = amount;
    }
}

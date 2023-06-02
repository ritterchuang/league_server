package org.lsj.gs.math.core.common.table.entity.detail.play;

// 百人遊戲詳細資訊 派獎
public class DetailPlayBaiRenAward implements IDetailPlayBaiRen{
    public final long time; // 經過時間
    public final String flag; // 標記
    public final int betArea; // 押注區域
    public final double amount; // 金額

    public DetailPlayBaiRenAward(long time, int betArea, double amount) {
        this.time = time;
        this.flag = "award";
        this.betArea = betArea;
        this.amount = amount;
    }
}

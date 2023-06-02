package org.lsj.gs.math.core.common.table.entity.detail.play;

// 百人遊戲詳細資訊 押注
public class DetailPlayBaiRenBet implements IDetailPlayBaiRen{
    public final long time; // 經過時間
    public final String flag; // 標記
    public final int betArea; // 押注區域
    public final int chips; // 籌碼

    public DetailPlayBaiRenBet(long time, int betArea, int chips) {
        this.time = time;
        this.flag = "bet";
        this.betArea = betArea;
        this.chips = chips;
    }
}

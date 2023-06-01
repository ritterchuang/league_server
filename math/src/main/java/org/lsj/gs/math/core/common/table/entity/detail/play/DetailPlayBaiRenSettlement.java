package org.lsj.gs.math.core.common.table.entity.detail.play;

// 百人遊戲詳細資訊 結算
public class DetailPlayBaiRenSettlement implements IDetailPlayBaiRen{
    public final long time; // 經過時間
    public final String flag; // 標記
    public final double score; // 分數

    public DetailPlayBaiRenSettlement(long time, double score) {
        this.time = time;
        this.flag = "settlement";
        this.score = score;
    }
}

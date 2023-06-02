package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

import java.util.List;

// 真人遊戲詳細紀錄
public class DetailRealPlayList {
    public final int realUid; // 真人識別碼
    public final int realChair; // 真人位置
    public List<IDetailPlayBaiRen> detail; // 詳細遊戲紀錄

    public DetailRealPlayList(int realUid, int realChair, List<IDetailPlayBaiRen> detail) {
        this.realUid = realUid;
        this.realChair = realChair;
        this.detail = detail;
    }
}

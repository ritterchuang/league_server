package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 所有派獎點詳細資訊
public class DetailWinAreas implements IDetailPlayBaiRen {
    public final int[] winAreas; // 所有派獎點

    public DetailWinAreas(int[] winAreas) {
        this.winAreas = winAreas;
    }

    public int[] getWinAreas() {
        return winAreas;
    }
}

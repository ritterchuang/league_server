package org.lsj.gs.math.core.common.table.entity.detail;

// 所有派獎點百家詳細資訊
public class DetailWinAreasBjl extends DetailWinAreas {
    public final int[] returnAreas; // 所有返還點

    public DetailWinAreasBjl(int[] winAreas, int[] returnAreas) {
        super(winAreas);
        this.returnAreas = returnAreas;
    }
}

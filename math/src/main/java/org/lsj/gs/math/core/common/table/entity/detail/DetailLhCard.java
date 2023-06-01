package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 龍虎牌詳細資訊
public class DetailLhCard implements IDetailPlayBaiRen {
    public final int[] c_long; // 龍牌
    public final int[] c_hu; // 虎牌

    public DetailLhCard(int[] c_long, int[] c_hu) {
        this.c_long = c_long;
        this.c_hu = c_hu;
    }
}

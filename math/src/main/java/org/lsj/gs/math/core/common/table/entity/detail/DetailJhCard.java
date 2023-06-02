package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 紅黑牌詳細資訊
public class DetailJhCard implements IDetailPlayBaiRen {
    public final int[] c_black; // 黑牌
    public final int[] c_red; // 紅牌
    public final int ct_black; // 黑牌型
    public final int ct_red; // 紅牌型

    public DetailJhCard(int[] c_black, int[] c_red, int ct_black, int ct_red) {
        this.c_black = c_black;
        this.c_red = c_red;
        this.ct_black = ct_black;
        this.ct_red = ct_red;
    }

    public int[] getC_black() {
        return c_black;
    }

    public int[] getC_red() {
        return c_red;
    }

    public int getCt_black() {
        return ct_black;
    }

    public int getCt_red() {
        return ct_red;
    }
}

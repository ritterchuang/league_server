package com.lx.gs.math.games.cjnn_java.entity.detail;

import com.lx.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 地牌詳細資訊
public class DetailDiCardCjnnJava implements IDetailPlayBaiRen {
    public final int[] c_di; // 地牌
    public final int ct_di; // 地牌型

    public DetailDiCardCjnnJava(int[] c_di, int ct_di) {
        this.c_di = c_di;
        this.ct_di = ct_di;
    }

    public int[] getC_di() {
        return c_di;
    }

    public int getCt_di() {
        return ct_di;
    }
}

package com.lx.gs.math.games.brnn_java.entity.detail;

import com.lx.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 莊牌詳細資訊
public class DetailBankerCardBrnnJava implements IDetailPlayBaiRen {
    public final int[] c_banker; // 莊牌
    public final int ct_banker; // 莊牌型

    public DetailBankerCardBrnnJava(int[] c_banker, int ct_banker) {
        this.c_banker = c_banker;
        this.ct_banker = ct_banker;
    }

    public int[] getC_banker() {
        return c_banker;
    }

    public int getCt_banker() {
        return ct_banker;
    }
}

package org.lsj.gs.math.games.brnn_java.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 玄牌詳細資訊
public class DetailXuanCardBrnnJava implements IDetailPlayBaiRen {
    public final int[] c_xuan; // 天牌
    public final int ct_xuan; // 天牌型

    public DetailXuanCardBrnnJava(int[] c_xuan, int ct_xuan) {
        this.c_xuan = c_xuan;
        this.ct_xuan = ct_xuan;
    }

    public int[] getC_xuan() {
        return c_xuan;
    }

    public int getCt_xuan() {
        return ct_xuan;
    }
}

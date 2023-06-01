package org.lsj.gs.math.games.cjnn_java.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 天牌詳細資訊
public class DetailTianCardCjnnJava implements IDetailPlayBaiRen {
    public final int[] c_tian; // 天牌
    public final int ct_tian; // 天牌型

    public DetailTianCardCjnnJava(int[] c_tian, int ct_tian) {
        this.c_tian = c_tian;
        this.ct_tian = ct_tian;
    }

    public int[] getC_tian() {
        return c_tian;
    }

    public int getCt_tian() {
        return ct_tian;
    }
}

package org.lsj.gs.math.games.cjnn_java.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 黃牌詳細資訊
public class DetailHuangCardCjnnJava implements IDetailPlayBaiRen {
    public final int[] c_huang; // 黃牌
    public final int ct_huang; // 黃牌型

    public DetailHuangCardCjnnJava(int[] c_huang, int ct_huang) {
        this.c_huang = c_huang;
        this.ct_huang = ct_huang;
    }

    public int[] getC_huang() {
        return c_huang;
    }

    public int getCt_huang() {
        return ct_huang;
    }
}

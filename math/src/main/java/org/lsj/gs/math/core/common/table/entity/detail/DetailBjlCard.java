package org.lsj.gs.math.core.common.table.entity.detail;

import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 百家樂牌詳細資訊
public class DetailBjlCard implements IDetailPlayBaiRen {
    public final int[] c_player; // 閒牌
    public final int[] c_banker; // 庄牌

    public DetailBjlCard(int[] c_player, int[] c_banker) {
        this.c_player = c_player;
        this.c_banker = c_banker;
    }
}

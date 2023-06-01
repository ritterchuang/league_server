package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractCtsMessage;

public class CtsBetBaiRenJava extends AbstractCtsMessage {
    private int area; // 下注區域
    private int chips; // 下注金額

    // 原始建構子提供JSON解析用
    public CtsBetBaiRenJava(){}

    public CtsBetBaiRenJava(int area, int chips) {
        this.area = area;
        this.chips = chips;
    }

    public int getArea() {
        return area;
    }

    public int getChips() {
        return chips;
    }
}

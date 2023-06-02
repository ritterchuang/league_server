package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

import java.util.ArrayList;
import java.util.List;

// 連點寶箱客製處理結果
public class HitResultExtendTreasureBox extends HitResultExtend{
    private final List<Integer> showOddsList; // 表演倍數列表
    private final List<Double> showWinList; // 表演分數列表

    public HitResultExtendTreasureBox(List<Integer> showOddsList, List<Double> showWinList) {
        this.showOddsList = showOddsList;
        this.showWinList = showWinList;
    }

    public HitResultExtendTreasureBox() {
        this.showOddsList = new ArrayList<>();
        this.showWinList = new ArrayList<>();
    }

    public List<Integer> getShowOddsList() {
        return showOddsList;
    }

    public List<Double> getShowWinList() {
        return showWinList;
    }
}

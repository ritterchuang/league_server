package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTreasureBox;
import org.lsj.utils.MathUtil;

import java.util.List;

// 客製表演連點寶箱
public class DisplayResultExtendTreasureBox extends AbstractDisplayResultExtendValue{
    private final List<Integer> showOddsList; // 表演倍數列表
    private final List<Double> showWinList; // 表演分數列表

    public DisplayResultExtendTreasureBox(int killCount, double basicWin, HitResultExtendTreasureBox hitResultExtendTreasureBox) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.showOddsList = hitResultExtendTreasureBox.getShowOddsList();
        this.showWinList = hitResultExtendTreasureBox.getShowWinList();
    }

    public List<Integer> getShowOddsList() {
        return showOddsList;
    }

    public List<Double> getShowWinList() {
        return showWinList;
    }
}

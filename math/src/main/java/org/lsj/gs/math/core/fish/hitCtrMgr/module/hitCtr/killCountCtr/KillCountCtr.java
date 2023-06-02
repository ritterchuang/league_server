package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

// 擊殺次數計算者
public class KillCountCtr implements IKillCountCtr{
    private final ITableUtil tableUtil; // 牌桌工具包

    public KillCountCtr(ITableUtil tableUtil) {
        this.tableUtil = tableUtil;
    }

    // 計算擊殺次數
    public int calculateKillCount(double usedRtp, double odd, int hitCount) {
        // 1. 計算擊殺率
        double killRate = MathUtil.divide(usedRtp, odd);

        // 2. 校正打擊次數
        int adjustHitCount = this.calculateAdjustHitCount(killRate, hitCount);

        // 3. 校正擊殺率
        double adjustKillRate = adjustHitCount == 0 ? 0: MathUtil.divide(killRate, MathUtil.divide((double)adjustHitCount, (double)hitCount));

        // 4. 計算擊殺次數
        int result = 0;
        for (int hitIndex = 0; hitIndex < adjustHitCount; hitIndex++) {
            if (this.tableUtil.getMainRandomUtil().isHitWithAccuracy(adjustKillRate, ConstMathCommon.AccuracyType.MILLION)) {
                result++;
            }
        }

        return result;
    }

    // 計算校正打擊次數
    private int calculateAdjustHitCount(double killRate, int hitCount) {
        if (killRate <= 1) {
            return hitCount;
        }

        return ((int)(killRate) + 1) * hitCount;
    }
}

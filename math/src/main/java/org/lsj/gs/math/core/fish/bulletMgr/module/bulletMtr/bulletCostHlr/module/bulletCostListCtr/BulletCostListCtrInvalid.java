package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

import java.util.Arrays;
import java.util.stream.Stream;

// 非法成本列表計算器
public class BulletCostListCtrInvalid extends AbstractBulletCostListCtr implements IBulletCostListCtr {
    public BulletCostListCtrInvalid() {}

    // 計算子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        return Arrays.stream(Stream.empty().toArray()).mapToDouble(d -> (double) d).toArray();
    }

    // 檢查成本是否合法
    @Override
    public boolean isValidBulletCost(double bulletCost, double base) {
        return false;
    }
}

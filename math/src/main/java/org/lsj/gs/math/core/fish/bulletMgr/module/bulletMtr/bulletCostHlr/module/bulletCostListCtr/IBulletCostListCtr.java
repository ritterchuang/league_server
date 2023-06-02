package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

// 子彈成本列表計算器介面
public interface IBulletCostListCtr {
    double[] calculateBulletCostList(double base); // 計算子彈成本列表
    boolean isValidBulletCost(double bulletCost, double base); // 檢查成本是否合法
}

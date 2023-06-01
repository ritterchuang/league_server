package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTen;
import org.lsj.utils.MathUtil;

import java.util.Arrays;

// 十等份再切十等份子彈成本列表計算器
public class BulletCostListCtrTen extends AbstractBulletCostListCtr implements IBulletCostListCtr {
    private final BulletCostListConfigExtendTen config; // 客製子彈成本列表類型設定
    private final double BULLET_COST_MIN = 0.01; // 最小成本

    public BulletCostListCtrTen(BulletCostListConfigExtend bulletCostListConfigExtend) {
        this.config = (BulletCostListConfigExtendTen) bulletCostListConfigExtend;
    }

    // 計算子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        // 1. 檢驗最小成本合理性
        double useBase = Math.max(base, this.BULLET_COST_MIN);

        // 2. 計算子彈成本列表(以1-10為例)
        return new double[]{
                MathUtil.multiply(useBase, 1.0), // 1
                MathUtil.multiply(useBase, 2.0), // 2
                MathUtil.multiply(useBase, 3.0), // 3
                MathUtil.multiply(useBase, 4.0), // 4
                MathUtil.multiply(useBase, 5.0), // 5
                MathUtil.multiply(useBase, 6.0), // 6
                MathUtil.multiply(useBase, 7.0), // 7
                MathUtil.multiply(useBase, 8.0), // 8
                MathUtil.multiply(useBase, 9.0), // 9
                MathUtil.multiply(useBase, 10.0) // 10
        };
    }

    // 檢查成本是否合法
    @Override
    public boolean isValidBulletCost(double clientBulletCost, double base) {
        // 1. 計算子彈成本列表
        double[] bulletCostList = this.calculateBulletCostList(base);

        // 2. 判斷子彈成本是否合法
        return Arrays.stream(bulletCostList).anyMatch(bulletCost -> bulletCost == clientBulletCost);
    }
}

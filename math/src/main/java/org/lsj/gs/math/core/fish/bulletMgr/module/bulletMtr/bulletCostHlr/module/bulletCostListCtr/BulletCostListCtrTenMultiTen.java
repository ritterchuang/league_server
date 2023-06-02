package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTenMultiTen;
import org.lsj.utils.MathUtil;

import java.util.Arrays;

// 十等份再切十等份子彈成本列表計算器
public class BulletCostListCtrTenMultiTen extends AbstractBulletCostListCtr implements IBulletCostListCtr {
    private final BulletCostListConfigExtendTenMultiTen config; // 客製子彈成本列表類型設定
    private final double BULLET_COST_MIN = 0.01; // 最小成本

    public BulletCostListCtrTenMultiTen(BulletCostListConfigExtend bulletCostListConfigExtend) {
        this.config = (BulletCostListConfigExtendTenMultiTen) bulletCostListConfigExtend;
    }

    // 計算子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        // 1. 檢驗最小成本合理性
        double useBase = Math.max(base, this.BULLET_COST_MIN);

        // 2. 計算子彈成本列表(以1-100為例)
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
                MathUtil.multiply(useBase, 10.0), // 10
                MathUtil.multiply(useBase, 20.0), // 20
                MathUtil.multiply(useBase, 30.0), // 30
                MathUtil.multiply(useBase, 40.0), // 40
                MathUtil.multiply(useBase, 50.0), // 50
                MathUtil.multiply(useBase, 60.0), // 60
                MathUtil.multiply(useBase, 70.0), // 70
                MathUtil.multiply(useBase, 80.0), // 80
                MathUtil.multiply(useBase, 90.0), // 90
                MathUtil.multiply(useBase, 100.0) // 100
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

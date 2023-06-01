package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendInvalid;

import java.util.Arrays;
import java.util.stream.Stream;

// 非法子彈成本處理者
public class BulletCostHlrInvalid implements IBulletCostHlr {
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型

    public BulletCostHlrInvalid(ConstMathFish.BulletCostType bulletCostType) {
        this.bulletCostType = bulletCostType;
    }

    //* 計算相關 *//
    // 計算子彈價值
    @Override
    public double calculateBulletBaseBet(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        return 0.0;
    }

    // 計算客製子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        return Arrays.stream(Stream.empty().toArray()).mapToDouble(d -> (double) d).toArray();
    }

    // 計算獎勵子彈成本額外資訊
    @Override
    public BulletCostExtend calculateAwardBulletCostExtend() {
        return new BulletCostExtendInvalid();
    }

    //* 成本資訊相關 *//
    // 取得子彈成本類型
    @Override
    public ConstMathFish.BulletCostType getBulletCostType() {
        return this.bulletCostType;
    }

    // 取得子彈成本
    @Override
    public double getBulletCost(ClientBullet clientBullet) {
        return 0.0;
    }

    //* 檢查相關 *//
    // 檢查子彈完整性
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet) {
        return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
    }
}

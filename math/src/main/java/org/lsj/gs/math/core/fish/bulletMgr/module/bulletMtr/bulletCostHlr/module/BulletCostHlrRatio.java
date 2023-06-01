package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr.BulletCostListCtrFactory;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr.IBulletCostListCtr;
import org.lsj.utils.MathUtil;

import java.util.Objects;

// 子彈成本處理者比例
public class BulletCostHlrRatio implements IBulletCostHlr {
    private final double base; // 房間底注
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型
    private final BulletCostConfigExtendRatio config; // 子彈成本額外設定
    private final IBulletCostListCtr bulletCostListCtr; // 客端子彈成本列表計算者

    public BulletCostHlrRatio(double base, ConstMathFish.BulletCostType bulletCostType, BulletCostConfigExtend bulletCostConfigExtend) {
        this.base = base;
        this.bulletCostType = bulletCostType;
        this.config = (BulletCostConfigExtendRatio)bulletCostConfigExtend;
        this.bulletCostListCtr = new BulletCostListCtrFactory().createBulletCostListCtr(this.config.getBulletCostListType(), this.config.getBulletCostListConfigExtend());
    }


    //* 計算相關 *//
    // 計算子彈價值
    @Override
    public double calculateBulletBaseBet(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        return MathUtil.multiply(MathUtil.divide(this.getBulletCost(clientBullet), this.config.getBulletCostExchange().getBaseCost()), this.config.getBulletCostExchange().getBaseBet());
    }

    // 取得計算客製子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        return this.bulletCostListCtr.calculateBulletCostList(base);
    }

    // 計算獎勵子彈成本額外資訊
    @Override
    public BulletCostExtend calculateAwardBulletCostExtend() {
        return new BulletCostExtendRatio();
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
        return ((BulletCostExtendRatio)clientBullet.getBulletCostExtend()).getCost();
    }

    //* 檢查相關 *//
    // 檢查子彈完整性
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet) {
        // 1. 檢驗子彈成本類型一致性
        if(!(clientBullet.getBulletCostType().equals(this.bulletCostType))){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
        }

        // 2. 檢驗子彈成本訊息完整性
        if(Objects.isNull(clientBullet.getBulletCostExtend()) || !(clientBullet.getBulletCostExtend().checkComplete())){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE;
        }

        // 3. 檢查成本正確性
        double clientBulletCost = ((BulletCostExtendRatio)clientBullet.getBulletCostExtend()).getCost();
        if (!this.bulletCostListCtr.isValidBulletCost(clientBulletCost, base)) {
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_COST_ERROR;
        }

        // 4. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }
}

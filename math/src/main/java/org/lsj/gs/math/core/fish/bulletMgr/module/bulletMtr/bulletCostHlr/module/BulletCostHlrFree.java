package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendFree;

import java.util.Objects;

// 子彈成本處理者免費
public class BulletCostHlrFree implements IBulletCostHlr {
    private final double base; // 房間底注
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型
    private final BulletCostConfigExtendFree config; // 子彈成本額外設定

    public BulletCostHlrFree(double base, ConstMathFish.BulletCostType bulletCostType, BulletCostConfigExtend bulletCostConfigExtend) {
        this.base = base;
        this.bulletCostType = bulletCostType;
        this.config = (BulletCostConfigExtendFree) bulletCostConfigExtend;
    }

    //* 計算相關 *//
    // 取得子彈價值
    @Override
    public double calculateBulletBaseBet(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        return awardBulletHlr.getObtainedAwardBulletBaseBet();
    }

    // 取得客製子彈成本列表
    @Override
    public double[] calculateBulletCostList(double base) {
        return new double[]{0};
    }

    @Override
    public BulletCostExtend calculateAwardBulletCostExtend() {
        return new BulletCostExtendFree();
    }

    //* 成本資訊相關*//
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
        // 1. 檢驗子彈成本類型一致性
        if(!(clientBullet.getBulletCostType().equals(this.bulletCostType))){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
        }

        // 2. 檢驗子彈成本訊息完整性
        if(Objects.isNull(clientBullet.getBulletCostExtend()) || !(clientBullet.getBulletCostExtend().checkComplete())){
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE;
        }

        // 3. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }
}

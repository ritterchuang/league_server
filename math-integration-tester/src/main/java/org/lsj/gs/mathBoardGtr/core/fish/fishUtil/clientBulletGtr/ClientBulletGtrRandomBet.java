package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

import java.util.Arrays;
import java.util.stream.Collectors;

// 客端子彈處理者
public class ClientBulletGtrRandomBet implements IClientBulletGtr {
    private ITableUtil tableUtil; // 牌桌工具包
    private ClientBullet clientBullet; // 獎勵子彈
    private int amount; // 數量
    private double baseBet; // 子彈價值

    public ClientBulletGtrRandomBet(ITableUtil tableUtil) {
        this.tableUtil = tableUtil;
        this.clientBullet = new ClientBullet();
        this.amount = 0;
        this.baseBet = 0;
    }

    // 計算模擬客端子彈
    @Override
    public ClientBullet generateClientBullet(MathFishConfig mathFishConfig) {
        if (this.amount > 0) {
            this.amount = this.amount - 1;
            return this.clientBullet;
        }

        // 簡易回傳子彈
        return new ClientBullet(
                1,
                ConstMathFish.BulletType.NORMAL,
                new BulletTypeExtendNormal(),
                ConstMathFish.BulletCostType.RATIO,
                new BulletCostExtendRatio(
                        this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                                Arrays.stream(mathFishConfig.getClientBulletConfig().getBulletConfigDynamicMap().get(1).getBulletCostArray()).boxed().collect(Collectors.toList()),
                                ConstMathCommon.AccuracyType.MILLION)));
    }

    // 更新獎勵子彈
    @Override
    public void updateAwardBullet(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, double baseBet) {
        // 1. 無設定，不更新
        if (awardBulletType.equals(ConstMathFish.AwardBulletType.NONE)) {
            return;
        }

        // 2.獎勵個數0，不更新
        AwardBulletExtendOneType awardBulletExtendOneType = (AwardBulletExtendOneType)awardBulletExtend;
        if (awardBulletExtendOneType.getAmount() == 0) {
            return;
        }

        // 3. 更新
        this.clientBullet = awardBulletExtendOneType.getObtainedBullet();
        this.amount = this.amount + ((AwardBulletExtendOneType) awardBulletExtend).getAmount();
        this.baseBet = baseBet;
    }
}

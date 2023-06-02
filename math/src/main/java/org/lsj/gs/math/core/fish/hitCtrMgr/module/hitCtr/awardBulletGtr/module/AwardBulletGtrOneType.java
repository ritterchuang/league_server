package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.RobotAwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult.AwardBulletCtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr.AwardBulletCtrFactory;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr.IAwardBulletCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 單一獎勵子彈生產者
public class AwardBulletGtrOneType extends AbstractAwardBulletGtr implements IAwardBulletGtr {
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletGtrConfigExtendOneType config; // 獎勵子彈額外設定
    private final IAwardBulletCtr awardBulletCtr; // 獎勵子彈計算者

    public AwardBulletGtrOneType(ConstMathFish.AwardBulletType awardBulletType, AwardBulletGtrConfigExtend awardBulletGtrConfigExtend, ITableUtil tableUtil) {
        super(tableUtil);
        this.awardBulletType = awardBulletType;
        this.config = (AwardBulletGtrConfigExtendOneType) awardBulletGtrConfigExtend;
        this.awardBulletCtr = new AwardBulletCtrFactory().createAwardBulletCtr(this.config.getAwardBulletCtrType(), this.config.getAwardBulletCtrConfigExtend(), tableUtil);
    }

    //* 計算者相關 *//
    // 計算獎勵子彈生產結果
    @Override
    public AwardBulletGtrResult calculateAwardBulletGtrResult(int killCount, Bullet currentBullet, BulletMgr bulletMgr) {
        // 1. 計算獎勵子彈結果
        AwardBulletCtrResult awardBulletCtrResult = this.awardBulletCtr.calculateAwardBulletCtrResult(killCount
                , this.config.getMaxAmount()
                , this.config.getBulletIndex()
                , currentBullet
                , bulletMgr);

        // 2. 回傳結果
        return new AwardBulletGtrResult(ConstMathFish.AwardBulletType.ONE_TYPE, new AwardBulletExtendOneType(awardBulletCtrResult.getClientBullet(), awardBulletCtrResult.getBulletAmount()));
    }

    // 計算預期總倍數
    @Override
    public double calculateAwardBulletOdds(Bullet currentBullet, BulletMgr bulletMgr) {
        return this.awardBulletCtr.calculateExpectTotalOdds(
                this.config.getMaxAmount()
                , this.config.getAmountArray()
                , this.config.getAmountWeightArray()
                , this.config.getBulletIndex()
                , currentBullet
                , bulletMgr);
    }

    //* 獎勵子彈資訊相關 *//
    // 取得獎勵子彈類型
    @Override
    public ConstMathFish.AwardBulletType getAwardBulletType() {
        return awardBulletType;
    }

    /* 機器人相關 */
    // 計算機器人獎勵子彈結果列表
    @Override
    public List<RobotAwardBulletGtrResult> calculateRobotAwardBulletGtrResult(BulletMgr bulletMgr) {
        // 1. 創建空間
        List<RobotAwardBulletGtrResult> result = new ArrayList<>();
        BulletMtr bulletMtr = bulletMgr.getBulletMtr(this.config.getBulletIndex());
        int totalWeight = Arrays.stream(this.config.getAmountWeightArray()).sum();

        // 2. 添加擊中結果
        for (int amountIndex = 0; amountIndex < this.config.getAmountArray().length; amountIndex++) {
            int bulletAmount = this.config.getAmountArray()[amountIndex];
            double expectOdds = MathUtil.multiply(bulletMtr.getBulletRtp(), bulletAmount);
            result.add(
                    new RobotAwardBulletGtrResult(
                            new AwardBulletGtrResult(ConstMathFish.AwardBulletType.ONE_TYPE,
                                    new AwardBulletExtendOneType(bulletMtr.calculateAwardClientBullet(this.config.getBulletIndex()), this.config.getAmountArray()[amountIndex])),
                            expectOdds,
                            MathUtil.divide(this.config.getAmountWeightArray()[amountIndex], totalWeight)
                    )
            );
        }

        // 3. 回傳結果
        return result;
    }

    @Override
    public AwardBulletGtrResult calculateNoKillResult() {
        return new AwardBulletGtrResult(ConstMathFish.AwardBulletType.ONE_TYPE, new AwardBulletExtendOneType());
    }
}

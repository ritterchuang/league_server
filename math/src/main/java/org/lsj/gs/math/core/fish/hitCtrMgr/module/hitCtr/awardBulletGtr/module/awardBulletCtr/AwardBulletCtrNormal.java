package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult.AwardBulletCtrResult;
import org.lsj.utils.MathUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

// 獎勵子彈計算者一般
public class AwardBulletCtrNormal implements IAwardBulletCtr {
    private final ConstMathFish.AwardBulletCtrType awardBulletCtrType; // 獎勵子彈計算者類型
    private final AwardBulletCtrConfigExtendNormal config; // 獎勵子彈計算者額外設定
    private final ITableUtil tableUtil; // 牌桌工具包
    private int awardBulletAmount; // 獎勵子彈個數

    public AwardBulletCtrNormal(ConstMathFish.AwardBulletCtrType awardBulletCtrType, AwardBulletCtrConfigExtend awardBulletCtrConfigExtend, ITableUtil tableUtil) {
        this.awardBulletCtrType = awardBulletCtrType;
        this.config = (AwardBulletCtrConfigExtendNormal) awardBulletCtrConfigExtend;
        this.tableUtil = tableUtil;
    }

    // 計算獎勵子彈結果
    @Override
    public AwardBulletCtrResult calculateAwardBulletCtrResult(int killCount, int maxAmount, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr) {
        // 1. 若擊殺次數為0，回傳空殼
        if (killCount == 0 || this.awardBulletAmount <= 0) {
            return new AwardBulletCtrResult();
        }

        // 2. 計算此次總共獲得子彈個數
        int awardBulletAmountTemp = this.awardBulletAmount * killCount;

        // 3. 獎勵子彈個數上限校正
        if (awardBulletAmountTemp + bulletMgr.getRecordAwardBulletAmount() > maxAmount) {
            awardBulletAmountTemp = maxAmount - bulletMgr.getRecordAwardBulletAmount();

            // 若此次使用子彈與紀錄子彈相同，增加一發
            if (awardBulletIndex == currentBullet.getBulletIndex()) {
                awardBulletAmountTemp++;
            }
        }

        // 4. 計算獎勵子彈結果資訊
        return new AwardBulletCtrResult(bulletMgr.calculateAwardClientBullet(awardBulletIndex), awardBulletAmountTemp);
    }

    // 計算獎勵子彈倍數
    @Override
    public double calculateExpectTotalOdds(int maxAmount, int[] amountArray, int[] amountWeightArray, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr) {
        // 1. 產出此次獎勵子彈個數
        int awardBulletAmountTemp = this.calculateAwardBulletAmount(maxAmount, amountArray, amountWeightArray, awardBulletIndex, currentBullet, bulletMgr);

        // 2. 計算獎勵子彈倍數
        double awardBulletOdds = MathUtil.multiply(bulletMgr.getBulletMtr(awardBulletIndex).getBulletRtp(), awardBulletAmountTemp);

        // 3. 記錄此次獎勵子彈個數
        this.awardBulletAmount = awardBulletAmountTemp;

        // 4. 回傳總倍數
        return awardBulletOdds;
    }

    // 計算獲得獎勵子彈個數
    private int calculateAwardBulletAmount(int maxAmount, int[] amountArray, int[] amountWeightArray, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr) {
        // 1. 計算此次獲得個數
        int result = amountArray[this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(amountWeightArray).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 2. 最大上限個數校正
        if (result + bulletMgr.getRecordAwardBulletAmount() > maxAmount) {
            result = maxAmount - bulletMgr.getRecordAwardBulletAmount();

            // 若此次使用子彈與紀錄子彈相同，增加一發
            if (awardBulletIndex == currentBullet.getBulletIndex()) {
                result++;
            }
        }

        // 3. 回傳
        return result;
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendInvalid;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendInvalid;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendInvalid;

// 打擊結果
public class HitResult {
    private final boolean killFlag; // 擊殺標誌
    private final int killCount; // 擊殺次數
    private final double basicWin; // 單次贏分
    private final double totalWin; // 總贏分
    private final double totalOdds; // 總倍數
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletExtend awardBulletExtend; // 獲得子彈
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊事件類型
    private final SpecialFeatureResultExtend specialFeatureResultExtend; // 特殊事件結果
    private final ConstMathFish.HitType hitType; // 處理類型
    private final HitResultExtend hitResultExtend; // 客製處理結果

    public HitResult() {
        this.killFlag = false;
        this.killCount = 0;
        this.basicWin = 0.0;
        this.totalWin = 0.0;
        this.totalOdds = 0.0;
        this.awardBulletType = ConstMathFish.AwardBulletType.INVALID;
        this.awardBulletExtend = new AwardBulletExtendInvalid();
        this.specialFeatureEnumType = ConstMathFish.SpecialFeatureEnumType.INVALID;
        this.specialFeatureResultExtend = new SpecialFeatureResultExtendInvalid();
        this.hitType = ConstMathFish.HitType.INVALID;
        this.hitResultExtend = new HitResultExtendInvalid();
    }

    public HitResult(boolean killFlag, int killCount, double basicWin, double totalWin, double totalOdds, ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureResultExtend specialFeatureResultExtend, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend) {
        this.killFlag = killFlag;
        this.killCount = killCount;
        this.basicWin = basicWin;
        this.totalWin = totalWin;
        this.totalOdds = totalOdds;
        this.awardBulletType = awardBulletType;
        this.awardBulletExtend = awardBulletExtend;
        this.specialFeatureEnumType = specialFeatureEnumType;
        this.specialFeatureResultExtend = specialFeatureResultExtend;
        this.hitType = hitType;
        this.hitResultExtend = hitResultExtend;
    }

    public boolean isKillFlag() {
        return killFlag;
    }

    public int getKillCount() {
        return killCount;
    }

    public double getBasicWin() {
        return basicWin;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public double getTotalOdds() {
        return totalOdds;
    }

    public ConstMathFish.AwardBulletType getAwardBulletType() {
        return awardBulletType;
    }

    public AwardBulletExtend getAwardBulletExtend() {
        return awardBulletExtend;
    }

    public ConstMathFish.SpecialFeatureEnumType getSpecialFeatureEnumType() {
        return specialFeatureEnumType;
    }

    public SpecialFeatureResultExtend getSpecialFeatureResultExtend() {
        return specialFeatureResultExtend;
    }

    public ConstMathFish.HitType getHitType() {
        return hitType;
    }

    public HitResultExtend getHitResultExtend() {
        return hitResultExtend;
    }
}

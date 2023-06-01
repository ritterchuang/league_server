package org.lsj.gs.math.core.fish.hitCtrMgr.entity.client;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendInvalid;

// 客端表演打擊結果
public class ClientHitResult {
    private final boolean killFlag; // 擊殺標誌
    private final int killCount; // 擊殺次數
    private final double basicWin; // 魚基本得分
    private final double totalWin; // 總贏分
    private final double totalOdds; // 總倍數
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletExtend awardBulletExtend; // 獲得子彈
    private final DisplayResultCollection displayResultCollection; // 客製表演資訊

    public ClientHitResult() {
        this.killFlag = false;
        this.killCount = 0;
        this.basicWin = 0.0;
        this.totalWin = 0.0;
        this.totalOdds = 0.0;
        this.awardBulletType = ConstMathFish.AwardBulletType.INVALID;
        this.awardBulletExtend = new AwardBulletExtendInvalid();
        this.displayResultCollection = new DisplayResultCollection();
    }

    public ClientHitResult(boolean killFlag, int killCount, double basicWin, double totalWin, double totalOdds, ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, DisplayResultCollection displayResultCollection) {
        this.killFlag = killFlag;
        this.killCount = killCount;
        this.basicWin = basicWin;
        this.totalWin = totalWin;
        this.totalOdds = totalOdds;
        this.awardBulletType = awardBulletType;
        this.awardBulletExtend = awardBulletExtend;
        this.displayResultCollection = displayResultCollection;
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

    public DisplayResultCollection getDisplayResultCollection() {
        return displayResultCollection;
    }
}

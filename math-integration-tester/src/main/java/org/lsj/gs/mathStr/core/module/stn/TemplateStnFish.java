package org.lsj.gs.mathStr.core.module.stn;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish.GameResultExtendFishJava;

// 基礎統計物件
public class TemplateStnFish extends TemplateStn implements IStn {
    private int killCount; // 目標擊殺次數
    private double targetTotalOdds; // 目標擊殺總倍數
    private double totalCredit; // 總有效押注
    private double totalOdds; // 總獲得倍數
    private double totalOddsSquare; // 總獲得倍數平方和
    private double maxOdds; // 最大倍數
    private int totalSpecialFeatureCount; // 總特殊事件次數
    private double totalSpecialFeatureWin; // 總特殊事件贏分
    private double totalSpecialFeatureOdds; // 總特殊事件倍數
    private int totalAwardBulletCount; // 總獲得子彈次數
    private int totalAwardBulletAmount; // 總獲得子彈個數

    public TemplateStnFish() {
        super();
        this.killCount = 0;
        this.targetTotalOdds = 0.0;
        this.totalCredit = 0.0;
        this.totalOdds = 0.0;
        this.totalOddsSquare = 0.0;
        this.totalSpecialFeatureCount = 0;
        this.totalSpecialFeatureWin = 0.0;
        this.totalSpecialFeatureOdds = 0.0;
        this.totalAwardBulletCount = 0;
        this.totalAwardBulletAmount = 0;
    }

    // 更新統計資訊
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 更新父類別統計資訊
        super.update(boardGtrResult);
        super.updateTie(boardGtrResult);

        // 2. 取得魚機額外結果
        GameResultExtendFishJava gameResultExtendFishJava = (GameResultExtendFishJava) boardGtrResult.getGameResultExtend();

        // 3. 更新魚機共用統計資訊
        this.updateCommonFishResult(gameResultExtendFishJava);

        // 4. 更新特殊事件資訊
        this.updateSpecialFeatureResult(gameResultExtendFishJava.getHitResult().getSpecialFeatureEnumType(), gameResultExtendFishJava.getHitResult().getSpecialFeatureResultExtend(), gameResultExtendFishJava.getCredit());

        // 5. 更新獎勵子彈資訊
        this.updateAwardBulletResult(gameResultExtendFishJava.getHitResult().getAwardBulletType(), gameResultExtendFishJava.getHitResult().getAwardBulletExtend());
    }

    // 更新魚機共用統計資訊
    private void updateCommonFishResult(GameResultExtendFishJava gameResultExtendFishJava) {
        // 1. 更新目標死亡次數
        if (gameResultExtendFishJava.getHitResult().isKillFlag()) {
            this.killCount++;
        }

        // 2. 更新押注金額
        this.totalCredit += gameResultExtendFishJava.getCredit();

        // 3. 更新目標總倍數
        this.targetTotalOdds += (gameResultExtendFishJava.getHitResult().getBasicWin() * gameResultExtendFishJava.getHitResult().getKillCount()) / gameResultExtendFishJava.getCredit();

        // 4. 更新打擊總倍數
        this.totalOdds += gameResultExtendFishJava.getHitResult().getTotalOdds();
        this.totalOddsSquare += gameResultExtendFishJava.getHitResult().getTotalOdds() * gameResultExtendFishJava.getHitResult().getTotalOdds();

        // 5. 更新最大倍數
        if (gameResultExtendFishJava.getHitResult().getTotalOdds() > this.maxOdds) {
            this.maxOdds = gameResultExtendFishJava.getHitResult().getTotalOdds();
        }
    }

    // 更新特殊事件統計
    private void updateSpecialFeatureResult(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureResultExtend specialFeatureResultExtend, double baseBet) {
        // 1. 若為非法，印出資訊
        if (specialFeatureEnumType.equals(ConstMathFish.SpecialFeatureEnumType.INVALID)) {
            System.out.println("Error, Invalid SpecialFeature Type");
            return;
        }

        // 2. 若無設定，不更新
        if (specialFeatureEnumType.equals(ConstMathFish.SpecialFeatureEnumType.NONE)) {
            return;
        }

        // 3. 若設定紅包，觸發就更新
        if (specialFeatureEnumType.equals(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE)) {
            SpecialFeatureResultExtendRedEnvelope extendResult = (SpecialFeatureResultExtendRedEnvelope)specialFeatureResultExtend;
            if (extendResult.isKillFlag()) {
                this.totalSpecialFeatureCount++;
                this.totalSpecialFeatureWin += extendResult.getTotalWin();
                this.totalSpecialFeatureOdds += extendResult.getTotalWin() / baseBet;
            }
        }

        return;
    }

    // 更新獎勵子彈統計
    private void updateAwardBulletResult(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend) {
        // 1. 若為非法，印出資訊
        if (awardBulletType.equals(ConstMathFish.AwardBulletType.INVALID)) {
            System.out.println("Error, Invalid AwardBulletType Type");
            return;
        }

        // 2. 若無設定，不更新
        if (awardBulletType.equals(ConstMathFish.AwardBulletType.NONE)) {
            return;
        }

        // 3. 若設定獎勵子彈，個數大於0更新
        if (awardBulletType.equals(ConstMathFish.AwardBulletType.ONE_TYPE)) {
            AwardBulletExtendOneType extendResult = (AwardBulletExtendOneType)awardBulletExtend;
            if (extendResult.getAmount() > 0) {
                this.totalAwardBulletCount++;
                this.totalAwardBulletAmount += extendResult.getAmount();
            }
        }
    }

    public int getKillCount() {
        return killCount;
    }

    public double getTargetTotalOdds() {
        return targetTotalOdds;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public double getTotalOdds() {
        return totalOdds;
    }

    public double getTotalOddsSquare() {
        return totalOddsSquare;
    }

    public double getMaxOdds() {
        return maxOdds;
    }

    public int getTotalSpecialFeatureCount() {
        return totalSpecialFeatureCount;
    }

    public double getTotalSpecialFeatureWin() {
        return totalSpecialFeatureWin;
    }

    public double getTotalSpecialFeatureOdds() {
        return totalSpecialFeatureOdds;
    }

    public int getTotalAwardBulletCount() {
        return totalAwardBulletCount;
    }

    public int getTotalAwardBulletAmount() {
        return totalAwardBulletAmount;
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;

// 機器人基本結果
public class RobotBasicAndAwardBulletResult {
    private final boolean killFlag; // 是否擊殺
    private final int basicOdds; // 基礎倍數
    private final AwardBulletGtrResult awardBulletGtrResult; // 獎勵子彈結果
    private final ConstMathFish.HitType hitType; // 打擊類型
    private final HitResultExtend hitResultExtend; // 客製打擊結果
    private final double appearAndKilledProbability; // 出現並死亡率

    public RobotBasicAndAwardBulletResult(boolean killFlag, int basicOdds, AwardBulletGtrResult awardBulletGtrResult, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend, double appearAndKilledProbability) {
        this.killFlag = killFlag;
        this.basicOdds = basicOdds;
        this.awardBulletGtrResult = awardBulletGtrResult;
        this.hitType = hitType;
        this.hitResultExtend = hitResultExtend;
        this.appearAndKilledProbability = appearAndKilledProbability;
    }

    public boolean isKillFlag() {
        return killFlag;
    }

    public int getBasicOdds() {
        return basicOdds;
    }

    public AwardBulletGtrResult getAwardBulletGtrResult() {
        return awardBulletGtrResult;
    }

    public ConstMathFish.HitType getHitType() {
        return hitType;
    }

    public HitResultExtend getHitResultExtend() {
        return hitResultExtend;
    }

    public double getAppearAndKilledProbability() {
        return appearAndKilledProbability;
    }
}

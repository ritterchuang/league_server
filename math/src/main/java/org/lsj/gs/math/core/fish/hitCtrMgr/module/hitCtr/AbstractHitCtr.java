package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.AwardBulletGtrFactory;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module.IHitCtrUtil;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.KillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.IRobotResultWpr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.RobotResultWpr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module.RobotResultCtrFactory;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module.IRtpChoiceHlr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module.RtpChoiceHlrFactory;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.SpecialFeatureCtrFactory;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;
import org.lsj.utils.MathUtil;

// 抽象打擊計算器
public abstract class AbstractHitCtr extends AbstractModule implements IHitCtr {
    protected final double PROFIT_RATE_RADIUS = 0.005; // 利潤率半徑
    protected final double humanRtp; // 真人返獎率
    protected final double robotRtp; // 機器人返獎率
    protected final IKillCountCtr killCountCtr; // 擊殺次數計算者
    protected final IAwardBulletGtr awardBulletGtr; // 獎勵子彈生產者
    protected final ISpecialFeatureCtr specialFeatureCtr; // 特殊事件計算者
    protected final IRtpChoiceHlr rtpChoiceHlr; // Rtp選擇處理者
    protected final IRobotResultWpr robotResultWpr; // 機器人結果處理者


    public AbstractHitCtr(HitCtrConfig hitCtrConfig, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.humanRtp = MathUtil.subtract(
                1.0,
                MathUtil.add(super.poolCtr.getPoolConfig().getAgencyPool().getFeeRate(), this.PROFIT_RATE_RADIUS)
        );
        this.robotRtp = 0.3;

        this.killCountCtr = new KillCountCtr(tableUtil);
        this.awardBulletGtr = new AwardBulletGtrFactory().createAwardBulletGtr(hitCtrConfig.getAwardBulletType(), hitCtrConfig.getAwardBulletGtrConfigExtend(), tableUtil);
        this.specialFeatureCtr = new SpecialFeatureCtrFactory().createSpecialFeatureCtr(hitCtrConfig.getSpecialFeatureType(), hitCtrConfig.getSpecialFeatureCtrConfigExtend(), tableUtil);
        this.rtpChoiceHlr = new RtpChoiceHlrFactory().createRtpChoiceHlr(hitCtrConfig.getRtpChoiceType(), hitCtrConfig.getRtpChoiceHlrConfigExtend());
        this.robotResultWpr = new RobotResultWpr(new RobotResultCtrFactory().createRobotResultCtr(hitCtrConfig.getHitType(), hitCtrConfig.getHitTypeConfigExtend(), this.robotRtp));
    }

    // 計算真人結果
    public HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, int hitCount, BulletMgr bulletMgr, IHitCtrUtil hitCtrUtil, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        // 1. 計算此次使用Rtp
        double totalRtp = this.rtpChoiceHlr.calculateUsedRtp(bullet.getBulletIndex(), bulletMgr, shuffleType, gameAdjustParameter, humanRtp);

        // 2. 計算扣除特殊事件Rtp
        double basicAndAwardBulletRtp = this.specialFeatureCtr.calculateBasicAndAwardBulletRtp(totalRtp);

        // 3. 計算打擊倍數資訊
        HitOddsInfo hitOddsInfo = hitCtrUtil.calculateHitOddsInfo(hit.getHitTypeExtend(), target);

        // 4. 計算子彈總倍數
        double awardBulletOdds = this.awardBulletGtr.calculateAwardBulletOdds(bullet, bulletMgr);

        // 5. 計算擊殺次數
        int killCount = this.killCountCtr.calculateKillCount(basicAndAwardBulletRtp, MathUtil.add(hitOddsInfo.getBasicOdds(), awardBulletOdds), hitCount);

        // 6. 計算獎勵子彈結果
        AwardBulletGtrResult awardBulletGtrResult = this.awardBulletGtr.calculateAwardBulletGtrResult(killCount, bullet, bulletMgr);

        // 7. 計算特殊獎勵結果
        SpecialFeatureResult specialFeatureResult = this.specialFeatureCtr.calculateSpecialFeatureResult(totalRtp, bullet.getBet(), this.killCountCtr);

        // 8. 計算擊殺標誌
        boolean killFlag = killCount > 0;

        // 9. 計算基本得分
        double basicWin = killFlag ? MathUtil.moneyScale(MathUtil.multiply(hitOddsInfo.getBasicOdds(), bullet.getBet())) : 0.0;

        // 10. 計算總得分
        double totalWin = this.calculateTotalWin(killCount, hitOddsInfo.getBasicOdds(), bullet.getBet(), specialFeatureResult);

        // 11. 計算總倍數
        double totalOdds = this.calculateTotalOdds(totalWin, bullet.getBet());

        // 12. 計算額外打擊結果
        HitResultExtend hitResultExtend = hitCtrUtil.calculateHitResultExtend(killFlag, hitOddsInfo, hit.getHitTypeExtend(), bullet);

        // 13. 包裝結果
        return new HitResult(
                killFlag,
                killCount,
                basicWin,
                totalWin,
                totalOdds,
                awardBulletGtrResult.getAwardBulletType(), awardBulletGtrResult.getAwardBulletExtend(),
                specialFeatureResult.getSpecialFeatureType(), specialFeatureResult.getSpecialFeatureResultExtend(),
                hit.getHitType(), hitResultExtend);
    }


    // 計算機器人打擊結果
    public RobotHitResult calculateRobotHitResult(int bulletIndex, BulletMgr bulletMgr, IClientHitResultPgr clientHitResultWpr) {
        return this.robotResultWpr.calculateRobotHitResult(bulletIndex, bulletMgr, clientHitResultWpr, this.awardBulletGtr, this.specialFeatureCtr, this.rtpChoiceHlr);
    }


    // 計算總得分
    private double calculateTotalWin(int killCount, int basicOdds, double baseBet, SpecialFeatureResult specialFeatureResult) {
        // 1. 計算期望總得分
        double result = MathUtil.multiply(killCount, MathUtil.multiply(basicOdds, baseBet));

        // 2. 加總特殊事件得分
        return MathUtil.moneyScale(MathUtil.add(result, specialFeatureResult.getTotalWin()));
    }

    // 計算總倍數
    private double calculateTotalOdds(double totalWin, double baseBet) {
        if (baseBet == 0) {
            return 0.0;
        }

        return MathUtil.divide(totalWin, baseBet);
    }

    // 重設
    @Override
    public void reset(){
        // 有需求才做
    }
}

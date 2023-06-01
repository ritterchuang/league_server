package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendTreasureBox;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 機器人結果計算者連點寶箱
public class RobotResultCtrTreasureBox extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendTreasureBox config; // 機器人結果打擊設定

    public RobotResultCtrTreasureBox(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendTreasureBox robotResultCtrConfigExtendTreasureBox, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendTreasureBox;
    }


    //* 機器人結果相關 *//
    // 計算機器人基本獎勵結果
    @Override
    public List<RobotBasicAndAwardBulletResult> calculateRobotBasicAndAwardBulletResult(double robotUsedRtp, BulletMgr bulletMgr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr) {
        return super.calculateRobotBasicAndAwardBulletResultList(
                specialFeatureCtr.calculateBasicAndAwardBulletRtp(robotUsedRtp),
                this.getRobotBasicOddsInfoList(),
                awardBulletGtr.calculateRobotAwardBulletGtrResult(bulletMgr),
                awardBulletGtr.calculateNoKillResult(),
                this.hitType,
                this.getRobotHitResultExtend()
        );
    }

    // 取得機器人基礎倍數資訊列表
    @Override
    public List<RobotBasicOddsInfo> getRobotBasicOddsInfoList() {
        // 1. 創建空間
        List<RobotBasicOddsInfo> result = new ArrayList<>();

        // 2. 加總權重和
        int totalWeight = this.config.getOddsRangeWeightList().stream().mapToInt(i -> i).sum();

        // 3. 添加倍數資訊
        for (int targetOddsRangeIndex = 0; targetOddsRangeIndex < this.config.getOddsRangeList().size(); targetOddsRangeIndex++) {
            // 3.1 計算倍數區間出現率
            double oddsRangeAppearRate = MathUtil.divide(this.config.getOddsRangeWeightList().get(targetOddsRangeIndex), totalWeight);
            int oddsUpperBound = this.config.getOddsRangeList().get(targetOddsRangeIndex)[1];
            int oddsLowerBound = this.config.getOddsRangeList().get(targetOddsRangeIndex)[0];

            // 3.2 計算倍數區間裡面倍數出現率
            int oddsCount = oddsUpperBound - oddsLowerBound + 1;
            double oddsAppearRate = MathUtil.divide(1, oddsCount);

            for (int oddsIndex = 0; oddsIndex < oddsCount; oddsIndex++) {
                // 3.3 計算基礎倍數
                int basicOdds = this.config.getOddsRangeList().get(targetOddsRangeIndex)[0] + oddsIndex;

                result.add(new RobotBasicOddsInfo(basicOdds, MathUtil.multiply(oddsAppearRate, oddsRangeAppearRate)));
            }
        }

        // 4. 回傳
        return result;
    }

    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendTreasureBox();
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr;

import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module.IRobotResultCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module.IRtpChoiceHlr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotPresentResult;
import org.lsj.utils.MathUtil;

import java.util.List;

// 機器人結果包裝者
public class RobotResultWpr implements IRobotResultWpr {
    private final IRobotResultCtr robotResultCtr; // 機器人結果計算者

    public RobotResultWpr(IRobotResultCtr robotResultCtr) {
        this.robotResultCtr = robotResultCtr;
    }


    // 計算機器人打擊結果
    @Override
    public RobotHitResult calculateRobotHitResult(int bulletIndex, BulletMgr bulletMgr, IClientHitResultPgr clientHitResultWpr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr, IRtpChoiceHlr rtpChoiceHlr) {
        // 1. 計算此次使用 rtp
        double robotUsedRtp = rtpChoiceHlr.calculateRobotUsedRtp(bulletIndex, bulletMgr, this.robotResultCtr.getRobotRtp());

        // 2. 計算機器人基礎倍數與獎勵子彈結果
        List<RobotBasicAndAwardBulletResult> robotBasicAndAwardBulletResultList = this.robotResultCtr.calculateRobotBasicAndAwardBulletResult(robotUsedRtp, bulletMgr, awardBulletGtr, specialFeatureCtr);

        // 3. 計算機器人特殊事件結果
        List<RobotSpecialFeatureResult> robotSpecialFeatureResultList = specialFeatureCtr.calculateRobotSpecialFeatureResultList(robotUsedRtp);

        // 4. 計算機器人表演結果
        List<RobotPresentResult> robotPresentResultList = this.robotResultCtr.calculateRobotPresentResultList(robotBasicAndAwardBulletResultList, robotSpecialFeatureResultList, clientHitResultWpr);

        // 5. 計算機器人表演率
        double robotPresentProbability = this.calculateRobotPresentProbability(robotPresentResultList);

        // 6. 封裝
        return new RobotHitResult(robotPresentProbability, robotPresentResultList.toArray(RobotPresentResult[]::new));
    }

    // 計算機器人表演率
    private double calculateRobotPresentProbability(List<RobotPresentResult> robotPresentResultList) {
        double result = 0.0;

        for (int resultIndex = 0; resultIndex < robotPresentResultList.size(); resultIndex++) {
            result = MathUtil.add(result, robotPresentResultList.get(resultIndex).getAppearAndPresentProbability());
        }

        return result;
    }
}

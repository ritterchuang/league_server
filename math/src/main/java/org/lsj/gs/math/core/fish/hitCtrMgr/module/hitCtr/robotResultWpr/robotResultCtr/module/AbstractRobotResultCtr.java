package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.RobotAwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletCombination;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotPresentResult;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 抽象機器人結果計算者
public abstract class AbstractRobotResultCtr implements IRobotResultCtr {
    protected final double robotRtp; // 機器人Rtp設置
    protected final int weightAccuracyDigit; // 權重精準度位數

    public AbstractRobotResultCtr(double robotRtp) {
        this.robotRtp = robotRtp;
        this.weightAccuracyDigit = 1_000_000;
    }


    //* 當前設定資訊相關 *//
    // 取得機器人Rtp
    @Override
    public double getRobotRtp() {
        return this.robotRtp;
    }


    //* 機器人結果相關 *//
    // 計算機器人表演結果列表
    @Override
    public List<RobotPresentResult> calculateRobotPresentResultList(List<RobotBasicAndAwardBulletResult> robotBasicAndAwardBulletResultList, List<RobotSpecialFeatureResult> robotSpecialFeatureResultList, IClientHitResultPgr clientHitResultWpr) {
        // 1. 篩選資料
        List<RobotBasicAndAwardBulletResult> filterBasicKillResult = robotBasicAndAwardBulletResultList.stream().filter(RobotBasicAndAwardBulletResult::isKillFlag).collect(Collectors.toList());
        List<RobotBasicAndAwardBulletResult> filterBasicNoKillResult = robotBasicAndAwardBulletResultList.stream().filter(robotBasicAndAwardBulletResult -> !robotBasicAndAwardBulletResult.isKillFlag()).collect(Collectors.toList());
        List<RobotSpecialFeatureResult> filterTriggerResult = robotSpecialFeatureResultList.stream().filter(robotSpecialFeatureResult -> robotSpecialFeatureResult.getSpecialFeatureResult().isKillFlag()).collect(Collectors.toList());
        List<RobotSpecialFeatureResult> filterNoTriggerResult = robotSpecialFeatureResultList.stream().filter(robotSpecialFeatureResult -> !robotSpecialFeatureResult.getSpecialFeatureResult().isKillFlag()).collect(Collectors.toList());

        // 2. 創建空殼
        List<RobotPresentResult> result = new ArrayList<>();

        // 3. 計算 魚死 + 特殊事件結果
        result.addAll(this.packageRobotPresentResultList(filterBasicKillResult, filterTriggerResult, clientHitResultWpr));

        // 4. 計算 魚死 + 無特殊事件結果
        result.addAll(this.packageRobotPresentResultList(filterBasicKillResult, filterNoTriggerResult, clientHitResultWpr));

        // 5. 計算 魚無死 + 特殊事件結果
        result.addAll(this.packageRobotPresentResultList(filterBasicNoKillResult, filterTriggerResult, clientHitResultWpr));

        // 6. 回傳
        return result;
    }

    // 計算機器人基礎與獎勵子彈結果
    protected List<RobotBasicAndAwardBulletResult> calculateRobotBasicAndAwardBulletResultList(double basicAndAwardBulletRtp, List<RobotBasicOddsInfo> robotBasicOddsInfoList, List<RobotAwardBulletGtrResult> robotAwardBulletGtrResultList, AwardBulletGtrResult noKillAwardBulletGtrResult, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend) {
        // 1. 創建空間
        List<RobotBasicAndAwardBulletResult> result = new ArrayList<>();

        // 2. 計算基礎倍數與獎勵子彈結果所有組合
        List<RobotBasicAndAwardBulletCombination> robotBasicAndAwardBulletCombinationList = this.calculateBasicOddsAndAwardBulletAllCombinationResultList(robotBasicOddsInfoList, robotAwardBulletGtrResultList);

        // 3. 添加 擊殺 機器人基礎與獎勵子彈結果
        result.addAll(this.calculateKillResultList(basicAndAwardBulletRtp, robotBasicAndAwardBulletCombinationList, hitType, hitResultExtend));

        // 4. 添加 未擊殺 機器人基礎與獎勵子彈結果
        result.add(this.calculateNoKillResult(basicAndAwardBulletRtp, robotBasicAndAwardBulletCombinationList, noKillAwardBulletGtrResult, hitType, hitResultExtend));

        // 5. 回傳
        return result;
    }

    // 計算 擊殺 機器人基礎與獎勵子彈結果
    private List<RobotBasicAndAwardBulletResult> calculateKillResultList(double basicAndAwardBulletRtp, List<RobotBasicAndAwardBulletCombination> robotBasicAndAwardBulletCombinationList, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend) {
        // 1. 創建空殼
        List<RobotBasicAndAwardBulletResult> result = new ArrayList<>();

        // 2. 準備資料
        for (int combinationIndex = 0; combinationIndex < robotBasicAndAwardBulletCombinationList.size(); combinationIndex++) {
            // 2.1 取得基礎倍數
            int basicOdds = robotBasicAndAwardBulletCombinationList.get(combinationIndex).getBasicOdds();

            // 2.2 取得獎勵子彈結果
            AwardBulletGtrResult awardBulletGtrResult = robotBasicAndAwardBulletCombinationList.get(combinationIndex).getRobotAwardBulletGtrResult().getAwardBulletGtrResult();

            // 2.3 取得出現並死亡率
            double appearAndKillProbability = robotBasicAndAwardBulletCombinationList.get(combinationIndex).getAppearAndKillProbability(basicAndAwardBulletRtp);

            // 2.4 添加結果
            result.add(new RobotBasicAndAwardBulletResult(
                    true, basicOdds, awardBulletGtrResult, hitType, hitResultExtend, appearAndKillProbability
            ));
        }

        // 3. 回傳
        return result;
    }

    // 計算 未擊殺 機器人基礎與獎勵子彈結果
    private RobotBasicAndAwardBulletResult calculateNoKillResult(double basicAndAwardBulletRtp, List<RobotBasicAndAwardBulletCombination> robotBasicAndAwardBulletCombinationList, AwardBulletGtrResult noKillAwardBulletGtrResult, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend) {
        // 1. 宣告變數
        double appearAndNoKillProbability = 0.0;

        // 2. 加總出現且沒死亡率
        for (int combinationIndex = 0; combinationIndex < robotBasicAndAwardBulletCombinationList.size(); combinationIndex++) {
            appearAndNoKillProbability = MathUtil.add(appearAndNoKillProbability,
                    robotBasicAndAwardBulletCombinationList.get(combinationIndex).getAppearAndNoKillProbability(basicAndAwardBulletRtp));
        }

        // 2. 回傳
        return new RobotBasicAndAwardBulletResult(false, 0, noKillAwardBulletGtrResult, hitType, hitResultExtend, appearAndNoKillProbability);
    }

    // 計算 機器人基礎與獎勵子彈 所有組合
    private List<RobotBasicAndAwardBulletCombination> calculateBasicOddsAndAwardBulletAllCombinationResultList(List<RobotBasicOddsInfo> robotBasicOddsInfoList, List<RobotAwardBulletGtrResult> robotAwardBulletGtrResultList) {
        // 1. 創立空間
        List<RobotBasicAndAwardBulletCombination> result = new ArrayList<>();

        // 2. 填寫資料
        for (int oddsIndex = 0; oddsIndex < robotBasicOddsInfoList.size(); oddsIndex++) {
            // 2.1 取得基礎倍數
            int basicOdds = robotBasicOddsInfoList.get(oddsIndex).getBasicOdds();

            for (int awardResultIndex = 0; awardResultIndex < robotAwardBulletGtrResultList.size(); awardResultIndex++) {
                // 2.2 取得機器人獎勵子彈結果
                RobotAwardBulletGtrResult robotAwardBulletGtrResult = robotAwardBulletGtrResultList.get(awardResultIndex);

                // 2.3 計算基礎倍數出現率
                double basicOddsAppearProbability = robotBasicOddsInfoList.get(oddsIndex).getOddsAppearProbability();

                // 2.4 計算此次結果出現率
                double combineResultAppearProbability = MathUtil.multiply(basicOddsAppearProbability, robotAwardBulletGtrResult.getAppearProbability());

                // 2.5 添加結果
                result.add(new RobotBasicAndAwardBulletCombination(basicOdds, robotAwardBulletGtrResult, combineResultAppearProbability));
            }
        }

        // 3. 回傳
        return result;
    }

    // 包裝機器人表演結果
    private List<RobotPresentResult> packageRobotPresentResultList(List<RobotBasicAndAwardBulletResult> robotBasicAndAwardBulletResultList, List<RobotSpecialFeatureResult> robotSpecialFeatureResultList, IClientHitResultPgr clientHitResultWpr) {
        // 1. 創立空間
        List<RobotPresentResult> result = new ArrayList<>();

        // 2. 填寫資料
        for (int basicIndex = 0; basicIndex < robotBasicAndAwardBulletResultList.size(); basicIndex++) {
            for (int specialIndex = 0; specialIndex < robotSpecialFeatureResultList.size(); specialIndex++) {
                // 2.1 計算出現且觸發率
                double appearAndTriggerProbability = MathUtil.multiply(robotBasicAndAwardBulletResultList.get(basicIndex).getAppearAndKilledProbability()
                        , robotSpecialFeatureResultList.get(specialIndex).getAppearAndTriggerProbability());

                // 2.2 添加結果
                result.add(new RobotPresentResult(
                        this.packageRobotHitResult(robotBasicAndAwardBulletResultList.get(basicIndex), robotSpecialFeatureResultList.get(specialIndex), clientHitResultWpr)
                        , (int) (MathUtil.multiply(appearAndTriggerProbability, this.weightAccuracyDigit) + 1)
                        , appearAndTriggerProbability
                ));
            }
        }

        // 3. 回傳
        return result;
    }

    // 包裝機器人打擊結果
    private ClientHitResult packageRobotHitResult(RobotBasicAndAwardBulletResult robotBasicAndAwardBulletResult, RobotSpecialFeatureResult robotSpecialFeatureResult, IClientHitResultPgr clientHitResultWpr) {
        // 1. 計算成員資料
        double basicWin = MathUtil.moneyScale(MathUtil.multiply(robotBasicAndAwardBulletResult.getBasicOdds(), 1.0));
        double totalWin = MathUtil.moneyScale(MathUtil.add(basicWin, robotSpecialFeatureResult.getSpecialFeatureResult().getTotalWin()));
        int killCount = robotBasicAndAwardBulletResult.isKillFlag() ? 1 : 0;

        // 2. 計算 打擊結果
        HitResult hitResult =  new HitResult(
                robotBasicAndAwardBulletResult.isKillFlag(),
                killCount,
                basicWin,
                totalWin,
                totalWin, // 因為底注為1，所以 odds = win
                robotBasicAndAwardBulletResult.getAwardBulletGtrResult().getAwardBulletType(),
                robotBasicAndAwardBulletResult.getAwardBulletGtrResult().getAwardBulletExtend(),
                robotSpecialFeatureResult.getSpecialFeatureResult().getSpecialFeatureType(),
                robotSpecialFeatureResult.getSpecialFeatureResult().getSpecialFeatureResultExtend(),
                robotBasicAndAwardBulletResult.getHitType(), robotBasicAndAwardBulletResult.getHitResultExtend()
        );

        // 3. 回傳 客端打擊結果
        return clientHitResultWpr.packageClientHitResult(hitResult);
    }
}
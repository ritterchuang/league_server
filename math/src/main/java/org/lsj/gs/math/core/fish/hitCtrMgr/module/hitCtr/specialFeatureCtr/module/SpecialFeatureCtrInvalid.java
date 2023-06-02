package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;

import java.util.Collections;
import java.util.List;

// 非法特殊事件計算者
public class SpecialFeatureCtrInvalid implements ISpecialFeatureCtr {
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊事件類型

    public SpecialFeatureCtrInvalid(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend) {
        this.specialFeatureEnumType = specialFeatureEnumType;
    }

    //* 計算者相關 *//
    // 取得特殊事件類型
    @Override
    public ConstMathFish.SpecialFeatureEnumType getSpecialFeatureType() {
        return specialFeatureEnumType;
    }


    //* 結果相關 *//
    // 計算真人特殊事件結果
    @Override
    public SpecialFeatureResult calculateSpecialFeatureResult(double totalRtp, double baseBet, IKillCountCtr killCountCtr) {
        return new SpecialFeatureResult();
    }

    // 計算機器人特殊事件結果
    @Override
    public List<RobotSpecialFeatureResult> calculateRobotSpecialFeatureResultList(double robotTotalRtp) {
        return Collections.emptyList();
    }

    @Override
    public List<RobotSpecialFeatureOddsInfo> calculateRobotSpecialFeatureOddsInfoList() {
        return Collections.emptyList();
    }


    //* Rtp相關 *//
    // 計算扣除特殊事件剩餘的Rtp
    @Override
    public double calculateBasicAndAwardBulletRtp(double totalRtp) {
        return 0.0;
    }
}

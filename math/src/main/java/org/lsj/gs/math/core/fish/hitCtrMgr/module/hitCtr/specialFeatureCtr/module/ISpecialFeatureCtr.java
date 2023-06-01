package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;

import java.util.List;

// 特殊事件計算者介面
public interface ISpecialFeatureCtr {
    //* 計算者相關 *//
    // 取得特殊事件類型
    ConstMathFish.SpecialFeatureEnumType getSpecialFeatureType();

    //* 真人結果相關 *//
    // 計算真人特殊事件結果
    SpecialFeatureResult calculateSpecialFeatureResult(double totalRtp, double baseBet, IKillCountCtr killCountCtr);


    //* 機器人結果相關 *//
    // 計算機器人特殊事件結果
    List<RobotSpecialFeatureResult> calculateRobotSpecialFeatureResultList(double robotTotalRtp);

    // 計算機器人特殊事件倍數資訊列表
    List<RobotSpecialFeatureOddsInfo> calculateRobotSpecialFeatureOddsInfoList();

    //* Rtp相關 *//
    // 計算扣除特殊事件剩餘的Rtp
    double calculateBasicAndAwardBulletRtp(double totalRtp);
}

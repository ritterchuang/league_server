package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendNone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 特殊事件None計算者
public class SpecialFeatureCtrNone implements ISpecialFeatureCtr {
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊事件類型
    private final SpecialFeatureCtrConfigExtendNone config; // 特殊事件None設定

    public SpecialFeatureCtrNone(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend) {
        this.specialFeatureEnumType = specialFeatureEnumType;
        this.config = (SpecialFeatureCtrConfigExtendNone) specialFeatureCtrConfigExtend;
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
        return new SpecialFeatureResult(false, 0.0, ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone());
    }

    // 計算機器人特殊事件結果
    @Override
    public List<RobotSpecialFeatureResult> calculateRobotSpecialFeatureResultList(double robotTotalRtp) {
        return new ArrayList<>() {
            {
                add(new RobotSpecialFeatureResult(new SpecialFeatureResult(false, 0.0, ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone()), 1.0));
            }
        };
    }

    // 計算機器人特殊事件倍數資訊列表
    @Override
    public List<RobotSpecialFeatureOddsInfo> calculateRobotSpecialFeatureOddsInfoList() {
        return Collections.emptyList();
    }

    //* Rtp相關 *//
    // 計算扣除特殊事件剩餘的Rtp
    @Override
    public double calculateBasicAndAwardBulletRtp(double totalRtp) {
        return totalRtp;
    }
}

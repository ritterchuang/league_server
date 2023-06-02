package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRandomOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRandomOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;

import java.util.Arrays;
import java.util.stream.Collectors;

// 隨機倍數打擊計算器工具包
public class HitCtrUtilRandomOdds implements IHitCtrUtil{
    private final HitTypeConfigExtendRandomOdds config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilRandomOdds(HitTypeConfigExtendRandomOdds hitTypeConfigExtendRandomOdds, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendRandomOdds;
        this.tableUtil = tableUtil;
    }


    // 計算隨機倍數打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算目標倍數
        int basicOdds = this.config.getOddsArray()[this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getOddsWeightArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 2. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtend());
    }

    // 計算隨機倍數客製打擊結果
    @Override
    public HitResultExtendRandomOdds calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        return new HitResultExtendRandomOdds();
    }

}

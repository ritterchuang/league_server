package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendWheel;
import org.lsj.utils.MathUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

// 輪盤打擊計算器工具包
public class HitCtrUtilWheel implements IHitCtrUtil{
    private final HitTypeConfigExtendWheel config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilWheel(HitTypeConfigExtendWheel hitTypeConfigExtendWheel, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendWheel;
        this.tableUtil = tableUtil;
    }


    // 計算輪盤打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算目標倍數位置
        int oddsIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getShowOddsWeightArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 2. 計算此次倍數
        int basicOdds = this.config.getShowOddsArray()[oddsIndex];

        // 3. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtendWheel(oddsIndex));
    }

    // 計算輪盤客製打擊結果
    @Override
    public HitResultExtendWheel calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 無擊殺，回傳空殼
        if (!killFlag) {
            return new HitResultExtendWheel();
        }

        // 2. 轉型
        HitOddsInfoExtendWheel hitOddsInfoExtendWheel = (HitOddsInfoExtendWheel)hitOddsInfo.getHitOddsInfoExtend();

        // 3. 計算目標倍數位置
        int oddsIndex = hitOddsInfoExtendWheel.getOddsIndex();

        // 4. 計算倍數
        int basicOdds = hitOddsInfo.getBasicOdds();

        // 5. 計算得分
        double showWin = MathUtil.moneyScale(MathUtil.multiply(basicOdds, bullet.getBet()));

        // 6. 回傳
        return new HitResultExtendWheel(oddsIndex, basicOdds, showWin);
    }
}

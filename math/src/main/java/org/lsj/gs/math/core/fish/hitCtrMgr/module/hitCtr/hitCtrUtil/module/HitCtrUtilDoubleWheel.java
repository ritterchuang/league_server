package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendDoubleWheel;

import java.util.Arrays;
import java.util.stream.Collectors;

// 雙重輪盤打擊計算器工具包
public class HitCtrUtilDoubleWheel implements IHitCtrUtil{
    private final HitTypeConfigExtendDoubleWheel config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilDoubleWheel(HitTypeConfigExtendDoubleWheel hitTypeConfigExtendDoubleWheel, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendDoubleWheel;
        this.tableUtil = tableUtil;
    }


    // 計算輪盤打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算內圈目標倍數位置
        int inSideOddsIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getInSideShowOddsWeightArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 2. 計算外圈目標倍數位置
        int outSideOddsIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getInSideOddsToOutSideOddsWeightArrayMap().get(this.config.getInSideShowOddsArray()[inSideOddsIndex])).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 3. 計算此次倍數
        int basicOdds = this.config.getInSideShowOddsArray()[inSideOddsIndex] * this.config.getOutSideShowOddsArray()[outSideOddsIndex];

        // 4. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtendDoubleWheel(inSideOddsIndex, outSideOddsIndex));
    }

    // 計算輪盤客製打擊結果
    @Override
    public HitResultExtendDoubleWheel calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 無擊殺，回傳空殼
        if (!killFlag) {
            return new HitResultExtendDoubleWheel();
        }

        // 2. 轉型
        HitOddsInfoExtendDoubleWheel hitOddsInfoExtendDoubleWheel = (HitOddsInfoExtendDoubleWheel)hitOddsInfo.getHitOddsInfoExtend();

        // 3. 計算內圈倍數位置
        int inSideOddsIndex = hitOddsInfoExtendDoubleWheel.getInSideOddsIndex();

        // 4. 計算外圈倍數位置
        int outSideOddsIndex = hitOddsInfoExtendDoubleWheel.getOutSideOddsIndex();

        // 5. 計算內圈倍數
        int inSideOdds = this.config.getInSideShowOddsArray()[inSideOddsIndex];

        // 6. 計算外圈倍數
        int outSideOdds = this.config.getOutSideShowOddsArray()[outSideOddsIndex];

        // 7. 回傳
        return new HitResultExtendDoubleWheel(inSideOddsIndex, outSideOddsIndex, inSideOdds, outSideOdds);
    }
}

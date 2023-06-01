package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendReel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;

// 固定倍數打擊計算器工具包
public class HitCtrUtilReel implements IHitCtrUtil{
    private final HitTypeConfigExtendReel config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilReel(HitTypeConfigExtendReel hitTypeConfigExtendReel, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendReel;
        this.tableUtil = tableUtil;
    }


    // 計算固定倍數打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算目標倍數區間
        int[] targetOddsRange = this.config.getOddsRangeList().get(this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(this.config.getOddsRangeWeightList(), ConstMathCommon.AccuracyType.A32768));

        // 2. 取得目標倍數
        int basicOdds = targetOddsRange[0] + this.tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(targetOddsRange[1] - targetOddsRange[0] + 1, ConstMathCommon.AccuracyType.A32768);

        // 3. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtend());
    }

    // 計算固定倍數客製打擊結果
    @Override
    public HitResultExtendReel calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 未擊殺回傳空殼
        if (!killFlag) {
            return new HitResultExtendReel();
        }

        // 2. 計算第一位數
        int firstNumber = hitOddsInfo.getBasicOdds() % 10;

        // 3. 計算第二位數
        int secondNumber = (hitOddsInfo.getBasicOdds() % 100) / 10;

        // 4. 計算第三位數
        int thirdNumber = hitOddsInfo.getBasicOdds() / 100;

        // 5. 回傳結果
        return new HitResultExtendReel(firstNumber, secondNumber, thirdNumber);
    }

}

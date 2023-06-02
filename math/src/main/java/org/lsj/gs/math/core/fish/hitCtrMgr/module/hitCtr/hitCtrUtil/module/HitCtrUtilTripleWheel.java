package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTripleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTripleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendTripleWheel;

import java.util.Arrays;
import java.util.stream.Collectors;

// 雙重輪盤打擊計算器工具包
public class HitCtrUtilTripleWheel implements IHitCtrUtil{
    private final HitTypeConfigExtendTripleWheel config; // 客製打擊計算器設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public HitCtrUtilTripleWheel(HitTypeConfigExtendTripleWheel hitTypeConfigExtendTripleWheel, ITableUtil tableUtil) {
        this.config = hitTypeConfigExtendTripleWheel;
        this.tableUtil = tableUtil;
    }

    // 計算輪盤打擊倍數資訊
    @Override
    public HitOddsInfo calculateHitOddsInfo(HitTypeExtend hitTypeExtend, ClientTarget target) {
        // 1. 計算外圈目標倍數位置
        int firstOddsIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getShowFirstOddsWeightArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 2. 計算中圈目標倍數位置
        int secondOddsIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getShowSecondOddsWeightArray().get(this.config.getFirstShowOddsArray()[firstOddsIndex])).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 3. 計算內圈目標倍數位置
        int thirdOddsIndex = this.calculateThirdOddsIndex(firstOddsIndex, secondOddsIndex);

        // 4. 計算目標倍數
        int basicOdds = this.config.getFirstShowOddsArray()[firstOddsIndex] * this.config.getSecondShowOddsArray()[secondOddsIndex] * this.config.getThirdShowOddsArray()[thirdOddsIndex];

        // 5. 回傳
        return new HitOddsInfo(basicOdds, new HitOddsInfoExtendTripleWheel(firstOddsIndex,secondOddsIndex,thirdOddsIndex));
    }

    // 計算輪盤客製打擊結果
    @Override
    public HitResultExtendTripleWheel calculateHitResultExtend(boolean killFlag, HitOddsInfo hitOddsInfo, HitTypeExtend hitTypeExtend, Bullet bullet) {
        // 1. 無擊殺，回傳空殼
        if (!killFlag) {
            return new HitResultExtendTripleWheel();
        }
        // 2. 轉型
        HitOddsInfoExtendTripleWheel hitOddsInfoExtendTripleWheel = (HitOddsInfoExtendTripleWheel)hitOddsInfo.getHitOddsInfoExtend();

        // 3. 計算外圈倍數位置
        int firstOddsIndex = hitOddsInfoExtendTripleWheel.getFirstOddsIndex();

        // 4. 計算中圈倍數位置
        int secondOddsIndex = hitOddsInfoExtendTripleWheel.getSecondOddsIndex();

        // 5. 計算內圈倍數位置
        int thirdOddsIndex = hitOddsInfoExtendTripleWheel.getThirdOddsIndex();

        // 6. 計算外圈倍數
        int firstOdds = this.config.getFirstShowOddsArray()[firstOddsIndex];

        // 7. 計算中圈倍數
        int secondOdds = this.config.getSecondShowOddsArray()[secondOddsIndex];

        // 8. 計算內圈倍數
        int thirdOdds = this.config.getThirdShowOddsArray()[thirdOddsIndex];

        // 9. 回傳
        return new HitResultExtendTripleWheel(firstOddsIndex, secondOddsIndex, thirdOddsIndex, firstOdds, secondOdds, thirdOdds);
    }

    //計算內圈目標倍數位置
    private int calculateThirdOddsIndex(int firstOddsIndex, int secondOddsIndex) {

        // 1. 計算外*中圈倍數
        int firstAndSecondOdds = this.config.getFirstShowOddsArray()[firstOddsIndex] * this.config.getSecondShowOddsArray()[secondOddsIndex];

        // 2. 計算內圈目標倍數位置
        int result = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.config.getShowThirdOddsWeightArray().get(firstAndSecondOdds).get(this.config.getFirstShowOddsArray()[firstOddsIndex])).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 3. 回傳
        return result;
    }
}

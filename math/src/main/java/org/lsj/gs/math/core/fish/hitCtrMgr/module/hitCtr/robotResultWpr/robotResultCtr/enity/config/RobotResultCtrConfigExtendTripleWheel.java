package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTripleWheel;

import java.util.Map;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendTripleWheel extends RobotResultCtrConfigExtend{
    private final int[] firstShowOddsArray; // 外圈表演倍數列表
    private final int[] secondShowOddsArray; // 中圈表演倍數列表
    private final int[] thirdShowOddsArray; // 內圈表演倍數列表
    private final int[] showFirstOddsWeightArray; // 外圈倍數權重列表
    private final Map<Integer, int[]> showSecondOddsWeightArray; // <表演倍數切割陣列列表>
    private final Map<Integer, Map<Integer,int[]>> showThirdOddsWeightArray; // <表演倍數切割陣列權重列表>

    public RobotResultCtrConfigExtendTripleWheel(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendTripleWheel hitTypeConfigExtendTripleWheel = (HitTypeConfigExtendTripleWheel)HitTypeConfigExtend;
        this.firstShowOddsArray = hitTypeConfigExtendTripleWheel.getFirstShowOddsArray();
        this.secondShowOddsArray = hitTypeConfigExtendTripleWheel.getSecondShowOddsArray();
        this.thirdShowOddsArray = hitTypeConfigExtendTripleWheel.getThirdShowOddsArray();
        this.showFirstOddsWeightArray = hitTypeConfigExtendTripleWheel.getShowFirstOddsWeightArray();
        this.showSecondOddsWeightArray = hitTypeConfigExtendTripleWheel.getShowSecondOddsWeightArray();
        this.showThirdOddsWeightArray = hitTypeConfigExtendTripleWheel.getShowThirdOddsWeightArray();
    }
    public int[] getFirstShowOddsArray() {return firstShowOddsArray;}
    public int[] getSecondShowOddsArray() {
        return secondShowOddsArray;
    }
    public int[] getThirdShowOddsArray() {
        return thirdShowOddsArray;
    }
    public int[] getShowFirstOddsWeightArray() {
        return showFirstOddsWeightArray;
    }
    public Map<Integer, int[]> getShowSecondOddsWeightArray() {
        return showSecondOddsWeightArray;
    }
    public Map<Integer, Map<Integer,int[]>> getShowThirdOddsWeightArray() {
        return showThirdOddsWeightArray;
    }
}

package org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.AdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;

import java.util.HashMap;
import java.util.Map;

// 遊戲調控參數計算器
public class GameAdjustParameterCtr {
    protected final PoolCtr poolCtr; // 水池控制器
    protected final Map<ConstMathCommon.PersonAdjustType, Double> personalAdjustParameterMap; // 個人控參數表

    public GameAdjustParameterCtr(PoolCtr poolCtr) {
        this.poolCtr = poolCtr;
        this.personalAdjustParameterMap = this.initialPersonalAdjustParameterMap(poolCtr.getPoolConfig().getAgencyPool().getFeeRate());
    }

    // 計算調控參數
    public GameAdjustParameter calculateGameAdjustParameter(ConstMathCommon.PersonAdjustType personAdjustType) {
        return new GameAdjustParameter(personAdjustType, this.calculateAdjustParameter(personAdjustType));
    }

    
    /* 個人控參數相關 */
    // 計算調控參數
    public AdjustParameter calculateAdjustParameter(ConstMathCommon.PersonAdjustType personAdjustType) {
        return new AdjustParameter(this.personalAdjustParameterMap.get(personAdjustType));
    }

    // 初始化調控參數
    private Map<ConstMathCommon.PersonAdjustType, Double> initialPersonalAdjustParameterMap(double feeRate) {
        Map<ConstMathCommon.PersonAdjustType, Double> result = new HashMap<>();

        result.put(ConstMathCommon.PersonAdjustType.NORMAL, feeRate);
        result.put(ConstMathCommon.PersonAdjustType.BLACK1, 0.15);
        result.put(ConstMathCommon.PersonAdjustType.BLACK2, 0.3);
        result.put(ConstMathCommon.PersonAdjustType.WHITE1, -0.05);
        result.put(ConstMathCommon.PersonAdjustType.WHITE2, -0.1);

        return result;
    }

    // 重設
    public void reset() {}
}

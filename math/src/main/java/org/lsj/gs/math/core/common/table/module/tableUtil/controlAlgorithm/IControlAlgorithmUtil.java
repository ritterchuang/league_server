package org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm;

import org.lsj.gs.math.core.common.ConstMathCommon;

//  強控演算法工具包介面
public interface IControlAlgorithmUtil {
    boolean isControlFlag(); // 取得強控標誌
    ConstMathCommon.PoolControlType getControlPoolControlType(); // 水池控制類型
    ConstMathCommon.DealType getControlDealType(); // 開牌類型
    ConstMathCommon.ShuffleType getControlShuffleType(); // 發牌類型
    ConstMathCommon.PersonAdjustType getControlPersonAdjustType(); // 個人控
    void reset();
}

package org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm;

import org.lsj.gs.math.core.common.ConstMathCommon;

//  強控演算法工具包
public class ControlAlgorithmUtil implements IControlAlgorithmUtil {
    private final boolean controlFlag; // 除錯標誌
    private final ConstMathCommon.PoolControlType controlPoolControlType; // 水池控制類型
    private final ConstMathCommon.DealType controlDealType; // 開牌類型
    private final ConstMathCommon.ShuffleType controlShuffleType; // 發牌類型
    private final ConstMathCommon.PersonAdjustType controlPersonAdjustType; // 個人控設定

    public ControlAlgorithmUtil() {
        this.controlFlag = false;
        this.controlPoolControlType = ConstMathCommon.PoolControlType.INVALID;
        this.controlDealType = ConstMathCommon.DealType.INVALID;
        this.controlShuffleType = ConstMathCommon.ShuffleType.INVALID;
        this.controlPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
    }

    public ControlAlgorithmUtil(boolean controlFlag,
                                ConstMathCommon.PoolControlType poolControlType,
                                ConstMathCommon.DealType dealType,
                                ConstMathCommon.ShuffleType shuffleType,
                                ConstMathCommon.PersonAdjustType personAdjustType) {
        this.controlFlag = controlFlag;
        this.controlPoolControlType = poolControlType;
        this.controlDealType = dealType;
        this.controlShuffleType = shuffleType;
        this.controlPersonAdjustType = personAdjustType;
    }

    @Override
    public boolean isControlFlag() {
        return controlFlag;
    }

    @Override
    public ConstMathCommon.PoolControlType getControlPoolControlType() {
        return controlPoolControlType;
    }

    @Override
    public ConstMathCommon.DealType getControlDealType() {
        return controlDealType;
    }

    @Override
    public ConstMathCommon.ShuffleType getControlShuffleType() {
        return controlShuffleType;
    }

    @Override
    public ConstMathCommon.PersonAdjustType getControlPersonAdjustType() {
        return controlPersonAdjustType;
    }

    // 重設
    @Override
    public void reset(){}
}

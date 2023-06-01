package org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 演算法類型計算器
public class AlgorithmTypeCtr {
    private final GameAdjustConfig gameAdjustConfig; // 調控演算法設定
    private final RngAlgorithmConfig rngAlgorithmConfig; // 隨機數演算法設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public AlgorithmTypeCtr(GameAdjustConfig gameAdjustConfig, RngAlgorithmConfig rngAlgorithmConfig, ITableUtil tableUtil) {
        this.gameAdjustConfig = gameAdjustConfig;
        this.rngAlgorithmConfig = rngAlgorithmConfig;
        this.tableUtil = tableUtil;
    }

    // 計算演算法類型
    public AlgorithmType calculateAlgorithmType(boolean needControl, ConstMathCommon.PersonAdjustType companyPersonAdjustType){
        switch(this.calculateGameAdjustType(needControl)){
            case NONE: return this.calculateAlgorithmTypeNone();
            case CONTROL: return this.calculateAlgorithmTypeControl();
            case RNG: return this.calculateAlgorithmTypeRng();
            default: return this.calculateAlgorithmSetting(companyPersonAdjustType);
        }
    }

    // 計算演算法類型 當調控類型為不調控
    private AlgorithmType calculateAlgorithmTypeNone(){
        return new AlgorithmType(
                ConstMathCommon.PersonAdjustType.NORMAL,
                ConstMathCommon.PoolControlType.NONE,
                ConstMathCommon.DealType.NONE,
                ConstMathCommon.ShuffleType.NATURE
        );
    }

    // 計算演算法類型 當調控類型為強制調控
    private AlgorithmType calculateAlgorithmTypeControl() {
        return new AlgorithmType(
                this.tableUtil.getControlAlgorithmUtil().getControlPersonAdjustType(),
                this.tableUtil.getControlAlgorithmUtil().getControlPoolControlType(),
                this.tableUtil.getControlAlgorithmUtil().getControlDealType(),
                this.tableUtil.getControlAlgorithmUtil().getControlShuffleType()
        );
    }

    // 計算演算法類型 當調控類型為有設定亂數
    private AlgorithmType calculateAlgorithmTypeRng() {
        return new AlgorithmType(
                this.rngAlgorithmConfig.getPersonAdjustType(),
                this.rngAlgorithmConfig.getPoolControlType(),
                this.rngAlgorithmConfig.getDealType(),
                this.rngAlgorithmConfig.getShuffleType()
        );
    }

    // 計算演算法類型 當調控類型為原始設定
    private AlgorithmType calculateAlgorithmSetting(ConstMathCommon.PersonAdjustType companyPersonAdjustType) {
        return new AlgorithmType(
                companyPersonAdjustType,
                this.gameAdjustConfig.getPoolControlType(),
                this.gameAdjustConfig.getDealType(),
                this.gameAdjustConfig.getShuffleType()
        );
    }

    // 計算調控類型
    private ConstMathCommon.GameAdjustType calculateGameAdjustType(boolean needControl){
        // 1. 無須調控狀態
        if(!needControl){
            return ConstMathCommon.GameAdjustType.NONE;
        }

        // 2. 指定強控狀態
        if(this.tableUtil.getControlAlgorithmUtil().isControlFlag()){
            return  ConstMathCommon.GameAdjustType.CONTROL;
        }

        // 3. 設定亂數狀態
        if(this.tableUtil.getMainRandomUtil().isSetRngDataState()){
            return ConstMathCommon.GameAdjustType.RNG;
        }

        // 4. 原始設定調控狀態
        return ConstMathCommon.GameAdjustType.SETTING;
    }

    // 重設
    public void reset() {}
}

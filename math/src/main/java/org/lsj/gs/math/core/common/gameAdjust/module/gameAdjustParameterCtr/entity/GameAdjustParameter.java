package org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 遊戲調控參數
public class GameAdjustParameter {
    private final ConstMathCommon.PersonAdjustType personAdjustType; // 個人控調控類型
    private final AdjustParameter adjustParameter; // 計算調控參數

    public GameAdjustParameter(ConstMathCommon.PersonAdjustType personAdjustType, AdjustParameter adjustParameter) {
        this.personAdjustType = personAdjustType;
        this.adjustParameter = adjustParameter;
    }

    public ConstMathCommon.PersonAdjustType getPersonAdjustType() {
        return personAdjustType;
    }

    public AdjustParameter getAdjustParameter() {
        return adjustParameter;
    }
}

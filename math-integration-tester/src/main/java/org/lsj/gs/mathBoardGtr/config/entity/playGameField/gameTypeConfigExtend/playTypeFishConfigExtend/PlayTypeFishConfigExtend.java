package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend;

import org.lsj.gs.mathStr.core.entity.ConstStr;

//遊玩模式魚機額外設定
public class PlayTypeFishConfigExtend {
    private final ConstStr.PlayCostType playCostType; // 遊戲押注類型
    private final TargetInfo[] targetInfoArray; // 目標資訊陣列

    public PlayTypeFishConfigExtend(ConstStr.PlayCostType playCostType, TargetInfo[] targetInfoArray) {
        this.playCostType = playCostType;
        this.targetInfoArray = targetInfoArray;
    }

    public ConstStr.PlayCostType getPlayCostType() {
        return playCostType;
    }

    public TargetInfo[] getTargetInfoArray() {
        return targetInfoArray;
    }
}

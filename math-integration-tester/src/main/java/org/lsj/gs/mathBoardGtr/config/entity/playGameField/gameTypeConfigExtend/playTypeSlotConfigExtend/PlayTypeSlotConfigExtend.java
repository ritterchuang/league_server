package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeSlotConfigExtend;

import org.lsj.gs.mathStr.core.entity.ConstStr;

//遊玩模式老虎機額外設定
public class PlayTypeSlotConfigExtend {
    private final ConstStr.PlayCostType playCostType; // 遊戲押注類型

    public PlayTypeSlotConfigExtend(ConstStr.PlayCostType playCostType) {
        this.playCostType = playCostType;
    }

    public ConstStr.PlayCostType getPlayCostType() {
        return playCostType;
    }
}

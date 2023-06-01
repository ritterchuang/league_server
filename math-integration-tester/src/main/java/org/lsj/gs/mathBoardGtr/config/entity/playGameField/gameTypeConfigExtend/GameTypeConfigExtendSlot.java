package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeSlotConfigExtend.PlayTypeSlotConfigExtendNormal;
import org.lsj.gs.mathStr.core.entity.ConstStr;

// 遊戲類型額外設定老虎機
public class GameTypeConfigExtendSlot extends GameTypeConfigExtend{
    private final ConstStr.PlayTypeSlot playTypeSlot; // 遊玩模式老虎機
    private final PlayTypeSlotConfigExtendNormal playTypeSlotConfigExtend; // 遊玩模式魚機額外設定

    public GameTypeConfigExtendSlot(ConstStr.PlayTypeSlot playTypeSlot, PlayTypeSlotConfigExtendNormal playTypeSlotConfigExtend) {
        this.playTypeSlot = playTypeSlot;
        this.playTypeSlotConfigExtend = playTypeSlotConfigExtend;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "playTypeSlot")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = PlayTypeSlotConfigExtendNormal.class, name = "NORMAL")
    })

    public ConstStr.PlayTypeSlot getPlayTypeSlot() {
        return playTypeSlot;
    }

    public PlayTypeSlotConfigExtendNormal getPlayTypeSlotConfigExtend() {
        return playTypeSlotConfigExtend;
    }
}

package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend.PlayTypeBaiRenConfigExtend;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeBaiRenConfigExtend.PlayTypeBaiRenConfigExtendNormal;
import org.lsj.gs.mathStr.core.entity.ConstStr;

// 遊戲類型額外設定百人
public class GameTypeConfigExtendBaiRen extends GameTypeConfigExtend{
    private final ConstStr.PlayTypeBaiRen playTypeBaiRen; // 遊玩模式卡牌
    private final PlayTypeBaiRenConfigExtend playTypeBaiRenConfigExtend; // 遊玩模式卡牌額外設定

    public GameTypeConfigExtendBaiRen(ConstStr.PlayTypeBaiRen playTypeBaiRen, PlayTypeBaiRenConfigExtend playTypeBaiRenConfigExtend) {
        this.playTypeBaiRen = playTypeBaiRen;
        this.playTypeBaiRenConfigExtend = playTypeBaiRenConfigExtend;
    }

    public ConstStr.PlayTypeBaiRen getPlayTypeBaiRen() {
        return playTypeBaiRen;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "playTypeBaiRen")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = PlayTypeBaiRenConfigExtendNormal.class, name = "NORMAL")
    })
    public PlayTypeBaiRenConfigExtend getPlayTypeBaiRenConfigExtend() {
        return playTypeBaiRenConfigExtend;
    }
}

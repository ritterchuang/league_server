package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.PlayTypeFishConfigExtend;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.PlayTypeFishConfigExtendNormal;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeFishConfigExtend.PlayTypeFishConfigExtendRandomTarget;
import org.lsj.gs.mathStr.core.entity.ConstStr;

// 遊戲類型額外設定魚機
public class GameTypeConfigExtendFish extends GameTypeConfigExtend{
    private final ConstStr.PlayTypeFish playTypeFish; // 遊玩模式魚機
    private final PlayTypeFishConfigExtend playTypeFishConfigExtend; // 遊玩模式魚機額外設定

    public GameTypeConfigExtendFish(ConstStr.PlayTypeFish playTypeFish, PlayTypeFishConfigExtend playTypeFishConfigExtend) {
        this.playTypeFish = playTypeFish;
        this.playTypeFishConfigExtend = playTypeFishConfigExtend;
    }

    public ConstStr.PlayTypeFish getPlayTypeFish() {
        return playTypeFish;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "playTypeFish")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = PlayTypeFishConfigExtendNormal.class, name = "NORMAL"),
            @JsonSubTypes.Type(value = PlayTypeFishConfigExtendRandomTarget.class, name = "RANDOM_TARGET")
    })

    public PlayTypeFishConfigExtend getPlayTypeFishConfigExtend() {
        return playTypeFishConfigExtend;
    }
}

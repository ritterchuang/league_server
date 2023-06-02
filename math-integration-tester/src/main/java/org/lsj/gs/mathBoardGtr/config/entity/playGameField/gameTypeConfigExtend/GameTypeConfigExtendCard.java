package org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtend;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtendNormal;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.playTypeCardConfigExtend.PlayTypeCardConfigExtendTimeOut;
import org.lsj.gs.mathStr.core.entity.ConstStr;

// 遊戲類型額外設定卡牌
public class GameTypeConfigExtendCard extends GameTypeConfigExtend{
    private final ConstStr.PlayTypeCard playTypeCard; // 遊玩模式卡牌
    private final PlayTypeCardConfigExtend playTypeCardConfigExtend; // 遊玩模式卡牌額外設定

    public GameTypeConfigExtendCard(ConstStr.PlayTypeCard playTypeCard, PlayTypeCardConfigExtend playTypeCardConfigExtend) {
        this.playTypeCard = playTypeCard;
        this.playTypeCardConfigExtend = playTypeCardConfigExtend;
    }

    public ConstStr.PlayTypeCard getPlayTypeCard() {
        return playTypeCard;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "playTypeCard")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = PlayTypeCardConfigExtendNormal.class, name = "NORMAL"),
            @JsonSubTypes.Type(value = PlayTypeCardConfigExtendTimeOut.class, name = "TIME_OUT")
    })
    public PlayTypeCardConfigExtend getPlayTypeCardConfigExtend() {
        return playTypeCardConfigExtend;
    }
}

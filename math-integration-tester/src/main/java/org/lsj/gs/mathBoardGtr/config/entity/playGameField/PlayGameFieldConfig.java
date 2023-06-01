package org.lsj.gs.mathBoardGtr.config.entity.playGameField;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.*;
import org.lsj.gs.mathStr.core.entity.ConstStr;

// 遊戲房間設定
public class PlayGameFieldConfig {
    private final ConstStr.GameType gameType; // 遊戲模式類型
    private final GameTypeConfigExtend gameTypeConfigExtend; // 遊戲類型額外設定
    private final int weight; // 權重
    private final TableFieldConfig tableFieldConfig; // 指定房間設定

    public PlayGameFieldConfig(ConstStr.GameType gameType,
                               GameTypeConfigExtend gameTypeConfigExtend,
                               int weight,
                               TableFieldConfig tableFieldConfig) {
        this.gameType = gameType;
        this.gameTypeConfigExtend = gameTypeConfigExtend;
        this.weight = weight;
        this.tableFieldConfig = tableFieldConfig;
    }

    public ConstStr.GameType getGameType() {
        return gameType;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "gameType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = GameTypeConfigExtendCard.class, name = "CARD"),
            @JsonSubTypes.Type(value = GameTypeConfigExtendFish.class, name = "FISH"),
            @JsonSubTypes.Type(value = GameTypeConfigExtendSlot.class, name = "SLOT"),
            @JsonSubTypes.Type(value = GameTypeConfigExtendBaiRen.class, name = "BAIREN")
    })
    public GameTypeConfigExtend getGameTypeConfigExtend() {
        return gameTypeConfigExtend;
    }

    public int getWeight() {
        return weight;
    }

    public TableFieldConfig getTableFieldConfig() {
        return tableFieldConfig;
    }
}

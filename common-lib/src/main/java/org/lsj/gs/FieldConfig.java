package org.lsj.gs;

import org.lsj.db.CompanyFieldObj;

import java.util.Map;

// 房間設定
public class FieldConfig {
    private int companyId; // 公司識別碼
    private int gameId; // 遊戲識別碼
    private String gameName; // 遊戲英文名稱
    private String gameNameCN; // 遊戲中文名稱
    private int defaultGameKind; // 預設遊戲種類
    private short gameState; // 遊戲狀態
    private short invisible; // 關閉標誌
    private short dissolveTime; // 解散時機
    private short minUser; // 最小玩家數
    private short maxUser; // 最大玩家數
    private String quickGamesJson; // 快捷遊戲設定
    private String extendJson; // 客製設定
    private Map<Integer, CompanyFieldObj> fieldConfigMap; // 公司房間設定

    // 原始建構子提供JSON解析用
    public FieldConfig() {}

    public FieldConfig(int companyId,
                       int gameId,
                       String gameName,
                       String gameNameCN,
                       int defaultGameKind,
                       short gameState,
                       short invisible,
                       short dissolveTime,
                       short minUser,
                       short maxUser,
                       String quickGamesJson,
                       String extendJson,
                       Map<Integer, CompanyFieldObj> fieldConfigMap) {
        this.companyId = companyId;
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameNameCN = gameNameCN;
        this.defaultGameKind = defaultGameKind;
        this.gameState = gameState;
        this.invisible = invisible;
        this.dissolveTime = dissolveTime;
        this.minUser = minUser;
        this.maxUser = maxUser;
        this.quickGamesJson = quickGamesJson;
        this.extendJson = extendJson;
        this.fieldConfigMap = fieldConfigMap;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameNameCN() {
        return gameNameCN;
    }

    public int getDefaultGameKind() {
        return defaultGameKind;
    }

    public short getGameState() {
        return gameState;
    }

    public short getInvisible() {
        return invisible;
    }

    public short getDissolveTime() {
        return dissolveTime;
    }

    public short getMinUser() {
        return minUser;
    }

    public short getMaxUser() {
        return maxUser;
    }

    public String getQuickGamesJson() {
        return quickGamesJson;
    }

    public String getExtendJson() {
        return extendJson;
    }

    public Map<Integer, CompanyFieldObj> getFieldConfigMap() {
        return fieldConfigMap;
    }
}

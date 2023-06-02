package org.lsj.gs;

import org.lsj.db.CompanyFieldObj;

import java.util.Map;

public class FieldConfigBuilder {
    private int companyId;
    private int gameId;
    private String gameName;
    private String gameNameCN;
    private int defaultGameKind;
    private short gameState;
    private short invisible;
    private short dissolveTime;
    private short minUser;
    private short maxUser;
    private String quickGamesJson;
    private String extendJson;
    private Map<Integer, CompanyFieldObj> fieldConfigMap;

    public FieldConfigBuilder setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public FieldConfigBuilder setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public FieldConfigBuilder setGameName(String gameName) {
        this.gameName = gameName;
        return this;
    }

    public FieldConfigBuilder setGameNameCN(String gameNameCN) {
        this.gameNameCN = gameNameCN;
        return this;
    }

    public FieldConfigBuilder setDefaultGameKind(int defaultGameKind) {
        this.defaultGameKind = defaultGameKind;
        return this;
    }

    public FieldConfigBuilder setGameState(short gameState) {
        this.gameState = gameState;
        return this;
    }

    public FieldConfigBuilder setInvisible(short invisible) {
        this.invisible = invisible;
        return this;
    }

    public FieldConfigBuilder setDissolveTime(short dissolveTime) {
        this.dissolveTime = dissolveTime;
        return this;
    }

    public FieldConfigBuilder setMinUser(short minUser) {
        this.minUser = minUser;
        return this;
    }

    public FieldConfigBuilder setMaxUser(short maxUser) {
        this.maxUser = maxUser;
        return this;
    }

    public FieldConfigBuilder setQuickGamesJson(String quickGamesJson) {
        this.quickGamesJson = quickGamesJson;
        return this;
    }

    public FieldConfigBuilder setExtendJson(String extendJson) {
        this.extendJson = extendJson;
        return this;
    }

    public FieldConfigBuilder setFieldConfigMap(Map<Integer, CompanyFieldObj> fieldConfigMap) {
        this.fieldConfigMap = fieldConfigMap;
        return this;
    }

    public FieldConfig createFieldConfig() {
        return new FieldConfig(companyId, gameId, gameName, gameNameCN, defaultGameKind, gameState, invisible, dissolveTime, minUser, maxUser, quickGamesJson, extendJson, fieldConfigMap);
    }
}

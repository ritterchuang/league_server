package com.lx.gs.mathStr.core.module.playStr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.ConstStr;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.GameCenterMgr;
import com.lx.gs.mathStr.core.module.GamePlayerFactory;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 遊戲模擬器工廠
public class PlayStrFactory {
    private final Map<Integer, ConstStr.GameType> gameEnum2GameTypeMap; // 遊戲類型對應表
    private final ControlAlgorithmConfig controlAlgorithmConfig; // 除錯設定

    public PlayStrFactory(Map<Integer, ConstStr.GameType> gameEnum2GameTypeMap, ControlAlgorithmConfig controlAlgorithmConfig) {
        this.gameEnum2GameTypeMap = gameEnum2GameTypeMap;
        this.controlAlgorithmConfig = controlAlgorithmConfig;
    }

    // 建立遊戲模擬器
    public AbstractPlayStr createPlayStr(GamePlayerFactory gamePlayerFactory, PoolCtr poolCtr, GameCenterMgr gameCenterMgr) {
        // 1. 生成玩家
        GamePlayerSimulation gamePlayerSimulation = gamePlayerFactory.createGamePlayer();

        // 2. 隨機選擇房間設定
        PlayGameFieldConfig playGameFieldConfig = gameCenterMgr.calculatePlayFieldConfig(gamePlayerSimulation.getUser());

        // 3. 創建遊戲模擬器
        return this.createPlayStrByGameType(gamePlayerSimulation, poolCtr, playGameFieldConfig, this.controlAlgorithmConfig);
    }

    // 依照遊戲類型建立遊戲模擬器
    private AbstractPlayStr createPlayStrByGameType(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig){
        switch(this.gameEnum2GameTypeMap.get(playGameFieldConfig.getTableFieldConfig().getGameId())){
            case CARD: return new PlayStrCard(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case BAIREN: return new PlayStrBaiRen(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case FISH: return new PlayStrFish(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            case SLOT: return new PlayStrSlot(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new DefaultPlayStr(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }
}

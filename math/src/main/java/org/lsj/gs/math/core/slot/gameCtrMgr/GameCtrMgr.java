package org.lsj.gs.math.core.slot.gameCtrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrMgrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.GameCtrFactory;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.IGameCtr;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 遊戲算分器管理器
public class GameCtrMgr {
    private final GameCtrMgrConfig config; // 遊戲算分器管理器設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, IGameCtr> gameCtrMap; // 遊戲算分器對應表 <遊戲狀態ID, 遊戲算分器>

    public GameCtrMgr(GameCtrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.gameCtrMap = this.createGameCtrMap();
    }

    // 計算畫面分數
    public GameCtrResult getGameCtrResult(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        return this.gameCtrMap.get(conditionId).calculateGameCtrResult(spinRequest, screenGtrResult);
    }

    // 計算全屏畫面倍數得分
    public GameCtrResult getGameCtrResultWithScreenMultiplier(
            int conditionId,
            SpinRequest spinRequest,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier) {
        return this.gameCtrMap.get(conditionId).calculateGameCtrResultWithScreenMultiplier(spinRequest, screenGtrResult, screenMultiplier);
    }

    // 計算多種倍數得分
    public GameCtrResult getGameCtrResultWithMultiplier(
            int conditionId,
            SpinRequest spinRequest,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix) {
        return this.gameCtrMap.get(conditionId).calculateGameCtrResultWithMultiplier(spinRequest, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);
    }

    // 創建 遊戲算分器對應表
    private Map<Integer, IGameCtr> createGameCtrMap() {
        Map<Integer, IGameCtr> result = new HashMap<>();

        this.config.getConditionIdToGameCtrConfigMap().forEach(
                (key, value) -> result.put(key, new GameCtrFactory().create(value, this.tableUtil)));

        return result;
    }
}

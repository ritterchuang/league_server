package org.lsj.gs.math.core.common.logic.logicSlot;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrSlot;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.GameCtrMgr;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrMgrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.moduleConfigMgr.ModuleConfigMgrSlotNormal;
import org.lsj.gs.math.core.slot.screenGtrMgr.ScreenGtrMgr;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrMgrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.List;
import java.util.Map;

// 虎機邏輯 一般
public class LogicSlotNormal extends AbstractLogicSlot implements ILogicSlot {
    private final ScreenGtrMgr screenGtrMgr; // 畫面產生器管理者
    private final GameCtrMgr gameCtrMgr; // 畫面算分器管理者


    public LogicSlotNormal(ITableBase table, TableFieldConfig tableFieldConfig, TableGameConfigSlot tableGameConfigSlot, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, IGameBetLogResultCtrSlot gameBetLogResultCtrSlot, ITableUtil tableUtil) {
        // 1. 初始化
        super(
                table,
                tableFieldConfig,
                tableGameConfigSlot,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtrSlot,
                tableUtil);

        // 2. 取得對應設定
        ModuleConfigMgrSlotNormal moduleConfigMgrSlotNormal = (ModuleConfigMgrSlotNormal) table.getTableConfigMgr().getModuleConfigMgrSlot();
        ScreenGtrMgrConfig screenGtrMgrConfig = moduleConfigMgrSlotNormal.createScreenGtrMgrConfig(tableGameConfigSlot);
        GameCtrMgrConfig gameCtrMgrConfig = moduleConfigMgrSlotNormal.createGameCtrMgrConfig(tableGameConfigSlot);


        // 3. 初始化模組 Normal
        this.gameCtrMgr = new GameCtrMgr(gameCtrMgrConfig, tableUtil); // 畫面計算者
        this.screenGtrMgr = new ScreenGtrMgr(screenGtrMgrConfig, tableUtil); // 畫面生產者
    }


    //* 取得遊戲相關資訊 *//
    // 取得預設場次
    public int getDefaultRound(int conditionId, GameStateHlrResult beforeGameStateHlrResult) {
        return super.progressHlrMgr.getDefaultRound(conditionId, beforeGameStateHlrResult);
    }

    // 隨機取得遊戲畫面結果
    public ScreenGtrResult getScreenGtrResult(int conditionId, ConstMathSlot.ReelRtpType reelRtpType) {
        return this.screenGtrMgr.getScreenGtrResult(conditionId, reelRtpType);
    }

    // 依照輪帶表，取得遊戲畫面結果
    public ScreenGtrResult getScreenGtrResultBySpecifyReelId(int conditionId, int reelId) {
        return this.screenGtrMgr.generateScreenGtrResultBySpecifyReelId(conditionId, reelId);
    }

    // 取得遊戲算分結果
    public GameCtrResult getGameCtrResult(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        return this.gameCtrMgr.getGameCtrResult(conditionId, spinRequest, screenGtrResult);
    }

    // 取得倍數遊戲算分結果
    public GameCtrResult getGameCtrResultWithScreenMultiplier(
            int conditionId,
            SpinRequest spinRequest,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier) {
        return this.gameCtrMgr.getGameCtrResultWithScreenMultiplier(conditionId, spinRequest, screenGtrResult, screenMultiplier);
    }

    // 取得倍數遊戲算分結果
    public GameCtrResult getGameCtrResultWithMultiplier(
            int conditionId,
            SpinRequest spinRequest,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix) {
        return this.gameCtrMgr.getGameCtrResultWithMultiplier(conditionId, spinRequest, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);
    }


}

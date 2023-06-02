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
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.CascadeClusterHlrMgr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.enity.CascadeClusterHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.moduleConfigMgr.ModuleConfigMgrSlotCascade;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 抽象邏輯 消除
public class LogicSlotCascade extends AbstractLogicSlot implements ILogicSlot {
    private final CascadeClusterHlrMgr cascadeClusterHlrMgr; // 消除集合處理器管理者


    public LogicSlotCascade(ITableBase table, TableFieldConfig tableFieldConfig, TableGameConfigSlot tableGameConfigSlot, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, IGameBetLogResultCtrSlot gameBetLogResultCtrSlot, ITableUtil tableUtil) {
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
        ModuleConfigMgrSlotCascade moduleConfigMgrSlotCascade = (ModuleConfigMgrSlotCascade) table.getTableConfigMgr().getModuleConfigMgrSlot();
        CascadeClusterHlrMgrConfig cascadeClusterHlrMgrConfig = moduleConfigMgrSlotCascade.createCascadeClusterHlrMgrConfig(tableGameConfigSlot);

        // 3. 初始化模組 Cascade
        this.cascadeClusterHlrMgr = new CascadeClusterHlrMgr(cascadeClusterHlrMgrConfig, super.accumulateWinCtr, tableUtil);
    }


    //* 取得遊戲相關資訊 *//
    // 取得消除集合處理者結果 TODO 模組化參數[for 飛鳥]
    public CascadeClusterHlrResult getCascadeClusterHlrResult(int conditionId, SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        return this.cascadeClusterHlrMgr.getCascadeClusterHlrResult(conditionId, spinRequest, reelRtpType);
    }

    //* 取得遊戲相關資訊 *//
    // 取得預設場次
    public int getDefaultRound(int conditionId, GameStateHlrResult beforeGameStateHlrResult) {
        return super.progressHlrMgr.getDefaultRound(conditionId, beforeGameStateHlrResult);
    }

    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultClusterCascade(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        return this.specialFeatureHlrMgr.getSpecialFeatureHlrResultClusterCascade(conditionId, spinRequest, screenGtrResult, cascadeHlrResultList);
    }

}

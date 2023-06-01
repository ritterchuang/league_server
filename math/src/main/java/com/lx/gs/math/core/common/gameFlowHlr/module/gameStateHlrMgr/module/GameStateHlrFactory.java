package com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.module.FiniteAwardPoolHlrFactory;
import com.lx.gs.math.core.common.logic.logicSlot.ILogicSlot;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.games._develop_hj_java.module.gameStateHlr.GameStateHlrDevelopHjBaseGame;
import com.lx.gs.math.games._model_hj_java.module.gameStateHlr.GameStateHlrModelHjBaseGame;
import com.lx.gs.math.games._model_hj_java.module.gameStateHlr.GameStateHlrModelHjFreeGame;
import com.lx.gs.math.games.bszx_java.module.gameStateHlr.GameStateHlrBszxBaseGame;
import com.lx.gs.math.games.bszx_java.module.gameStateHlr.GameStateHlrBszxFreeGame;
import com.lx.gs.math.games.ccc_java.entity.config.GameStateConfigExtendCccBaseGame;
import com.lx.gs.math.games.ccc_java.module.gameStateHlr.GameStateHlrCccBaseGame;
import com.lx.gs.math.games.cjwp_java.module.gameStateHlr.GameStateHlrCjwpBaseGame;
import com.lx.gs.math.games.cjwp_java.module.gameStateHlr.GameStateHlrCjwpFreeGame;
import com.lx.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryBaseGame;
import com.lx.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryBonusGame;
import com.lx.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryFreeGame;
import com.lx.gs.math.games.dydb_java.module.gameStateHlr.GameStateHlrDydbBaseGame;
import com.lx.gs.math.games.dydb_java.module.gameStateHlr.GameStateHlrDydbFreeGame;
import com.lx.gs.math.games.hjxb_java.module.gameStateHlr.GameStateHlrHjxbBaseGame;
import com.lx.gs.math.games.hjxb_java.module.gameStateHlr.GameStateHlrHjxbFreeGame;
import com.lx.gs.math.games.lll_java.entity.config.GameStateConfigExtendLllBaseGame;
import com.lx.gs.math.games.lll_java.module.gameStateHlr.GameStateHlrLllBaseGame;
import com.lx.gs.math.games.lmjjc_java.module.gameStateHlr.GameStateHlrLmjjcBaseGame;
import com.lx.gs.math.games.lmjjc_java.module.gameStateHlr.GameStateHlrLmjjcFreeGame;
import com.lx.gs.math.games.mjws_java.module.gameStateHlr.GameStateHlrMjwsBaseGame;
import com.lx.gs.math.games.mjws_java.module.gameStateHlr.GameStateHlrMjwsFreeGame;
import com.lx.gs.math.games.olldbz_java.module.gameStateHlr.GameStateHlrOlldbzBaseGame;
import com.lx.gs.math.games.olldbz_java.module.gameStateHlr.GameStateHlrOlldbzFreeGame;
import com.lx.gs.math.games.pxky_java.enity.config.GameStateConfigExtendPxkyBaseGame;
import com.lx.gs.math.games.pxky_java.module.gameStateHlr.GameStateHlrPxkyBaseGame;
import com.lx.gs.math.games.sbxc_java.entity.config.GameStateConfigExtendSbxcBaseGame;
import com.lx.gs.math.games.sbxc_java.module.gameStateHlr.GameStateHlrSbxcBaseGame;
import com.lx.gs.math.games.sdzw_java.module.gameStateHlr.GameStateHlrSdzwBaseGame;
import com.lx.gs.math.games.sdzw_java.module.gameStateHlr.GameStateHlrSdzwFreeGame;
import com.lx.gs.math.games.sn_java.module.gameStateHlr.GameStateHlrSnBaseGame;
import com.lx.gs.math.games.sn_java.module.gameStateHlr.GameStateHlrSnFreeGame;
import com.lx.gs.math.games.swzs_java.module.gameStateHlr.GameStateHlrSwzsBaseGame;
import com.lx.gs.math.games.swzs_java.module.gameStateHlr.GameStateHlrSwzsFreeGame;
import com.lx.gs.math.games.wl_java.module.gameStateHlr.GameStateHlrWlBaseGame;
import com.lx.gs.math.games.wl_java.module.gameStateHlr.GameStateHlrWlFreeGame;
import com.lx.gs.math.games.xjtb_java.module.gameStateHlr.GameStateHlrXjtbBaseGame;
import com.lx.gs.math.games.xjtb_java.module.gameStateHlr.GameStateHlrXjtbFreeGame;
import com.lx.gs.math.games.xzcq_java.entity.config.GameStateConfigExtendXzcqBaseGame;
import com.lx.gs.math.games.xzcq_java.module.gameStateHlr.GameStateHlrXzcqBaseGame;
import com.lx.gs.math.games.zcjz_java.entity.config.GameStateConfigExtendZcjzBaseGame;
import com.lx.gs.math.games.zcjz_java.module.gameStateHlr.GameStateHlrZcjzBaseGame;

// 遊戲狀態處理者工廠
public class GameStateHlrFactory {

    // 創建遊戲狀態
    public IGameStateHlr create(int conditionId, int gameStateId, GameStateConfig gameStateConfig, ILogicSlot logicSlot, ITableUtilSlot tableUtilSlot) {
        switch (gameStateConfig.getGameStateType()) {
            case PXKY_BASEGAME: return new GameStateHlrPxkyBaseGame(
                                    conditionId,
                                    gameStateId,
                                    tableUtilSlot,
                                    logicSlot,
                                    new FiniteAwardPoolHlrFactory().create(
                                            ((GameStateConfigExtendPxkyBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                                    gameStateConfig);
            case DYDB_BASEGAME: return new GameStateHlrDydbBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case DYDB_FREEGAME: return new GameStateHlrDydbFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case SDZW_BASEGAME: return new GameStateHlrSdzwBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case SDZW_FREEGAME: return new GameStateHlrSdzwFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case WL_BASEGAME: return new GameStateHlrWlBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case WL_FREEGAME: return new GameStateHlrWlFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case MJWS_BASEGAME: return new GameStateHlrMjwsBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case MJWS_FREEGAME: return new GameStateHlrMjwsFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case BSZX_BASEGAME: return new GameStateHlrBszxBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case BSZX_FREEGAME: return new GameStateHlrBszxFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case CCC_BASEGAME: return new GameStateHlrCccBaseGame(
                    conditionId,
                    gameStateId,
                    tableUtilSlot,
                    logicSlot,
                    new FiniteAwardPoolHlrFactory().create(
                            ((GameStateConfigExtendCccBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                    gameStateConfig);
            case SBXC_BASEGAME: return new GameStateHlrSbxcBaseGame(
                    conditionId,
                    gameStateId,
                    tableUtilSlot,
                    logicSlot,
                    new FiniteAwardPoolHlrFactory().create(
                            ((GameStateConfigExtendSbxcBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                    gameStateConfig);
            case SN_BASEGAME: return new GameStateHlrSnBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case SN_FREEGAME: return new GameStateHlrSnFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case SWZS_BASEGAME: return new GameStateHlrSwzsBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case SWZS_FREEGAME: return new GameStateHlrSwzsFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case LMJJC_BASEGAME: return new GameStateHlrLmjjcBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case LMJJC_FREEGAME: return new GameStateHlrLmjjcFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case DGRY_BASEGAME: return new GameStateHlrDgryBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case DGRY_FREEGAME: return new GameStateHlrDgryFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case DGRY_BONUSGAME: return new GameStateHlrDgryBonusGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case LLL_BASEGAME: return new GameStateHlrLllBaseGame(
                    conditionId,
                    gameStateId,
                    tableUtilSlot,
                    logicSlot,
                    new FiniteAwardPoolHlrFactory().create(
                            ((GameStateConfigExtendLllBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                    gameStateConfig);
            case XZCQ_BASEGAME: return new GameStateHlrXzcqBaseGame(
                    conditionId,
                    gameStateId,
                    tableUtilSlot,
                    logicSlot,
                    new FiniteAwardPoolHlrFactory().create(
                            ((GameStateConfigExtendXzcqBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                    gameStateConfig);
            case HJXB_BASEGAME: return new GameStateHlrHjxbBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case HJXB_FREEGAME: return new GameStateHlrHjxbFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case OLLDBZ_BASEGAME: return new GameStateHlrOlldbzBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case OLLDBZ_FREEGAME: return new GameStateHlrOlldbzFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case CJWP_BASEGAME: return new GameStateHlrCjwpBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case CJWP_FREEGAME: return new GameStateHlrCjwpFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case XJTB_BASEGAME: return new GameStateHlrXjtbBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case XJTB_FREEGAME: return new GameStateHlrXjtbFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case ZCJZ_BASEGAME: return new GameStateHlrZcjzBaseGame(
                    conditionId,
                    gameStateId,
                    tableUtilSlot,
                    logicSlot,
                    new FiniteAwardPoolHlrFactory().create(
                            ((GameStateConfigExtendZcjzBaseGame)gameStateConfig.getGameStateConfigExtend()).getFiniteAwardPoolConfig(), tableUtilSlot),
                    gameStateConfig);
            case MODEL_HJ_BASEGAME: return new GameStateHlrModelHjBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case MODEL_HJ_FREEGAME: return new GameStateHlrModelHjFreeGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            case DEVELOP_HJ_BASEGAME: return new GameStateHlrDevelopHjBaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
            default: return new GameStateHlrDefault(conditionId, gameStateId, tableUtilSlot, gameStateConfig);
        }
    }
}

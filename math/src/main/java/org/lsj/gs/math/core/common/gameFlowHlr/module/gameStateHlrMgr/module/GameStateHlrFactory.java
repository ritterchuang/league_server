package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.module.FiniteAwardPoolHlrFactory;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.games._develop_hj_java.module.gameStateHlr.GameStateHlrDevelopHjBaseGame;
import org.lsj.gs.math.games._model_hj_java.module.gameStateHlr.GameStateHlrModelHjBaseGame;
import org.lsj.gs.math.games._model_hj_java.module.gameStateHlr.GameStateHlrModelHjFreeGame;
import org.lsj.gs.math.games.bszx_java.module.gameStateHlr.GameStateHlrBszxBaseGame;
import org.lsj.gs.math.games.bszx_java.module.gameStateHlr.GameStateHlrBszxFreeGame;
import org.lsj.gs.math.games.ccc_java.entity.config.GameStateConfigExtendCccBaseGame;
import org.lsj.gs.math.games.ccc_java.module.gameStateHlr.GameStateHlrCccBaseGame;
import org.lsj.gs.math.games.cjwp_java.module.gameStateHlr.GameStateHlrCjwpBaseGame;
import org.lsj.gs.math.games.cjwp_java.module.gameStateHlr.GameStateHlrCjwpFreeGame;
import org.lsj.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryBaseGame;
import org.lsj.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryBonusGame;
import org.lsj.gs.math.games.dgry_java.module.gameStateHlr.GameStateHlrDgryFreeGame;
import org.lsj.gs.math.games.dydb_java.module.gameStateHlr.GameStateHlrDydbBaseGame;
import org.lsj.gs.math.games.dydb_java.module.gameStateHlr.GameStateHlrDydbFreeGame;
import org.lsj.gs.math.games.hjxb_java.module.gameStateHlr.GameStateHlrHjxbBaseGame;
import org.lsj.gs.math.games.hjxb_java.module.gameStateHlr.GameStateHlrHjxbFreeGame;
import org.lsj.gs.math.games.lll_java.entity.config.GameStateConfigExtendLllBaseGame;
import org.lsj.gs.math.games.lll_java.module.gameStateHlr.GameStateHlrLllBaseGame;
import org.lsj.gs.math.games.lmjjc_java.module.gameStateHlr.GameStateHlrLmjjcBaseGame;
import org.lsj.gs.math.games.lmjjc_java.module.gameStateHlr.GameStateHlrLmjjcFreeGame;
import org.lsj.gs.math.games.lucky777.module.gameStateHlr.GameStateHlrLucky777BaseGame;
import org.lsj.gs.math.games.mjws_java.module.gameStateHlr.GameStateHlrMjwsBaseGame;
import org.lsj.gs.math.games.mjws_java.module.gameStateHlr.GameStateHlrMjwsFreeGame;
import org.lsj.gs.math.games.olldbz_java.module.gameStateHlr.GameStateHlrOlldbzBaseGame;
import org.lsj.gs.math.games.olldbz_java.module.gameStateHlr.GameStateHlrOlldbzFreeGame;
import org.lsj.gs.math.games.pxky_java.enity.config.GameStateConfigExtendPxkyBaseGame;
import org.lsj.gs.math.games.pxky_java.module.gameStateHlr.GameStateHlrPxkyBaseGame;
import org.lsj.gs.math.games.sbxc_java.entity.config.GameStateConfigExtendSbxcBaseGame;
import org.lsj.gs.math.games.sbxc_java.module.gameStateHlr.GameStateHlrSbxcBaseGame;
import org.lsj.gs.math.games.sdzw_java.module.gameStateHlr.GameStateHlrSdzwBaseGame;
import org.lsj.gs.math.games.sdzw_java.module.gameStateHlr.GameStateHlrSdzwFreeGame;
import org.lsj.gs.math.games.sn_java.module.gameStateHlr.GameStateHlrSnBaseGame;
import org.lsj.gs.math.games.sn_java.module.gameStateHlr.GameStateHlrSnFreeGame;
import org.lsj.gs.math.games.swzs_java.module.gameStateHlr.GameStateHlrSwzsBaseGame;
import org.lsj.gs.math.games.swzs_java.module.gameStateHlr.GameStateHlrSwzsFreeGame;
import org.lsj.gs.math.games.wl_java.module.gameStateHlr.GameStateHlrWlBaseGame;
import org.lsj.gs.math.games.wl_java.module.gameStateHlr.GameStateHlrWlFreeGame;
import org.lsj.gs.math.games.xjtb_java.module.gameStateHlr.GameStateHlrXjtbBaseGame;
import org.lsj.gs.math.games.xjtb_java.module.gameStateHlr.GameStateHlrXjtbFreeGame;
import org.lsj.gs.math.games.xzcq_java.entity.config.GameStateConfigExtendXzcqBaseGame;
import org.lsj.gs.math.games.xzcq_java.module.gameStateHlr.GameStateHlrXzcqBaseGame;
import org.lsj.gs.math.games.zcjz_java.entity.config.GameStateConfigExtendZcjzBaseGame;
import org.lsj.gs.math.games.zcjz_java.module.gameStateHlr.GameStateHlrZcjzBaseGame;

// 遊戲狀態處理者工廠
public class GameStateHlrFactory {

    // 創建遊戲狀態
    public IGameStateHlr create(int conditionId, int gameStateId, GameStateConfig gameStateConfig, ILogicSlot logicSlot, ITableUtilSlot tableUtilSlot) {
        switch (gameStateConfig.getGameStateType()) {
            case LUCKY777_BASEGAME: return new GameStateHlrLucky777BaseGame(conditionId, gameStateId, tableUtilSlot, logicSlot, gameStateConfig);
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

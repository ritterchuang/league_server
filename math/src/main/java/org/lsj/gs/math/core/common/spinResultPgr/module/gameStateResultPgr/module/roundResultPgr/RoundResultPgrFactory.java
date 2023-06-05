package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultCascadePgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultNormalPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.*;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundNormalResultPgr.*;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 局結果包裝者工廠
public class RoundResultPgrFactory {

    // 創建客端局結果包裝者 TODO 區分 normal、cascade
    public IRoundResultPgr create(ConstMathSlot.RoundType roundType, ClientRoundResultPgrConfig clientRoundResultPgrConfig, ITableUtil tableUtil) {
        switch (roundType) {
            case CASCADE: return this.createRoundCascadeResultPgr(clientRoundResultPgrConfig, tableUtil);
            default: return this.createRoundNormalResultPgr(clientRoundResultPgrConfig, tableUtil);
        }
    }

    // 產出局包裝者 消除
    private IRoundResultPgr createRoundCascadeResultPgr(ClientRoundResultPgrConfig clientRoundResultPgrConfig, ITableUtil tableUtil) {
        ClientRoundResultCascadePgrConfig config = (ClientRoundResultCascadePgrConfig) clientRoundResultPgrConfig;
        switch (config.getRoundCascadeGameType()) {
            case MJWS_BASEGAME: return new RoundCascadeResultPgrMjwsBaseGame(config, tableUtil);
            case MJWS_FREEGAME: return new RoundCascadeResultPgrMjwsFreeGame(config, tableUtil);
            case LMJJC_BASEGAME: return new RoundCascadeResultPgrLmjjcBaseGame(config, tableUtil);
            case LMJJC_FREEGAME: return new RoundCascadeResultPgrLmjjcFreeGame(config, tableUtil);
            case DGRY_BASEGAME: return new RoundCascadeResultPgrDgryBaseGame(config, tableUtil);
            case DGRY_FREEGAME: return new RoundCascadeResultPgrDgryFreeGame(config, tableUtil);
            case DGRY_BONUSGAME: return new RoundCascadeResultPgrDgryBonusGame(config, tableUtil);
            case CJWP_BASEGAME: return new RoundCascadeResultPgrCjwpBaseGame(config, tableUtil);
            case CJWP_FREEGAME: return new RoundCascadeResultPgrCjwpFreeGame(config, tableUtil);
            default: return new RoundCascadeResultPgrDevHj(config, tableUtil);
        }
    }

    // 產出局包裝者 一般
    private IRoundResultPgr createRoundNormalResultPgr(ClientRoundResultPgrConfig clientRoundResultPgrConfig, ITableUtil tableUtil) {
        ClientRoundResultNormalPgrConfig config = (ClientRoundResultNormalPgrConfig) clientRoundResultPgrConfig;
        switch (config.getRoundNormalGameType()) {
            case LUCKY777_BASEGAME: return new RoundResultNormalPgrLucky777BaseGame(config, tableUtil);
            case PXKY_BASEGAME: return new RoundResultNormalPgrPxkyBaseGame(config, tableUtil);
            case DYDB_BASEGAME: return new RoundResultNormalPgrDydbBaseGame(config, tableUtil);
            case DYDB_FREEGAME: return new RoundResultNormalPgrDydbFreeGame(config, tableUtil);
            case SDZW_BASEGAME: return new RoundResultNormalPgrSdzwBaseGame(config, tableUtil);
            case SDZW_FREEGAME: return new RoundResultNormalPgrSdzwFreeGame(config, tableUtil);
            case WL_BASEGAME: return new RoundResultNormalPgrWlBaseGame(config, tableUtil);
            case WL_FREEGAME: return new RoundResultNormalPgrWlFreeGame(config, tableUtil);
            case BSZX_BASEGAME: return new RoundResultNormalPgrBszxBaseGame(config, tableUtil);
            case BSZX_FREEGAME: return new RoundResultNormalPgrBszxFreeGame(config, tableUtil);
            case CCC_BASEGAME: return new RoundResultNormalPgrCccBaseGame(config, tableUtil);
            case SBXC_BASEGAME: return new RoundResultNormalPgrSbxcBaseGame(config, tableUtil);
            case SN_BASEGAME: return new RoundResultNormalPgrSnBaseGame(config, tableUtil);
            case SN_FREEGAME: return new RoundResultNormalPgrSnFreeGame(config, tableUtil);
            case SWZS_BASEGAME: return new RoundResultNormalPgrSwzsBaseGame(config, tableUtil);
            case SWZS_FREEGAME: return new RoundResultNormalPgrSwzsFreeGame(config, tableUtil);
            case LLL_BASEGAME: return new RoundResultNormalPgrLllBaseGame(config, tableUtil);
            case XZCQ_BASEGAME: return new RoundResultNormalPgrXzcqBaseGame(config, tableUtil);
            case HJXB_BASEGAME: return new RoundResultNormalPgrHjxbBaseGame(config, tableUtil);
            case HJXB_FREEGAME: return new RoundResultNormalPgrHjxbFreeGame(config, tableUtil);
            case OLLDBZ_BASEGAME: return new RoundResultNormalPgrOlldbzBaseGame(config, tableUtil);
            case OLLDBZ_FREEGAME: return new RoundResultNormalPgrOlldbzFreeGame(config, tableUtil);
            case XJTB_BASEGAME: return new RoundResultNormalPgrXjtbBaseGame(config, tableUtil);
            case XJTB_FREEGAME: return new RoundResultNormalPgrXjtbFreeGame(config, tableUtil);
            case ZCJZ_BASEGAME: return new RoundResultNormalPgrZcjzBaseGame(config, tableUtil);
            default: return new RoundResultNormalPgrDefault(config, tableUtil);
        }
    }
}

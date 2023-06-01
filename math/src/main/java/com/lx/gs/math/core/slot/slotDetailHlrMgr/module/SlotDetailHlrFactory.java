package com.lx.gs.math.core.slot.slotDetailHlrMgr.module;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfig;
import com.lx.gs.math.games._develop_hj_java.module.logic.slotDetailCtr.SlotDetailHlrDevelopHjBaseGame;
import com.lx.gs.math.games._model_hj_java.module.logic.slotDetailCtr.SlotDetailHlrModelHjBaseGame;
import com.lx.gs.math.games._model_hj_java.module.logic.slotDetailCtr.SlotDetailHlrModelHjFreeGame;
import com.lx.gs.math.games.bszx_java.module.logic.slotDetailCtr.SlotDetailHlrBszxBaseGame;
import com.lx.gs.math.games.bszx_java.module.logic.slotDetailCtr.SlotDetailHlrBszxFreeGame;
import com.lx.gs.math.games.ccc_java.module.logic.slotDetailCtr.SlotDetailHlrCcc;
import com.lx.gs.math.games.cjwp_java.module.logic.slotDetailCtr.SlotDetailHlrCjwpBaseGame;
import com.lx.gs.math.games.cjwp_java.module.logic.slotDetailCtr.SlotDetailHlrCjwpFreeGame;
import com.lx.gs.math.games.dgry_java.module.logic.slotDetailCtr.SlotDetailHlrDgryBaseGame;
import com.lx.gs.math.games.dgry_java.module.logic.slotDetailCtr.SlotDetailHlrDgryBonusGame;
import com.lx.gs.math.games.dgry_java.module.logic.slotDetailCtr.SlotDetailHlrDgryFreeGame;
import com.lx.gs.math.games.dydb_java.module.logic.slotDetailCtr.SlotDetailHlrDydbBaseGame;
import com.lx.gs.math.games.dydb_java.module.logic.slotDetailCtr.SlotDetailHlrDydbFreeGame;
import com.lx.gs.math.games.hjxb_java.module.logic.slotDetailCtr.SlotDetailHlrHjxbBaseGame;
import com.lx.gs.math.games.hjxb_java.module.logic.slotDetailCtr.SlotDetailHlrHjxbFreeGame;
import com.lx.gs.math.games.lll_java.module.logic.slotDetailCtr.SlotDetailHlrLll;
import com.lx.gs.math.games.lmjjc_java.module.logic.slotDetailCtr.SlotDetailHlrLmjjcBaseGame;
import com.lx.gs.math.games.lmjjc_java.module.logic.slotDetailCtr.SlotDetailHlrLmjjcFreeGame;
import com.lx.gs.math.games.mjws_java.module.logic.slotDetailCtr.SlotDetailHlrMjwsBaseGame;
import com.lx.gs.math.games.mjws_java.module.logic.slotDetailCtr.SlotDetailHlrMjwsFreeGame;
import com.lx.gs.math.games.olldbz_java.module.logic.slotDetailCtr.SlotDetailHlrOlldbzBaseGame;
import com.lx.gs.math.games.olldbz_java.module.logic.slotDetailCtr.SlotDetailHlrOlldbzFreeGame;
import com.lx.gs.math.games.pxky_java.module.logic.slotDetailCtr.SlotDetailHlrPxky;
import com.lx.gs.math.games.sbxc_java.module.logic.slotDetailCtr.SlotDetailHlrSbxc;
import com.lx.gs.math.games.sdzw_java.module.logic.slotDetailCtr.SlotDetailHlrSdzwBaseGame;
import com.lx.gs.math.games.sdzw_java.module.logic.slotDetailCtr.SlotDetailHlrSdzwFreeGame;
import com.lx.gs.math.games.sn_java.module.logic.slotDetailCtr.SlotDetailHlrSnBaseGame;
import com.lx.gs.math.games.sn_java.module.logic.slotDetailCtr.SlotDetailHlrSnFreeGame;
import com.lx.gs.math.games.swzs_java.module.logic.slotDetailCtr.SlotDetailHlrSwzsBaseGame;
import com.lx.gs.math.games.swzs_java.module.logic.slotDetailCtr.SlotDetailHlrSwzsFreeGame;
import com.lx.gs.math.games.wl_java.module.logic.slotDetailCtr.SlotDetailHlrWlBaseGame;
import com.lx.gs.math.games.wl_java.module.logic.slotDetailCtr.SlotDetailHlrWlFreeGame;
import com.lx.gs.math.games.xjtb_java.module.logic.slotDetailCtr.SlotDetailHlrXjtbBaseGame;
import com.lx.gs.math.games.xjtb_java.module.logic.slotDetailCtr.SlotDetailHlrXjtbFreeGame;
import com.lx.gs.math.games.xzcq_java.module.logic.slotDetailCtr.SlotDetailHlrXzcq;
import com.lx.gs.math.games.zcjz_java.module.logic.slotDetailCtr.SlotDetailHlrZcjz;

// 虎機詳細記錄計算者工程
public class SlotDetailHlrFactory {

    // 創建虎機詳細記錄計算者
    public ISlotDetailHlr create(SlotDetailHlrConfig slotDetailHlrConfig, ITableUtil tableUtil) {
        switch (slotDetailHlrConfig.getSlotDetailType()) {
            case PXKY_BASEGAME: return new SlotDetailHlrPxky(slotDetailHlrConfig, tableUtil);
            case DYDB_BASEGAME: return new SlotDetailHlrDydbBaseGame(slotDetailHlrConfig, tableUtil);
            case DYDB_FREEGAME: return new SlotDetailHlrDydbFreeGame(slotDetailHlrConfig, tableUtil);
            case SDZW_BASEGAME: return new SlotDetailHlrSdzwBaseGame(slotDetailHlrConfig, tableUtil);
            case SDZW_FREEGAME: return new SlotDetailHlrSdzwFreeGame(slotDetailHlrConfig, tableUtil);
            case WL_BASEGAME: return new SlotDetailHlrWlBaseGame(slotDetailHlrConfig, tableUtil);
            case WL_FREEGAME: return new SlotDetailHlrWlFreeGame(slotDetailHlrConfig, tableUtil);
            case MJWS_BASEGAME: return new SlotDetailHlrMjwsBaseGame(slotDetailHlrConfig, tableUtil);
            case MJWS_FREEGAME: return new SlotDetailHlrMjwsFreeGame(slotDetailHlrConfig, tableUtil);
            case BSZX_BASEGAME: return new SlotDetailHlrBszxBaseGame(slotDetailHlrConfig, tableUtil);
            case BSZX_FREEGAME: return new SlotDetailHlrBszxFreeGame(slotDetailHlrConfig, tableUtil);
            case CCC_BASEGAME: return new SlotDetailHlrCcc(slotDetailHlrConfig, tableUtil);
            case SBXC_BASEGAME: return new SlotDetailHlrSbxc(slotDetailHlrConfig, tableUtil);
            case SN_BASEGAME: return new SlotDetailHlrSnBaseGame(slotDetailHlrConfig, tableUtil);
            case SN_FREEGAME: return new SlotDetailHlrSnFreeGame(slotDetailHlrConfig, tableUtil);
            case SWZS_BASEGAME: return new SlotDetailHlrSwzsBaseGame(slotDetailHlrConfig, tableUtil);
            case SWZS_FREEGAME: return new SlotDetailHlrSwzsFreeGame(slotDetailHlrConfig, tableUtil);
            case LMJJC_BASEGAME: return new SlotDetailHlrLmjjcBaseGame(slotDetailHlrConfig, tableUtil);
            case LMJJC_FREEGAME: return new SlotDetailHlrLmjjcFreeGame(slotDetailHlrConfig, tableUtil);
            case DGRY_BASEGAME: return new SlotDetailHlrDgryBaseGame(slotDetailHlrConfig, tableUtil);
            case DGRY_FREEGAME: return new SlotDetailHlrDgryFreeGame(slotDetailHlrConfig, tableUtil);
            case DGRY_BONUSGAME: return new SlotDetailHlrDgryBonusGame(slotDetailHlrConfig, tableUtil);
            case LLL_BASEGAME: return new SlotDetailHlrLll(slotDetailHlrConfig, tableUtil);
            case XZCQ_BASEGAME: return new SlotDetailHlrXzcq(slotDetailHlrConfig, tableUtil);
            case HJXB_BASEGAME: return new SlotDetailHlrHjxbBaseGame(slotDetailHlrConfig, tableUtil);
            case HJXB_FREEGAME: return new SlotDetailHlrHjxbFreeGame(slotDetailHlrConfig, tableUtil);
            case OLLDBZ_BASEGAME: return new SlotDetailHlrOlldbzBaseGame(slotDetailHlrConfig, tableUtil);
            case OLLDBZ_FREEGAME: return new SlotDetailHlrOlldbzFreeGame(slotDetailHlrConfig, tableUtil);
            case CJWP_BASEGAME: return new SlotDetailHlrCjwpBaseGame(slotDetailHlrConfig, tableUtil);
            case CJWP_FREEGAME: return new SlotDetailHlrCjwpFreeGame(slotDetailHlrConfig, tableUtil);
            case XJTB_BASEGAME: return new SlotDetailHlrXjtbBaseGame(slotDetailHlrConfig, tableUtil);
            case XJTB_FREEGAME: return new SlotDetailHlrXjtbFreeGame(slotDetailHlrConfig, tableUtil);
            case ZCJZ_BASEGAME: return new SlotDetailHlrZcjz(slotDetailHlrConfig, tableUtil);
            case MODEL_HJ_BASEGAME: return new SlotDetailHlrModelHjBaseGame(slotDetailHlrConfig, tableUtil);
            case MODEL_HJ_FREEGAME: return new SlotDetailHlrModelHjFreeGame(slotDetailHlrConfig, tableUtil);
            case DEVELOP_HJ_BASEGAME: return new SlotDetailHlrDevelopHjBaseGame();
            default: return new SlotDetailHlrInvalid();
        }
    }
}

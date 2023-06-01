package com.lx.gs.math.core.slot.moduleConfigMgr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigCascade;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.RoundConfigNormal;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrConfigExtend;
import com.lx.gs.math.games._develop_hj_java.enity.config.SlotDetailHlrConfigExtendDevelopHjBaseGame;
import com.lx.gs.math.games._model_hj_java.enity.ConstModelHjJava;
import com.lx.gs.math.games._model_hj_java.enity.config.SlotDetailHlrConfigExtendModelHjBaseGame;
import com.lx.gs.math.games._model_hj_java.enity.config.SlotDetailHlrConfigExtendModelHjFreeGame;
import com.lx.gs.math.games.bszx_java.enity.ConstBszxJava;
import com.lx.gs.math.games.bszx_java.enity.config.SlotDetailHlrConfigExtendBszxBaseGame;
import com.lx.gs.math.games.bszx_java.enity.config.SlotDetailHlrConfigExtendBszxFreeGame;
import com.lx.gs.math.games.ccc_java.entity.ConstCccJava;
import com.lx.gs.math.games.ccc_java.entity.config.SlotDetailHlrConfigExtendCcc;
import com.lx.gs.math.games.cjwp_java.entity.ConstCjwpJava;
import com.lx.gs.math.games.cjwp_java.entity.config.SlotDetailHlrConfigExtendCjwpBaseGame;
import com.lx.gs.math.games.cjwp_java.entity.config.SlotDetailHlrConfigExtendCjwpFreeGame;
import com.lx.gs.math.games.dgry_java.entity.ConstDgryJava;
import com.lx.gs.math.games.dgry_java.entity.config.SlotDetailHlrConfigExtendDgryBaseGame;
import com.lx.gs.math.games.dgry_java.entity.config.SlotDetailHlrConfigExtendDgryBonusGame;
import com.lx.gs.math.games.dgry_java.entity.config.SlotDetailHlrConfigExtendDgryFreeGame;
import com.lx.gs.math.games.dydb_java.enity.ConstDydbJava;
import com.lx.gs.math.games.dydb_java.enity.config.SlotDetailHlrConfigExtendDydbBaseGame;
import com.lx.gs.math.games.dydb_java.enity.config.SlotDetailHlrConfigExtendDydbFreeGame;
import com.lx.gs.math.games.hjxb_java.entity.ConstHjxbJava;
import com.lx.gs.math.games.hjxb_java.entity.config.SlotDetailHlrConfigExtendHjxbBaseGame;
import com.lx.gs.math.games.hjxb_java.entity.config.SlotDetailHlrConfigExtendHjxbFreeGame;
import com.lx.gs.math.games.lll_java.entity.ConstLllJava;
import com.lx.gs.math.games.lll_java.entity.config.SlotDetailHlrConfigExtendLll;
import com.lx.gs.math.games.lmjjc_java.entity.ConstLmjjcJava;
import com.lx.gs.math.games.lmjjc_java.entity.config.SlotDetailHlrConfigExtendLmjjcBaseGame;
import com.lx.gs.math.games.lmjjc_java.entity.config.SlotDetailHlrConfigExtendLmjjcFreeGame;
import com.lx.gs.math.games.mjws_java.enity.ConstMjwsJava;
import com.lx.gs.math.games.mjws_java.enity.config.SlotDetailHlrConfigExtendMjwsBaseGame;
import com.lx.gs.math.games.mjws_java.enity.config.SlotDetailHlrConfigExtendMjwsFreeGame;
import com.lx.gs.math.games.olldbz_java.entity.ConstOlldbzJava;
import com.lx.gs.math.games.olldbz_java.entity.config.SlotDetailHlrConfigExtendOlldbzBaseGame;
import com.lx.gs.math.games.olldbz_java.entity.config.SlotDetailHlrConfigExtendOlldbzFreeGame;
import com.lx.gs.math.games.pxky_java.enity.ConstPxkyJava;
import com.lx.gs.math.games.pxky_java.enity.config.SlotDetailHlrConfigExtendPxky;
import com.lx.gs.math.games.sbxc_java.entity.ConstSbxcJava;
import com.lx.gs.math.games.sbxc_java.entity.config.SlotDetailHlrConfigExtendSbxc;
import com.lx.gs.math.games.sdzw_java.enity.ConstSdzwJava;
import com.lx.gs.math.games.sdzw_java.enity.config.SlotDetailHlrConfigExtendSdzwBaseGame;
import com.lx.gs.math.games.sdzw_java.enity.config.SlotDetailHlrConfigExtendSdzwFreeGame;
import com.lx.gs.math.games.sn_java.entity.ConstSnJava;
import com.lx.gs.math.games.sn_java.entity.config.SlotDetailHlrConfigExtendSnBaseGame;
import com.lx.gs.math.games.sn_java.entity.config.SlotDetailHlrConfigExtendSnFreeGame;
import com.lx.gs.math.games.swzs_java.entity.ConstSwzsJava;
import com.lx.gs.math.games.swzs_java.entity.config.SlotDetailHlrConfigExtendSwzsBaseGame;
import com.lx.gs.math.games.swzs_java.entity.config.SlotDetailHlrConfigExtendSwzsFreeGame;
import com.lx.gs.math.games.wl_java.enity.ConstWlJava;
import com.lx.gs.math.games.wl_java.enity.config.SlotDetailHlrConfigExtendWlBaseGame;
import com.lx.gs.math.games.wl_java.enity.config.SlotDetailHlrConfigExtendWlFreeGame;
import com.lx.gs.math.games.xjtb_java.entity.ConstXjtbJava;
import com.lx.gs.math.games.xjtb_java.entity.config.SlotDetailHlrConfigExtendXjtbBaseGame;
import com.lx.gs.math.games.xjtb_java.entity.config.SlotDetailHlrConfigExtendXjtbFreeGame;
import com.lx.gs.math.games.xzcq_java.entity.ConstXzcqJava;
import com.lx.gs.math.games.xzcq_java.entity.config.SlotDetailHlrConfigExtendXzcq;
import com.lx.gs.math.games.zcjz_java.entity.ConstZjczJava;
import com.lx.gs.math.games.zcjz_java.entity.config.SlotDetailHlrConfigExtendZcjz;

// 虎機詳細記錄處理者額外設定工具包
public class SlotDetailHlrConfigExtendUtil {

    // 計算詳細記錄計算者設定檔 TODO DEFAULT
    public SlotDetailHlrConfigExtend calculateSlotDetailCtrConfigExtend (ConstMathSlot.SlotDetailType slotDetailType, GameStateConfig gameStateConfig) {
        switch (slotDetailType) {
            case DYDB_BASEGAME: return new SlotDetailHlrConfigExtendDydbBaseGame(
                    ConstDydbJava.SymbolEnumDydbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case DYDB_FREEGAME: return new SlotDetailHlrConfigExtendDydbFreeGame(
                    ConstDydbJava.SymbolEnumDydbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SDZW_BASEGAME: return new SlotDetailHlrConfigExtendSdzwBaseGame(
                    ConstSdzwJava.SymbolEnumSdzwJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SDZW_FREEGAME: return new SlotDetailHlrConfigExtendSdzwFreeGame(
                    ConstSdzwJava.SymbolEnumSdzwJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case WL_BASEGAME: return new SlotDetailHlrConfigExtendWlBaseGame(
                    ConstWlJava.SymbolEnumWlJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case WL_FREEGAME: return new SlotDetailHlrConfigExtendWlFreeGame(
                    ConstWlJava.SymbolEnumWlJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case BSZX_BASEGAME: return new SlotDetailHlrConfigExtendBszxBaseGame(
                    ConstBszxJava.SymbolEnumBszxJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case BSZX_FREEGAME: return new SlotDetailHlrConfigExtendBszxFreeGame(
                    ConstBszxJava.SymbolEnumBszxJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case CCC_BASEGAME: return new SlotDetailHlrConfigExtendCcc(
                    ConstCccJava.SymbolEnumCccJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SBXC_BASEGAME: return new SlotDetailHlrConfigExtendSbxc(
                    ConstSbxcJava.SymbolEnumSbxcJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SN_BASEGAME: return new SlotDetailHlrConfigExtendSnBaseGame(
                    ConstSnJava.SymbolEnumSnJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SN_FREEGAME: return new SlotDetailHlrConfigExtendSnFreeGame(
                    ConstSnJava.SymbolEnumSnJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SWZS_BASEGAME: return new SlotDetailHlrConfigExtendSwzsBaseGame(
                    ConstSwzsJava.SymbolEnumSwzsJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case SWZS_FREEGAME: return new SlotDetailHlrConfigExtendSwzsFreeGame(
                    ConstSwzsJava.SymbolEnumSwzsJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case LLL_BASEGAME: return new SlotDetailHlrConfigExtendLll(
                    ConstLllJava.SymbolEnumLllJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case XZCQ_BASEGAME: return new SlotDetailHlrConfigExtendXzcq(
                    ConstXzcqJava.SymbolEnumXzcqJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case HJXB_BASEGAME: return new SlotDetailHlrConfigExtendHjxbBaseGame(
                    ConstHjxbJava.SymbolEnumHjxbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case HJXB_FREEGAME: return new SlotDetailHlrConfigExtendHjxbFreeGame(
                    ConstHjxbJava.SymbolEnumHjxbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case OLLDBZ_BASEGAME: return new SlotDetailHlrConfigExtendOlldbzBaseGame(
                    ConstOlldbzJava.SymbolEnumOlldbzJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case OLLDBZ_FREEGAME: return new SlotDetailHlrConfigExtendOlldbzFreeGame(
                    ConstOlldbzJava.SymbolEnumOlldbzJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case XJTB_BASEGAME: return new SlotDetailHlrConfigExtendXjtbBaseGame(
                    ConstXjtbJava.SymbolEnumXjtbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case XJTB_FREEGAME: return new SlotDetailHlrConfigExtendXjtbFreeGame(
                    ConstXjtbJava.SymbolEnumXjtbJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case ZCJZ_BASEGAME: return new SlotDetailHlrConfigExtendZcjz(
                    ConstZjczJava.SymbolEnumZcjzJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case MODEL_HJ_BASEGAME: return new SlotDetailHlrConfigExtendModelHjBaseGame(
                    ConstModelHjJava.SymbolEnumModelHjJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case MODEL_HJ_FREEGAME: return new SlotDetailHlrConfigExtendModelHjFreeGame(
                    ConstModelHjJava.SymbolEnumModelHjJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            // TODO
            case DEVELOP_HJ_BASEGAME: return new SlotDetailHlrConfigExtendDevelopHjBaseGame(
            );
            case MJWS_BASEGAME: return new SlotDetailHlrConfigExtendMjwsBaseGame(
                    ConstMjwsJava.SymbolEnumMjwsJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case MJWS_FREEGAME: return new SlotDetailHlrConfigExtendMjwsFreeGame(
                    ConstMjwsJava.SymbolEnumMjwsJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case LMJJC_BASEGAME: return new SlotDetailHlrConfigExtendLmjjcBaseGame(
                    ConstLmjjcJava.SymbolEnumLmjjcJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case LMJJC_FREEGAME: return new SlotDetailHlrConfigExtendLmjjcFreeGame(
                    ConstLmjjcJava.SymbolEnumLmjjcJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case DGRY_BASEGAME: return new SlotDetailHlrConfigExtendDgryBaseGame(
                    ConstDgryJava.SymbolEnumDgryJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case DGRY_FREEGAME: return new SlotDetailHlrConfigExtendDgryFreeGame(
                    ConstDgryJava.SymbolEnumDgryJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case DGRY_BONUSGAME: return new SlotDetailHlrConfigExtendDgryBonusGame(
                    ConstDgryJava.SymbolEnumDgryJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case CJWP_BASEGAME: return new SlotDetailHlrConfigExtendCjwpBaseGame(
                    ConstCjwpJava.SymbolEnumCjwpJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            case CJWP_FREEGAME: return new SlotDetailHlrConfigExtendCjwpFreeGame(
                    ConstCjwpJava.SymbolEnumCjwpJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigCascade)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
            default: return new SlotDetailHlrConfigExtendPxky(ConstPxkyJava.SymbolEnumPxkyJava.getSymbolIdStringMap(),
                    gameStateConfig.getRoundConfig().getFrameConfig(),
                    ((RoundConfigNormal)gameStateConfig.getRoundConfig()).getReelConfig(),
                    (gameStateConfig.getRoundConfig()).getSymbolConfig());
        }
    }
}

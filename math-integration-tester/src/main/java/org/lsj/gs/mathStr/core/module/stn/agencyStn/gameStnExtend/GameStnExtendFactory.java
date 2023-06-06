package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.enums.GameId;
import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;

// 客製遊戲統記者工廠
public class GameStnExtendFactory {
    private final AgencyStnConfig config; // 統計者設定

    public GameStnExtendFactory(AgencyStnConfig config) {
        this.config = config;
    }

    // 建構客製遊戲統記者
    public GameStnExtend createGameStnExtend(int gameIndex){
        switch(GameId.fromId(gameIndex)){
            case JCBY_JAVA:
            case CSBY_JAVA:
            case SLBY_JAVA:
            case RYCS_JAVA:
            case SZZB_JAVA:
            case _DEVELOP_BY_JAVA:
                return new GameStnExtendFish(this.config);
            case LUCKY777:
            case DYDB_JAVA:
            case SDZW_JAVA:
            case WL_JAVA:
            case BSZX_JAVA:
            case PXKY_JAVA:
            case CCC_JAVA:
            case SBXC_JAVA:
            case SN_JAVA:
            case SWZS_JAVA:
            case LLL_JAVA:
            case XZCQ_JAVA:
            case HJXB_JAVA:
            case OLLDBZ_JAVA:
            case XJTB_JAVA:
            case ZCJZ_JAVA:
                return new GameStnExtendSlotNormal(this.config);
            case MJWS_JAVA:
            case LMJJC_JAVA:
            case DGRY_JAVA:
            case CJWP_JAVA:
                return new GameStnExtendSlotCascade(this.config);

            default: return new GameStnExtendDefault(this.config);
        }
    }
}

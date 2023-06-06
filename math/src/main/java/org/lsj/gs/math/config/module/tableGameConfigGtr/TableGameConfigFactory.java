package org.lsj.gs.math.config.module.tableGameConfigGtr;

import org.lsj.enums.GameId;
import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfig;
import org.lsj.gs.math.config.module.tableGameConfigGtr.games.*;
import org.lsj.gs.user.IUser;
import org.lsj.gs.math.config.module.tableGameConfigGtr.games.*;

// 卡牌牌桌遊戲設定工廠
public class TableGameConfigFactory {
    // 產生牌桌遊戲設定
    public AbstractTableGameConfig createTableGameConfig(int gameId, IUser user){
        switch(GameId.fromId(gameId)){
            case LUCKY777: return new TableGameConfigGtrLucky777().generateTableGameConfig(user);

            case HHDZ_JAVA: return new TableGameConfigGtrHhdzJava().generateTableGameConfig(user);
            case BJL_JAVA: return new TableGameConfigGtrBjlJava().generateTableGameConfig(user);
            case BRNN_JAVA: return new TableGameConfigGtrBrnnJava().generateTableGameConfig(user);
            case LHD_JAVA: return new TableGameConfigGtrLhdJava().generateTableGameConfig(user);
            case ZJH_JAVA: return new TableGameConfigGtrZjhJava().generateTableGameConfig(user);
            case QZNN_JAVA: return new TableGameConfigGtrQznnJava().generateTableGameConfig(user);
            case EBG_JAVA: return new TableGameConfigGtrEbgJava().generateTableGameConfig(user);
            case QZNN_KSZ_JAVA: return new TableGameConfigGtrQznnKszJava().generateTableGameConfig(user);
            case SG_JAVA: return new TableGameConfigGtrSgJava().generateTableGameConfig(user);
            case QZPJ_JAVA: return new TableGameConfigGtrQzpjJava().generateTableGameConfig(user);
            case TBNN_JAVA: return new TableGameConfigGtrTbnnJava().generateTableGameConfig(user);
            case QZNN_K4Z_JAVA: return new TableGameConfigGtrQznnK4zJava().generateTableGameConfig(user);
            case LZNN_JAVA: return new TableGameConfigGtrLznnJava().generateTableGameConfig(user);
            case MYBJL_JAVA: return new TableGameConfigGtrMybjlJava().generateTableGameConfig(user);
            case CJNN_JAVA: return new TableGameConfigGtrCjnnJava().generateTableGameConfig(user);

            case JCBY_JAVA: return new TableGameConfigGtrJcbyJava().generateTableGameConfig(user);
            case CSBY_JAVA: return new TableGameConfigGtrCsbyJava().generateTableGameConfig(user);
            case SLBY_JAVA: return new TableGameConfigGtrSlbyJava().generateTableGameConfig(user);
            case SZZB_JAVA: return new TableGameConfigGtrSzzbJava().generateTableGameConfig(user);
            case RYCS_JAVA: return new TableGameConfigGtrRycsJava().generateTableGameConfig(user);
            case _MODEL_BY_JAVA: return new TableGameConfigGtrModelByJava().generateTableGameConfig(user);

            case PXKY_JAVA: return new TableGameConfigGtrPxkyJava().generateTableGameConfig(user);
            case DYDB_JAVA: return new TableGameConfigGtrDydbJava().generateTableGameConfig(user);
            case SDZW_JAVA: return new TableGameConfigGtrSdzwJava().generateTableGameConfig(user);
            case WL_JAVA: return new TableGameConfigGtrWlJava().generateTableGameConfig(user);
            case MJWS_JAVA: return new TableGameConfigGtrMjwsJava().generateTableGameConfig(user);
            case BSZX_JAVA: return new TableGameConfigGtrBszxJava().generateTableGameConfig(user);
            case CCC_JAVA: return new TableGameConfigGtrCccJava().generateTableGameConfig(user);
            case SBXC_JAVA: return new TableGameConfigGtrSbxcJava().generateTableGameConfig(user);
            case SN_JAVA: return new TableGameConfigGtrSnJava().generateTableGameConfig(user);
            case SWZS_JAVA: return new TableGameConfigGtrSwzsJava().generateTableGameConfig(user);
            case LMJJC_JAVA: return new TableGameConfigGtrLmjjcJava().generateTableGameConfig(user);
            case DGRY_JAVA: return new TableGameConfigGtrDgryJava().generateTableGameConfig(user);
            case LLL_JAVA: return new TableGameConfigGtrLllJava().generateTableGameConfig(user);
            case XZCQ_JAVA: return new TableGameConfigGtrXzcqJava().generateTableGameConfig(user);
            case OLLDBZ_JAVA: return new TableGameConfigGtrOlldbzJava().generateTableGameConfig(user);
            case HJXB_JAVA: return new TableGameConfigGtrHjxbJava().generateTableGameConfig(user);
            case CJWP_JAVA: return new TableGameConfigGtrCjwpJava().generateTableGameConfig(user);
            case XJTB_JAVA: return new TableGameConfigGtrXjtbJava().generateTableGameConfig(user);
            case ZCJZ_JAVA: return new TableGameConfigGtrZcjzJava().generateTableGameConfig(user);
            case _MODEL_HJ_JAVA: return new TableGameConfigGtrModelHjJava().generateTableGameConfig(user);

            case _DEVELOP_BY_JAVA: return new TableGameConfigGtrDevelopByJava().generateTableGameConfig(user);
            case _DEVELOP_HJ_JAVA: return new TableGameConfigGtrDevelopHjJava().generateTableGameConfig(user);
            default: return new TableGameConfigGtrDefault().generateTableGameConfig(user);
        }
    }
}

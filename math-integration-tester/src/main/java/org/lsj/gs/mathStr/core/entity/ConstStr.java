package org.lsj.gs.mathStr.core.entity;

import org.lsj.enums.GameId;

import java.util.HashMap;
import java.util.Map;

// 模擬器定義
public class ConstStr {
    // 遊戲類型對應表
    public final static Map<Integer, GameType> gameEnum2GameTypeMap = new HashMap<>(){{
        put(GameId.LUCKY777.getId(), GameType.SLOT);

        put(GameId.HHDZ_JAVA.getId(), GameType.BAIREN);
        put(GameId.BJL_JAVA.getId(), GameType.BAIREN);
        put(GameId.BRNN_JAVA.getId(), GameType.BAIREN);
        put(GameId.LHD_JAVA.getId(), GameType.BAIREN);
        put(GameId.ZJH_JAVA.getId(), GameType.CARD);
        put(GameId.QZNN_JAVA.getId(), GameType.CARD);
        put(GameId.EBG_JAVA.getId(), GameType.CARD);
        put(GameId.QZNN_KSZ_JAVA.getId(), GameType.CARD);
        put(GameId.SG_JAVA.getId(), GameType.CARD);
        put(GameId.QZPJ_JAVA.getId(), GameType.CARD);
        put(GameId.TBNN_JAVA.getId(), GameType.CARD);
        put(GameId.QZNN_K4Z_JAVA.getId(), GameType.CARD);
        put(GameId.LZNN_JAVA.getId(), GameType.CARD);
        put(GameId.MYBJL_JAVA.getId(), GameType.BAIREN);
        put(GameId.CJNN_JAVA.getId(), GameType.BAIREN);

        put(GameId.JCBY_JAVA.getId(), GameType.FISH);
        put(GameId.CSBY_JAVA.getId(), GameType.FISH);
        put(GameId.SLBY_JAVA.getId(), GameType.FISH);
        put(GameId.RYCS_JAVA.getId(), GameType.FISH);
        put(GameId.SZZB_JAVA.getId(), GameType.FISH);

        put(GameId.PXKY_JAVA.getId(), GameType.SLOT);
        put(GameId.DYDB_JAVA.getId(), GameType.SLOT);
        put(GameId.SDZW_JAVA.getId(), GameType.SLOT);
        put(GameId.WL_JAVA.getId(), GameType.SLOT);
        put(GameId.MJWS_JAVA.getId(), GameType.SLOT);
        put(GameId.BSZX_JAVA.getId(), GameType.SLOT);
        put(GameId.CCC_JAVA.getId(), GameType.SLOT);
        put(GameId.SBXC_JAVA.getId(), GameType.SLOT);
        put(GameId.SN_JAVA.getId(), GameType.SLOT);
        put(GameId.SWZS_JAVA.getId(), GameType.SLOT);
        put(GameId.LMJJC_JAVA.getId(), GameType.SLOT);
        put(GameId.DGRY_JAVA.getId(), GameType.SLOT);
        put(GameId.LLL_JAVA.getId(), GameType.SLOT);
        put(GameId.XZCQ_JAVA.getId(), GameType.SLOT);
        put(GameId.HJXB_JAVA.getId(), GameType.SLOT);
        put(GameId.OLLDBZ_JAVA.getId(), GameType.SLOT);
        put(GameId.CJWP_JAVA.getId(), GameType.SLOT);
        put(GameId.XJTB_JAVA.getId(), GameType.SLOT);
        put(GameId.ZCJZ_JAVA.getId(), GameType.SLOT);
        put(GameId._DEVELOP_BY_JAVA.getId(), GameType.FISH);
        put(GameId._MODEL_BY_JAVA.getId(), GameType.FISH);
        put(GameId._MODEL_HJ_JAVA.getId(), GameType.SLOT);
        put(GameId._DEVELOP_HJ_JAVA.getId(), GameType.SLOT);
    }};

    // 遊戲類型
    public enum GameType {
        INVALID(0), // 無效
        CARD(1), // 棋牌
        BAIREN(2), // 百人
        FISH(3), // 魚機
        SLOT(4); // 老虎機

        private final int code; // 編碼
        GameType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲模式卡牌類型
    public enum PlayTypeCard {
        NORMAL(0), // 一般模式
        TIME_OUT(1); // 超時模式

        private final int code; // 編碼
        PlayTypeCard(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲模式百人類型
    public enum PlayTypeBaiRen {
        NORMAL(0); // 一般模式

        private final int code; // 編碼
        PlayTypeBaiRen(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲模式魚機類型
    public enum PlayTypeFish {
        NORMAL(0), // 一般模式
        RANDOM_TARGET(1); // 隨機目標

        private final int code; // 編碼
        PlayTypeFish(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲模式老虎機類型
    public enum PlayTypeSlot {
        NORMAL(0); // 一般模式

        private final int code; // 編碼
        PlayTypeSlot(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲模式類型
    public enum PlayCostType {
        FIX_COST(0), // 一般模式
        RANDOM_COST(1); // 隨機成本

        private final int code; // 編碼
        PlayCostType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 水池統計類型
    public enum PoolStatisticType{
        NORMAL(0); // 一般模式

        private final int code; // 編碼
        PoolStatisticType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 模擬器錯誤碼
    public enum StrErrorCode {
        NO_SATISFY_SETTING(1); // 無滿足的設定

        private final int code; // 編碼
        StrErrorCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

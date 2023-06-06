package org.lsj.gs.math.core.slot;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 老虎機工具包定義 TODO 整理
public class ConstMathSlot {
    // 狀態定義
    public enum StateEnumSlot {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumSlot(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumSlot fromCode(int code) {
            final StateEnumSlot[] allEnumInstance = values();
            for (StateEnumSlot enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 邏輯類型定義
    public enum LogicType {
        INVALID(0), // 非法
        NORMAL(1), // 一般
        CASCADE(1); // 消除

        private final int code; // 編碼
        LogicType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    //* 玩法相關 *//
    // 押注類型定義
    public enum BetType {
        INVALID(0), // 非法
        NONE(1); // 無特殊押注

        private final int code; // 編碼
        BetType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 玩法類型定義
    public enum SpinType {
        INVALID(0), // 非法
        NORMAL(1), // 一般玩法
        EXTRA_SPIN(2), // 重轉玩法
        BUYFEATURE01(3), // Buy Feature 01
        BUYFEATURE02(4);; // Buy Feature 02

        private final int code; // 編碼
        SpinType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 押注玩法類型定義
    public enum BetSpinType {
        INVALID(0), // 非法
        NONE_NORMAL(1), // 一般玩法
        NONE_BUYFEATURE01(2), // Buy Feature 01
        NONE_BUYFEATURE02(3); // Buy Feature 02

        private final int code; // 編碼
        BetSpinType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 成本類型定義
    public enum PaymentType {
        INVALID(0), // 非法
        RATIO(1); // 比例計算成本

        private final int code; // 編碼
        PaymentType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 遊戲狀態相關*//
    // 遊戲狀態類型定義
    public enum GameStateType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設
        PXKY_BASEGAME(2), // 貔貅基本遊戲
        DYDB_BASEGAME(3), // 大運基本遊戲
        DYDB_FREEGAME(4), // 大運免費遊戲
        SDZW_BASEGAME(5), // 聖誕任務基礎遊戲
        SDZW_FREEGAME(6), // 聖誕任務免費遊戲
        WL_BASEGAME(7), // 旺來基本遊戲
        WL_FREEGAME(8), // 旺來免費遊戲
        MJWS_BASEGAME(9), // 麻將無雙基本遊戲
        MJWS_FREEGAME(10), // 麻將無雙免費遊戲
        BSZX_BASEGAME(11), // 寶石之星基礎遊戲
        BSZX_FREEGAME(12), // 寶石之星免費遊戲
        CCC_BASEGAME(13), // 777基本遊戲
        SBXC_BASEGAME(14), // 三倍小丑基本遊戲
        SN_BASEGAME(15), // 水牛基本遊戲
        SN_FREEGAME(16), // 水牛免費遊戲
        SWZS_BASEGAME(17), // 死亡之書基礎遊戲
        SWZS_FREEGAME(18), // 死亡之書免費遊戲
        LLL_BASEGAME(19), // 龍龍龍基本遊戲
        XZCQ_BASEGAME(20), // 小豬傳奇基本遊戲
        OLLDBZ_BASEGAME(21), // 歐賴利的寶藏基本遊戲
        OLLDBZ_FREEGAME(22), // 歐賴利的寶藏免費遊戲
        HJXB_BASEGAME(23), // 黃金西部基本遊戲
        HJXB_FREEGAME(24), // 黃金西部免費遊戲
        LMJJC_BASEGAME(25), // 羅馬競技場基本遊戲
        LMJJC_FREEGAME(26), // 羅馬競技場免費遊戲
        CJWP_BASEGAME(27), // 超級王牌基本遊戲
        CJWP_FREEGAME(28), // 超級王牌免費遊戲
        XJTB_BASEGAME(29), // 仙境探寶基本遊戲
        XJTB_FREEGAME(30), // 仙境探寶免費遊戲
        ZCJZ_BASEGAME(31), // 招財金豬基本遊戲
        DGRY_BASEGAME(32), // 帝国榮耀基本遊戲
        DGRY_FREEGAME(33), // 帝国榮耀免費遊戲
        DGRY_BONUSGAME(34), // 帝国榮耀額外遊戲
        LUCKY777_BASEGAME(35), // LUCKY777 基本遊戲
        MODEL_HJ_BASEGAME(800), // 模板虎機基礎遊戲
        MODEL_HJ_FREEGAME(801), // 模板虎機免費遊戲
        DEVELOP_HJ_BASEGAME(990), // 開發基本
        MODEL_MEGA_BASEGAME(992), // 巨大標誌
        MODEL_INDEPENDENT_BASEGAME(993), // 獨立滾輪
        MODEL_CASCADE_SYMBOL_FREEGAME(994); // 消除掉落免費

        private final int code; // 編碼
        GameStateType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 初始畫面定義
    public enum InitialScreenType {
        INVALID(0), // 非法
        NONE(1), // 無特殊定義
        MJHL(2), // 麻將胡了
        CJWP(3); //超級王牌

        private final int code; // 編碼
        InitialScreenType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 局類型定義
    public enum RoundType {
        INVALID(0), // 非法
        NORMAL(1), // 一般
        CASCADE(2); // 消除類

        private final int code; // 編碼
        RoundType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 一般局類型遊戲定義
    public enum RoundNormalGameType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設空殼
        PXKY_BASEGAME(2), // 貔貅基本遊戲
        DYDB_BASEGAME(3), // 大運奪寶基本遊戲
        DYDB_FREEGAME(4), // 大運奪寶免費遊戲
        SDZW_BASEGAME(5), // 聖誕任務基本遊戲
        SDZW_FREEGAME(6), // 聖誕任務免費遊戲
        WL_BASEGAME(7), // 旺來基本遊戲
        WL_FREEGAME(8), // 旺來免費遊戲
        BSZX_BASEGAME(9), // 寶石之星基本遊戲
        BSZX_FREEGAME(10), // 寶石之星免費遊戲
        CCC_BASEGAME(11), // 777基本遊戲
        SBXC_BASEGAME(12), // 三倍小丑基本遊戲
        SN_BASEGAME(13), // 水牛基本遊戲
        SN_FREEGAME(14), // 水牛免費遊戲
        SWZS_BASEGAME(15), // 死亡之書基本遊戲
        SWZS_FREEGAME(16), // 死亡之書免費遊戲
        LLL_BASEGAME(17), // 龍龍龍基本遊戲
        XZCQ_BASEGAME(18), // 小豬傳奇基本遊戲
        OLLDBZ_BASEGAME(19), // 歐賴利的寶藏基本遊戲
        OLLDBZ_FREEGAME(20), // 歐賴利的寶免費遊戲
        HJXB_BASEGAME(21), // 黃金西部基本遊戲
        HJXB_FREEGAME(22), // 黃金西部免費遊戲
        XJTB_BASEGAME(23), // 仙境探寶基本遊戲
        XJTB_FREEGAME(24), // 仙境探寶免費遊戲
        ZCJZ_BASEGAME(25), // 招財金豬基本遊戲
        LUCKY777_BASEGAME(26), // LUCKY777 基本遊戲
        MODEL_HJ_BASEGAME(880), // 模組虎機一般遊戲
        MODEL_HJ_FREEGAME(881), // 模組虎機免費遊戲
        MODEL_MEGA_BASEGAME(990),
        MODEL_INDEPENDENT_BASEGAME(991);

        private final int code; // 編碼
        RoundNormalGameType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 消除局類型遊戲定義
    public enum RoundCascadeGameType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設空殼
        DEVELOP_HJ_BASEGAME(2), // 開發虎機 基本遊戲
        MJWS_BASEGAME(3), // 麻將無雙 基本遊戲
        MJWS_FREEGAME(4), // 麻將無雙 免費遊戲
        LMJJC_BASEGAME(5), // 羅馬競技場 基本遊戲
        LMJJC_FREEGAME(6), // 羅馬競技場 免費遊戲
        CJWP_BASEGAME(7), // 超級王牌 基本遊戲
        CJWP_FREEGAME(8), // 超級王牌 免費遊戲
        DGRY_BASEGAME(9), // 帝国榮耀 基本遊戲
        DGRY_FREEGAME(10), // 帝国榮耀 免費遊戲
        DGRY_BONUSGAME(11); // 帝国榮耀 額外遊戲

        private final int code; // 編碼
        RoundCascadeGameType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 消除類型定義
    public enum CascadeType {
        INVALID(0), // 非法
        MJHL(1), // 麻將胡了
        SCATTER_LAST_NORMAL(2),
        CJWP(3), // 超級王牌
        DGRY(4), // 帝国榮耀
        NONE(5); // 沒有額外設定

        private final int code; // 編碼
        CascadeType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 消除集合類型定義
    public enum CascadeClusterType {
        INVALID(0), // 非法
        MJHL(1), // 麻將胡了[修改金色位置]
        CJWP(2), // 超級王牌[修改金色位置/Random Wild]
        DGRY(3); // 帝国榮耀[Buy Feature with Multiplier]

        private final int code; // 編碼
        CascadeClusterType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 聽牌限制定義 TODO 帶確認是否移除
    public enum CascadeReadyHandLimitType {
        INVALID(0), // 非法
        NO_LIMIT(1), // 無限制
        MAX_TRIGGER_LIMIT(1); // 觸發次數達上限，則不表演聽牌

        private final int code; // 編碼
        CascadeReadyHandLimitType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 消除方式定義
    public enum EliminationType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設無消除
        NORMAL(2), // 一般消除 //todo 改名
        SYMBOL_BOMB(3), // 指定標誌，九宮格爆炸
        IF_HIT(4); // 如果有得分才消除

        private final int code; // 編碼
        EliminationType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 畫面相關 *//
    // 畫面類型定義
    public enum FrameType {
        INVALID(0), // 非法
        FIX(1), // 固定畫面尺寸
        NO_SCREEN(2); // 沒有畫面

        private final int code; // 編碼
        FrameType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 輪帶表類型定義
    public enum ReelType {
        INVALID(0), // 非法
        REEL_DEPENDENT(1), // 相依滾輪
        REEL_INDEPENDENT(2), // 獨立滾輪
        REEL_MEGA_DEPENDENT(3), // 巨大相依滾輪
        NON_REEL_WEIGHT(4), // 無滾輪，權重
        NONE(5); // 不需要產畫面

        private final int code; // 編碼
        ReelType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 破框類型定義
    public enum DampType {
        INVALID(0,0), // 非法
        NO_DAMP(1,0), // 每軸無破框
        ONE_DAMP(2,1); // 每軸一個破框

        private final int code; // 編碼
        private final int dampAmount;
        DampType(int code, int dampAmount){
            this.code = code;
            this.dampAmount = dampAmount;
        }
        public int getCode() {
            return code;
        }

        public int getDampAmount() {
            return dampAmount;
        }
    }

    // 新增標誌類型定義
    public enum AddSymbolType {
        INVALID(0), // 非法
        NONE(1), // 不須計算
        NORMAL(2); // 需帶出每軸新增標誌

        private final int code; // 編碼
        AddSymbolType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 輪帶表停輪表現類型定義
    public enum ReelStopType {
        INVALID(0), // 非法
        STOP_BY_DEPENDENT_REEL_INDEX(1), // 給停輪位置
        STOP_BY_INDEPENDENT_REEL_INDEX(2), // 給停輪位置
        STOP_BY_SCREEN(3); // 給停輪畫面

        private final int code; // 編碼
        ReelStopType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    // 標誌定義
    public enum SymbolAttribute {
        EMPTY,
        M1,
        M2,
        M3,
        M4,
        M5,
        M6,
        M7,
        M8,
        M9,
        M10,
        ACE,
        KING,
        QUEEN,
        JOKER,
        TEN,
        NINE,
        EIGHT,
        SEVEN,
        SIX,
        FIVE,
        FOUR,
        THREE,
        TWO,
        ONE,
        WILD_01,
        WILD_02,
        WILD_03,
        WILD_04,
        WILD_05,
        FREE_GAME_01,
        FREE_GAME_02,
        FREE_GAME_03,
        FREE_GAME_04,
        FREE_GAME_05,
        BONUS_GAME_01,
        BONUS_GAME_02,
        BONUS_GAME_03,
        BONUS_GAME_04,
        BONUS_GAME_05;
    }

    // 標誌類型定義
    public enum SymbolAttributeType {
        EMPTY_SYMBOL,
        BASE_SYMBOL,
        MAIN_SYMBOL,
        WILD_SYMBOL,
        FREE_GAME_SYMBOL,
        BONUS_GAME_SYMBOL;
    }


    // 客端畫面轉譯類型
    public enum ScreenDampType {
        INVALID(0,0), // 非法
        DAMP_NONE(1,0), // 多給出一個標誌[往下多停一格]
        DAMP_ONE(2,1); // 多給出一個標誌[往下多停一格]

        private final int code; // 編碼
        private final int dampAmount; // 破框格數
        ScreenDampType(int code, int dampAmount){
            this.code = code;
            this.dampAmount = dampAmount;
        }
        public int getCode() {
            return code;
        }
        public int getDampAmount() {
            return dampAmount;
        }
    }





    //* 算分相關 *//
    // 算分方向定義
    public enum GameHitDirectionType {
        INVALID(0), // 非法
        LEFT_TO_RIGHT(1), // 由左至右算分
        RIGHT_TO_LEFT(2), // 由右至左算分
        NONE_DIRECTION(3); // 不需方向

        private final int code; // 編碼
        GameHitDirectionType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 算分方式定義
    public enum GameHitType {
        INVALID(0), // 非法
        LINE_GAME(1), // 線式算分
        WAY_GAME(2), // 路式算分
        CONNECT_GAME(3), // 連接式算分
        NONE(4); // 不需算分

        private final int code; // 編碼
        GameHitType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // Way 算分方式
    public enum GameCalculateType {
        INVALID(0), // 非法
        SINGLE_WILD(1), // 單一百搭
        MIX_WILD(2), // 混百搭
        SINGLE_NORMAL(3), // 單一標誌 + 百搭
        MIX_NORMAL(4); // 混標誌 + 百搭

        private final int code; // 編碼
        GameCalculateType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 特殊事件相關 *//
    // 特殊事件定義
    public enum SpecialFeatureType {
        INVALID(0), // 非法
        NONE(1), // 無特殊事件
        SF_01(2), // 畫面有2欄有FreeGame_01
        SF_02(3), // 畫面有3欄有FreeGame_01
        SF_03(4), // 畫面有4欄有FreeGame_01
        SF_04(5), // 畫面有5欄有FreeGame_01
        SF_05(6), // 畫面中由左往右連續 3 軸各有1個以上FreeGame_01 Symbol或 Wild_01 Symbol
        SF_06(7), // 畫面中由左往右連續 4 軸各有1個以上FreeGame_01 Symbol或 Wild_01 Symbol
        SF_07(8), // 畫面中由左往右連續 5 軸各有1個以上FreeGame_01 Symbol或 Wild_01 Symbol
        SF_08(9), // 畫面有3個以上 FreeGame_01
        SF_09(10), // 連續消除4次
        SF_10(11), // 連續消除5次
        SF_11(12), // 連續消除6次
        SF_12(13), // 連續消除7次或以上
        SF_13(14); // 畫面中由左往右連續 3 軸各有1個以上FreeGame_01 Symbol 且須達成連線(pay line設定寫死在FeatureHlr中)

        private final int code; // 編碼
        SpecialFeatureType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 觸發事件
    public enum TriggerEvent {
        INVALID(0), // 非法
        TRIGGER_01(1), // 觸發類型01
        TRIGGER_02(2), // 觸發類型02
        TRIGGER_03(3), // 觸發類型03
        TRIGGER_04(4), // 觸發類型04
        TRIGGER_05(5), // 觸發類型05
        TRIGGER_06(16), // 觸發類型06
        RE_TRIGGER_01(6), // 再次觸發類型01
        RE_TRIGGER_02(7), // 再次觸發類型02
        RE_TRIGGER_03(8), // 再次觸發類型03
        RE_TRIGGER_04(9), // 再次觸發類型04
        RE_TRIGGER_05(10), // 再次觸發類型05
        GET_PAY_01(11), // 獲得贏分類型01
        GET_PAY_02(12), // 獲得贏分類型02
        GET_PAY_03(13), // 獲得贏分類型03
        GET_PAY_04(14), // 獲得贏分類型04
        GET_PAY_05(15); // 獲得贏分類型05
        
        private final int code; // 編碼
        TriggerEvent(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public static boolean isReTrigger(TriggerEvent triggerEvent) {
            return 6 <= triggerEvent.getCode() && triggerEvent.getCode() <= 10;
        }
    }


    //* 遊戲進度相關 *//
    // 進度類型定義
    public enum ProgressType {
        INVALID(0), // 非法
        ROUND(1), // 局進度類型
        TRIGGER_ROUND(2), // 觸發局進度類型
        STAGE(3), // 階段進度類型
        CASCADE(4); // 消除進度類型

        private final int code; // 編碼
        ProgressType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 進度類型定義
    public enum ClientProgressType {
        INVALID(0), // 非法
        ROUND(1), // 局進度類型
        TRIGGER_ROUND(2), // 觸發局進度類型
        CASCADE(3); // 消除進度類型

        private final int code; // 編碼
        ClientProgressType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 遊戲結果相關 *//
    // 動畫類型定義
    public enum AnimationType {
        INVALID(0), // 非法
        ODDS_ANIMATION(1); // 倍數動畫

        private final int code; // 編碼
        AnimationType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 倍數動畫類型定義
    public enum OddsWinType {
        INVALID(0), // 非法
        NO_WIN(1), // 沒有倍數得分
        REGULAR_WIN(2), // 普通倍數得分
        BIG_WIN(3), // 大獎倍數得分
        MEGA_WIN(4), // 巨獎倍數得分
        ULTRA_WIN(5); // 超巨獎倍數得分

        private final int code; // 編碼
        OddsWinType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 共用資料類型
    public enum CommonInputType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設
        NONE(2), // 不須共用資料
        BIRDS_PARTY(3); // 飛鳥類型共用資料

        private final int code; // 編碼
        CommonInputType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 共用資料類型
    public enum GameStateInputType {
        INVALID(0), // 非法
        DEFAULT(1), // 預設
        NONE(2); // 不須共用資料

        private final int code; // 編碼
        GameStateInputType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 有限表演類型
    public enum FiniteAwardPoolType {
        INVALID(0), // 非法
        BASE_RE_SPIN(1); // Base畫面 + ReSpin畫面

        private final int code; // 編碼
        FiniteAwardPoolType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 詳細記錄類型 TODO
    public enum SlotDetailType {
        INVALID(0), // 非法
        PXKY_BASEGAME(1), // 貔貅老虎機
        DYDB_BASEGAME(2), // 大運奪寶基礎遊戲
        DYDB_FREEGAME(3), // 大運奪寶免費遊戲
        SDZW_BASEGAME(4), // 聖誕任務基礎遊戲
        SDZW_FREEGAME(5), // 聖誕任務免費遊戲
        WL_BASEGAME(6), // 旺來基礎遊戲
        WL_FREEGAME(7), // 旺來免費遊戲
        MJWS_BASEGAME(8), // 麻將無雙 基礎遊戲
        MJWS_FREEGAME(9), // 麻將無雙 免費遊戲
        BSZX_BASEGAME(10), // 寶石之星基礎遊戲
        BSZX_FREEGAME(11), // 寶石之星免費遊戲
        CCC_BASEGAME(12), // 777老虎機
        SBXC_BASEGAME(13), // 三倍小丑老虎機
        SN_BASEGAME(14), // 水牛基礎遊戲
        SN_FREEGAME(15), // 水牛免費遊戲
        SWZS_BASEGAME(16), // 死亡之書基礎遊戲
        SWZS_FREEGAME(17), // 死亡之書免費遊戲
        LLL_BASEGAME(18), // 龍龍龍老虎機
        XZCQ_BASEGAME(19), // 小豬傳奇老虎機
        OLLDBZ_BASEGAME(20), // 歐賴利的寶藏基礎遊戲
        OLLDBZ_FREEGAME(21), // 歐賴利的寶藏免費遊戲
        HJXB_BASEGAME(22), // 黃金西部基礎遊戲
        HJXB_FREEGAME(23), // 黃金西部免費遊戲
        LMJJC_BASEGAME(24), // 羅馬競技場 基礎遊戲
        LMJJC_FREEGAME(25), // 羅馬競技場 免費遊戲
        CJWP_BASEGAME(26), // 超級王牌 基礎遊戲
        CJWP_FREEGAME(27), // 超級王牌 免費遊戲
        XJTB_BASEGAME(28), // 仙境探寶基礎遊戲
        XJTB_FREEGAME(29), // 仙境探寶免費遊戲
        ZCJZ_BASEGAME(30), // 招財金豬老虎機
        DGRY_BASEGAME(31), // 帝国榮耀 基礎遊戲
        DGRY_FREEGAME(32), // 帝国榮耀 免費遊戲
        DGRY_BONUSGAME(33), // 帝国榮耀 額外遊戲
        LUCKY777_BASEGAME(34), // LUCKY777 基礎遊戲
        DEVELOP_HJ_BASEGAME(981), // 開發虎機基礎遊戲
        MODEL_HJ_BASEGAME(991), // 模板虎機基礎遊戲
        MODEL_HJ_FREEGAME(992); // 模板虎機免費遊戲

        private final int code; // 編碼
        SlotDetailType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 聽牌相關 *//
    // 聽牌定義
    public enum ReadyHandType {
        INVALID(0), // 非法
        DEFAULT(1), // 倍數動畫
        READY_HAND_01(2), // 畫面出現2顆 FreeGame_01 後，後面的輪軸放慢，前面的輪軸有出現的標誌閃爍
        READY_HAND_02(3), // 畫面出現1顆 FreeGame_01 後，後面的輪軸放慢，前面的輪軸有出現的標誌閃爍
        READY_HAND_03(4), // 畫面第一軸出現 BonusGame_01後，第五輪放慢，前面的輪軸有出現的標誌閃爍
        READY_HAND_04(5), // 畫面第1、2軸出現 Wild、Free01，第3輪放慢，前面的輪軸有出現的標誌閃爍
        READY_HAND_05(6), // 畫面第1、2、3軸出現 BonusGame_01後，第4輪放慢，前面的輪軸有出現的標誌閃爍
        READY_HAND_06(7); // 畫面第1、2、3、4軸出現 BonusGame_01後，第5輪放慢，前面的輪軸有出現的標誌閃爍

        private final int code; // 編碼
        ReadyHandType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 聽牌限制定義
    public enum ReadyHandLimitType {
        INVALID(0), // 非法
        NO_LIMIT(1), // 無限制
        MAX_TRIGGER_LIMIT(1); // 觸發次數達上限，則不表演聽牌

        private final int code; // 編碼
        ReadyHandLimitType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    //* 遊戲流程相關 *//
    // 條件狀態類型定義
    public enum Condition {
        CD_FALSE(0),    // 條件不成立
        CD_TRUE(1),     // 條件成立
        CD_01(2),       // 最後一場結果滿足 Trigger01 - Trigger05 其中一項
        CD_02(3),       // 最後一場結果滿足 Trigger06
        CD_BASIC_BET(4), // 普通押注
        CD_BUY_FEATURE_01(5), // Buy Feature 01
        CD_BUY_FEATURE_02(6); // Buy Feature 02

        private final int code; // 編碼
        Condition(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    //* 高低表相關 *//
    // 輪帶高低表類型定義
    public enum ReelRtpType {
        INVALID(0), // 非法
        HIGH(1), // 高表
        LOW(2); // 低表

        private final int code; // 編碼
        ReelRtpType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

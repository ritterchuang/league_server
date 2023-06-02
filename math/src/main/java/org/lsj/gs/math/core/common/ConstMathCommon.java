package org.lsj.gs.math.core.common;

// 數值共用定義
public class ConstMathCommon {
    /* 遊戲相關 */
    // 牌桌狀態
    public enum StateEnum{
        GAME_END(99); // 遊戲結束

        private final int code; // 編碼
        StateEnum(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 手續費類型
    public enum FeeType{
        INVALID(-1),
        NONE(0),
        FIXED(1),
        RATE(2);

        private final int type; // 編碼
        FeeType(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
        public static FeeType fromType(int type) {
            final FeeType[] allEnumInstance = values();
            for (FeeType enumInstance : allEnumInstance) {
                if (enumInstance.getType() == type) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }


    /* 玩家相關 */

    // 玩家類型
    public enum UserType {
        HUMAN(0),
        ROBOT(1);

        private final int code; // 編碼
        UserType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 離開原因
    public enum LeaveReason {
        NORMAL(0), // 正常離開
        MONEY_NOT_ENOUGH(1), // 錢不夠
        MONEY_TOO_MUCH(2), // 錢太多
        KICK_CUT(3), // 斷線被踢
        KICK_UNREADY(4), // 超時不準備被踢
        KICK_NO_OPERATION(5), // 長期沒有操作
        KICK_ILLEGAL_OPERATION(6); // 非法操作

        private final int code; // 編碼
        LeaveReason(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 遊戲玩家處理器消耗亂數列舉
    public enum GamePlayerHlrConsumeRnd{
        NO(0), // 無須消耗
        YES(1); // 需要消耗

        private final int code; // 編碼
        GamePlayerHlrConsumeRnd(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    /* 調控相關 */
    // 遊戲調控類型
    public enum GameAdjustType{
        INVALID(0), // 無效
        NONE(1), // 不使用
        CONTROL(2), // 強制指定調控類型
        RNG(3), // 設定亂數時的調控類型
        SETTING(4); // 依照原始設定調控

        private final int code; // 編碼
        GameAdjustType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 水池控制類型
    public enum PoolControlType{
        INVALID(0), // 無效
        NONE(1), // 不使用
        GUARANTEE(2), // 保證
        PERSON_ADJUST_PREFER_GUARANTEE(3); // 個人控優先保證

        private final int code; // 編碼
        PoolControlType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 開牌類型
    public enum DealType {
        INVALID(0), // 無效
        NONE(1), // 不變更
        GRADIENT(2); // 梯度開牌

        private final int code; // 編碼
        DealType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 開牌類型
    public enum ShuffleType{
        INVALID(0), // 無效
        NATURE(1), // 自然開牌,
        PSEUDO_NATURE(2); // 偽自然開牌

        private final int code; // 編碼
        ShuffleType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 個人控類型
    public enum PersonAdjustType{
        INVALID(-99, "無效"), // 無效
        NORMAL(0, "自然"), // 自然
        WHITE1(1, "白1"), // 白一
        WHITE2(2, "白2"), // 白二
        BLACK1(-1, "黑1"), // 黑一
        BLACK2(-2, "黑2"); // 黑二

        private final int code; // 編碼
        private final String name; // 名稱
        PersonAdjustType(int code, String name){
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }

        public static PersonAdjustType fromName(String name) {
            final PersonAdjustType[] allEnumInstance = values();
            for (PersonAdjustType enumInstance : allEnumInstance) {
                if (enumInstance.getName().equals(name)) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 調控類型
    public enum AdjustType{
        PERSON(1), // 個人控
        MARKET(2); // 大盤控

        private final int code; // 編碼
        AdjustType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    /* 亂數相關 */
    // 精準度
    public enum AccuracyType {
        A32768(0), // 32768精準度
        MILLION(1), // 百萬精準度
        BILLION(2); // 億精準度

        private final int code; // 編碼
        AccuracyType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }


    /* 例外狀況相關 */
    // 牌桌協議編碼
    public enum TableProtocolCode {
        // 共用
        COMMON_SUCCESS(100), // 共用成功
        COMMON_FAIL(101), // 共用失敗
        COMMON_MONEY_NOT_ENOUGH(102), // 餘額不足
        COMMON_JSON_FORMAT_ERROR(103), // JSON格式錯誤
        COMMON_STATE_ERROR(104), // 遊戲狀態錯誤
        COMMON_TABLE_CREATE_FAIL(105), // 牌桌建立失敗
        COMMON_USER_ERROR(106), // 玩家資訊異常

        // 魚機
        FISH_BULLET_INDEX_NOT_EXIST(201), // 子彈Index不存在
        FISH_BULLET_AMOUNT_NOT_ENOUGH(202), // 子彈數量不足
        FISH_BULLET_COST_ERROR(203), // 子彈成本錯誤
        FISH_BULLET_NOT_CONSISTENCY(204), // 子彈資訊不一致
        FISH_BULLET_NOT_COMPLETE(205), // 子彈資訊不完整
        FISH_HIT_NOT_EXIST(206), // 打擊資訊不存在
        FISH_HIT_NOT_CONSISTENCY(207), // 打擊資訊不一致
        FISH_HIT_NOT_COMPLETE(208), // 打擊資訊不完整
        FISH_BULLET_STATE_ERROR(209), // 子彈狀態錯誤[僅內部使用]

        // 虎機
        SLOT_CREDIT_NOT_EXIST(301), // 押注分數不存在
        SLOT_BET_TYPE_NOT_EXIST(302), // 押注類型不存在
        SLOT_SPIN_TYPE_NOT_EXIST(303), // 玩法類型不存在
        SLOT_BET_SPIN_TYPE_NOT_EXIST(304); // 押注玩法類型不存在

        private final int code; // 編碼
        TableProtocolCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

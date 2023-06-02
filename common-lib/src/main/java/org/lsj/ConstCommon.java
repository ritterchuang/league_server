package org.lsj;

// 共用定義
public class ConstCommon {
    /* 帳務相關 */
    // 預設公司利潤率
    public static final double DEFAULT_COMPANY_PROFIT_RATE = 0.03;

    // 帳務小數精準度
    public static final int MONEY_SCALE = 2;

    /* 使用者資訊相關 */
    // 使用者類型
    public enum UserType {
        HUMAN(0), // 真人
        ROBOT(1); // 機器人

        private final int code; // 編碼
        UserType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 角色類型
    public enum RoleType {
        INVALID(0), // 非法
        PLAYER(1), // 玩家
        WATCHER(2), // 旁觀者
        SUBSCRIBER(3); // 訂閱者

        private final int code; // 編碼
        RoleType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 玩家狀態類型(原有結構)
    public enum UserStateType {
        INVALID(0), // 非法
        WAIT(1), // 等待
        READY(2), // 準備中
        PLAY(3), // 遊戲中
        RESERVE(4), // 預約中
        WATCH(5), // 旁觀中
        CUT(6), // 掉線中
        INBG(7); // 切後台中

        private final int code; // 編碼
        UserStateType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 性別定義
    public enum SexType {
        FEMALE(0), // 女
        MALE(1); // 男

        private final int code; // 編碼
        SexType(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

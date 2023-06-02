package org.lsj.gs.math.core.baiRen;

// 數值卡牌定義
public class ConstMathBaiRen {
    // 動作狀態
    public enum Action {
        INVALID(-1), // 非法
        BEGIN(1), // 開始
        STOP(2); // 停止

        private final int code; // 編碼
        Action(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 玩家狀態定義; 目前僅使用 READY; 系統不支援玩家狀態;
    public enum UserState{
        INVALID(0), // 非法
        WAIT(1), // 等待
        READY(2), // 已準備
        PLAY(3), // 遊戲中
        RESERVE(4), // 預約中
        WATCH(5), // 旁觀中
        CUT(6), // 掉線中
        INBG(7); // 切後台中

        private final int code; // 編碼
        UserState(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 榜單身分類型
    public enum RichPlayerType{
        INVALID(0), // 非法
        RICH(1), // 大富豪
        LUCKY(2), // 幸運星
        PREDICT(3); // 神算子

        private final int code; // 編碼
        RichPlayerType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

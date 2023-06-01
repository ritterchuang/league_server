package org.lsj.gs.math.core.common.spinResultPgr;

public class ConstPgrSlot {

    // 客端輪帶表停輪表現類型定義
    public enum ClientReelStopType {
        INVALID(0), // 非法
        STOP_BY_DEPENDENT_REEL_INDEX(1), // 給停輪位置
        STOP_BY_DEPENDENT_REEL_BOX(2), // 給停輪物件
        STOP_BY_INDEPENDENT_REEL_INDEX(3), // 給停輪位置
        STOP_BY_SCREEN_SYMBOL_BOX(4), // 給停輪畫面
        NO_SCREEN(5); // 不需要產畫面

        private final int code; // 編碼
        ClientReelStopType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 客端畫面轉譯類型
    public enum ScreenOffsetType {
        INVALID(0,0), // 非法
        OFFSET_NONE(1,0),
        OFFSET_ONE(2,1); // 多給出一個標誌[往下多停一格]

        private final int code; // 編碼
        private final int offset;

        ScreenOffsetType(int code, int offset){
            this.code = code;
            this.offset = offset;
        }
        public int getCode() {
            return code;
        }
        public int getOffset() {
            return offset;
        }
    }

    // 畫面擊中表現類型定義
    public enum ClientSymbolHitType {
        INVALID(0), // 非法
        POSITION_ID(1), // 輪帶軸標誌位置
        POSITION_BOOLEAN(2); // 畫面指定座標

        private final int code; // 編碼
        ClientSymbolHitType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 新增標誌類型定義
    public enum AddSymbolOrderType {
        INVALID(0), // 非法
        NORMAL(1), // 不須計算
        REVERSE(2); // 需帶出每軸新增標誌

        private final int code; // 編碼
        AddSymbolOrderType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

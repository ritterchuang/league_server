package org.lsj.gs.math.core.card;

// 數值卡牌定義
public class ConstMathCard {
    // 卡牌狀態
    public enum CardState {
        NONE(0), // 無效卡牌
        UN_TAKEN(1), // 未被取走
        TAKEN(2); // 已被取走

        private final int code; // 編碼
        CardState(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 牌面狀態
    public enum CardFaceEnum{
        BACK(999); // 牌背

        private final int code; // 編碼
        CardFaceEnum(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 輸贏類型
    public enum WinType {
        TIE(0), // 和
        WIN(1), // 贏
        LOSS(2); // 輸

        private final int code; // 編碼
        WinType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 點數類型
    public enum PointType{
        P_A(1),
        P_2(2),
        P_3(3),
        P_4(4),
        P_5(5),
        P_6(6),
        P_7(7),
        P_8(8),
        P_9(9),
        P_T(10),
        P_J(11),
        P_Q(12),
        P_K(13);

        private final int point; // 編碼
        PointType(int point){
            this.point = point;
        }
        public int getPoint() {
            return point;
        }
    }

    // 牌種類型
    public enum CardType{
        POKER(1),
        PAIGOW(2),
        MAHJONG(3);

        private final int code; // 編碼
        CardType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }

    }

    // 搶莊類型
    public enum BankerType {
        RATE_LIST(1),
        MAX_RATE(2);

        private final int code; // 編碼
        BankerType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }

    }

    // 下注類型
    public enum QzBetType {
        BET_01(1), // 一般搶莊類，需考慮玩家數、最大押注、最小押注、特定倍數
        BET_02(2); // 搶莊二八槓類，需考慮玩家數、最小押注

        private final int code; // 編碼
        QzBetType(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }

    }
}

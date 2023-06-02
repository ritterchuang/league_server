package org.lsj.gs.math.games.ebg_java.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 搶莊二八槓定義
public class ConstEbgJava {

    // 狀態定義
    public enum StateEnumEbgJava {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        VIE_BANKER(3), // 搶莊
        BET(4), // 下注
        DEAL_CARD(5), // 發牌
        COMPARE(6), // 比牌
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumEbgJava(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumEbgJava fromCode(int code) {
            final StateEnumEbgJava[] allEnumInstance = values();
            for (StateEnumEbgJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 時間定義
    public enum TimeEnumEbgJava {
        READY(0, 15), // 準備
        GAME_BEGIN(1, 2.5), // 遊戲開始
        VIE_BANKER(2, 5), // 搶莊
        SHOW_BANKER(3, 1), // 搶莊結果
        BET(4, 5), // 下注
        BET_RESULT(5, 0), // 下注結果
        DEAL_CARD(6, 7), // 發牌
        COMPARE(7, 7.5), // 比牌
        END_GAME(8, 2); // 結束結果

        private final int code; // 編碼
        private final double timeSec; // 時間(秒)
        TimeEnumEbgJava(int code, double timeSec){
            this.code = code;
            this.timeSec = timeSec;
        }
        public int getCode() {
            return code;
        }
        public double getTimeSec(){
            return this.timeSec;
        }
        public long getMilliTimeSec() { return (long)(this.timeSec * 1000);}
    }


    // 操作定義
    public enum OperationEnumEbgJava {
        INVALID(0), // 非法操作
        VIE(1), // 搶莊
        BET(2); // 下注

        private final int code; // 編碼
        OperationEnumEbgJava(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 二八槓牌型定義
    public enum EbgTypeEnumEbgJava {
        INVALID(-1, 0),

        BS_64(0, 0),
        BS_73(1, 1),
        BS_91(2, 2),

        ONE_65(3, 3),
        ONE_74(4, 4),
        ONE_83(5, 5),
        ONE_92(6, 6),
        ONE_AND_HALF(7, 7),

        TWO_75(8, 8),
        TWO_84(9, 9),
        TWO_93(10, 10),
        TWO_AND_HALF(11, 11),

        THREE_21(12, 12),
        THREE_76(13, 13),
        THREE_85(14, 14),
        THREE_94(15, 15),
        THREE_AND_HALF(16, 16),

        FOUR_31(17, 17),
        FOUR_86(18, 18),
        FOUR_95(19, 19),
        FOUR_AND_HALF(20, 20),

        FIVE_32(21, 21),
        FIVE_41(22, 22),
        FIVE_87(23, 23),
        FIVE_96(24, 24),
        FIVE_AND_HALF(25, 25),

        SIX_42(26, 26),
        SIX_51(27, 27),
        SIX_97(28, 28),
        SIX_AND_HALF(29, 29),

        SEVEN_43(30, 30),
        SEVEN_52(31, 31),
        SEVEN_61(32, 32),
        SEVEN_98(33, 33),
        SEVEN_AND_HALF(34, 34),

        EIGHT_53(35, 35),
        EIGHT_62(36, 36),
        EIGHT_71(37, 37),
        EIGHT_AND_HALF(38, 38),

        NINE_54(39, 39),
        NINE_63(40, 40),
        NINE_72(41, 41),
        NINE_81(42, 42),
        NINE_AND_HALF(43, 43),

        EBG(44, 44),

        BAO_1(45, 45),
        BAO_2(46, 46),
        BAO_3(47, 47),
        BAO_4(48, 48),
        BAO_5(49, 49),
        BAO_6(50, 50),
        BAO_7(51, 51),
        BAO_8(52, 52),
        BAO_9(53, 53),

        KING(54, 54);

        private final int type; // 牌型編碼
        private final int weight; // 牌型權重
        EbgTypeEnumEbgJava(int type, int weight){
            this.type = type;
            this.weight = weight;
        }
        public int getType() {
            return type;
        }

        public int getWeight() {
            return weight;
        }
    }
}

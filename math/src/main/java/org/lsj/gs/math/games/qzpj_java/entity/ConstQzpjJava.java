package org.lsj.gs.math.games.qzpj_java.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 搶莊牌九定義
public class ConstQzpjJava {

    // 狀態定義
    public enum StateEnumQzpjJava {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        VIE_BANKER(3), // 搶莊
        BET(4), // 下注
        DEAL_CARD(5), // 發牌
        COMPARE(6), // 比牌
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumQzpjJava(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumQzpjJava fromCode(int code) {
            final StateEnumQzpjJava[] allEnumInstance = values();
            for (StateEnumQzpjJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 時間定義
    public enum TimeEnumQzpjJava {
        READY(0, 15), // 準備
        GAME_BEGIN(1, 2.5), // 遊戲開始
        VIE_BANKER(2, 5), // 搶莊
        SHOW_BANKER(3, 1), // 搶莊結果
        BET(4, 5), // 下注
        BET_RESULT(5, 0), // 下注結果
        DEAL_CARD(6, 7), // 發牌
        COMPARE(7, 7), // 比牌
        END_GAME(8, 1); // 結束結果

        private final int code; // 編碼
        private final double timeSec; // 時間(秒)
        TimeEnumQzpjJava(int code, double timeSec){
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
    public enum OperationEnumQzpjJava {
        INVALID(0), // 非法操作
        VIE(1), // 搶莊
        BET(2); // 下注

        private final int code; // 編碼
        OperationEnumQzpjJava(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 牌九牌型定義
    public enum PjTypeEnumQzpjJava {
        INVALID(-1), // 非法
        PT_0(0),     // 0 点
        PT_1(1),     // 1 点
        PT_2(2),     // 2 点
        PT_3(3),     // 3 点
        PT_4(4),     // 4 点
        PT_5(5),     // 5 点
        PT_6(6),     // 6 点
        PT_7(7),     // 7 点
        PT_8(8),     // 8 点
        PT_9(9),     // 9 点
        DGJ(10),     // 地高九
        TGJ(11),     // 天高九
        DG(12),      // 地杠
        TG(13),      // 天杠
        DW(14),      // 地王
        TW(15),      // 天王
        ZW(16),      // 杂五
        Z7(17),      // 杂七
        Z8(18),      // 杂八
        Z9(19),      // 杂九
        SLL(20),     // 双零霖
        SGJ(21),     // 双高脚
        SHT(22),     // 双红头
        SFT(23),     // 双斧头
        SBD(24),     // 双板凳
        SCS(25),     // 双长三
        SM(26),      // 双梅
        SE(27),      // 双鹅
        SR(28),      // 双人
        SD(29),      // 双地
        ST(30),      // 双天
        ZZ(31);      // 至尊

        private final int type; // 牌型大小順序
        PjTypeEnumQzpjJava(int type) { this.type = type;}
        public int getType() {
            return this.type;
        }
    }
}

package org.lsj.gs.math.games.qznn_java.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 搶莊牛牛定義
public class ConstQznnJava {

    // 狀態定義
    public enum StateEnumQznnJava {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        VIE_BANKER(3), // 搶莊
        BET(4), // 下注
        DEAL_CARD(5), // 發牌
        SELECT(6), // 選牌
        COMPARE(7), // 比牌
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumQznnJava(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumQznnJava fromCode(int code) {
            final StateEnumQznnJava[] allEnumInstance = values();
            for (StateEnumQznnJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 時間定義
    public enum TimeEnumQznnJava {
        READY(0, 15), // 準備
        GAME_BEGIN(1, 2.5), // 遊戲開始
        VIE_BANKER(2, 5), // 搶莊
        SHOW_BANKER(3, 1.5), // 搶莊結果
        BET(4, 5), // 下注
        BET_RESULT(5, 0.5), // 下注結果
        DEAL_CARD(6, 1), // 發牌
        SELECT(7, 6), // 選牌
        SELECT_RESULT(8, 0.5), // 選牌結果
        COMPARE(9, 2), // 比牌
        END_GAME(10, 2); // 結束結果

        private final int code; // 編碼
        private final double timeSec; // 時間(秒)
        TimeEnumQznnJava(int code, double timeSec){
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

    // 牛型定義
    public enum NiuTypeEnumQznnJava {
        INVALID(-1), // 無效
        NIU_0(0), // 無牛
        NIU_1(1), // 牛1
        NIU_2(2), // 牛2
        NIU_3(3), // 牛3
        NIU_4(4), // 牛4
        NIU_5(5), // 牛5
        NIU_6(6), // 牛6
        NIU_7(7), // 牛7
        NIU_8(8), // 牛8
        NIU_9(9), // 牛9
        NIU_NIU(10), // 牛牛
        FLOWER_4(11), // 四花牛; 一張10 另外四張皆JQK組成;
        FLOWER_5(12), // 五花牛; 全部牌皆JQK組成;
        BOMB(13), // 四炸彈; 四張牌點數一致;
        SMALL_NIU(14); // 小牛牛; 五張牌皆小於5 且點數總和小於等於10;

        private final int type; // 牌型
        NiuTypeEnumQznnJava(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    // 操作定義
    public enum OperationEnumQznnJava {
        INVALID(0), // 非法操作
        VIE(1), // 搶莊
        BET(2); // 下注

        private final int code; // 編碼
        OperationEnumQznnJava(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    // 攤牌定義
    public enum SelectTypeEnumQznnJava {
        INVALID(0), // 非法操作
        SHOWDOWN(1); // 攤牌

        private final int code; // 編碼
        SelectTypeEnumQznnJava(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}

package org.lsj.gs.math.games.sg_java.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 三公定義
public class ConstSgJava {

    // 狀態定義
    public enum StateEnumSgJava {
        INVALID(0), // 非法
        WAIT_BEGIN(1), // 等待開始
        GAME_BEGIN(2), // 遊戲開始
        DEAL_CARD(3), // 發牌
        VIE_BANKER(4), // 搶莊
        BET(5), // 下注
        SELECT(6), // 選牌
        COMPARE(7), // 比牌
        GAME_END(ConstMathCommon.StateEnum.GAME_END.getCode());

        private final int code; // 編碼
        StateEnumSgJava(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public static StateEnumSgJava fromCode(int code) {
            final StateEnumSgJava[] allEnumInstance = values();
            for (StateEnumSgJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }
    }

    // 時間定義
    public enum TimeEnumSgJava {
        READY(0, 15), // 準備
        GAME_BEGIN(1, 1.5), // 遊戲開始
        DEAL_CARD(2, 1), // 發牌
        VIE_BANKER(3, 5), // 搶莊
        SHOW_BANKER(4, 1), // 搶莊結果
        BET(5, 5), // 下注
        SELECT(6, 4), // 選牌
        COMPARE(7, 3), // 比牌
        END_GAME(8, 2); // 結束結果

        private final int code; // 編碼
        private final double timeSec; // 時間(秒)
        TimeEnumSgJava(int code, double timeSec){
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

    // 三公牌型定義
    public enum SgTypeEnumSgJava {
        INVALID(-1), // 非法
        PT_0(0), // 0 點
        PT_1(1), // 1 點
        PT_2(2), // 2 點
        PT_3(3), // 3 點
        PT_4(4), // 4 點
        PT_5(5), // 5 點
        PT_6(6), // 6 點
        PT_7(7), // 7 點
        PT_8(8), // 8 點
        PT_9(9), // 9 點
        SANGONG(10), // 三公
        DASANGONG(11), // 大三公
        ZHIZUN(12); // 至尊

        private final int type; // 牌型大小順序
        SgTypeEnumSgJava(int type) { this.type = type;}
        public int getType() {
            return this.type;
        }
    }
}

package org.lsj.gs.math.games.hhdz_java.entity;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;

import java.util.Arrays;
import java.util.Map;

// 新紅黑大戰定義
public class ConstHhdzJava {

    // 路圖定義
    public enum ChartEnumHhdzJava {
        INVALID(-1), // 無效
        BLACK(0), // 黑
        RED(1); // 紅

        private final int type; // 牌型
        ChartEnumHhdzJava(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }

        // 轉換牌型編碼為路圖編碼
        public static int transformCardTypeToChartType(int cardType){
            switch(BetAreaIdHhdzJava.fromCode(cardType)){
                case BLACK: return ChartEnumHhdzJava.BLACK.getType();
                case RED: return ChartEnumHhdzJava.RED.getType();
                default: return ChartEnumHhdzJava.INVALID.getType();
            }
        }
    }

    // 押注區定義
    public enum BetAreaIdHhdzJava {
        INVALID(-1, 0), // 無效
        BLACK(0, 1), // 黑贏
        RED(1, 1), // 紅贏
        CARD_TYPE(2, 0); // 牌型

        private final int code; // 牌型
        private final int rate; // 押注區倍率

        BetAreaIdHhdzJava(int code, int rate) {
            this.code = code;
            this.rate = rate;
        }

        public int getCode() {
            return code;
        }
        public int getRate() {
            return rate;
        }

        // 取得區域個數
        public static int getBetAreaCount(){
            return (int) Arrays.stream(values()).mapToInt(BetAreaIdHhdzJava::getCode).filter(betAreaCode -> betAreaCode != -1).count();
        }

        // 取得區域陣列
        public static int[] getBetAreaCodeArray(){
            return Arrays.stream(values()).mapToInt(BetAreaIdHhdzJava::getCode).filter(betAreaCode -> betAreaCode != -1).toArray();
        }

        public static BetAreaIdHhdzJava fromCode(int code) {
            final BetAreaIdHhdzJava[] allEnumInstance = values();
            for (BetAreaIdHhdzJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }

        public static BetAreaIdHhdzJava calculateWinBetAreaIdHhdzJava(Map<Integer, JhStack> areaStackMap){
            int compareResult = areaStackMap.get(0).compareTo(areaStackMap.get(1));

            if (compareResult > 0) {
                return BLACK;
            }
            return RED;
        }
    }
}

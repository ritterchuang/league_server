package org.lsj.gs.math.games.lhd_java.entity;

import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;

import java.util.Arrays;
import java.util.Map;

// 新龍虎鬥定義
public class ConstLhdJava {

    // 路圖定義
    public enum LhChartEnumLhdJava {
        INVALID(-1), // 無效
        DRAGON(1), // 龍
        TIGER(2), // 虎
        TIE(3); // 和

        private final int type; // 牌型
        LhChartEnumLhdJava(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }

        // 轉換牌型編碼為路圖編碼
        public static int transformCardTypeToChartType(int cardType){
            switch(BetAreaIdLhdJava.fromCode(cardType)){
                case DRAGON: return LhChartEnumLhdJava.DRAGON.getType();
                case TIGER: return LhChartEnumLhdJava.TIGER.getType();
                case TIE: return LhChartEnumLhdJava.TIE.getType();
                default: return LhChartEnumLhdJava.INVALID.getType();
            }
        }
    }

    // 押注區定義
    public enum BetAreaIdLhdJava{
        INVALID(-1, 0), // 無效
        DRAGON(0, 1), // 龍
        TIGER(1, 1), // 虎
        TIE(2, 8); // 和

        private final int code; // 牌型
        private final int rate; // 押注區倍率

        BetAreaIdLhdJava(int code, int rate) {
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
            return (int) Arrays.stream(values()).mapToInt(BetAreaIdLhdJava::getCode).filter(betAreaCode -> betAreaCode != -1).count();
        }

        public static BetAreaIdLhdJava fromCode(int code) {
            final BetAreaIdLhdJava[] allEnumInstance = values();
            for (BetAreaIdLhdJava enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }

        // 計算贏分區域
        public static BetAreaIdLhdJava calculateWinBetAreaIdLhdJava(Map<Integer, LhStack> stackMap){
            int compareResult = stackMap.get(0).compareTo(stackMap.get(1));

            switch(compareResult){
                case 1: return DRAGON;
                case -1: return TIGER;
                default: return TIE;
            }
        }
    }
}

package org.lsj.gs.math.core.baiRen;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 數值百人類 牛牛定義
public class ConstMathNiu {

    // 路圖定義 玩家角度
    public enum ChartEnum {
        INVALID(-1),    // 無效
        WIN(1),         // 該區贏
        LOSS(0);        // 該區輸

        private final int code; // 牌型
        ChartEnum(int code){
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        // 取得區域個數
        public static int getChartTypeCount(){
            return (int) Arrays.stream(values()).mapToInt(ChartEnum::getCode).filter(chartCode -> !(chartCode == -1)).count();
        }

        public static ChartEnum calculateChartCode(NiuStackCommon bankerStack, NiuStackCommon playerStack) {
            int compareResult = playerStack.compareTo(bankerStack);

            if (compareResult > 0) {
                return ChartEnum.WIN;
            }

            return ChartEnum.LOSS;
        }
    }

    // 押注區定義
    public enum BetAreaEnum {
        INVALID(-1),    // 無效
        TIAN(0),        // 天
        DI(1),          // 地
        XUAN(2),        // 玄
        HUANG(3),       // 黃
        BANKER(4);      // 庄

        private final int code; // 牌型

        BetAreaEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        // 取得區域個數
        public static int getDealAreaCount(){
            return (int) Arrays.stream(values()).mapToInt(BetAreaEnum::getCode).filter(betAreaCode -> !(betAreaCode == -1)).count();
        }

        // 取得區域個數
        public static int getBetAreaCount(){
            return (int) Arrays.stream(values()).mapToInt(BetAreaEnum::getCode).filter(betAreaCode -> !(betAreaCode == -1 || betAreaCode == 4)).count();
        }

        // 取得莊家區域
        public static int getBankerAreaId(){
            return 4;
        }

        public static BetAreaEnum fromCode(int code) {
            final BetAreaEnum[] allEnumInstance = values();
            for (BetAreaEnum enumInstance : allEnumInstance) {
                if (enumInstance.getCode() == code) {
                    return enumInstance;
                }
            }
            return INVALID;
        }

        public static List<Integer> calculateAreaIdListByChartType(Map<Integer, NiuStackCommon> areaStackMap, ChartEnum chartEnum){
            NiuStackCommon bankerStack = areaStackMap.get(BetAreaEnum.BANKER.getCode());

            List<Integer> result = new ArrayList<>();

            for (int betAreaId = 0; betAreaId < BetAreaEnum.getBetAreaCount(); betAreaId++) {
                NiuStackCommon playerStack = areaStackMap.get(betAreaId);
                ChartEnum currentResult = ChartEnum.calculateChartCode(bankerStack, playerStack);
                if (currentResult.equals(chartEnum)) {
                    result.add(betAreaId);
                }
            }
            return result;
        }


    }
}

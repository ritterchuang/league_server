package org.lsj.utils;

import org.lsj.ConstCommon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

// 數字工具包
public class MathUtil {

    private MathUtil() {
    }

    private static final int DEFAULT_DIVIDE_SCALE = 10;

    // 精確加法運算
    public static double add(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    // 精確加法運算
    public static double add(double[] doubleArray){
        if(Objects.isNull(doubleArray) || doubleArray.length == 0){
            return 0.0;
        }

        double result = 0.0;
        for (double v : doubleArray) {
            result = add(result, v);
        }

        return result;
    }

    // 精確減法運算
    public static double subtract(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    // 精確乘法運算
    public static double multiply(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    // 精確除法運算
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_DIVIDE_SCALE);
    }

    // 指定精準度的除法運算
    private static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, RoundingMode.FLOOR).doubleValue();
    }

    // 調整至金錢單位
    public static double moneyScale(double value) {
        return MathUtil.floor(value, ConstCommon.MONEY_SCALE);
    }

    // 精確無條件捨去運算
    private static double floor(double value, int scale) {
        final BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        return bigDecimal.setScale(scale, RoundingMode.FLOOR).doubleValue();
    }

    // 精確平均倍數運算
    public static double averageOdds(int[] oddsArray, int[] weightArray) {
        // 1. 檢查陣列長度一致性
        if (!(oddsArray.length == weightArray.length)) {
            return 0.0;
        }

        // 2. 檢查權重非負性
        if (!Arrays.stream(weightArray).allMatch(weight -> weight >= 0)) {
            return 0.0;
        }

        // 3. 檢查權重總和恆正性
        if (!(Arrays.stream(weightArray).sum() > 0)) {
            return 0.0;
        }

        // 4. 計算平均倍數
        int totalWeight = Arrays.stream(weightArray).sum();
        int totalOddsWeight = 0;
        for (int oddsIndex = 0; oddsIndex < oddsArray.length; oddsIndex++) {
            totalOddsWeight += oddsArray[oddsIndex] * weightArray[oddsIndex];
        }
        return MathUtil.divide(totalOddsWeight, totalWeight);
    }
}

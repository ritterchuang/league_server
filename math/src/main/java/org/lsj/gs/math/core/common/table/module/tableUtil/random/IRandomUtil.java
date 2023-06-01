package org.lsj.gs.math.core.common.table.module.tableUtil.random;

import org.lsj.gs.math.core.common.ConstMathCommon;

import java.util.List;

// 隨機工具包介面
public interface IRandomUtil {
    /* 產生亂數相關 */
    // 產生指定精準度的上界隨機整數
    int getRandomIntWithAccuracy(int bound, ConstMathCommon.AccuracyType accuracyType);

    // 產生指定精準度的隨機小數
    double getRandomDoubleWithAccuracy(ConstMathCommon.AccuracyType accuracyType);

    /* 擊中相關 */
    // 指定精準度的擊中判斷
    boolean isHitWithAccuracy(double prob, ConstMathCommon.AccuracyType accuracyType);

    /* 陣列取值 */
    // 指定精準度的陣列均勻取值
    <T> T getRandomElement(List<T> elementList, ConstMathCommon.AccuracyType accuracyType);

    // 取得指定精準度的隨機陣列索引
    int getArrayIndexByWeightWithAccuracy(List<Integer> weight, ConstMathCommon.AccuracyType accuracyType);

    //* 交換相關 *//

    // 交換陣列元素位置
    void swap(Object[] array, int index1, int index2);

    // 交換陣列元素位置
    <T> void swapList(List<T> list, int index1, int index2);

    // 物件洗牌
    <T> void shuffleList(List<T> list);

    //* 隨機字串 *//

    // 隨機英數字
    String randomAlphanumeric(int count);

    // 重置
    void reset();
}

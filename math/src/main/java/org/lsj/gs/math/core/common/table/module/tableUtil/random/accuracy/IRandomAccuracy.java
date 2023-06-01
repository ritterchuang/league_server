package org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy;

// 精準度亂數產生器介面
public interface IRandomAccuracy {
    // 產生隨機整數
    int getRandomInt();

    // 產生上界隨機整數 [0, bound)
    int getRandomInt(int bound);

    // 產生隨機小數
    double getRandomDouble();
}

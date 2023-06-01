package org.lsj.gs.math.core.common.table.module.tableUtil.random.accuracy;

import org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr.IRandomWpr;

// 十億精準度亂數產生器
public class RandomBillion implements IRandomAccuracy{
    private final IRandomWpr randomWpr; // 封裝亂數產生器
    private final int MAX_VALUE; // 最大整數值

    public RandomBillion(IRandomWpr randomWpr) {
        this.randomWpr = randomWpr;
        this.MAX_VALUE = 1000_000_000;
    }

    // 產生隨機整數
    @Override
    public int getRandomInt() {
        return (this.randomWpr.nextInt(1000) * 1000 * 1000) +
                (this.randomWpr.nextInt(1000) * 1000) +
                (this.randomWpr.nextInt(1000));
    }

    // 產生上界隨機整數 [0, bound)
    @Override
    public int getRandomInt(int bound) {
        int randomInt;

        do{
            randomInt = this.getRandomInt();
        } while ( randomInt >= (((int)Math.floor((double)this.MAX_VALUE / (double)bound)) * bound));

        return randomInt % bound;
    }

    // 產生隨機小數
    @Override
    public double getRandomDouble() {
        return ((double)this.getRandomInt() / (double)this.MAX_VALUE);
    }
}

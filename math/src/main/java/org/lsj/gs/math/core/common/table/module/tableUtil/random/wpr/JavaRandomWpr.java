package org.lsj.gs.math.core.common.table.module.tableUtil.random.wpr;

import java.util.Random;

// 內建封裝亂數產生器
public class JavaRandomWpr implements IRandomWprNotSupportSetRngData {
    private final Random random; // 亂數產生器

    public JavaRandomWpr() {
        this.random = new Random();
    }

    // 產生上界整數 [0, bound)
    @Override
    public int nextInt(int bound) {
        return this.random.nextInt(bound);
    }
}

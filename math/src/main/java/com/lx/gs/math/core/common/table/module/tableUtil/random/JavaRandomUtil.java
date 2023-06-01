package com.lx.gs.math.core.common.table.module.tableUtil.random;

import com.lx.gs.math.core.common.table.module.tableUtil.random.wpr.JavaRandomWpr;

// 內建隨機工具包
public class JavaRandomUtil extends AbstractRandomUtil implements IRandomUtilNotSupportSetRngData{
    public JavaRandomUtil() {
        super(new JavaRandomWpr());
    }

    // 重置
    @Override
    public void reset() {}
}

package com.lx.gs.rngDataGtr.core.module.rngDataGtr;

import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;

// 亂數產生器設定列印器
public class RngDataGtrConfigPrinter {
    public void print(RngDataGtrConfig rngDataGtrConfig){
        System.out.println(rngDataGtrConfig.getSystemPrintString());
    }
}

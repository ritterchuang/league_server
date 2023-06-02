package org.lsj.gs.rngDataGtr.core.module.rngDataGtr;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;

// 亂數產生器設定列印器
public class RngDataGtrConfigPrinter {
    public void print(RngDataGtrConfig rngDataGtrConfig){
        System.out.println(rngDataGtrConfig.getSystemPrintString());
    }
}

package com.lx.gs.mathStr.config.resources.agencyStn;

import com.lx.gs.mathStr.config.entity.AgencyStnConfig;

// 統計設定資源 不輸出原始資料
public class AgencyStnR01OutputRawData {
    public AgencyStnConfig create(){
        return new AgencyStnConfig(true);
    }
}

package org.lsj.gs.mathStr.config.resources.agencyStn;

import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;

// 統計設定資源 不輸出原始資料
public class AgencyStnR00NotOutputRawData {
    public AgencyStnConfig create(){
        return new AgencyStnConfig(false);
    }
}

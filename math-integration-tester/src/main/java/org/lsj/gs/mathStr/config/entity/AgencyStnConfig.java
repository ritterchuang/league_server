package org.lsj.gs.mathStr.config.entity;

// 統計者設定
public class AgencyStnConfig {
    private boolean outputRawData; // 輸出原始資料標誌

    public AgencyStnConfig() {}

    public AgencyStnConfig(boolean outputRawData) {this.outputRawData = outputRawData;}

    public boolean isOutputRawData() {
        return this.outputRawData;
    }
}

package org.lsj.gs.rngDataGtr.core.module.rngDataGtr;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataScenarioHlr.RngDataScenarioHlr;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.RngDataTester;

// 抽象亂數產生器
public abstract class AbstractRngDataGtr implements IRngDataGtr{
    private final RngDataGtrConfig rngDataGtrConfig; // 亂數產生器設定檔
    private final RngDataScenarioHlr rngDataScenarioHlr; // 亂數情境處理器
    private final RngDataTester rngDataTester; // 亂數檢測器

    public AbstractRngDataGtr(RngDataGtrConfig rngDataGtrConfig) {
        // 1. 記錄設定檔
        this.rngDataGtrConfig = rngDataGtrConfig;

        // 2. 模組初始化
        this.rngDataScenarioHlr= new RngDataScenarioHlr(rngDataGtrConfig.getRngDataScenarioHlrConfig());
        this.rngDataTester = new RngDataTester(rngDataGtrConfig.getTestCaseConfig());
    }

    public RngDataGtrConfig getRngDataGtrConfig() {
        return rngDataGtrConfig;
    }

    public RngDataScenarioHlr getRngDataScenarioHlr() {
        return rngDataScenarioHlr;
    }

    public RngDataTester getRngDataTester() {
        return rngDataTester;
    }
}

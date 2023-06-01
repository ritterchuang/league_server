package com.lx.gs.rngDataGtr.core.module.rngDataGtr;

import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;

import java.util.List;

// 亂數產生器
public class RngDataGtrDefault extends AbstractRngDataGtr{

    public RngDataGtrDefault(RngDataGtrConfig rngDataGtrConfig) {
        super(rngDataGtrConfig);
    }

    public List<TestCaseGameResult> run(){
        return null;
    }
}

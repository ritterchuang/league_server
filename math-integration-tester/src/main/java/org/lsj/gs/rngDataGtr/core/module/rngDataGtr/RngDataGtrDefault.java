package org.lsj.gs.rngDataGtr.core.module.rngDataGtr;

import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;

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

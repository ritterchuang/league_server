package com.lx.gs.rngDataGtr.core.module.rngDataGtr;

import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;

import java.util.List;

// 亂數產生器介面
public interface IRngDataGtr {
    List<TestCaseGameResult> run();
}

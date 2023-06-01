package com.lx.gs.rngDataGtr.core.module.rngDataGtr;

import com.lx.gs.mathBoardGtr.core.BoardGtrFactory;
import com.lx.gs.mathBoardGtr.core.baiRen.IBoardGtrBaiRen;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.TestCaseGameResultPrinter;
import com.lx.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

// 百人亂數產生器
public class RngDataGtrBaiRen extends AbstractRngDataGtr{

    public RngDataGtrBaiRen(RngDataGtrConfig rngDataGtrConfig) {
        super(rngDataGtrConfig);
    }

    public List<TestCaseGameResult> run(){
        List<TestCaseGameResult> testCaseGameResultList = new ArrayList<>();

        // 1. 建立局產生器
        IBoardGtrBaiRen boardGtr = new BoardGtrFactory(super.getRngDataGtrConfig().getBoardGtrConfig().getGamePlayer(),
                new PoolCtr(super.getRngDataGtrConfig().getBoardGtrConfig().getPoolCtrConfig()),
                super.getRngDataGtrConfig().getBoardGtrConfig().getPlayGameFieldConfig(),
                super.getRngDataGtrConfig().getBoardGtrConfig().getControlAlgorithmConfig())
                .createBoardGtrBaiRen();

        for(int testCaseIndex = 0; testCaseIndex < super.getRngDataTester().getRngDataTesterConfig().getTestCaseConfigList().size(); testCaseIndex++){
            for(int boardIndex = 0; boardIndex < super.getRngDataScenarioHlr().getRunBoardLimit(); boardIndex++){
                // 2. 產生局結果
                BoardGtrResult boardGtrResult = boardGtr.calculateOneBoardResult();

                // 3. 計算檢測結果
                TestCaseResult testCaseResult = super.getRngDataTester().calculateTestCaseResult(testCaseIndex, boardGtrResult);

                // 4. 新增檢測結果
                if(testCaseResult.isMatch()){
                    TestCaseGameResult testCaseGameResult = new TestCaseGameResult(boardGtrResult, testCaseResult);
                    new TestCaseGameResultPrinter().print(testCaseGameResult);
                    testCaseGameResultList.add(testCaseGameResult);
                    break;
                }

                // 5. 找不到驗測結果
                if( boardIndex == (super.getRngDataScenarioHlr().getRunBoardLimit() -1)
                        && !testCaseResult.isMatch()){
                    TestCaseGameResult testCaseGameResult = new TestCaseGameResult(boardGtrResult, testCaseResult);
                    new TestCaseGameResultPrinter().print(testCaseGameResult);
                    testCaseGameResultList.add(testCaseGameResult);
                }
            }
            // 6. 印出進度資訊
            this.printProgress(
                    testCaseIndex,
                    super.getRngDataTester().getRngDataTesterConfig().getTestCaseConfigList().size(),
                    super.getRngDataTester().getRngDataTesterConfig().getTestCaseConfigList());
        }

        return testCaseGameResultList;
    }

    private void printProgress(int currentIndex, int totalSize, List<TestCaseConfig> testCaseConfigList){
        String progressString = new StringBuilder()
                .append("[進度資訊 " + (currentIndex + 1) + "/" + totalSize + "]").append(" ")
                .append(testCaseConfigList.get(currentIndex).getTestCaseTypeEnum().name()).append(" ")
                .append(JsonUtil.getInstance().writeValueAsStringWithoutException(testCaseConfigList.get(currentIndex).getTestCaseConfigExtend()))
                .toString();
        System.out.println(progressString);
    }
}

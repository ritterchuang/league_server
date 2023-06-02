package org.lsj.gs.rngDataGtr.core.module.rngDataGtr;

import org.lsj.gs.mathBoardGtr.core.BoardGtrFactory;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.gs.rngDataGtr.config.entity.RngDataGtrConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.TestCaseGameResultPrinter;
import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

// 魚機亂數產生器
public class RngDataGtrFish extends AbstractRngDataGtr{

    public RngDataGtrFish(RngDataGtrConfig rngDataGtrConfig) {
        super(rngDataGtrConfig);
    }

    public List<TestCaseGameResult> run(){
        List<TestCaseGameResult> testCaseGameResultList = new ArrayList<>();

        for(int testCaseIndex = 0; testCaseIndex < super.getRngDataTester().getRngDataTesterConfig().getTestCaseConfigList().size(); testCaseIndex++){
            for(int boardIndex = 0; boardIndex < super.getRngDataScenarioHlr().getRunBoardLimit(); boardIndex++){
                // 1. 產生局結果
                BoardGtrResult boardGtrResult = new BoardGtrFactory(super.getRngDataGtrConfig().getBoardGtrConfig().getGamePlayer(),
                        new PoolCtr(super.getRngDataGtrConfig().getBoardGtrConfig().getPoolCtrConfig()),
                        super.getRngDataGtrConfig().getBoardGtrConfig().getPlayGameFieldConfig(),
                        super.getRngDataGtrConfig().getBoardGtrConfig().getControlAlgorithmConfig())
                        .createBoardGtrFish().calculateOneBoardResult();

                // 2. 計算檢測結果
                TestCaseResult testCaseResult = super.getRngDataTester().calculateTestCaseResult(testCaseIndex, boardGtrResult);

                // 3. 新增檢測結果
                if(testCaseResult.isMatch()){
                    TestCaseGameResult testCaseGameResult = new TestCaseGameResult(boardGtrResult, testCaseResult);
                    new TestCaseGameResultPrinter().print(testCaseGameResult);
                    testCaseGameResultList.add(testCaseGameResult);
                    break;
                }

                // 4. 找不到驗測結果
                if( boardIndex == (super.getRngDataScenarioHlr().getRunBoardLimit() -1)
                        && !testCaseResult.isMatch()){
                    TestCaseGameResult testCaseGameResult = new TestCaseGameResult(boardGtrResult, testCaseResult);
                    new TestCaseGameResultPrinter().print(testCaseGameResult);
                    testCaseGameResultList.add(testCaseGameResult);
                }
            }
            // 4. 印出進度資訊
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

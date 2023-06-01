package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;

import java.util.List;

// 客製測項設定 百家類 押注區
public class TestCaseConfigEBaiRenBjBet extends AbstractTestCaseConfigExtend {
    private final int winReturnType; // 0: 目標輸贏, 1: 目標返還
    private final List<ConstMathBjl.BetAreaEnum> betAreaList; // 押注區域列表
    private final List<ConstMathBjl.BetAreaEnum> targetBetAreaList; // 目標區域結果列表

    public TestCaseConfigEBaiRenBjBet(int winReturnType, List<ConstMathBjl.BetAreaEnum> betAreaList, List<ConstMathBjl.BetAreaEnum> targetBetAreaList) {
        this.winReturnType = winReturnType;
        this.betAreaList = betAreaList;
        this.targetBetAreaList = targetBetAreaList;
    }

    public int getWinReturnType() {
        return winReturnType;
    }

    public List<ConstMathBjl.BetAreaEnum> getBetAreaList() {
        return betAreaList;
    }

    public List<ConstMathBjl.BetAreaEnum> getTargetBetAreaList() {
        return targetBetAreaList;
    }

    @Override
    public String systemPrintString() {
        return  this.winReturnType
                + "_"
                + this.calculateString("betArea_", this.betAreaList)
                + "_"
                + this.calculateString("targetArea_", this.targetBetAreaList);
    }

    private String calculateString(String targetNam, List<ConstMathBjl.BetAreaEnum> targetList) {
        StringBuilder sb = new StringBuilder();
        sb.append(targetNam);

        for (int betId = 0; betId < targetList.size(); betId++) {
            sb.append(targetList.get(betId).name());
            if (betId != targetList.size() - 1) {
                sb.append("_");
            }
        }

        return sb.toString();
    }
}

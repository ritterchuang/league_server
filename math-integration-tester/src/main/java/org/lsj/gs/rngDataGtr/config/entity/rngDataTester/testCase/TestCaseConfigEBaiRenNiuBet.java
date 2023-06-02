package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;

import java.util.List;

// 客製測項設定 百人牛類 押注區
public class TestCaseConfigEBaiRenNiuBet extends AbstractTestCaseConfigExtend {
    private final List<ConstMathNiu.BetAreaEnum> betAreaList; // 押注區域列表
    private final List<ConstMathNiu.BetAreaEnum> winBetAreaList; // 獲勝區域結果列表

    public TestCaseConfigEBaiRenNiuBet(List<ConstMathNiu.BetAreaEnum> betAreaList, List<ConstMathNiu.BetAreaEnum> winBetAreaList) {
        this.betAreaList = betAreaList;
        this.winBetAreaList = winBetAreaList;
    }

    public List<ConstMathNiu.BetAreaEnum> getBetAreaList() {
        return betAreaList;
    }

    public List<ConstMathNiu.BetAreaEnum> getWinBetAreaList() {
        return winBetAreaList;
    }

    @Override
    public String systemPrintString() {
        return  this.calculateString("betArea_", this.betAreaList)
                + "_"
                + this.calculateString("winArea_", this.winBetAreaList);
    }

    private String calculateString(String targetNam, List<ConstMathNiu.BetAreaEnum> targetList) {
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
